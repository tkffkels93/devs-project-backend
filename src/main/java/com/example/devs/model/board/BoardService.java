package com.example.devs.model.board;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.enums.BoardStatus;
import com.example.devs._core.errors.exception.Exception400;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception403;
import com.example.devs._core.errors.exception.Exception404;
import com.example.devs._core.utils.ImageUtil;
import com.example.devs.model.bookmark.BookmarkRepository;
import com.example.devs.model.bookmark.BookmarkService;
import com.example.devs.model.like.LikeRepository;
import com.example.devs.model.like.LikeService;
import com.example.devs.model.photo.Photo;
import com.example.devs.model.photo.PhotoRepository;
import com.example.devs.model.photo.PhotoService;
import com.example.devs.model.reply.ReplyRepository;
import com.example.devs.model.reply.ReplyService;
import com.example.devs.model.user.User;
import com.example.devs.model.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final LikeService likeService;
    private final BookmarkService bookmarkService;
    private final ReplyService replyService;
    private final PhotoService photoService;
    private final LikeRepository likeRepository;

    //게시글 목록 불러오기 ( 일반 사용자용 )
    public Page<BoardResponse.ListDTO> getBoards(int page, int size, BoardRole boardRole, Integer userId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Object[]> boards = boardRepository.findAllByBoardRole(pageable, boardRole);
        List<Object[]> boardList = boards.getContent();
        List<BoardResponse.ListDTO> boardDtoList = new ArrayList<>();

        for (Object[] result : boardList) {
            Board board = (Board) result[0];
            Long likeCount = (Long) result[1];
            Long bookmarkCount = (Long) result[2];
            Long replyCount = (Long) result[3];
            BoardResponse.ListDTO dto = new BoardResponse.ListDTO(board);
            //좋아요와 북마크 개수를 셋팅한다
            dto.setLikeCount(likeCount);
            dto.setBookmarkCount(bookmarkCount);
            dto.setReplyCount(replyCount);
            dto.setMyLike(likeService.isLiked(boardRole, board.getId(), userId));
            dto.setMyBookmark(bookmarkService.isBookmarked(boardRole, board.getId(), userId));
            boardDtoList.add(dto);
        }
        return new PageImpl<>(boardDtoList, pageable, boards.getTotalElements());
    }

    //게시글 내용 불러오기 ( 일반 사용자용 )
    public BoardResponse.DetailDTO getBoardDetail(BoardRole boardRole, Integer boardId, Integer userId) {
        User user = userRepository.findById(userId).get();

        Board board = boardRepository.findByBoardRoleAndId(boardRole, boardId)
                .orElseThrow(() -> new Exception404("게시물을 찾을 수 없습니다."));
        //조회수 증가 --> 본인이 작성한 글을 조회하는 경우에는 조회수가 증가하지 않음
        if (!board.getUser().getId().equals(userId)) {
            board.setHit(board.getHit() + 1);
        }

        // 게시글 북마크, 좋아요 여부
        Boolean isBookmarked = bookmarkService.isBookmarked(boardRole, boardId, userId);
        Boolean isLiked = likeService.isLiked(boardRole, boardId, userId);
        Integer likeCount = likeRepository.countByBoardId(boardId, BoardRole.Board, BoardStatus.PUBLISHED);

        //댓글 가져오기&이미지 가져오기
        List<BoardResponse.ReplyDTO> repliesDto = replyService.getReplies(boardRole, boardId, userId);
        List<BoardResponse.PhotoDTO> photoDTOs = photoService.getPhotos(boardId);


        BoardResponse.DetailDTO dto = new BoardResponse.DetailDTO(board, repliesDto, photoDTOs, isBookmarked, isLiked, likeCount, user.getImage());
        dto.setOwner(
                board.getUser().getId().equals(userId) //이 글의 작성자이면 true로 셋팅
        );
        return dto;
    }

    //인기 게시글 목록(top10) 가져오기
    public List<BoardResponse.Top10ListDTO> getTop10Boards(BoardRole boardRole) {
        List<Board> boards = boardRepository.findTop10ByBoardRole(boardRole);
        AtomicInteger counter = new AtomicInteger(1);
        return boards.stream().map(board -> {
            BoardResponse.Top10ListDTO dto = new BoardResponse.Top10ListDTO(board);
            dto.setRank(counter.getAndIncrement());
            return dto;
        }).toList();
    }


    // 게시글 리스트 (관리자)
    public BoardResponse.BoardListDTO getBoardList(User sessionAdmin) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        List<Board> boardDTO = boardRepository.findByBoardRole(BoardRole.Board);
        Integer totalBoardCount = boardDTO.size();
        List<BoardResponse.BoardList> boardList = boardDTO.stream().map(BoardResponse.BoardList::new).toList();

        return new BoardResponse.BoardListDTO(totalBoardCount, boardList);
    }

    // 게시글 상세 정보 (관리자)
    public BoardResponse.BoardDetailDTO getBoardDetail(User sessionAdmin, Integer boardId) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시물을 찾을 수 없습니다."));

        return new BoardResponse.BoardDetailDTO(board);
    }

    // 게시글 숨김
    @Transactional
    public void hideBoard(User sessionUser, Integer boardId) {
        userRepository.findById(
                Optional.ofNullable(sessionUser)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (board.getStatus() == BoardStatus.HIDE) {
            throw new Exception400("이미 숨긴 게시글입니다.");
        }

        board.setStatus(BoardStatus.HIDE);
        boardRepository.save(board);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(User sessionUser, Integer boardId) {
        userRepository.findById(
                Optional.ofNullable(sessionUser)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (board.getStatus() == BoardStatus.DELETED) {
            throw new Exception400("이미 삭제된 게시글입니다.");
        }

        board.setStatus(BoardStatus.DELETED);
        boardRepository.save(board);
    }

    //일반사용자용 게시글 작성하기
    @Transactional
    public void writeBoard(Integer userId, BoardRequest.Write writeDto) throws IOException {

        //게시글 먼저 저장. 이미지는 아래에 따로 저장
        Board board = Board.builder()
                .title(writeDto.getTitle())
                .content(writeDto.getContent())
                .hit(0)
                .boardRole(BoardRole.Board)
                .status(BoardStatus.PUBLISHED)
                .user(User.builder().id(userId).build())
                .build();
        board = boardRepository.save(board);

        List<ImageUtil.FileUploadResult> fileUploadResults;
        if (writeDto.getImages() != null) {
            fileUploadResults = ImageUtil.uploadBase64Images(writeDto.getImages());
            for (ImageUtil.FileUploadResult fileUploadResult : fileUploadResults) {
                Photo photo = Photo.builder()
                        .board(board)
                        .fileName(fileUploadResult.getFileName())
                        .filePath(fileUploadResult.getFilePath())
                        .build();
                photoService.savePhoto(photo);
            }
        }
    }

    // 검색한 게시판
    public Page<BoardResponse.SearchBoardListDTO> getBoardsBySearch(Pageable pageable, BoardRole boardRole, Integer userId, String query) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Page<Object[]> boards = boardRepository.findBoardByQuery(pageable, boardRole, query);
        List<Object[]> boardList = boards.getContent();
        List<BoardResponse.SearchBoardListDTO> boardDtoList = new ArrayList<>();

        for (Object[] result : boardList) {
            Board board = (Board) result[0];
            Long likeCount = (Long) result[1];
            Long bookmarkCount = (Long) result[2];
            Long replyCount = (Long) result[3];
            BoardResponse.SearchBoardListDTO dto = new BoardResponse.SearchBoardListDTO(board);
            //좋아요와 북마크 개수를 셋팅한다
            dto.setLikeCount(likeCount);
            dto.setBookmarkCount(bookmarkCount);
            dto.setReplyCount(replyCount);
            if (board.getUser().getId().equals(userId)) { //이 게시글이 내가 쓴 글이면
                //좋아요 눌렀는지 확인 후 true/false 설정
                dto.setMyLike(likeService.isLiked(boardRole, board.getId(), userId));
                dto.setMyBookmark(bookmarkService.isBookmarked(boardRole, board.getId(), userId));
            }
            boardDtoList.add(dto);
        }
        return new PageImpl<>(boardDtoList, pageable, boards.getTotalElements());
    }
}
