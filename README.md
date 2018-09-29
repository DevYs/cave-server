# Cave Server

Cave Server는 구글에서 서비스 중인 Google Photo를 활용한 영화 감상을 위한 웹 애플리케이션입니다.

### 개발 및 환경
 * Java 1.8
 * Spring Boot 2.0.2 Release 
 * Thymeleaf 3
 * Embedded Tomcat 8
 * Bootstrap 3.3.2
 * JQuery 1.11.2
 * Berkeley DB 7.5.11

### 다운로드
##### - Widnows
* [Windows x64](https://drive.google.com/open?id=19pGMEOfaW32PBcvqR-1TZAB4U4Z_pNDb)
* [Windows x86](https://drive.google.com/open?id=1BGVf1rOBw3nnsSden7bfDli7QICee9Rz)

##### - Linux
* [Linux x64](https://drive.google.com/open?id=1vGVh-dBAadwfu6rJPvVG1UUsiP6M4qsa)
* [Linux i586](https://drive.google.com/open?id=1Nicl-CgQMtXKpdsT9gfieKTOCkVS1nl3)

##### - Mac OS X
* [Mac OS X x64](https://drive.google.com/open?id=1CoWOkx0l6dCLNlS5tGKSaM6m1xXAeH9e)

***

### 설치
- Windows
1. Windows x64 또는 Windows x86 다운로드 후 원하는 위치에 압축 해제
2. 압축을 해제한 디렉터리로 이동
3. **start.bat** 실행
4. 웹 브라우저를 실행시킵니다.
5. 주소창에 **http://localhost:8080/**을 입력합니다.
6. 관리 계정은 **admin**이며 초기 비밀번호는 **0000**입니다.

- Linux
1. Linux x64 또는 Windows i586 다운로드 후 원하는 위치에 압축 해제
2. 터미널 실행 후 압축을 해제한 디렉터리로 이동
3. **./start.sh** 입력 후 Enter
4. 웹 브라우저를 실행시킵니다.
5. 주소창에 **http://localhost:8080/**을 입력합니다.
6. 관리 계정은 **admin**이며 초기 비밀번호는 **0000**입니다.

- Mac OS X
1. Mac OS X x64 다운로드 후 원하는 위치에 압축 해제
2. 터미널 실행 후 압축을 해제한 디렉터리로 이동
3. **./start.sh** 입력 후 Enter
4. 웹 브라우저를 실행시킵니다.
5. 주소창에 **http://localhost:8080/**을 입력합니다.
6. 관리 계정은 **admin**이며 초기 비밀번호는 **0000**입니다.

***

### 설정 
##### 사용자와 관리자 모두 비밀번호를 변경할 수 있습니다.
##### 포트는 관리자만 변경할 수 있습니다. 
##### 포트를 변경한 후에는 Cave Server를 재시작해야합니다.

***

### 관리
##### 관리메뉴는 관리자만 접근할 수 있습니다.

- 사용자관리
##### 사용자를 **추가/수정/삭제** 합니다.
##### 사용자를 관리자로 추가합니다.
##### 사용자는 기본적으로 콘텐츠의 감상이 가능합니다.
##### 사용자에게 관리자 권한을 부여하면 사용자/채널/콘텐츠 등의 관리가 가능합니다.

- 채널관리
##### 채널을 **추가/수정/삭제** 합니다.
##### 채널은 콘텐츠의 메뉴를 의미합니다. 한국영화, 일본영화 등의 국가별로 분류가 될 수 있으며 또는 액션, 스릴러 등의 장르로 채널이 분류될 수 있습니다. 

- 콘텐츠관리
##### 콘텐츠를 **추가/수정/삭제** 합니다.
##### 채널 하위의 콘텐츠를 의미합니다. '명량'이라는 콘텐츠를 등록할 경우 한국영화 채널 또는 전쟁 채널, 액션 채널 등의 하위의 콘텐츠가 될 수 있습니다.
##### 해당 콘텐츠의 영상을 추가/수정/삭제 합니다.
##### 영상관리에서는 영상 제목과 필요할 경우 smi, srt, vtt 등의 자막을 추가할 수 있습니다.

### Berkeley DB 백업
##### Cave Server는 Berkeley DB라는 경량 임베디드 데이터베이스를 사용합니다. 
##### 데이터가 저장되는 DB파일은 **bdb** 디렉터리에 저장됩니다.
##### 사용자, 채널, 콘텐츠, 영상의 데이터가 저장됩니다.
##### bdb 디렉터리만 복사하면 백업이 완료됩니다. 
