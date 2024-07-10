-- 관리자 계정 생성
INSERT INTO user_tb (email, password, nickname, username, phone, birth, image, role, provider, status,
                     created_at, updated_at)
VALUES ('admin@gmail.com', '1234', 'admin', '관리자', '010-1234-5678', '1980-01-01', 'admin_image.png',
        'ADMIN', 'EMAIL', 'ACTIVE', NOW(), null);

-- 일반 사용자 계정 생성
INSERT INTO user_tb (email, password, nickname, username, phone, birth, image, role, provider, status,
                     created_at, updated_at)
VALUES ('ssar@nate.com', '1234', 'ssar', '쌀', '010-1234-5678', '1990-08-15', 'profile_basic.png',
        'USER', 'EMAIL', 'ACTIVE', NOW(), null),
       ('cos@nate.com', '1234', 'cos', '코스', '010-2345-6789', '1991-04-25', 'profile_basic.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null),
       ('love@nate.com', '1234', 'love', '러브', '010-3456-7890', '1992-12-05', 'profile_basic.png',
        'USER', 'NAVER', 'ACTIVE', NOW(), null),
       ('kimsaewoon@gmail.com', '1234', 'kimsaewoon', '김세운', '010-4567-8901', '1984-06-20', 'profile_rlatpdns.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null),
       ('egdg7777@gmail.com', '1234', 'egdg7777', '하승진', '010-2649-1492', '1990-09-15', 'profile_gktmdwls.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null),
       ('tkffkels93@gmail.com', '1234', 'tkffkels93', '김완준', '010-5678-9012', '1995-02-10', 'profile_basic.png',
        'USER', 'EMAIL', 'ACTIVE', NOW(), null),
       ('zeeq125@gmail.com', '1234', 'zeeq125', '김정수', '010-6789-0123', '1996-10-30', 'profile_basic.png',
        'USER', 'NAVER', 'ACTIVE', NOW(), null),
       ('junsik213@naver.com', '1234', 'junsik213', '임준식', '010-7890-1234', '1997-07-12', 'profile_basic.png',
        'USER', 'NAVER', 'ACTIVE', NOW(), null),
       ('ij0512@naver.com', '1234', 'ij0512', '공지영', '010-8901-2345', '1998-03-22', 'profile_rhdwldud.jpg',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null);

-- Board 엔터티용 더미 데이터 생성
INSERT INTO board_tb (user_id, board_role, title, content, hit, status, created_at, updated_at)
VALUES (1, 'Board', '첫 번째 게시글', '첫 번째 게시글의 내용입니다.', 100, 'PUBLISHED', NOW(), NULL),
       (2, 'Inquiry', '두 번째 게시글', '두 번째 게시글의 내용입니다.', 50, 'HIDDEN', NOW(), NULL),
       (3, 'Answer', '세 번째 게시글', '세 번째 게시글의 내용입니다.', 200, 'PUBLISHED', NOW(), NULL),
       (4, 'Board', '네 번째 게시글', '네 번째 게시글의 내용입니다.', 80, 'DELETED', NOW(), NULL),
       (5, 'Inquiry', '다섯 번째 게시글', '다섯 번째 게시글의 내용입니다.', 150, 'PUBLISHED', NOW(), NULL),
       (6, 'Answer', '여섯 번째 게시글', '여섯 번째 게시글의 내용입니다.', 120, 'PUBLISHED', NOW(), NULL),
       (7, 'Board', '일곱 번째 게시글', '일곱 번째 게시글의 내용입니다.', 300, 'HIDDEN', NOW(), NULL),
       (8, 'Inquiry', '여덟 번째 게시글', '여덟 번째 게시글의 내용입니다.', 90, 'PUBLISHED', NOW(), NULL),
       (9, 'Answer', '아홉 번째 게시글', '아홉 번째 게시글의 내용입니다.', 180, 'PUBLISHED', NOW(), NULL),
       (1, 'Board', '열 번째 게시글', '열 번째 게시글의 내용입니다.', 70, 'PUBLISHED', NOW(), NULL),
       (1, 'Inquiry', '열한 번째 게시글', '열한 번째 게시글의 내용입니다.', 230, 'PUBLISHED', NOW(), NULL),
       (1, 'Answer', '열두 번째 게시글', '열두 번째 게시글의 내용입니다.', 40, 'HIDDEN', NOW(), NULL),
       (1, 'Board', '열세 번째 게시글', '열세 번째 게시글의 내용입니다.', 260, 'PUBLISHED', NOW(), NULL),
       (1, 'Inquiry', '열네 번째 게시글', '열네 번째 게시글의 내용입니다.', 60, 'PUBLISHED', NOW(), NULL),
       (2, 'Answer', '열다섯 번째 게시글', '열다섯 번째 게시글의 내용입니다.', 110, 'DELETED', NOW(), NULL),
       (2, 'Board', '열여섯 번째 게시글', '열여섯 번째 게시글의 내용입니다.', 140, 'PUBLISHED', NOW(), NULL),
       (2, 'Inquiry', '열일곱 번째 게시글', '열일곱 번째 게시글의 내용입니다.', 320, 'PUBLISHED', NOW(), NULL),
       (2, 'Answer', '열여덟 번째 게시글', '열여덟 번째 게시글의 내용입니다.', 130, 'PUBLISHED', NOW(), NULL),
       (2, 'Board', '열아홉 번째 게시글', '열아홉 번째 게시글의 내용입니다.', 240, 'HIDDEN', NOW(), NULL),
       (2, 'Inquiry', '스무 번째 게시글', '스무 번째 게시글의 내용입니다.', 260, 'PUBLISHED', NOW(), NULL);

-- Reply 엔터티용 더미 데이터 생성
INSERT INTO reply_tb (user_id, board_id, comment, status, created_at, updated_at)
VALUES (1, 1, '첫 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (1, 1, '두 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (2, 2, '세 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (2, 2, '네 번째 댓글입니다.', 'HIDDEN', NOW(), NULL),
       (3, 3, '다섯 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (3, 3, '여섯 번째 댓글입니다.', 'DELETED', NOW(), NULL),
       (4, 4, '일곱 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (4, 4, '여덟 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (5, 5, '아홉 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (5, 5, '열 번째 댓글입니다.', 'HIDDEN', NOW(), NULL),
       (6, 6, '열한 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (6, 6, '열두 번째 댓글입니다.', 'DELETED', NOW(), NULL),
       (7, 7, '열세 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (7, 7, '열네 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (8, 8, '열다섯 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (8, 8, '열여섯 번째 댓글입니다.', 'HIDDEN', NOW(), NULL),
       (9, 9, '열일곱 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (9, 9, '열여덟 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (10, 10, '열아홉 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (10, 10, '스무 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL);

-- Photo 엔터티용 더미 데이터 생성
INSERT INTO photo_tb (board_id, file_name, file_path)
VALUES (1, 'photo_1.jpg', '/photos/1/photo_1.jpg'),
       (2, 'photo_2.jpg', '/photos/2/photo_2.jpg'),
       (3, 'photo_3.jpg', '/photos/3/photo_3.jpg'),
       (4, 'photo_4.jpg', '/photos/4/photo_4.jpg'),
       (5, 'photo_5.jpg', '/photos/5/photo_5.jpg'),
       (6, 'photo_6.jpg', '/photos/6/photo_6.jpg'),
       (7, 'photo_7.jpg', '/photos/7/photo_7.jpg'),
       (8, 'photo_8.jpg', '/photos/8/photo_8.jpg'),
       (9, 'photo_9.jpg', '/photos/9/photo_9.jpg'),
       (10, 'photo_10.jpg', '/photos/10/photo_10.jpg'),
       (11, 'photo_11.jpg', '/photos/11/photo_11.jpg'),
       (12, 'photo_12.jpg', '/photos/12/photo_12.jpg'),
       (13, 'photo_13.jpg', '/photos/13/photo_13.jpg'),
       (14, 'photo_14.jpg', '/photos/14/photo_14.jpg'),
       (15, 'photo_15.jpg', '/photos/15/photo_15.jpg'),
       (16, 'photo_16.jpg', '/photos/16/photo_16.jpg'),
       (17, 'photo_17.jpg', '/photos/17/photo_17.jpg'),
       (18, 'photo_18.jpg', '/photos/18/photo_18.jpg'),
       (19, 'photo_19.jpg', '/photos/19/photo_19.jpg'),
       (20, 'photo_20.jpg', '/photos/20/photo_20.jpg');

-- Bookmark 엔터티용 더미 데이터 생성
INSERT INTO bookmark_tb (user_id, board_id, created_at, updated_at)
VALUES (1, 1, NOW(), NULL),
       (2, 3, NOW(), NULL),
       (3, 5, NOW(), NULL),
       (4, 7, NOW(), NULL),
       (5, 9, NOW(), NULL),
       (6, 11, NOW(), NULL),
       (7, 13, NOW(), NULL),
       (8, 15, NOW(), NULL),
       (9, 17, NOW(), NULL),
       (10, 19, NOW(), NULL),
       (1, 2, NOW(), NULL),
       (2, 4, NOW(), NULL),
       (3, 6, NOW(), NULL),
       (4, 8, NOW(), NULL),
       (5, 10, NOW(), NULL),
       (6, 12, NOW(), NULL),
       (7, 14, NOW(), NULL),
       (8, 16, NOW(), NULL),
       (9, 18, NOW(), NULL),
       (10, 20, NOW(), NULL);