package example.day35_day01_0825;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 1. 해당 컨트롤러에게 HTTP(웹기술) 적용 <--- 서블릿 필요(톰캣포함 Spring Boot 내장)
// 2. 서블릿에게 상속받기( 해당 클래스로부터 멤버변수/메소드 물려받기 ), extends HttpServlet 
// 3. 물려받은 기능(init, serivce, destory) 재정의 --> 오버라이딩
// 4. HTTP doXXX 메소드 오버라이딩(재정의)하여 기능 구현 --> 컨트롤러내 비지니스 로직 구현 (컨트롤러 역할) 
// 5. 해당 컨트롤러에 HTTP 주소 등록, @WebServlet("주소정의")
@WebServlet("/example/day35_day01_0825")
public class BoardController extends HttpServlet {
    // [1] 서블릿이 최초 실행 된 경우 딱 1번 실행되는 메소드
    @Override
    public void init() throws ServletException { 
        // 초기값, DB 연동 등등
        super.init(); 
    }
    // [2] 서블릿이 생성되고 요청마다 (스레드풀에서 스레드할당 받아) 실행되는 메소드
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // 활용 : 요청한 사람 IP 조회하여 기능 제한 등등
        super.service(req, res);
    }
    // [3] 서블릿이 사라질 때(서버 종료될 때) 1번 실행되는 메소드, 외부 연동해제 
    @Override
    public void destroy() { super.destroy(); }
    // ****************HTTP Method CRUD**********************

    // [4-1] doGet : HTTP 요총이 GET 이면
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    // [4-2] doPost : HTTP 요청이 post 이면
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }    
    // [4-3] dpPut : HTTP 요청이 put 이면
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
    // [4-4] doDelet : HTTP 요청이 delet 이면
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
