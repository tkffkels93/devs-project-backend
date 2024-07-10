-- 관리자 계정 생성
INSERT INTO user_tb (email, password, nickname, username, phone, birth, image, role, provider, status,
                     created_at, updated_at)
VALUES ('admin@gmail.com', '1234', 'admin', '관리자', '010-1234-5678', '1980-01-01', 'admin_image.png', 'ADMIN', 'EMAIL',
        'ACTIVE', NOW(), null);