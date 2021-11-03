package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ins")
public class InsBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String json = Utils.getJson(req);//json 에 Utils 연결
        System.out.println("json : " + json);

        Gson gson = new Gson();//Gson 객체화
        BoardVO param = gson.fromJson(json, BoardVO.class);//java -> Json
        System.out.println(param.getTitle());
        System.out.println(param.getCtnt());
        System.out.println(param.getWriter());

        int result = BoardDAO.insBoard(param);//BoardDAO 의 insert 기능 가져오기
        System.out.println("result : " + result);


    }
}
