package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/upd")
public class UpdBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String json = Utils.getJson(req);//json 가져오기
        Gson gson = new Gson();//Gson 객체 생성
        BoardVO param = gson.fromJson(json, BoardVO.class);//java -> json

        int result = BoardDAO.updBoard(param);//주소값 가져오기
        System.out.println("result : " + result);//출력
    }
}
