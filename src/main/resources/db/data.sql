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