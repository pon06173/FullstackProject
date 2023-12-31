package org.comstudy.myapp.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

interface Command {
   String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}

class HelloCommand implements Command {
   public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      return "hello";
   }
}

class HomeCommand implements Command {
   public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      return "home";
   }
}

public class DispatcherServlet extends HttpServlet {
   
   public static HashMap<String, Command> cmdMap = new HashMap<String, Command>();
   
   @Override
   public void init() throws ServletException {
      cmdMap.put("/hello.do", new HelloCommand());
      cmdMap.put("/home.do", new HomeCommand());
   }

   protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setCharacterEncoding("UTF-8");
      req.setCharacterEncoding("UTF-8");
      resp.setContentType("text/html; charset=UTF-8");
      //System.out.println("DispatcherServlet - " + req.getMethod());
      
      int beginIndex = req.getContextPath().length();
      String endPoint = req.getRequestURI().substring(beginIndex);
      //System.out.println("end point : " + endPoint);
      
//      String viewName = new HomeCommand().process(req, resp);
//      if("/hello.do".equals(endPoint)) {
//         viewName = new HelloCommand().process(req, resp);
//      }
      
      String viewName = cmdMap.get(endPoint).process(req, resp);
      
      String prefix = "/WEB-INF/views/";
      String suffix = ".jsp";
      String path = prefix + viewName + suffix;
      //System.out.println("path : " + path);
      RequestDispatcher view = req.getRequestDispatcher(path);
      view.forward(req, resp);
   }

   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      process(req, resp);
   }

   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      process(req, resp);
   }
}