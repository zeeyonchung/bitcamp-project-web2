# bitcamp-project-web2

### v3.1 - 프로트엔트 정리
- HTML, 자바스크립트, CSS 분리
- 자주 쓰는 함수를 라이브러리로 만든다.

### v3.0 - AJAX 

### v2.9 - 클라이언트에서 JSP 직접 접근 못하게 막기
- JSP는 WEB-INF 아래로 감춘다.

### v2.8 - Log4J 적용

### v2.7 - 요청 파라미터의 커스텀 에디터 등록하기

### v2.6 - 트랜잭션 처리

### v2.5 - 페이지 컨트롤러에서 비즈니스 로직 분리하여 Service 객체 만들기

### v2.4 - 로그인 여부 검사하기
- 방법1) 서블릿 필터 이용
    - LoginCherckFilter
    - 로그인을 수행하는 URL을 제외하고 나머지 모든 URL에 대해서는 로그인 여부를 검사
- 방법2) 스프링의 인터셉터 이용

### v2.3 - Persistence Framework인 Mybatis 적용하기

### v2.2 - 페이지 컨트롤러에서 요청 핸들러의 파라미터 값 적용

### v2.1 - DispatcherServlet을 스프링의 프론트 컨트롤러로 대체하기

### v2.0 - 애노테이션을 적용하여 페이지 컨트롤러에서 호출될 메서드를 지정하기

### v1.9 - 스프링 필터, 리스너 사용

### v1.8 - 사이드메뉴바 추가

### v1.7 - 화면의 틀을 공유하여 공통 코드 작성 줄이기

### v1.6 - 프론트 컨트롤러 패턴 적용
- 모든 클라이언트의 요청을 한 서블릿에서 받는다.
  그리고 요청에 따라 작업할 객체에 실행을 위임한다.
- DispatcherServlet 클래스 작성
- 프론트 컨트롤러와 페이지 컨트롤러의 호출 규칙 정의
  - PageController 인터페이스 생성
- 나머지 서블릿들은 일반 POJO 객체로 만든다.
- AuthFilter 이용하여 로그인 사용자의 사진 파일명을 준비한다.


### v1.5 - JSP를 사용하여 화면 출력 부분 분리
- MVC 구조 완성

### v1.4 - 스프링 IoC 컨테이너 적용
- 스프링 라이브러리 파일 준비
  -  build.gradle 파일에 스프링 의존 라이브러리 추가
  - 이클립스 설정 파일 갱신
- 스프링 IoC 컨테이너가 사용할 설정 파일 준비
  - /WEB-INF/conf/application-context.xml
- ContextLoaderListener 클래스 변경
  - 스프링 IoC 컨테이너 생성
- DAO 또는 DataSource 객체에 @Component 애노테이션을 붙인다.
  - 스프링 IoC 컨테이너는 이런 애노테이션이 붙은 객체를 관리해준다.
- Servlet 클래스 변경
  - 스프링 IoC 컨테이너를 통해 DAO 객체를 얻어야 한다.

### v1.3 - 로그인한 사용자의 유형 지정 및 로그인 사용자의 사진 출력하기

### v1.2 - 학생 등록시 사진 업로드 하기

### v1.0 - 쿠키를 이용하여 로그인 아이디 저장하기, 로그인 처리
- 로그인 폼 생성
  - LoginFormServlet.java 생성
- MemberDao와 MemberMysqlDao 변경 - exist(email,password) 메서드 추가

### v0.9 - Listener 컴포넌트를 사용하여 웹애플리케이션에서 사용할 도구 준비하기
- 웹 애플리케이션이 시작되면 서블릿들이 공동으로 사용할 객체를 준비시킨다.
- ServletContextListener 구현체 만들기
- 즉 ContextLoaderListener 클래스 정의
  이 클래스는 기존의 ContextLoaderServlet의 역할 대체

### v0.8 - Filter 컴포넌트를 사용하여 POST 요청 데이터의 문자집합을 자동으로 설정한다.
- CharacterEncodingFilter 클래스 정의

### v0.7 - ServletContect 보관소 기능을 사용하여 DAO 공유하기
- 기존의 DAO 클래스에서 Singleton 패턴을 제거한다.
- 다른 서블릿이 사용할 DAO 객체를 준비시키는 서블릿을 만든다.
- DAO 클래스의 인터페이스를 정의한다.