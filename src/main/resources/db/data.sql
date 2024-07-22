-- 관리자 계정 생성
INSERT INTO user_tb (email, password, nickname, username, phone, birth, image, role, provider, status,
                     created_at, updated_at)
VALUES ('admin@gmail.com', '1234', 'admin', '관리자', '010-1234-5678', '1980-01-01', 'admin_image.png',
        'ADMIN', 'EMAIL', 'ACTIVE', NOW(), null);

-- 일반 사용자 계정 생성, 비밀번호는 암호화해서 저장된다 (1234)
INSERT INTO user_tb (email, password, nickname, username, phone, position, introduce, birth, image, role, provider,
                     status, created_at, updated_at)
VALUES ('ssar@nate.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', '쌀', '최주호',
        '010-1234-5678', '선임 연구원', '반갑습니다!', '1990-08-15', '/upload/star.png',
        'USER', 'EMAIL', 'ACTIVE', NOW(), null),
       ('cos@nate.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', '코스', '김코스',
        '010-2345-6789', '책임 연구원', '반갑습니다!', '1991-04-25', '/upload/ent.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null),
       ('love@nate.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', '러브', '박러브',
        '010-3456-7890', '선임 연구원', '반갑습니다!', '1992-12-05', '/upload/love.png',
        'USER', 'NAVER', 'ACTIVE', NOW(), null),
       ('kimsaewoon@gmail.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', '꽁이', '김세운',
        '010-4567-8901', '과장', '반갑습니다!', '1984-06-20', '/upload/dog.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null),
       ('egdg7777@gmail.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', '씅씅', '하승진',
        '010-2649-1492', '팀장', '반갑습니다!', '1990-09-15', '/upload/cat.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null),
       ('tkffkels93@gmail.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', '살라딘93', '김완준',
        '010-5678-9012', '대리', '반갑습니다!', '1995-02-10', '/upload/keyboard.png',
        'USER', 'EMAIL', 'ACTIVE', NOW(), null),
       ('zeeq125@gmail.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', '지크125', '김정수',
        '010-6789-0123', '대리', '반갑습니다!', '1996-10-30', '/upload/imac.png',
        'USER', 'NAVER', 'ACTIVE', NOW(), null),
       ('junsik213@naver.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', '식식213', '임준식',
        '010-7890-1234', '팀장', '반갑습니다!', '1997-07-12', '/upload/health.png',
        'USER', 'NAVER', 'ACTIVE', NOW(), null),
       ('ij0512@naver.com', '$2a$10$CluX5UWKeV5f5n4S1AIptO7sJOg.faRFeVaZHfSenxkSHFuRcS9Yu', '지영0512', '공지영',
        '010-8901-2345', '주임', '반갑습니다!', '1998-03-22', '/upload/dear.png',
        'USER', 'KAKAO', 'ACTIVE', NOW(), null);

-- Board 엔터티용 더미 데이터 생성
INSERT INTO board_tb (user_id, board_role, title, content, hit, status, created_at, updated_at)
VALUES
    (1, 'Board', 'JavaScript 비동기 처리',
     '[{"insert":"JavaScript에서 비동기 처리를 어떻게 할 수 있는지 설명하겠습니다. "},{"insert":"비동기 처리의 기본 개념부터 시작하여, 콜백 함수, 프로미스, async/await 구문을 다룹니다.\n\n","attributes":{"bold":true}},{"insert":"비동기 처리는 서버와의 데이터 통신, 파일 읽기/쓰기 등 다양한 작업에서 중요합니다. 콜백 함수는 기본적인 비동기 처리 방법으로, 함수가 다른 함수에 인수로 전달되는 방식입니다.\n\n"},{"insert":"프로미스는 비동기 작업이 성공하거나 실패했을 때 실행할 코드를 명확하게 정의할 수 있게 해줍니다.","attributes":{"italic":true}},{"insert":" 마지막으로, async/await는 프로미스 기반의 비동기 코드를 동기 코드처럼 작성할 수 있게 해주는 구문입니다.\n\n"},{"insert":"예제 코드를 통해 각 방법의 사용법을 익혀봅시다.\n\n","attributes":{"bold":true}},{"insert":"비동기 처리의 필요성은 사용자 경험을 향상시키는 데 있습니다. 예를 들어, 서버에서 데이터를 가져올 때 페이지가 멈추지 않고 계속 작동하게 할 수 있습니다. 비동기 처리를 사용하면 사용자가 인터페이스와 상호작용하는 동안 백그라운드에서 작업이 완료될 수 있습니다.\n\n"},{"insert":"비동기 처리를 구현하는 가장 기본적인 방법은 콜백 함수입니다. 콜백 함수는 특정 작업이 완료되었을 때 호출되는 함수입니다. 그러나 콜백 지옥이라고 불리는 문제로 인해 복잡한 비동기 처리를 구현할 때 코드 가독성이 떨어질 수 있습니다.\n\n이를 해결하기 위해 프로미스가 도입되었습니다. 프로미스는 비동기 작업을 처리하기 위한 객체로, 작업이 성공하거나 실패했을 때 실행할 코드를 명확하게 정의할 수 있습니다. 프로미스를 사용하면 콜백 지옥을 피할 수 있으며, 체이닝을 통해 가독성을 높일 수 있습니다.\n\n","attributes":{"underline":true}},{"insert":"마지막으로, async/await 구문은 프로미스 기반의 비동기 코드를 동기 코드처럼 작성할 수 있게 해줍니다. 이는 비동기 작업을 더 직관적으로 작성할 수 있게 하며, 코드의 가독성을 크게 향상시킵니다. async 키워드를 함수 앞에 붙이면 해당 함수는 항상 프로미스를 반환하며, await 키워드를 사용하면 프로미스가 해결될 때까지 기다릴 수 있습니다.\n\n"}]',
     100, 'PUBLISHED', NOW(), NULL),

    (2, 'Board', 'CSS Flexbox 활용',
     '[{"insert":"CSS의 Flexbox를 사용하여 레이아웃을 구성하는 방법에 대해 알아보겠습니다. Flexbox는 1차원 레이아웃 모델로, 행과 열 단위로 요소를 배치할 때 유용합니다.\n\n"},{"insert":"Flex 컨테이너와 그 자식 요소들의 배치 속성을 통해 다양한 레이아웃을 손쉽게 구현할 수 있습니다. ","attributes":{"bold":true}},{"insert":"Flexbox의 기본 속성들, 예를 들어 justify-content, align-items, flex-direction 등을 설명하고, 실제 예제 코드를 통해 다양한 레이아웃을 구성해 보겠습니다.\n\n또한, Flexbox를 사용할 때 자주 겪는 문제와 그 해결 방법도 다룹니다.\n\n","attributes":{"italic":true}},{"insert":"Flexbox의 가장 큰 장점은 복잡한 레이아웃을 간단하게 구현할 수 있다는 점입니다. 예를 들어, 세로로 정렬된 요소들을 중앙에 배치하거나, 가로로 나란히 배치된 요소들을 간격을 맞추어 정렬할 수 있습니다. 이를 통해 다양한 화면 크기와 해상도에 대응하는 반응형 레이아웃을 쉽게 구현할 수 있습니다.\n\n"},{"insert":"Flexbox의 기본 개념을 이해하는 것은 중요합니다. Flex 컨테이너는 Flexbox 레이아웃을 적용할 부모 요소를 의미하며, 그 안에 있는 자식 요소들이 Flex 아이템이 됩니다. justify-content 속성은 주 축(가로 또는 세로) 방향으로 아이템을 정렬하는 방법을 정의하며, align-items 속성은 교차 축(가로 또는 세로) 방향으로 아이템을 정렬하는 방법을 정의합니다.\n\n","attributes":{"underline":true}},{"insert":"예제 코드를 통해 실제로 Flexbox를 사용하여 다양한 레이아웃을 구성해 봅시다. 기본적인 레이아웃 예제를 통해 각 속성이 어떻게 작동하는지 확인할 수 있습니다. 또한, Flexbox를 사용할 때 자주 겪는 문제와 그 해결 방법도 다룰 것입니다. 예를 들어, 브라우저 간의 호환성 문제나 복잡한 레이아웃에서 발생할 수 있는 버그들을 해결하는 방법을 알아보겠습니다.\n\n"}]',
     50, 'HIDE', NOW(), NULL),

    (3, 'Board', 'React Hook 활용법',
     '[{"insert":"React에서 Hook을 활용하여 상태와 생명주기를 관리하는 방법을 설명합니다. useState와 useEffect Hook을 중심으로, 함수형 컴포넌트에서 상태를 관리하고, 컴포넌트가 마운트되거나 언마운트될 때 특정 작업을 수행하는 방법을 다룹니다.\n\n"},{"insert":"또한, 커스텀 Hook을 만들어 코드 재사용성을 높이는 방법도 소개합니다.","attributes":{"bold":true}},{"insert":" 예제 코드를 통해 각 Hook의 사용법을 익히고, 실제 프로젝트에서 어떻게 활용할 수 있는지 알아봅시다.\n\nuseState Hook은 함수형 컴포넌트에서 상태를 관리하는 기본적인 방법입니다. 상태는 컴포넌트의 렌더링과 동작을 제어하는 중요한 역할을 합니다. useState를 사용하면 상태 값을 정의하고, 이를 업데이트하는 함수도 함께 제공받을 수 있습니다. 상태 값이 변경되면 컴포넌트는 다시 렌더링됩니다.\n\n"},{"insert":"useEffect Hook은 컴포넌트의 생명주기와 관련된 작업을 처리하는 데 사용됩니다. 예를 들어, 컴포넌트가 처음 마운트될 때, 상태가 변경될 때, 컴포넌트가 언마운트될 때 특정 작업을 수행할 수 있습니다. useEffect는 의존성 배열을 통해 어떤 상태가 변경될 때 이펙트가 실행될지를 정의할 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"커스텀 Hook은 반복적인 로직을 추상화하여 코드의 재사용성을 높이는 방법입니다.","attributes":{"underline":true}},{"insert":" 커스텀 Hook을 사용하면 여러 컴포넌트에서 공통적으로 사용되는 로직을 하나의 함수로 정의하고, 이를 필요할 때마다 호출할 수 있습니다. 이를 통해 코드의 가독성과 유지보수성을 향상시킬 수 있습니다.\n\nReact Hook을 활용한 예제 코드를 통해 각 Hook의 사용법을 익히고, 실제 프로젝트에서 어떻게 활용할 수 있는지 알아봅시다. 다양한 상황에서 Hook을 효과적으로 사용하는 방법을 배우고, 이를 통해 더 나은 React 애플리케이션을 개발해 봅시다.\n\n"}]',
     200, 'PUBLISHED', NOW(), NULL),

    (4, 'Board', 'Python 데이터 분석',
     '[{"insert":"Python을 사용한 데이터 분석 기법을 소개합니다. Pandas 라이브러리를 활용하여 데이터를 불러오고, 정리하고, 분석하는 방법을 다룹니다.\n\n"},{"insert":"DataFrame을 생성하고, 데이터를 필터링하고, 그룹화하여 유의미한 정보를 추출하는 과정을 설명합니다. 또한, Matplotlib와 Seaborn을 사용하여 데이터 시각화를 수행하는 방법도 소개합니다.\n\n","attributes":{"bold":true}},{"insert":"예제 데이터를 통해 각 단계를 실습해보며, 데이터 분석의 기본기를 다져봅시다. Pandas는 데이터 조작 및 분석을 위한 강력한 도구로, 다양한 형식의 데이터를 쉽게 다룰 수 있습니다. CSV 파일, Excel 파일, SQL 데이터베이스 등에서 데이터를 불러와 DataFrame으로 변환하고, 이를 통해 데이터를 조작할 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"Matplotlib는 데이터 시각화를 위한 라이브러리로, 다양한 유형의 그래프와 차트를 생성할 수 있습니다. 데이터를 시각적으로 표현하면 패턴과 트렌드를 쉽게 파악할 수 있으며, 이를 통해 더 나은 의사 결정을 내릴 수 있습니다. Seaborn은 Matplotlib를 기반으로 한 고급 시각화 라이브러리로, 더욱 아름답고복잡한 시각화를 손쉽게 만들 수 있습니다.\n\n예제 데이터를 통해 Pandas와 Matplotlib, Seaborn을 사용하여 데이터 분석의 기본 단계를 실습해봅시다. 데이터를 불러오고, 정리하고, 분석하는 과정을 단계별로 따라가며, 각 단계에서 발생할 수 있는 문제와 그 해결 방법도 다룹니다. 이를 통해 데이터 분석의 전반적인 과정을 이해하고, 실무에서 어떻게 적용할 수 있는지 배워봅시다.\n\n","attributes":{"underline":true}}]',
    80, 'DELETED', NOW(), NULL),

    (5, 'Board', 'Git 브랜치 전략',
     '[{"insert":"효율적인 Git 브랜치 전략에 대해 알아보겠습니다. Git Flow, GitHub Flow, GitLab Flow 등 다양한 브랜치 전략을 비교하고, 각 전략의 장단점을 설명합니다.\n\n또한, 브랜치 생성, 병합, 충돌 해결 등의 기본적인 Git 작업을 다루고, 실제 프로젝트에서 브랜치를 관리하는 모범 사례를 소개합니다.\n\n코드의 버전 관리를 체계적으로 수행하여 협업 효율성을 높이는 방법을 배워봅시다.\n\n","attributes":{"bold":true}},{"insert":"Git Flow는 복잡한 프로젝트에서 브랜치를 체계적으로 관리하는 방법을 제공하는 전략입니다. 주 브랜치와 여러 기능 브랜치를 통해 개발, 릴리즈, 핫픽스 등을 관리할 수 있습니다. GitHub Flow는 간단하고 직관적인 브랜치 전략으로, 모든 변경 사항을 하나의 메인 브랜치로 병합하는 방식을 사용합니다.\n\n","attributes":{"italic":true}},{"insert":"GitLab Flow는 Git Flow와 GitHub Flow의 장점을 결합한 전략으로, 다양한 배포 환경을 지원합니다.","attributes":{"underline":true}},{"insert":" 각 브랜치 전략의 장단점을 비교하고, 프로젝트의 특성에 맞는 전략을 선택하는 방법을 알아봅시다.\n\n브랜치 생성, 병합, 충돌 해결 등의 기본적인 Git 작업을 실습해보며, 실제 프로젝트에서 브랜치를 관리하는 모범 사례를 소개합니다. 이를 통해 코드의 버전 관리를 체계적으로 수행하고, 협업 효율성을 높일 수 있는 방법을 배워봅시다.\n\n"}]',
     150, 'PUBLISHED', NOW(), NULL),

    (6, 'Board', 'Docker 기초와 활용',
     '[{"insert":"Docker를 활용하여 개발 환경을 구축하고, 애플리케이션을 배포하는 방법을 설명합니다. Docker 이미지와 컨테이너의 개념을 이해하고, Dockerfile을 작성하여 이미지를 빌드하는 과정을 다룹니다.\n\n","attributes":{"bold":true}},{"insert":"또한, Docker Compose를 사용하여 멀티 컨테이너 애플리케이션을 관리하는 방법도 소개합니다.\n\n예제 프로젝트를 통해 Docker의 기본 사용법을 익히고, 실무에서 어떻게 활용할 수 있는지 알아봅시다.","attributes":{"italic":true}},{"insert":" Docker는 애플리케이션을 컨테이너라는 격리된 환경에서 실행할 수 있도록 도와줍니다. 이를 통해 일관된 실행 환경을 제공하고, 개발, 테스트, 배포 과정을 효율적으로 관리할 수 있습니다.\n\nDocker 이미지는 애플리케이션과 그 실행 환경을 포함하는 읽기 전용 템플릿입니다. Dockerfile은 이미지를 빌드하기 위한 스크립트로, 애플리케이션의 설치 및 설정 과정을 정의합니다. Dockerfile을 작성하여 이미지를 빌드하고, 이를 통해 컨테이너를 실행할 수 있습니다.\n\n","attributes":{"underline":true}},{"insert":"Docker Compose는 여러 개의 컨테이너를 정의하고 실행하기 위한 도구로, 복잡한 애플리케이션을 쉽게 관리할 수 있습니다. 예제 프로젝트를 통해 Docker와 Docker Compose를 사용하여 개발 환경을 구축하고, 애플리케이션을 배포하는 과정을 실습해봅시다.\n\n"}]',
     120, 'PUBLISHED', NOW(), NULL),

    (7, 'Board', 'AWS 클라우드 서비스',
     '[{"insert":"AWS에서 제공하는 주요 클라우드 서비스에 대해 설명합니다. EC2, S3, RDS 등의 서비스를 소개하고, 각 서비스의 주요 기능과 사용 사례를 다룹니다.\n\n또한, AWS Management Console과 CLI를 사용하여 서비스를 관리하는 방법도 설명합니다.\n\nAWS를 활용하여 클라우드 인프라를 구축하고 운영하는 기본기를 다져봅시다.\n\n","attributes":{"bold":true}},{"insert":"EC2는 확장 가능하고 안전한 가상 서버를 제공하며, 다양한 인스턴스 타입을 통해 다양한 워크로드를 지원합니다.","attributes":{"italic":true}},{"insert":" S3는 확장 가능하고 내구성이 뛰어난 객체 스토리지를 제공하며, 다양한 데이터 백업 및 아카이빙 솔루션을 지원합니다. RDS는 관리형 관계형 데이터베이스 서비스를 제공하며, MySQL, PostgreSQL, MariaDB 등 여러 데이터베이스 엔진을 지원합니다.\n\n","attributes":{"underline":true}},{"insert":"AWS Management Console은 웹 기반 인터페이스로, AWS 리소스를 손쉽게 관리할 수 있습니다. AWS CLI(Command Line Interface)는 명령줄 도구로, 스크립트를 통해 AWS 서비스를 자동화할 수 있습니다. 이러한 도구들을 사용하여 클라우드 인프라를 효율적으로 관리하는 방법을 배워봅시다.\n\n"}]',
     300, 'HIDE', NOW(), NULL),

    (8, 'Board', 'REST API 설계',
     '[{"insert":"REST API를 설계하는 방법에 대해 설명하겠습니다. ","attributes":{"bold":true}},{"insert":"RESTful 원칙을 따르는 API 설계 방법을 설명하고, 효율적인 엔드포인트 구조와 데이터 모델링 방법을 공유합니다.\n\nAPI는 클라이언트와 서버 간의 데이터 교환을 위한 인터페이스입니다. REST는 Representational State Transfer의 약자로, HTTP를 기반으로 하는 아키텍처 스타일입니다.\n\n효율적인 API 설계를 위해 리소스 경로와 HTTP 메서드를 적절히 사용해야 합니다.\n\n","attributes":{"italic":true}},{"insert":"API 버저닝과 인증 방법에 대해서도 다룹니다.","attributes":{"underline":true}},{"insert":" 이를 통해 안정적이고 확장 가능한 API를 설계할 수 있습니다.\n\nREST API 설계 원칙을 준수하면 유지보수가 용이한 시스템을 구축할 수 있습니다. 예제 코드를 통해 실제로 REST API를 설계하고 구현하는 과정을 살펴봅시다.\n\n"}]',
     90, 'PUBLISHED', NOW(), NULL),

    (9, 'Board', 'TypeScript 소개',
     '[{"insert":"TypeScript의 기본 개념과 사용 방법에 대해 설명하겠습니다. ","attributes":{"bold":true}},{"insert":"TypeScript를 사용하여 타입 안전한 코드를 작성하는 방법을 설명하고, 주요 타입 시스템과 인터페이스, 클래스 사용 방법을 소개합니다.\n\nTypeScript는 JavaScript의 상위 집합으로, 정적 타입 검사를 지원합니다. 이를 통해 코드의 오류를 사전에 방지할 수 있습니다.\n\nTypeScript와 JavaScript의 차이점을 설명합니다. TypeScript를 사용하면 대규모 프로젝트에서 코드의 가독성과 유지보수성을 높일 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"이를 통해 개발 생산성을 향상시킬 수 있습니다. TypeScript를 사용한 개발 방법을 익혀보세요.\n\n","attributes":{"underline":true}},{"insert":"TypeScript는 인터페이스와 제네릭을 지원하여 복잡한 타입을 쉽게 정의할 수 있습니다. 또한, 클래스 기반 객체지향 프로그래밍을 지원하여, 더 구조적인 코드 작성을 도와줍니다. TypeScript의 강력한 타입 시스템을 활용하여 더 안전하고 견고한 코드를 작성해봅시다.\n\n"}]',
     180, 'PUBLISHED', NOW(), NULL),

    (1, 'Board', 'CI/CD 파이프라인',
     '[{"insert":"Continuous Integration 및 Continuous Deployment 파이프라인을 구축하는 방법에 대해 설명하겠습니다. ","attributes":{"bold":true}},{"insert":"Jenkins, Travis CI, GitHub Actions 등을 활용하여 자동화된 빌드 및 배포 환경을 구성하는 방법을 설명합니다.\n\nCI/CD는 소프트웨어 개발의 중요한 부분으로, 코드 변경 사항을 자동으로 빌드하고 테스트하며 배포하는 프로세스를 의미합니다.\n\n이를 통해 개발 속도를 높이고 품질을 향상시킬 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"CI/CD 파이프라인을 구축하여 효율적인 소프트웨어 개발을 경험해보세요.\n\n","attributes":{"underline":true}},{"insert":"자동화된 빌드 및 배포 환경을 통해 개발 효율성을 극대화할 수 있습니다. 예제 프로젝트를 통해 실제로 CI/CD 파이프라인을 구축하고 운영하는 과정을 실습해봅시다. 이를 통해 CI/CD의 개념을 이해하고, 실무에서어떻게 적용할 수 있는지 배워봅시다.\n\n"}]',
    70, 'PUBLISHED', NOW(), NULL),

    (2, 'Board', 'JavaScript 프레임워크 비교',
     '[{"insert":"JavaScript의 주요 프레임워크인 React, Angular, Vue.js를 비교해보겠습니다. ","attributes":{"bold":true}},{"insert":"각 프레임워크의 특징과 장단점을 설명하고, 어떤 상황에서 어떤 프레임워크를 선택해야 하는지에 대해 다룹니다.\n\nReact는 페이스북에서 개발한 라이브러리로, 컴포넌트 기반 아키텍처를 통해 재사용성을 높이고, 단방향 데이터 바인딩을 지원합니다.\n\nAngular는 구글에서 개발한 프레임워크로, 양방향 데이터 바인딩과 강력한 DI(Dependency Injection) 시스템을 제공하며, 대규모 애플리케이션에 적합합니다.\n\nVue.js는 비교적 가벼운 프레임워크로, 간단하고 빠르게 학습할 수 있으며, 단방향과 양방향 데이터 바인딩을 모두 지원합니다.\n\n","attributes":{"italic":true}},{"insert":"각 프레임워크의 주요 개념과 사용 예제를 통해 차이점을 알아봅시다.\n\n","attributes":{"underline":true}},{"insert":"React는 컴포넌트 기반 아키텍처를 통해 재사용성을 높이고, 단방향 데이터 바인딩을 지원합니다. Angular는 양방향 데이터 바인딩과 강력한 DI(Dependency Injection) 시스템을 제공하며, 대규모 애플리케이션에 적합합니다. Vue.js는 비교적 가벼운 프레임워크로, 간단하고 빠르게 학습할 수 있으며, 단방향과 양방향 데이터 바인딩을 모두 지원합니다.\n\n"}]',
     90, 'PUBLISHED', NOW(), NULL),

    (3, 'Board', 'Python 웹 크롤링',
     '[{"insert":"Python을 사용하여 웹 크롤링을 하는 방법을 설명합니다. ","attributes":{"bold":true}},{"insert":"BeautifulSoup과 Requests 라이브러리를 활용하여 웹 페이지에서 데이터를 추출하는 과정을 다룹니다.\n\n웹 크롤링은 특정 웹 페이지의 HTML 코드를 분석하여 필요한 데이터를 자동으로 수집하는 기술입니다. BeautifulSoup은 HTML 및 XML 파일을 파싱하여 데이터를 추출할 수 있는 라이브러리이며, Requests는 HTTP 요청을 보내고 응답을 받는 라이브러리입니다.\n\n","attributes":{"italic":true}},{"insert":"예제 코드를 통해 웹 크롤링의 기본 원리와 방법을 익히고, 실제로 데이터를 수집해보는 과정을 실습해봅시다.\n\n","attributes":{"underline":true}},{"insert":"웹 크롤링을 할 때 주의해야 할 사항과 법적 문제도 함께 다룹니다. 웹 사이트의 로봇 배제 표준(robots.txt)을 준수하고, 크롤링 빈도를 조절하여 서버에 과부하를 주지 않도록 해야 합니다. 웹 크롤링을 통해 수집한 데이터를 분석하고, 이를 활용하여 유의미한 인사이트를 도출하는 방법도 알아봅시다.\n\n"}]',
     100, 'PUBLISHED', NOW(), NULL),

    (4, 'Board', 'Python 웹 개발 프레임워크',
     '[{"insert":"Python에서 사용 가능한 주요 웹 개발 프레임워크인 Django와 Flask를 비교해보겠습니다. ","attributes":{"bold":true}},{"insert":"각 프레임워크의 특징과 장단점을 설명하고, 어떤 상황에서 어떤 프레임워크를 선택해야 하는지에 대해 다룹니다.\n\nDjango는 고수준의 Python 웹 프레임워크로, 배터리 포함(batteries-included) 철학을 바탕으로 많은 기본 기능을 제공합니다. ORM(Object-Relational Mapping), 인증 시스템, 관리자 인터페이스 등을 기본적으로 제공하여, 빠르고 효율적으로 웹 애플리케이션을 개발할 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"Flask는 경량의 웹 프레임워크로, 단순하고 유연한 구조를 제공합니다. Flask는 필요한 확장 기능만 선택하여 사용할 수 있어, 작은 프로젝트나 마이크로서비스에 적합합니다.\n\n","attributes":{"underline":true}},{"insert":"각 프레임워크의 주요 개념과 사용 예제를 통해 차이점을 알아봅시다.\n\nDjango는 고수준의 Python 웹 프레임워크로, 배터리 포함(batteries-included) 철학을 바탕으로 많은 기본 기능을 제공합니다. ORM(Object-Relational Mapping), 인증 시스템, 관리자 인터페이스 등을 기본적으로 제공하여, 빠르고 효율적으로 웹 애플리케이션을 개발할 수 있습니다. Flask는 경량의 웹 프레임워크로, 단순하고 유연한 구조를 제공합니다. Flask는 필요한 확장 기능만 선택하여 사용할 수 있어, 작은 프로젝트나 마이크로서비스에 적합합니다.\n\n"}]',
     80, 'DELETED', NOW(), NULL),

    (5, 'Board', 'JavaScript 모듈 시스템',
     '[{"insert":"JavaScript의 모듈 시스템에 대해 설명합니다. ","attributes":{"bold":true}},{"insert":"CommonJS, AMD, ES6 모듈 등 다양한 모듈 시스템의 특징과 사용 방법을 다룹니다.\n\n모듈 시스템은 코드의 재사용성과 유지보수성을 높이는 데 중요한 역할을 합니다. CommonJS는 주로 Node.js 환경에서 사용되며, require() 함수를 사용하여 모듈을 불러옵니다.\n\nAMD(Asynchronous Module Definition)는 비동기적으로 모듈을 로드할 수 있는 방식을 제공하며, 주로 브라우저 환경에서 사용됩니다. define() 함수를 사용하여 모듈을 정의하고, require() 함수를 사용하여 모듈을 불러옵니다.\n\n","attributes":{"italic":true}},{"insert":"ES6 모듈은 최신 JavaScript 표준에 포함된 모듈 시스템으로, import와 export 키워드를 사용하여 모듈을 정의하고 불러옵니다.\n\n","attributes":{"underline":true}},{"insert":"각 모듈 시스템의 장단점을 비교하고, 실제 예제 코드를 통해 사용 방법을 익혀봅시다. 모듈 시스템을 사용하여 코드를 더 구조적으로 작성하고, 유지보수성을 높이는 방법을 배워봅시다.\n\n"}]',
     150, 'PUBLISHED', NOW(), NULL),

    (6, 'Board', 'Docker와 Kubernetes',
     '[{"insert":"Docker와 Kubernetes를 사용하여 컨테이너화된 애플리케이션을 관리하는 방법을 설명합니다. ","attributes":{"bold":true}},{"insert":"Docker는 애플리케이션을 컨테이너라는 격리된 환경에서 실행할 수 있도록 도와주며, Kubernetes는 이러한 컨테이너를 오케스트레이션하는 도구입니다.\n\nDocker를 사용하여 애플리케이션의 이미지를 만들고, 이를 컨테이너로 실행하는 방법을 다룹니다. Docker Compose를 사용하여 멀티 컨테이너 애플리케이션을 관리하는 방법도 소개합니다.\n\n","attributes":{"italic":true}},{"insert":"Kubernetes는 컨테이너화된 애플리케이션의 배포, 확장, 관리 등을 자동화하는 도구로, 클러스터 환경에서 컨테이너를 효율적으로 관리할 수 있습니다.\n\n","attributes":{"underline":true}},{"insert":"예제 프로젝트를 통해 Docker와 Kubernetes의 기본 사용법을 익히고, 실제로 애플리케이션을 배포하고 관리하는 과정을 실습해봅시다. 이를 통해 컨테이너화된 애플리케이션의 장점을 이해하고, 실무에서 어떻게 활용할 수 있는지 배워봅시다.\n\n"}]',
     120, 'PUBLISHED', NOW(), NULL),

    (7, 'Board', 'AWS Lambda 활용법',
     '[{"insert":"AWS Lambda를 사용하여 서버리스 애플리케이션을 구축하는 방법을 설명합니다. ","attributes":{"bold":true}},{"insert":"Lambda는 서버를 관리하지 않고 코드를 실행할 수 있는 AWS의 서버리스 컴퓨팅 서비스입니다. 이벤트 기반으로 동작하며, 필요한 경우에만 코드를 실행하고, 사용한 만큼만 비용을 지불합니다.\n\nLambda 함수를 작성하고, 이를 다양한 AWS 서비스와 연동하여 서버리스 아키텍처를 구축하는 방법을 다룹니다. 예를 들어, S3 버킷에 파일이 업로드될 때 Lambda 함수를 트리거하여 특정 작업을 수행하거나, API Gateway와 연동하여 RESTful API를 제공할 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"또한, Lambda 함수를 관리하고 모니터링하는 방법도 소개합니다. AWS CloudWatch를 사용하여 Lambda 함수의 로그를 확인하고, 성능을 모니터링할 수 있습니다.\n\n","attributes":{"underline":true}},{"insert":"예제 프로젝트를 통해 AWS Lambda의 기본 사용법을 익히고, 실제로 서버리스 애플리케이션을 구축하는 과정을 실습해봅시다. 이를 통해 서버리스 아키텍처의 장점을 이해하고, 실무에서 어떻게 활용할 수 있는지 배워봅시다.\n\n"}]',
    300, 'HIDE', NOW(), NULL),

    (8, 'Board', 'REST API 보안',
     '[{"insert":"REST API의 보안 강화 방법에 대해 설명합니다. ","attributes":{"bold":true}},{"insert":"API 키, OAuth, JWT(Json Web Token) 등 다양한 인증 및 권한 부여 방법을 다룹니다. 또한, HTTPS를 사용하여 데이터 전송을 암호화하고, CORS(Cross-Origin Resource Sharing)를 설정하여 보안을 강화하는 방법도 설명합니다.\n\nAPI 키는 간단한 문자열로, 클라이언트가 API를 호출할 때 식별할 수 있게 해줍니다. OAuth는 토큰 기반 인증 시스템으로, 제3자 애플리케이션이 사용자 데이터를 접근할 수 있도록 허용합니다.\n\nJWT는 JSON 객체를 사용하여 클레임을 안전하게 전달하는 방법으로, 인증과 권한 부여에 널리 사용됩니다.\n\n","attributes":{"italic":true}},{"insert":"HTTPS를 사용하면 클라이언트와 서버 간의 통신을 암호화할 수 있습니다. 이를 통해 중간에 데이터가 도청되거나 변조되는 것을 방지할 수 있습니다.\n\n","attributes":{"underline":true}},{"insert":"CORS 설정을 통해 특정 도메인에서만 API에 접근할 수 있도록 제한할 수 있습니다. 이를 통해 API의 보안을 강화하고, 악의적인 공격을 방지할 수 있습니다. 예제 코드를 통해 각 방법을 실습하고, REST API의 보안을 강화하는 방법을 배워봅시다.\n\n"}]',
     90, 'PUBLISHED', NOW(), NULL),

    (9, 'Board', 'TypeScript와 React',
     '[{"insert":"TypeScript를 사용하여 React 애플리케이션을 개발하는 방법을 설명합니다. ","attributes":{"bold":true}},{"insert":"TypeScript는 정적 타입 검사를 제공하여, 코드의 안정성과 가독성을 높이는 데 도움이 됩니다. React와 TypeScript를 함께 사용하면, 컴포넌트의 props와 state에 타입을 정의하여, 코드의 오류를 사전에 방지할 수 있습니다.\n\nTypeScript와 React를 설정하는 방법, 주요 타입 시스템과 제네릭 사용법, 인터페이스와 클래스 사용 방법을 다룹니다. 예제 코드를 통해 TypeScript와 React의 통합을 실습해봅시다.\n\n","attributes":{"italic":true}},{"insert":"TypeScript와 React의 통합을 통해 대규모 프로젝트에서의 코드 유지보수성과 가독성을 높일 수 있습니다.\n\n","attributes":{"underline":true}},{"insert":"TypeScript는 인터페이스와 제네릭을 지원하여 복잡한 타입을 쉽게 정의할 수 있습니다. 또한, 클래스 기반 객체지향 프로그래밍을 지원하여, 더 구조적인 코드 작성을 도와줍니다. 예제 프로젝트를 통해 TypeScript와 React를 사용하여 실제 애플리케이션을 개발하는 과정을 실습해봅시다.\n\n"}]',
     180, 'PUBLISHED', NOW(), NULL),

    (1, 'Board', 'CI/CD 파이프라인 구축',
     '[{"insert":"Continuous Integration 및 Continuous Deployment 파이프라인을 구축하는 방법에 대해 설명하겠습니다. ","attributes":{"bold":true}},{"insert":"Jenkins, Travis CI, GitHub Actions 등을 활용하여 자동화된 빌드 및 배포 환경을 구성하는 방법을 설명합니다.\n\nCI/CD는 소프트웨어 개발의 중요한 부분으로, 코드 변경 사항을 자동으로 빌드하고 테스트하며 배포하는 프로세스를 의미합니다. 이를 통해 개발 속도를 높이고 품질을 향상시킬 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"CI/CD 파이프라인을 구축하여 효율적인 소프트웨어 개발을 경험해보세요.\n\n","attributes":{"underline":true}},{"insert":"자동화된 빌드 및 배포 환경을 통해 개발 효율성을 극대화할 수 있습니다. 예제 프로젝트를 통해 실제로 CI/CD 파이프라인을 구축하고 운영하는 과정을 실습해봅시다. 이를 통해 CI/CD의 개념을 이해하고, 실무에서 어떻게 적용할 수 있는지 배워봅시다.\n\n"}]',
     70, 'PUBLISHED', NOW(), NULL),

    (2, 'Board', 'JavaScript 성능 최적화',
     '[{"insert":"JavaScript 애플리케이션의 성능을 최적화하는 방법에 대해 설명합니다. ","attributes":{"bold":true}},{"insert":"코드 스플리팅, 지연 로딩, 메모리 관리 등의 기법을 다룹니다. 또한, 최신 브라우저의 성능 향상 기능을 활용하는 방법도 설명합니다.\n\n코드 스플리팅은 애플리케이션의 크기를 줄이고, 초기 로딩 속도를 향상시키는 방법입니다. Webpack과 같은 도구를 사용하여 애플리케이션을 여러 개의 청크로 나누고, 필요한 청크만 로드할 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"지연 로딩은 사용자가 필요로 하는 리소스만 로드하는 방법으로, 페이지 로딩 속도를 크게 향상시킬 수 있습니다.\n\n","attributes":{"underline":true}},{"insert":"메모리 관리는 불필요한 메모리 사용을 줄이고, 성능을 향상시키는 중요한 기법입니다. 가비지 컬렉션을 이해하고, 메모리 누수를 방지하는 방법을 배워봅시다. 최신 브라우저의 성능 향상 기능을 활용하여, JavaScript 애플리케이션의 성능을 최적화하는 방법을 알아봅시다.\n\n"}]',
     100, 'PUBLISHED', NOW(), NULL),

    (3, 'Board', 'Python 데이터 시각화',
     '[{"insert":"Python을 사용하여 데이터를 시각화하는 방법을 설명합니다. ","attributes":{"bold":true}},{"insert":"Matplotlib, Seaborn, Plotly 등의 라이브러리를 활용하여 다양한 유형의 차트와 그래프를 생성하는 방법을 다룹니다.\n\nMatplotlib는 가장 기본적인 데이터 시각화 라이브러리로, 다양한 유형의 그래프와 차트를 생성할 수 있습니다. Seaborn은 Matplotlib를 기반으로 한 고급 시각화 라이브러리로, 더욱 아름답고 복잡한 시각화를 손쉽게 만들 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"Plotly는 대화형 시각화를 지원하는 라이브러리로, 웹 애플리케이션에서 사용할 수 있습니다.\n\n","attributes":{"underline":true}},{"insert":"각 라이브러리의 주요 기능과 사용 예제를 통해 데이터를 시각화하는 방법을 익히고, 실제 프로젝트에서 어떻게 활용할 수 있는지 알아봅시다. 데이터를 시각적으로 표현하면 패턴과 트렌드를 쉽게 파악할 수 있으며, 이를 통해 더 나은 의사 결정을 내릴 수 있습니다.\n\n"}]',
     80, 'DELETED', NOW(), NULL),

    (4, 'Board', 'Python 데이터 전처리',
     '[{"insert":"Python을 사용하여 데이터를 전처리하는 방법을 설명합니다. ","attributes":{"bold":true}},{"insert":"Pandas 라이브러리를 활용하여 데이터 정제, 결측값 처리, 이상값 탐지 등의 기법을 다룹니다. 데이터 전처리는 데이터 분석의 중요한 단계로, 분석의 정확성과 신뢰성을 높이는 데 중요한 역할을 합니다.\n\nPandas는 데이터 조작 및 분석을 위한 강력한 도구로, 다양한 형식의 데이터를 쉽게 다룰 수 있습니다. 데이터 정제는 데이터의 일관성을 유지하고, 분석에 적합한 형태로 변환하는 과정입니다. 결측값 처리는 데이터셋에서 누락된 값을 처리하는 방법으로, 이를 통해 분석의 정확성을 높일 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"이상값 탐지는 데이터셋에서 이상치(Outlier)를 식별하고, 이를 처리하는 방법입니다.\n\n","attributes":{"underline":true}},{"insert":"예제 데이터를 통해 각 단계를 실습해보며, 데이터 전처리의 기본기를 다져봅시다. 데이터를 정제하고, 결측값을 처리하며, 이상값을 탐지하는 과정을 단계별로 따라가며, 각 단계에서 발생할 수 있는 문제와 그 해결 방법도 다룹니다. 이를 통해 데이터 전처리의 전반적인 과정을 이해하고, 실무에서 어떻게 적용할 수 있는지 배워봅시다.\n\n"}]',
     90, 'PUBLISHED', NOW(), NULL),

    (5, 'Board', 'JavaScript 테스트 자동화',
     '[{"insert":"JavaScript 애플리케이션의 테스트 자동화 방법에 대해 설명합니다. ","attributes":{"bold":true}},{"insert":"Jest, Mocha, Jasmine 등의 테스트 프레임워크를 활용하여 단위 테스트, 통합 테스트, E2E(End-to-End) 테스트를 수행하는 방법을 다룹니다.\n\n단위 테스트는 개별 모듈이나 함수의 동작을 검증하는 테스트로, 테스트의 가장 기본적인 형태입니다.통합 테스트는 여러 모듈이 함께 동작하는 방식을 검증하는 테스트로, 모듈 간의 상호작용을 확인할 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"E2E 테스트는 애플리케이션의 전체적인 흐름을 검증하는 테스트로, 사용자가 실제로 애플리케이션을 사용하는 방식으로 테스트를 수행합니다.\n\n","attributes":{"underline":true}},{"insert":"각 테스트 프레임워크의 특징과 사용 방법을 예제 코드를 통해 실습해봅시다. 이를 통해 애플리케이션의 품질을 높이고, 버그를 사전에 방지할 수 있습니다. 테스트 자동화를 통해 개발 속도를 높이고, 안정적인 소프트웨어를 개발하는 방법을 배워봅시다.\n\n"}]',
    150, 'PUBLISHED', NOW(), NULL),

    (6, 'Board', 'Docker와 CI/CD',
     '[{"insert":"Docker를 사용하여 CI/CD 파이프라인을 구축하는 방법을 설명합니다. ","attributes":{"bold":true}},{"insert":"Docker와 Jenkins, GitLab CI/CD, GitHub Actions 등을 통합하여 자동화된 빌드, 테스트, 배포 환경을 구성하는 방법을 다룹니다.\n\nDocker는 애플리케이션을 컨테이너라는 격리된 환경에서 실행할 수 있도록 도와주며, CI/CD는 코드 변경 사항을 자동으로 빌드하고 테스트하며 배포하는 프로세스를 의미합니다. 이를 통해 개발 속도를 높이고 품질을 향상시킬 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"예제 프로젝트를 통해 Docker와 CI/CD 도구를 사용하여 실제로 파이프라인을 구축하고 운영하는 과정을 실습해봅시다.\n\n","attributes":{"underline":true}},{"insert":"이를 통해 Docker와 CI/CD의 개념을 이해하고, 실무에서 어떻게 적용할 수 있는지 배워봅시다. Docker와 CI/CD를 통합하여 효율적인 소프트웨어 개발을 경험해보세요.\n\n"}]',
     120, 'PUBLISHED', NOW(), NULL),

    (7, 'Board', 'AWS EC2 설정',
     '[{"insert":"AWS EC2 인스턴스를 설정하고 관리하는 방법을 설명합니다. ","attributes":{"bold":true}},{"insert":"EC2 인스턴스 생성, 보안 그룹 설정, 키 페어 생성, SSH를 통한 접속 방법 등을 다룹니다. 또한, EC2 인스턴스의 스케일링 및 모니터링 방법도 소개합니다.\n\nEC2는 AWS에서 제공하는 가상 서버로, 다양한 워크로드를 지원합니다. 인스턴스를 생성하고, 보안 그룹을 설정하여 네트워크 접근을 제어할 수 있습니다. 키 페어를 생성하여 안전하게 인스턴스에 접속할 수 있으며, SSH를 통해 인스턴스에 접속하여 관리 작업을 수행할 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"EC2 인스턴스의 스케일링은 트래픽 증가에 따라 인스턴스 수를 자동으로 조절하는 방법으로, 오토 스케일링 그룹을 통해 설정할 수 있습니다.\n\n","attributes":{"underline":true}},{"insert":"모니터링은 CloudWatch를 사용하여 인스턴스의 성능을 실시간으로 확인하고, 문제 발생 시 알림을 받을 수 있습니다. 예제 프로젝트를 통해 EC2 인스턴스를 설정하고 관리하는 과정을 실습해봅시다.\n\n"}]',
     300, 'HIDE', NOW(), NULL),

    (8, 'Board', 'REST API 설계와 구현',
     '[{"insert":"REST API를 설계하고 구현하는 방법에 대해 설명합니다. ","attributes":{"bold":true}},{"insert":"RESTful 원칙을 따르는 API 설계 방법을 설명하고, 효율적인 엔드포인트 구조와 데이터 모델링 방법을 공유합니다. 또한, Node.js와 Express를 사용하여 REST API를 구현하는 방법을 다룹니다.\n\nAPI는 클라이언트와 서버 간의 데이터 교환을 위한 인터페이스입니다. REST는 Representational State Transfer의 약자로, HTTP를 기반으로 하는 아키텍처 스타일입니다. 효율적인 API 설계를 위해 리소스 경로와 HTTP 메서드를 적절히 사용해야 합니다.\n\n","attributes":{"italic":true}},{"insert":"API 버저닝과 인증 방법에 대해서도 다룹니다.","attributes":{"underline":true}},{"insert":" 이를 통해 안정적이고 확장 가능한 API를 설계할 수 있습니다.\n\nREST API 설계 원칙을 준수하면 유지보수가 용이한 시스템을 구축할 수 있습니다. 예제 코드를 통해 실제로 REST API를 설계하고 구현하는 과정을 살펴봅시다.\n\n"}]',
     90, 'PUBLISHED', NOW(), NULL),

    (9, 'Board', 'React 상태 관리',
     '[{"insert":"React에서 상태 관리를 효율적으로 하는 방법에 대해 설명하겠습니다. ","attributes":{"bold":true}},{"insert":"React는 컴포넌트 기반 라이브러리로, 각 컴포넌트는 자체적인 상태를 가질 수 있습니다. 상태 관리는 컴포넌트의 동작을 제어하고, 사용자 인터페이스를 동적으로 업데이트하는 데 중요한 역할을 합니다.\n\nuseState 훅을 사용하면 함수형 컴포넌트에서 상태를 관리할 수 있습니다. useReducer 훅은 복잡한 상태 로직을 관리하는 데 유용합니다. 리듀서는 현재 상태와 액션을 받아 새로운 상태를 반환하는 함수입니다.\n\n","attributes":{"italic":true}},{"insert":"React 컨텍스트 API를 사용하여 전역 상태를 관리하는 방법을 소개합니다.","attributes":{"underline":true}},{"insert":" 컨텍스트는 트리 구조의 하위 컴포넌트에 데이터를 전달하는 방법을 제공합니다.\n\n효율적인 상태 관리를 통해 React 애플리케이션을 더욱 효과적으로 개발할 수 있습니다.\n\n"}]',
     180, 'PUBLISHED', NOW(), NULL);

INSERT INTO board_tb (user_id, board_role, title, content, hit, status, created_at, updated_at)
VALUES
    (8, 'Board', 'REST API 설계 및 구현 방법과 실습',
     '[{"insert":"REST API를 설계하고 구현하는 방법에 대해 설명합니다. ","attributes":{"bold":true}},{"insert":"RESTful 원칙을 따르는 API 설계 방법을 설명하고, 효율적인 엔드포인트 구조와 데이터 모델링 방법을 공유합니다. 또한, Node.js와 Express를 사용하여 REST API를 구현하는 방법을 다룹니다.\n\nAPI는 클라이언트와 서버 간의 데이터 교환을 위한 인터페이스입니다. ","attributes":{"italic":true}},{"insert":"REST는 Representational State Transfer의 약자로, HTTP를 기반으로 하는 아키텍처 스타일입니다. 효율적인 API 설계를 위해 리소스 경로와 HTTP 메서드를 적절히 사용해야 합니다.\n\n","attributes":{"underline":true}},{"insert":"API 버저닝과 인증 방법에 대해서도 다룹니다.","attributes":{"bold":true}},{"insert":" 이를 통해 안정적이고 확장 가능한 API를 설계할 수 있습니다.\n\nREST API 설계 원칙을 준수하면 유지보수가 용이한 시스템을 구축할 수 있습니다. 예제 코드를 통해 실제로 REST API를 설계하고 구현하는 과정을 살펴봅시다.\n\nREST API 설계의 첫 번째 단계는 리소스를 정의하는 것입니다. 리소스는 URI로 식별되며, 각 리소스는 고유한 경로를 가져야 합니다. 예를 들어, 사용자 정보를 제공하는 API는 \"/users\" 경로를 사용할 수 있습니다. 각 리소스는 다양한 HTTP 메서드(GET, POST, PUT, DELETE)를 통해 접근할 수 있으며, 이를 통해 CRUD(Create, Read, Update, Delete) 작업을 수행할 수 있습니다.\n\n리소스 경로를 정의한 후에는 데이터 모델링을 수행해야 합니다. 데이터 모델링은 리소스의 속성과 관계를 정의하는 과정입니다. 예를 들어, 사용자 리소스는 이름, 이메일, 나이 등의 속성을 가질 수 있으며, 주문 리소스와의 관계를 정의할 수 있습니다. 데이터 모델링을 통해 API의 구조를 명확히 하고, 클라이언트와 서버 간의 데이터 교환을 원활하게 할 수 있습니다.\n\n","attributes":{"italic":true}},{"insert":"API의 버저닝은 변경 사항을 관리하는 중요한 방법입니다.","attributes":{"underline":true}},{"insert":" 예를 들어, 새로운 기능을 추가하거나 기존 기능을 변경할 때는 새로운 버전을 생성하여 클라이언트가 원하는 버전을 선택할 수 있도록 해야 합니다. API 버저닝은 URI 경로에 버전 번호를 추가하거나, HTTP 헤더를 통해 구현할 수 있습니다. 이를 통해 다양한 클라이언트가 안정적으로 API를 사용할 수 있습니다.\n\n인증과 권한 부여는 REST API의 보안을 강화하는 중요한 요소입니다. 인증은 클라이언트가 누구인지 확인하는 과정이며, 권한 부여는 클라이언트가 특정 작업을 수행할 권한이 있는지 확인하는 과정입니다. 대표적인 인증 방법으로는 API 키, OAuth, JWT(Json Web Token) 등이 있습니다. API 키는 간단한 문자열로, 클라이언트가 API를 호출할 때 식별할 수 있게 해줍니다. OAuth는 토큰 기반 인증 시스템으로, 제3자 애플리케이션이 사용자 데이터를 접근할 수 있도록 허용합니다. JWT는 JSON 객체를 사용하여 클레임을 안전하게 전달하는 방법으로, 인증과 권한 부여에 널리 사용됩니다.\n\nHTTPS를 사용하면 클라이언트와 서버 간의 통신을 암호화할 수 있습니다. 이를 통해 중간에 데이터가 도청되거나 변조되는 것을 방지할 수 있습니다. CORS(Cross-Origin Resource Sharing) 설정을 통해 특정 도메인에서만 API에 접근할 수 있도록 제한할 수 있습니다. 이를 통해 API의 보안을 강화하고, 악의적인 공격을 방지할 수 있습니다.\n\n","attributes":{"bold":true}},{"insert":"각 방법을 실습해보며, REST API의 보안을 강화하는 방법을 배워봅시다.","attributes":{"italic":true}},{"insert":"API 문서화는 클라이언트가 API를 이해하고 사용할 수 있도록 도와줍니다. Swagger와 같은 도구를 사용하여 API 문서를 자동으로 생성할 수 있습니다. 문서화된 API는 클라이언트가 요청을 보내고 응답을 받을 때 필요한 정보를 명확히 제공하며, API의 사용성을 높여줍니다.\n\n에러 처리는 REST API의 중요한 부분입니다. 클라이언트가 잘못된 요청을 보냈을 때, 서버는 적절한 에러 메시지를 반환해야 합니다. 일반적으로 4xx 상태 코드는 클라이언트 오류를 나타내고, 5xx 상태 코드는 서버 오류를 나타냅니다. 예를 들어, 잘못된 요청 형식은 400 Bad Request, 인증 실패는 401 Unauthorized, 서버 내부 오류는 500 Internal Server Error를 반환할 수 있습니다. 에러 메시지를 명확히 작성하여 클라이언트가 문제를 신속히 해결할 수 있도록 도와줍시다.\n\n","attributes":{"underline":true}},{"insert":"REST API의 성능 최적화도 중요한 요소입니다.","attributes":{"bold":true}},{"insert":" 캐싱을 사용하여 반복적인 요청에 대한 응답 시간을 줄일 수 있습니다. 클라이언트 측 캐싱은 응답 헤더에 캐싱 지시자를 포함하여, 클라이언트가 응답을 캐시하고 재사용할 수 있게 합니다. 서버 측 캐싱은 프록시 서버나 캐시 서버를 사용하여, 서버의 부하를 줄이고 응답 시간을 단축할 수 있습니다. 성능 최적화를 위해 쿼리 최적화, 데이터 압축, 로드 밸런싱 등의 기술을 사용할 수 있습니다.\n\n테스트 자동화는 REST API 개발 과정에서 필수적인 부분입니다. 유닛 테스트, 통합 테스트, E2E(End-to-End) 테스트를 통해 API의 기능과 성능을 검증할 수 있습니다. 유닛 테스트는 개별 함수나 메서드의 동작을 검증하며, 통합 테스트는 여러 모듈 간의 상호작용을 검증합니다. E2E 테스트는 실제 사용자 시나리오를 기반으로 API의 전체적인 동작을 검증합니다.\n\n","attributes":{"italic":true}},{"insert":"테스트 자동화를 통해 버그를 사전에 발견하고 수정할 수 있으며, 코드 변경에 따른 영향 범위를 최소화할 수 있습니다.","attributes":{"underline":true}},{"insert":" 테스트 도구로는 Postman, Newman, Jest, Mocha 등을 사용할 수 있습니다. Postman은 API 테스트를 위한 GUI 기반 도구로, 다양한 요청을 쉽게 생성하고 테스트할 수 있습니다. Newman은 Postman 컬렉션을 명령줄에서 실행할 수 있는 도구입니다. Jest와 Mocha는 JavaScript 테스트 프레임워크로, Node.js 환경에서 유닛 테스트와 통합 테스트를 수행할 수 있습니다.\n\nAPI의 로깅과 모니터링은 운영 중인 API의 상태를 지속적으로 파악하고, 문제 발생 시 신속히 대응할 수 있게 해줍니다. 로깅은 요청과 응답, 에러 등의 정보를 기록하여, API의 동작을 추적하고 분석하는 데 유용합니다. 모니터링은 API의 성능, 가용성, 에러 발생률 등을 실시간으로 확인할 수 있으며, 알림 설정을 통해 문제 발생 시 즉각 대응할 수 있습니다. 로깅 도구로는 Winston, Bunyan 등이 있으며, 모니터링 도구로는 Prometheus, Grafana, ELK 스택 등이 있습니다.\n\n","attributes":{"bold":true}},{"insert":"API의 지속적인 개선과 성능 최적화를 위해 로깅과 모니터링을 적극 활용합시다.","attributes":{"italic":true}},{"insert":"REST API의 버전 관리와 배포 전략도 중요한 요소입니다. 새로운 기능 추가나 기존 기능 수정 시, 기존 클라이언트가 영향을 받지 않도록 버전 관리를 철저히 해야 합니다. 이를 위해 여러 버전을 동시에 운영하거나, 점진적 배포를 통해 새로운 버전을 테스트할 수 있습니다. 버전 관리는 Git과 같은 버전 관리 도구를 사용하여 코드 변경 사항을 추적하고 관리할 수 있습니다. 배포 자동화는 CI/CD 도구를 사용하여, 코드 변경 사항이 자동으로 빌드, 테스트, 배포되도록 설정할 수 있습니다. 이를 통해 배포 과정의 실수를 줄이고, 배포 시간을 단축할 수 있습니다.\n\n"}]',
     90, 'PUBLISHED', NOW(), NULL);

    -- Reply 엔터티용 더미 데이터 생성
INSERT INTO reply_tb (user_id, board_id, comment, status, created_at, updated_at, board_role)
VALUES
    (1, 1, '이 게시글을 통해 많은 것을 배웠습니다.\n\n설명이 매우 명확하고 구체적이어서 이해하기 쉬웠습니다.\n\n앞으로도 좋은 정보 많이 공유해주시길 바랍니다.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (1, 1, '게시글 덕분에 많은 도움이 되었습니다.\n\n설명이 매우 자세하고 이해하기 쉽게 작성되어 있어 좋았습니다.\n\n앞으로도 좋은 정보 많이 공유해주시길 바랍니다.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (2, 2, '이 게시글의 주제가 매우 흥미롭습니다.\n\n앞으로도 더 많은 정보를 공유해주시기 바랍니다.\n\n다른 사람들이 이 게시글을 보고 많은 것을 배웠으면 좋겠습니다.', 'PUBLISHED', NOW(), NULL, 'Board'),
    (2, 2, '게시글의 내용이 이해하기 쉽지 않았습니다.\n\n일부 설명이 더 필요할 것 같습니다.\n\n특히 특정 기능의 동작 방식에 대해 더 자세히 설명해주시면 좋겠습니다.\n\n감사합니다.', 'HIDE', NOW(), NULL, 'Board'),
    (3, 3, '이 게시글을 통해 많은 것을 배웠습니다.\n\n특히 예제 코드가 큰 도움이 되었습니다.\n\n앞으로도 이런 유용한 정보가 담긴 게시글을 많이 작성해주시길 기대합니다.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (3, 3, '게시글에 설명된 내용 중 일부가 최신 정보와 다릅니다.\n\n최신 정보를 반영해 업데이트해주시면 더 많은 사람들이 유용하게 사용할 수 있을 것 같습니다.\n\n감사합니다.', 'DELETED', NOW(), NULL, 'Board'),
    (4, 4, '게시글을 통해 많은 정보를 얻었습니다.\n\n특히 실제 예제를 통해 이해할 수 있었던 부분이 좋았습니다.\n\n앞으로도 좋은 정보 많이 공유해주세요.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (4, 4, '내용이 매우 유익했습니다.\n\n특히 마지막 부분의 결론이 인상적이었습니다.\n\n다른 주제에 대해서도 이런 자세한 설명을 기대합니다.\n\n좋은 게시글 감사합니다.', 'PUBLISHED', NOW(), NULL, 'Board'),
    (5, 5, '이 게시글 덕분에 많은 도움이 되었습니다.\n\n설명이 매우 자세하고 이해하기 쉽게 작성되어 있어 좋았습니다.\n\n앞으로도 좋은 정보 많이 공유해주시길 바랍니다.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (5, 5, '게시글의 내용이 상당히 유익했습니다.\n\n특히 각 단계를 따라가며 실습할 수 있어서 좋았습니다.\n\n다른 주제에 대해서도 이런 유용한 게시글을 기대합니다.', 'HIDE', NOW(), NULL, 'Board'),
    (6, 6, '이 게시글을 통해 많은 것을 배웠습니다.\n\n설명이 매우 명확하고 구체적이어서 이해하기 쉬웠습니다.\n\n앞으로도 좋은 정보 많이 공유해주시길 바랍니다.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (6, 6, '내용이 매우 유익했습니다.\n\n특히 마지막 부분의 결론이 인상적이었습니다.\n\n다른 주제에 대해서도 이런 자세한 설명을 기대합니다.\n\n좋은 게시글 감사합니다.', 'DELETED', NOW(), NULL, 'Board'),
    (7, 7, '게시글을 통해 많은 정보를 얻었습니다.\n\n특히 실제 예제를 통해 이해할 수 있었던 부분이 좋았습니다.\n\n앞으로도 좋은 정보 많이 공유해주세요.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (7, 7, '내용이 매우 유익했습니다.\n\n특히 마지막 부분의 결론이 인상적이었습니다.\n\n다른 주제에 대해서도 이런 자세한 설명을 기대합니다.\n\n좋은 게시글 감사합니다.', 'PUBLISHED', NOW(), NULL, 'Board'),
    (8, 8, '이 게시글 덕분에 많은 도움이 되었습니다.\n\n설명이 매우 자세하고 이해하기 쉽게 작성되어 있어 좋았습니다.\n\n앞으로도 좋은 정보 많이 공유해주시길 바랍니다.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (8, 8, '게시글의 내용이 상당히 유익했습니다.\n\n특히 각 단계를 따라가며 실습할 수 있어서 좋았습니다.\n\n다른 주제에 대해서도 이런 유용한 게시글을 기대합니다.', 'HIDE', NOW(), NULL, 'Board'),
    (9, 9, '이 게시글을 통해 많은 것을 배웠습니다.\n\n설명이 매우 명확하고 구체적이어서 이해하기 쉬웠습니다.\n\n앞으로도 좋은 정보 많이 공유해주시길 바랍니다.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (9, 9, '내용이 매우 유익했습니다.\n\n특히 마지막 부분의 결론이 인상적이었습니다.\n\n다른 주제에 대해서도 이런 자세한 설명을 기대합니다.\n\n좋은 게시글 감사합니다.', 'PUBLISHED', NOW(), NULL, 'Board'),
    (10, 10, '게시글 덕분에 많은 도움이 되었습니다.\n\n설명이 매우 자세하고 이해하기 쉽게 작성되어 있어 좋았습니다.\n\n앞으로도 좋은 정보 많이 공유해주시길 바랍니다.\n\n감사합니다!', 'PUBLISHED', NOW(), NULL, 'Board'),
    (10, 10, '게시글의 내용이 상당히 유익했습니다.\n\n특히 각 단계를 따라가며 실습할 수 있어서 좋았습니다.\n\n다른 주제에 대해서도 이런 유용한 게시글을 기대합니다.', 'PUBLISHED', NOW(), NULL, 'Board');

-- Photo 엔터티용 더미 데이터 생성
INSERT INTO photo_tb (board_id, file_name, file_path)
VALUES (1, 'profile_basic.png', '/images/profile_basic.png'),
       (1, 'profile_basic.png', '/images/profile_basic.png'),
--        (2, 'profile_basic.png', '/images/profile_basic.png'),
       (3, 'profile_basic.png', '/images/profile_basic.png'),
       (4, 'profile_basic.png', '/images/profile_basic.png'),
       (5, 'profile_basic.png', '/images/profile_basic.png'),
       (6, 'profile_basic.png', '/images/profile_basic.png'),
       (7, 'profile_basic.png', '/images/profile_basic.png'),
       (8, 'profile_basic.png', '/images/profile_basic.png'),
       (9, 'profile_basic.png', '/images/profile_basic.png'),
       (10, 'profile_basic.png', '/images/profile_basic.png'),
       (11, 'profile_basic.png', '/images/profile_basic.png'),
       (12, 'profile_basic.png', '/images/profile_basic.png'),
       (13, 'profile_basic.png', '/images/profile_basic.png'),
       (14, 'profile_basic.png', '/images/profile_basic.png'),
       (15, 'profile_basic.png', '/images/profile_basic.png'),
       (16, 'profile_basic.png', '/images/profile_basic.png'),
       (17, 'profile_basic.png', '/images/profile_basic.png'),
       (18, 'profile_basic.png', '/images/profile_basic.png'),
       (19, 'profile_basic.png', '/images/profile_basic.png'),
       (20, 'profile_basic.png', '/images/profile_basic.png');

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
       (2, 7, NOW(), NULL, 'Board'),
       (3, 6, NOW(), NULL, 'Board'),
       (4, 8, NOW(), NULL, 'Board'),
       (5, 10, NOW(), NULL, 'Board'),
       (6, 12, NOW(), NULL, 'Board'),
       (7, 14, NOW(), NULL, 'Board'),
       (2, 16, NOW(), NULL, 'Board'),
       (9, 18, NOW(), NULL, 'Board'),
       (10, 20, NOW(), NULL, 'Board'),
       (7, 7, NOW(), NULL, 'Board');

-- 좋아요 테이블
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (1, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (2, 4, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (2, 7, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (2, 16, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (5, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (6, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (7, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (8, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (9, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (10, 1, 'Board', NOW());
INSERT INTO like_tb (user_id, board_id, board_role, created_at)
VALUES (7, 7, 'Board', NOW());