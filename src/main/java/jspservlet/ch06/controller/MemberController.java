package jspservlet.ch06.controller;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import jspservlet.ch06.domain.*;

@WebServlet(urlPatterns = "/ch06/member-controller", name = "BloggerController")
public class MemberController extends HttpServlet {
    ArrayList<Member> memberList = new ArrayList<Member>();

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto -generated method stub
        for (int i = 1; i <= 10; i++) {
            // 주소 객체 생성
            Address address = new Address();
            address.setSeq(i);
            address.setZipcode(12345 + i);
            address.setSido("서울");
            address.setBldg(i);
            // 멤버 객체 생성 후 주소 배정
            Member member = new Member();
            member.setSeq(i);
            member.setEmail("user" + i + "@" + "induk.ac.kr");
            member.setPw("pw" + i);
            member.setName("user" + i);
            member.setAddress(address);
            // 멤버 리스트에 멤버 추가
            memberList.add(member);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Member searched = null;
        String name = request.getParameter("name");
        for (Member m : memberList) {
            if (name.equals(m.getName()))
                searched = m;
        }
        if (searched != null) {
            request.setAttribute("member", searched);
            request.getRequestDispatcher("address-table.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("not-found.jsp").forward(request, response);
        }
    }
}

