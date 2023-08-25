package org.comstudy.myapp;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.myapp.model.Phone;
import org.comstudy.myapp.model.PhoneDAO;

@WebServlet({"/", "*.phone"})
public class PhoneBookApp extends HttpServlet {
//   public static ArrayList<Phone> phoneList = new ArrayList<Phone>();
//   static int phoneSeq = 1;
//   static {
//      phoneList.add(new Phone(phoneSeq++, "홍길동", "010-1111-1111"));
//      phoneList.add(new Phone(phoneSeq++, "김길동", "010-1111-2222"));
//      phoneList.add(new Phone(phoneSeq++, "박길동", "010-1111-3333"));
//   }
	private PhoneDAO dao = new PhoneDAO();
   
   protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setCharacterEncoding("UTF-8");
      req.setCharacterEncoding("UTF-8");
      int beginIndex = req.getContextPath().length();
      String endPoint = req.getRequestURI().substring(beginIndex);
      System.out.println("endPoint => " + endPoint);
         
      // 다음과 같이 구현 하시오. (Command를 제거 하고 그와 같은 기능을 제어문에 구현)
      String viewName = "";
      if("/list.phone".equals(endPoint)) {
         ArrayList<Phone> list =(ArrayList<Phone>)dao.selectAll();
         String searchName = req.getParameter("name");
         if(searchName != null && searchName.length() > 0 && !searchName.equals(" ")) {
            ArrayList<Phone> newList = new ArrayList<Phone>();
            for(Phone phone :list) {
               if(phone.getName().indexOf(searchName) != -1) {                  
                  newList.add(phone);
               }
            }
            list = newList;
         }
         req.setAttribute("list", list);
         viewName = "list";
      } 
      if("/input.phone".equals(endPoint)) {
         // GET 요청 처리
         viewName = "input";
         
         if("POST".equals(req.getMethod())) {
            // POST 요청 처리
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            dao.insert(new Phone(name, phone));
            viewName = "redirect:list.phone";
         } 
      }
      if("/delete.phone".equals(endPoint)) {
         // GET 요청 처리
         int no = Integer.parseInt(req.getParameter("no"));
        // 삭제 페이지로 이동 
         req.setAttribute("phone", dao.selectOne(new Phone(no)));
         
         viewName = "delete";
         
         if("POST".equals(req.getMethod())) {
        	dao.delete(new Phone(no));
            viewName = "redirect:list.phone";
         }
      }
      if("/modify.phone".equals(endPoint)) {
         int no = Integer.parseInt(req.getParameter("no"));
         req.setAttribute("phone", dao.selectOne(new Phone(no)));
         viewName = "modify";
         
         if("POST".equals(req.getMethod())) {
            // POST 요청 처리
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            dao.update(new Phone(no, name, phone));
            viewName = "redirect:list.phone";
         } 
      }
      
      if(viewName.indexOf("redirect:") == 0) {
         int startIdx = "redirect:".length();
         resp.sendRedirect(viewName.substring(startIdx));
      } else {
         viewName = "/WEB-INF/views/" + viewName + ".jsp";
         RequestDispatcher view = req.getRequestDispatcher(viewName);
         view.forward(req, resp);
      }
      
   } // end of process

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         process(req, resp);
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      process(req, resp);
   }
}