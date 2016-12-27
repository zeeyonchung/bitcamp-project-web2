# bitcamp-project-web2


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