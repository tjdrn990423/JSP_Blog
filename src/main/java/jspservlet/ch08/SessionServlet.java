package jspservlet.ch08;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet({ "/ch08/sessionLogin.do", "/ch08/sessionLogout.do" })
public class SessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SessionServlet() {
        super();
    }
    Member registeredUser = null; // 등록된 사용자
    public void init(ServletConfig config) throws ServletException {
        registeredUser = new Member();
        registeredUser.setId("dream@induk.ac.kr");
        registeredUser.setPw("cometrue");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(); // 있는 경우 반환, 없는 경우 만들어서 반환
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        if(action.equals("sessionLogin.do")) {
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            Member m = new Member();
            m.setId(id);
            m.setPw(pw);
            if(id.equals(registeredUser.getId()) && pw.equals(registeredUser.getPw())) {
                session.setAttribute("login", m);
                request.getRequestDispatcher("session-login-form.jsp").forward(request, response);
            }
            else {
                response.sendRedirect("session-login-form.jsp");
            }
        }
        else if(action.equals("sessionLogout.do")) {
            session.invalidate();
            response.sendRedirect("session-login-form.jsp");
        }
        else
            ;
    }
}

