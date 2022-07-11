package iducs.jsp.b.kskbblog.controller;

import iducs.jsp.b.kskbblog.model.Blog;
import iducs.jsp.b.kskbblog.model.Blogger;
import iducs.jsp.b.kskbblog.repository.BloggerDAOImpl;
import iducs.jsp.b.kskbblog.util.DescByBlogTitle;
import iducs.jsp.b.kskbblog.util.DescByBloggerName;
import iducs.jsp.b.kskbblog.util.Pagination;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "members", urlPatterns = {"/blogger/post-form.do", "/blogger/post.do",
        "/blogger/list.do", "/blogger/sort.do",
        "/blogger/detail.do", "/blogger/update-form.do", "/blogger/update.do", "/blogger/delete.do" ,
        "/blogger/login-form.do", "/blogger/login.do", "/blogger/logout.do"})
public class BloggerController extends HttpServlet{
    // DAO or Repository 객체 생성
    BloggerDAOImpl dao = new BloggerDAOImpl();

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); //session 객체 생성, 서블릿에서는 session 객체가 기본 객체가 아님
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command =uri.substring(contextPath.length()+1); //blogs/post.do, blogs/get.do가 반환됨
        String action = command.substring(command.lastIndexOf("/")+1); //post.do , list.do 반환

        if(action.equals("post-form.do")) {
            request.getRequestDispatcher("blogger-post-form.jsp").forward(request, response);
        }
        else if(action.equals("post.do")) {
            Blogger blogger = new Blogger();

            blogger.setEmail(request.getParameter("email"));
            blogger.setPw(request.getParameter("pw"));
            blogger.setName(request.getParameter("name"));
            blogger.setPhone(request.getParameter("phone"));
            blogger.setAddress(request.getParameter("address"));

            if (dao.create(blogger) > 0) {
                request.setAttribute("blogger", blogger);
                request.setAttribute("msg", "성공 : 회원 등록을 성공하였습니다.");
                // 처리 결과를 view에 전달한다.
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "실패 : 회원 등록을 실패하였습니다.");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }
        }
        if(action.equals("login-form.do")) {
            request.getRequestDispatcher("blogger-login-form.jsp").forward(request, response);
        }
        else if(action.equals("login.do")) {
            Blogger blogger = new Blogger();

            String bloggerEmail = request.getParameter("email");
            session.setAttribute("bloggerEmail", bloggerEmail);

            blogger.setEmail(request.getParameter("email"));
            blogger.setPw(request.getParameter("pw"));
            Blogger retBlogger = null;
            if ((retBlogger = dao.read(blogger)) != null) {
                session.setAttribute("logined", retBlogger); // session 처리
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "실패 : 회원 로그인 실패하였습니다.");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }
        }
        else if(action.equals("logout.do")) {
            session.invalidate(); // session 객체를 무효화 함. 즉 logined 속성값이 메모리에서 사라지게 됨
            request.getRequestDispatcher("../main/index.jsp").forward(request, response);
        }
        else if(action.equals("list.do")) {
            //ArrayList<Blogger> bloggerList = new ArrayList<Blogger>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
            ArrayList<Blogger> bloggerList = new ArrayList<Blogger>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
            String pageNo = request.getParameter("pn"); // 매개변수로 전달된 현재 페이지 번호가 정수현으로 저장

            int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
            int perPage = 3; // 한 페이지에 나타나는 행의 수
            int perPagination = 3; // 한 화면에 나타나는 페이지 번호 수

            int totalRows = dao.readTotalRows(); // dao에서 총 행의 수를 질의함
            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);

            if((bloggerList = (ArrayList<Blogger>) dao.readListPagination(pagination)) != null) { // 한 개 이상의 블로그가 반환. JCF(Java Collection Framework)에 대한 이해
                request.setAttribute("bloggerList", bloggerList);
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("blogger-list-view.jsp").forward(request, response); // members/blogger-list-view.jsp로 포워딩
            } else {
                request.setAttribute("msg", "실패 : 회원 목록 조회 실패하였습니다.");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);; // 오류
            }
        }else if (action.equals("detail.do")) {
            Blogger blogger = new Blogger();
            String strId = request.getParameter("id");
            long id = Long.parseLong(strId);
            blogger.setId(id);
            Blogger retBlogger = null;
            if ((retBlogger = dao.readBlogger(blogger)) != null) {
                request.setAttribute("blogger", retBlogger);
                request.getRequestDispatcher("blogger-detail.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "블로그 멤버 조회 실패");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }

        }else if(action.equals("update-form.do")) { // update를 위한 정보 조회후 view에게 전달송
            Blogger blogger = new Blogger();
            String strId = request.getParameter("id");
            long id = Long.parseLong(strId);
            blogger.setId(id);
            Blogger retBlogger = null;
            if((retBlogger = dao.readBlogger(blogger)) != null) {
                request.setAttribute("blogger", retBlogger); // key -> blog
                request.getRequestDispatcher("blogger-update.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "블로그 멤버 업데이트를 위한 조회 실패");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);; // 오류
            }
        }else if(action.equals("update.do")) { // dao에게 업데이트를 요청
            Blogger blogger = new Blogger();
            blogger.setId(Long.parseLong(request.getParameter("id")));
            blogger.setName(request.getParameter("name"));
            blogger.setEmail(request.getParameter("email"));
            blogger.setAddress(request.getParameter("address"));
            blogger.setPhone(request.getParameter("phone"));

            if (dao.update(blogger) > 0) {
                request.setAttribute("blogger", blogger);
                request.setAttribute("msg", "블로그 멤버 업데이트 성공");
                // 처리 결과를 view에 전달한다. about.jsp -> processok.jsp
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "블로그 멤버 업데이트 실패");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }
        } else if(action.equals("delete.do")){
            Blogger blogger = new Blogger();
            blogger.setId(Long.parseLong(request.getParameter("id")));

            if (dao.delete(blogger) > 0) {
                request.setAttribute("blogger", blogger);
                request.setAttribute("msg", "블로그 멤버 삭제 성공");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "블로그 멤버 삭제 실패");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);

            }
        }
        else if(action.equals("sort.do")){
            ArrayList<Blogger> bloggerList = new ArrayList<Blogger>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
            String properties = request.getParameter("by");
            if((bloggerList = (ArrayList<Blogger>) dao.readList()) != null) {
                if(properties.equals("desc,name"))
                    Collections.sort(bloggerList, new DescByBloggerName()); // 블로그 제목 기준 내림차순
                request.setAttribute("bloggerList", bloggerList);
                request.getRequestDispatcher("blogger-list-view.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "블로그 멤버 삭제 실패");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doService(request, response);
    }
}
