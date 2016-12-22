# bitcamp-project-web2

### v0.8 - Filter 컴포넌트를 사용하여 POST 요청 데이터의 문자집합을 자동으로 설정한다.
- CharacterEncodingFilter 클래스 정의

### v0.7 - ServletContect 보관소 기능을 사용하여 DAO 공유하기
- 기존의 DAO 클래스에서 Singleton 패턴을 제거한다.
- 다른 서블릿이 사용할 DAO 객체를 준비시키는 서블릿을 만든다.
- DAO 클래스의 인터페이스를 정의한다.