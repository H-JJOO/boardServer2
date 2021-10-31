package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/selList")
public class SelBoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<BoardVO> list = BoardDAO.selBoardList();//버스 주소값 가져옴
        Gson gson = new Gson();
        String json = gson.toJson(list);//버스 주소값을 json 으로 바꿔준다
        System.out.println("json : " + json);
        res.setContentType("text/plain;;charset=UTF-8");//한글 깨지지 않게 하기
        res.setCharacterEncoding("UTF-8");// 한글 깨지지 않게 하기
        PrintWriter out = res.getWriter();
        out.print(json);//바꾼 버스 주소값을 응답
    }
}
