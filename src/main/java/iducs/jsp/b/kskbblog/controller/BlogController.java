package iducs.jsp.b.kskbblog.controller;

import iducs.jsp.b.kskbblog.model.Blog;
import iducs.jsp.b.kskbblog.repository.BlogDAOlmpl;
import iducs.jsp.b.kskbblog.util.DescByBlogTitle;
import iducs.jsp.b.kskbblog.util.Pagination;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/*WebServlet value 값은 URL 또는 URI 연관있음. 서블릿 클래스 이름은 내부적으로 객체들간의 상호작용에 작용함*/
@WebServlet(name = "post", urlPatterns ={"/blogs/postForm.do", "/blogs/post.do",
        "/blogs/list.do", "/blogs/sort.do", "/main/index.do",
        "/blogs/detail.do", "/blogs/updateForm.do", "/blogs/update.do", "/blogs/delete.do"}) //URL pattern : blogs/blogs/post.do
public class BlogController extends HttpServlet {
    BlogDAOlmpl dao = new BlogDAOlmpl();
    public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1);
        String action = command.substring(command.lastIndexOf("/") + 1);

        if(action.equals("post.do")) {
            Blog blog = new Blog();
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if (dao.create(blog) > 0) {
                request.setAttribute("blog", blog);
                // 컨트롤러가 view에게 전달해서 처리함
                // msg라는 애트리뷰트 이름으로 message + "반갑습니다." 라는 애트리뷰트 값을 request객체에 설정
                //forward는 request, response 객체를 전달, URL 변경되지 않음 vs sendRedirect()
                request.getRequestDispatcher("../test/about.jsp").forward(request, response);
            } else {

                request.getRequestDispatcher("../test/error.jsp").forward(request, response);
            }
        } else if(action.equals("list.do")){
            ArrayList<Blog> blogList = new ArrayList<Blog>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
            String pageNo = request.getParameter("pn"); // 매개변수로 전달된 현재 페이지 번호가 정수현으로 저장
            int curPageNo = (pageNo != null)? Integer.parseInt(pageNo):1;
            int perPage = 3; // 한 페이지에 나타나는 행의 수
            int perPagination = 3; // 한 화면에 나타나는 페이지 번호 수

            int totalRows = dao.readTotalRows(); // dao에서 총 행의 수를 질의함

            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);
            if((blogList = (ArrayList<Blog>) dao.readListPagination(pagination)) != null) { // 한 개 이상의 블로그가 반환. JCF(Java Collection Framework)에 대한 이해
                request.setAttribute("blogList", blogList);
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("list.jsp").forward(request, response); // blogs/list.jsp로 포워딩
            } else {
                request.setAttribute("msg", "실패 : 블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);; // 오류
            }
        }
        else if(action.equals("sort.do")){
            ArrayList<Blog> blogList = new ArrayList<Blog>(); // 처리결과 한개 이상의 블로그를 저장하는 객체
            String properties = request.getParameter("by");
            if((blogList = (ArrayList<Blog>) dao.readList()) != null) {
                if(properties.equals("desc,title"))
                    Collections.sort(blogList, new DescByBlogTitle()); // 블로그 제목 기준 내림차순
                request.setAttribute("blogList", blogList);
                request.getRequestDispatcher("list.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        else if (action.equals("detail.do")) {
            Blog blog = new Blog();
            String strId = request.getParameter("id");
            long id = Long.parseLong(strId);
            blog.setId(id);
            Blog retBlog = null;
            if ((retBlog = dao.read(blog)) != null) {

                request.setAttribute("blog", retBlog);
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 조회 실패");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }  else if(action.equals("updateForm.do")) { // update를 위한 정보 조회후 view에게 전달송
            Blog blog = new Blog();
            String strId = request.getParameter("id");
            long id = Long.parseLong(strId);
            blog.setId(id);
            Blog retBlog = null;
            if((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog); // key -> blog
                request.getRequestDispatcher("updateForm.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 업데이트를 위한 조회 실패");
                request.getRequestDispatcher("../test/error.jsp").forward(request, response);; // 오류
            }
        } else if(action.equals("update.do")) { // dao에게 업데이트를 요청
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if (dao.update(blog) > 0) {
                request.setAttribute("blog", blog);
                // 처리 결과를 view에 전달한다. about.jsp -> processok.jsp
                request.getRequestDispatcher("../test/about.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../test/error.jsp").forward(request, response);
            }
        } else if
        (action.equals("delete.do")) {
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));

            if (dao.delete(blog) > 0) {
                request.setAttribute("blog", blog);
                request.setAttribute("work", "블로그 삭제");
                request.getRequestDispatcher("../test/about.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../test/error.jsp").forward(request, response);


            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
        //처리된 결과를 매트리뷰트로 설정한다.
        //request.setAttribute("name",blog.getName());
        //request.setAttribute("email",blog.getEmail());
        //blog.setPhone(request.getParameter("phone"));
        //blog.setMessage(request.getParameter("message"));
        //request.setAttribute("blog",blog);

        // 컨트롤러가 뷰에게 전달해서 처리함
        //msg라는 애트리뷰트 이름으로 message +"반갑습니다." 라는 애트리뷰트 값을 request객체에 설정
        //request.setAttribute("msg",message+"명령실행.");
        //forward는 request,response 객체를 전달, URL 변경되지 않음 vs sendRedirect()
        //처리 결과를 view에 전달한다.
        //request.getRequestDispatcher("about.jsp").forward(request,response);

          /*
        String inputName = request.getParameter("name");
        String inputEmail = request.getParameter("email");
        String phone = request.getParameter("phone");

        Blog blog = new Blog();
        blog.setName(inputName);
        blog.setEmail(inputEmail);
        blog.setPhone(phone);
        blog.setMessage(request.getParameter("message"));

        request.setAttribute("list", blog);

        request.getRequestDispatcher("postView.jsp").forward(request, response);
        */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }
}