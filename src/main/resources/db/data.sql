-- 관리자 계정 생성
INSERT INTO user_tb (email, password, nickname, username, phone, birth, image, role, provider, status,
                     created_at, updated_at)
VALUES ('admin@gmail.com', '1234', 'admin', '관리자', '010-1234-5678', '1980-01-01', 'admin_image.png',
        'ADMIN', 'EMAIL', 'ACTIVE', NOW(), null);

-- 일반 사용자 계정 생성, 비밀번호는 암호화해서 저장된다 (1234)
INSERT INTO user_tb (email, password, nickname, username, phone, birth, image, role, provider, status,
                     created_at, updated_at)
VALUES ('ssar@nate.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', 'ssar', '쌀', '010-1234-5678',
        '1990-08-15', 'profile_basic.png',
        'USER', 'EMAIL', 'ACTIVE', NOW(), null),
       ('cos@nate.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', 'cos', '코스', '010-2345-6789',
        '1991-04-25', 'profile_basic.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null),
       ('love@nate.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', 'love', '러브', '010-3456-7890',
        '1992-12-05', 'profile_basic.png',
        'USER', 'NAVER', 'ACTIVE', NOW(), null),
       ('kimsaewoon@gmail.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', 'kimsaewoon', '김세운',
        '010-4567-8901', '1984-06-20', 'profile_rlatpdns.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null),
       ('egdg7777@gmail.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', 'egdg7777', '하승진',
        '010-2649-1492', '1990-09-15', 'profile_gktmdwls.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null),
       ('tkffkels93@gmail.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', 'tkffkels93', '김완준',
        '010-5678-9012', '1995-02-10', 'profile_basic.png',
        'USER', 'EMAIL', 'ACTIVE', NOW(), null),
       ('zeeq125@gmail.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', 'zeeq125', '김정수',
        '010-6789-0123', '1996-10-30', 'profile_basic.png',
        'USER', 'NAVER', 'ACTIVE', NOW(), null),
       ('junsik213@naver.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', 'junsik213', '임준식',
        '010-7890-1234', '1997-07-12', 'profile_basic.png',
        'USER', 'NAVER', 'ACTIVE', NOW(), null),
       ('ij0512@naver.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', 'ij0512', '공지영',
        '010-8901-2345', '1998-03-22', 'profile_rhdwldud.jpg',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null);

-- Board 엔터티용 더미 데이터 생성
INSERT INTO board_tb (user_id, board_role, title, content, hit, status, created_at, updated_at)
VALUES (1, 'Board', '첫 번째 게시글',
        '첫 번째 게시글의 내용입니다. 이 게시글은 게시판에 처음으로 올라온 게시글로, 다양한 정보와 유용한 내용을 포함하고 있습니다. 이 게시판은 사용자가 다양한 정보를 공유하고, 질문하고, 답변을 받을 수 있는 공간입니다. 이 첫 번째 게시글을 통해 사용자들이 게시판의 목적과 사용 방법을 이해하고, 적극적으로 참여할 수 있도록 안내하고자 합니다. 게시판의 규칙과 에티켓을 준수하며, 서로에게 도움이 되는 정보를 공유하는 커뮤니티를 만들어갑시다. 많은 참여 부탁드립니다.',
        100, 'PUBLISHED', NOW(), NULL),
       (2, 'Inquiry', '기능 문의 게시글',
        '두 번째 게시글의 내용입니다. 이 게시글은 특정 기능에 대한 사용법 및 문제 해결 방법을 문의하는 내용입니다. 사용 중에 발생한 문제점이나 궁금한 사항을 자세히 설명하고, 이에 대한 해결 방법을 요청합니다. 해당 기능을 보다 효과적으로 활용하기 위한 팁이나 다른 사용자들의 경험을 공유받고자 합니다. 이 게시글을 통해 다른 사용자들도 비슷한 문제를 겪을 경우 참고할 수 있는 유용한 정보를 얻을 수 있을 것입니다. 신속한 답변 부탁드립니다.',
        50, 'HIDE', NOW(), NULL),
       (3, 'Answer', '문의 답변 게시글',
        '세 번째 게시글의 내용입니다. 이 게시글은 이전에 올라온 문의사항에 대한 답변을 제공하는 내용입니다. 사용자가 제기한 문제나 질문에 대해 상세하게 설명하고, 단계별로 해결 방법을 제시합니다. 관련 자료나 링크를 포함하여 사용자가 쉽게 이해하고 따라할 수 있도록 도와줍니다. 이 답변을 통해 많은 사용자들이 유사한 문제를 해결할 수 있기를 바랍니다. 언제든 추가 질문이 있으면 댓글로 남겨주세요.',
        200, 'PUBLISHED', NOW(), NULL),
       (4, 'Board', '이용 안내 게시글',
        '네 번째 게시글의 내용입니다. 이 게시글은 게시판의 이용 규칙 및 안내 사항을 포함하고 있습니다. 게시판을 올바르게 사용하기 위한 기본적인 규칙과 에티켓을 설명하며, 사용자들이 지켜야 할 사항들을 상세히 안내합니다. 예를 들어, 중복된 질문을 피하고, 명확하고 간결하게 질문을 작성하는 방법, 그리고 다른 사용자를 존중하는 태도를 강조합니다. 이 안내를 통해 사용자들이 더 나은 커뮤니티를 만들어 갈 수 있도록 돕습니다.',
        80, 'DELETED', NOW(), NULL),
       (5, 'Inquiry', '기능 개선 요청',
        '다섯 번째 게시글의 내용입니다. 이 게시글은 특정 기능의 개선을 요청하는 내용입니다. 현재 사용 중인 기능에서 불편함을 느끼거나 개선이 필요하다고 생각되는 점을 자세히 설명하고, 구체적인 개선 방안을 제안합니다. 이러한 요청을 통해 사용자 경험을 향상시키고, 더 나은 서비스 제공을 위한 아이디어를 공유합니다. 이 게시글이 많은 사용자들의 공감을 얻어 실제 개선으로 이어지기를 기대합니다. 피드백을 통해 발전하는 커뮤니티가 되기를 바랍니다.',
        150, 'PUBLISHED', NOW(), NULL),
       (6, 'Answer', '기능 개선 답변',
        '여섯 번째 게시글의 내용입니다. 이 게시글은 기능 개선 요청에 대한 답변을 제공하는 내용입니다. 사용자가 제안한 개선 방안에 대해 검토한 결과와 향후 계획을 설명합니다. 개선 요청에 대한 긍정적인 피드백과 함께, 추가로 고려해야 할 사항이나 현재 진행 중인 다른 개선 작업에 대한 정보를 제공합니다. 이 답변을 통해 사용자들이 개선 요청이 어떻게 반영될지에 대한 이해를 돕고, 지속적인 피드백을 통해 더 나은 서비스를 제공할 수 있도록 노력합니다.',
        120, 'PUBLISHED', NOW(), NULL),
       (7, 'Board', '업데이트 안내',
        '일곱 번째 게시글의 내용입니다. 이 게시글은 최근 업데이트된 기능에 대한 소개와 사용법을 설명하고 있습니다. 업데이트된 기능을 효과적으로 활용하는 방법과 새로 추가된 기능의 장점에 대해 상세히 안내합니다. 사용자들이 새로운 기능을 쉽게 이해하고, 이를 통해 더 나은 경험을 할 수 있도록 돕습니다. 또한, 이번 업데이트를 통해 해결된 문제점과 앞으로 계획된 업데이트 일정에 대해서도 설명합니다. 많은 관심과 사용 부탁드립니다.',
        300, 'HIDE', NOW(), NULL),
       (8, 'Inquiry', '오류 문의 게시글',
        '여덟 번째 게시글의 내용입니다. 이 게시글은 특정 오류에 대한 문의사항을 담고 있으며, 오류 해결 방법에 대한 조언을 구하는 내용입니다. 사용 중 발생한 오류의 증상과 상황을 자세히 설명하고, 이를 해결하기 위한 조치를 요청합니다. 다른 사용자들이 비슷한 문제를 겪었을 경우, 어떤 방법으로 해결했는지에 대한 경험을 공유받고자 합니다. 이 게시글이 많은 사용자들에게 유용한 정보를 제공할 수 있기를 바랍니다. 빠른 답변 부탁드립니다.',
        90, 'PUBLISHED', NOW(), NULL),
       (9, 'Answer', '오류 해결 방법',
        '아홉 번째 게시글의 내용입니다. 이 게시글은 이전에 올라온 오류 문의에 대한 해결 방법을 자세히 설명합니다. 사용자가 제기한 오류에 대한 원인 분석과 함께, 단계별로 문제를 해결할 수 있는 가이드를 제공합니다. 관련 자료나 스크린샷을 포함하여 사용자가 쉽게 따라할 수 있도록 도와줍니다. 이 답변을 통해 많은 사용자들이 유사한 문제를 해결할 수 있기를 바랍니다. 언제든 추가 질문이 있으면 댓글로 남겨주세요.',
        180, 'PUBLISHED', NOW(), NULL),
       (1, 'Board', '커뮤니티 이벤트',
        '열 번째 게시글의 내용입니다. 이 게시글은 커뮤니티 이벤트에 대한 공지사항을 포함하고 있습니다. 이벤트의 세부 일정 및 참여 방법에 대한 정보를 상세히 설명합니다. 사용자들이 이벤트에 적극적으로 참여할 수 있도록 유도하며, 이벤트를 통해 얻을 수 있는 혜택과 재미를 강조합니다. 많은 사용자들이 참여하여 커뮤니티의 활기를 더할 수 있기를 기대합니다. 이벤트에 대한 자세한 내용은 게시글을 참조하시기 바랍니다.',
        70, 'PUBLISHED', NOW(), NULL),
       (1, 'Inquiry', '기능 요청 게시글',
        '열한 번째 게시글의 내용입니다. 이 게시글은 특정 기능의 개선 요청을 담고 있으며, 해당 기능의 개선을 통해 사용자 경험을 향상시키기 위한 제안을 하고 있습니다. 현재 기능에서 느끼는 불편함과 개선이 필요하다고 생각되는 점을 구체적으로 설명합니다. 이를 통해 더 나은 서비스를 제공받고자 하는 사용자의 바람을 담고 있습니다. 많은 사용자들이 공감하고, 실제로 개선이 이루어질 수 있기를 기대합니다. 피드백을 부탁드립니다.',
        230, 'PUBLISHED', NOW(), NULL),
       (1, 'Answer', '기능 요청 답변',
        '열두 번째 게시글의 내용입니다. 이 게시글은 기능 요청에 대한 답변을 제공하며, 향후 계획과 관련된 정보를 공유합니다. 사용자가 제안한 기능 개선 방안에 대한 검토 결과와 함께, 현재 진행 중인 다른 개선 작업에 대해서도 설명합니다. 사용자의 의견을 반영하여 더 나은 서비스를 제공하기 위한 노력의 일환으로, 추가적인 피드백을 지속적으로 수렴하고자 합니다. 이 답변을 통해 사용자들이 개선 요청이 어떻게 반영될지에 대한 이해를 돕습니다.',
        40, 'HIDE', NOW(), NULL),
       (1, 'Board', '공지사항 게시글',
        '열세 번째 게시글의 내용입니다. 이 게시글은 중요 공지사항을 포함하고 있으며, 사용자들이 반드시 알아야 할 정보를 제공합니다. 공지사항의 내용은 커뮤니티 이용에 중요한 영향을 미칠 수 있는 사항들로 구성되어 있으며, 이를 통해 사용자들이 최신 정보를 빠르게 확인할 수 있도록 돕습니다. 공지사항을 통해 커뮤니티의 변화나 새로운 정책에 대해 알리고, 사용자들이 이에 맞춰 행동할 수 있도록 안내합니다. 많은 관심 부탁드립니다.',
        260, 'PUBLISHED', NOW(), NULL),
       (1, 'Inquiry', '문의사항 게시글',
        '열네 번째 게시글의 내용입니다. 이 게시글은 특정 기능에 대한 문의사항을 담고 있으며, 해당 기능의 사용법이나 문제 해결 방법에 대한 조언을 구하는 내용입니다. 사용 중에 발생한 문제점이나 궁금한 사항을 자세히 설명하고, 이에 대한 해결 방법을 요청합니다. 이 게시글을 통해 다른 사용자들도 비슷한 문제를 겪을 경우 참고할 수 있는 유용한 정보를 얻을 수 있을 것입니다. 신속한 답변 부탁드립니다.',
        60, 'PUBLISHED', NOW(), NULL),
       (2, 'Answer', '답변 게시글',
        '열다섯 번째 게시글의 내용입니다. 이 게시글은 이전에 올라온 문의사항에 대한 답변을 제공하는 내용입니다. 사용자가 제기한 문제나 질문에 대해 상세하게 설명하고, 단계별로 해결 방법을 제시합니다. 관련 자료나 링크를 포함하여 사용자가 쉽게 이해하고 따라할 수 있도록 도와줍니다. 이 답변을 통해 많은 사용자들이 유사한 문제를 해결할 수 있기를 바랍니다. 언제든 추가 질문이 있으면 댓글로 남겨주세요.',
        110, 'DELETED', NOW(), NULL),
       (2, 'Board', '새로운 기능 안내',
        '열여섯 번째 게시글의 내용입니다. 이 게시글은 새로운 기능에 대한 소개와 사용법을 설명하고 있습니다. 업데이트된 기능을 효과적으로 활용하는 방법과 새로 추가된 기능의 장점에 대해 상세히 안내합니다. 사용자들이 새로운 기능을 쉽게 이해하고, 이를 통해 더 나은 경험을 할 수 있도록 돕습니다. 또한, 이번 업데이트를 통해 해결된 문제점과 앞으로 계획된 업데이트 일정에 대해서도 설명합니다. 많은 관심과 사용 부탁드립니다.',
        140, 'PUBLISHED', NOW(), NULL),
       (2, 'Inquiry', '서비스 문의 게시글',
        '열일곱 번째 게시글의 내용입니다. 이 게시글은 특정 서비스에 대한 문의사항을 담고 있으며, 해당 서비스의 사용법이나 문제 해결 방법에 대한 조언을 구하는 내용입니다. 사용 중에 발생한 문제점이나 궁금한 사항을 자세히 설명하고, 이에 대한 해결 방법을 요청합니다. 이 게시글을 통해 다른 사용자들도 비슷한 문제를 겪을 경우 참고할 수 있는 유용한 정보를 얻을 수 있을 것입니다. 신속한 답변 부탁드립니다.',
        320, 'PUBLISHED', NOW(), NULL),
       (2, 'Answer', '서비스 답변 게시글',
        '열여덟 번째 게시글의 내용입니다. 이 게시글은 이전에 올라온 서비스 문의에 대한 답변을 제공하는 내용입니다. 사용자가 제기한 문제나 질문에 대해 상세하게 설명하고, 단계별로 해결 방법을 제시합니다. 관련 자료나 링크를 포함하여 사용자가 쉽게 이해하고 따라할 수 있도록 도와줍니다. 이 답변을 통해 많은 사용자들이 유사한 문제를 해결할 수 있기를 바랍니다. 언제든 추가 질문이 있으면 댓글로 남겨주세요.',
        130, 'PUBLISHED', NOW(), NULL),
       (2, 'Board', '공지사항 게시글',
        '열아홉 번째 게시글의 내용입니다. 이 게시글은 중요 공지사항을 포함하고 있으며, 사용자들이 반드시 알아야 할 정보를 제공합니다. 공지사항의 내용은 커뮤니티 이용에 중요한 영향을 미칠 수 있는 사항들로 구성되어 있으며, 이를 통해 사용자들이 최신 정보를 빠르게 확인할 수 있도록 돕습니다. 공지사항을 통해 커뮤니티의 변화나 새로운 정책에 대해 알리고, 사용자들이 이에 맞춰 행동할 수 있도록 안내합니다. 많은 관심 부탁드립니다.',
        240, 'HIDE', NOW(), NULL),
       (2, 'Inquiry', '서비스 요청 게시글',
        '스무 번째 게시글의 내용입니다. 이 게시글은 특정 서비스의 개선 요청을 담고 있으며, 해당 서비스의 개선을 통해 사용자 경험을 향상시키기 위한 제안을 하고 있습니다. 현재 서비스에서 느끼는 불편함과 개선이 필요하다고 생각되는 점을 구체적으로 설명합니다. 이를 통해 더 나은 서비스를 제공받고자 하는 사용자의 바람을 담고 있습니다. 많은 사용자들이 공감하고, 실제로 개선이 이루어질 수 있기를 기대합니다. 피드백을 부탁드립니다.',
        260, 'PUBLISHED', NOW(), NULL);
-- Reply 엔터티용 더미 데이터 생성
INSERT INTO reply_tb (user_id, board_id, comment, status, created_at, updated_at)
VALUES (1, 1, '첫 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (1, 1, '두 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (2, 2, '세 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (2, 2, '네 번째 댓글입니다.', 'HIDE', NOW(), NULL),
       (3, 3, '다섯 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (3, 3, '여섯 번째 댓글입니다.', 'DELETED', NOW(), NULL),
       (4, 4, '일곱 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (4, 4, '여덟 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (5, 5, '아홉 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (5, 5, '열 번째 댓글입니다.', 'HIDE', NOW(), NULL),
       (6, 6, '열한 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (6, 6, '열두 번째 댓글입니다.', 'DELETED', NOW(), NULL),
       (7, 7, '열세 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (7, 7, '열네 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (8, 8, '열다섯 번째 댓글입니다.', 'PUBLISHED', NOW(), NULL),
       (8, 8, '열여섯 번째 댓글입니다.', 'HIDE', NOW(), NULL),
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
INSERT INTO bookmark_tb (user_id, board_id, created_at, updated_at, board_role)
VALUES (1, 1, NOW(), NULL, 'Board'),
       (2, 3, NOW(), NULL, 'Board'),
       (3, 5, NOW(), NULL, 'Board'),
       (4, 7, NOW(), NULL, 'Board'),
       (5, 9, NOW(), NULL, 'Board'),
       (6, 11, NOW(), NULL, 'Board'),
       (7, 13, NOW(), NULL, 'Board'),
       (8, 15, NOW(), NULL, 'Board'),
       (9, 17, NOW(), NULL, 'Board'),
       (10, 19, NOW(), NULL, 'Board'),
       (1, 2, NOW(), NULL, 'Board'),
       (2, 4, NOW(), NULL, 'Board'),
       (3, 6, NOW(), NULL, 'Board'),
       (4, 8, NOW(), NULL, 'Board'),
       (5, 10, NOW(), NULL, 'Board'),
       (6, 12, NOW(), NULL, 'Board'),
       (7, 14, NOW(), NULL, 'Board'),
       (8, 16, NOW(), NULL, 'Board'),
       (9, 18, NOW(), NULL, 'Board'),
       (10, 20, NOW(), NULL, 'Board'),
        (7, 7, NOW(), NULL, 'Board');



-- 좋아요 테이블
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 1, 'Board', NOW());

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 2, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 2, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 2, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 2, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 2, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 2, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 2, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 2, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 2, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 2, 'Board', NOW());

-- 이 패턴을 계속해서 적용하여 1부터 10까지의 board_id에 대해 생성

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 3, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 3, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 3, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 3, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 3, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 3, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 3, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 3, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 3, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 3, 'Board', NOW());

-- 같은 방식으로 4부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 4, 'Board', NOW());

-- 같은 방식으로 5부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 5, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 5, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 5, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 5, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 5, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 5, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 5, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 5, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 5, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 5, 'Board', NOW());

-- BoardRole: Inquiry (1부터 10까지의 board_id)
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 1, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 1, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 1, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 1, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 1, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 1, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 1, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 1, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 1, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 1, 'Inquiry', NOW());

-- 같은 방식으로 2부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 2, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 2, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 2, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 2, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 2, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 2, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 2, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 2, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 2, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 2, 'Inquiry', NOW());

-- 같은 방식으로 3부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 3, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 3, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 3, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 3, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 3, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 3, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 3, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 3, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 3, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 3, 'Inquiry', NOW());

-- 같은 방식으로 4부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 4, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 4, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 4, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 4, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 4, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 4, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 4, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 4, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 4, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 4, 'Inquiry', NOW());

-- 같은 방식으로 5부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 5, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 5, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 5, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 5, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 5, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 5, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 5, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 5, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 5, 'Inquiry', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 5, 'Inquiry', NOW());

-- BoardRole: Answer (1부터 10까지의 board_id)
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 1, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 1, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 1, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 1, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 1, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 1, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 1, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 1, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 1, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 1, 'Answer', NOW());

-- 같은 방식으로 2부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 2, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 2, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 2, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 2, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 2, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 2, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 2, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 2, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 2, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 2, 'Answer', NOW());

-- 같은 방식으로 3부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 3, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 3, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 3, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 3, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 3, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 3, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 3, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 3, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 3, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 3, 'Answer', NOW());

-- 같은 방식으로 4부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 4, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 4, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 4, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 4, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 4, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 4, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 4, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 4, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 4, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 4, 'Answer', NOW());

-- 같은 방식으로 5부터 10까지의 board_id에 대해 추가

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (1, 5, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (2, 5, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (3, 5, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (4, 5, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (5, 5, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (6, 5, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 5, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (8, 5, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (9, 5, 'Answer', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (10, 5, 'Answer', NOW());

INSERT INTO like_tb (user_id, board_id, board_role, created_at) VALUES (7, 7, 'Board', NOW());