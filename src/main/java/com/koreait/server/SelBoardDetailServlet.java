package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sel")
public class SelBoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //GET 방식이기때문에 쿼리스트링(?블라블라)으로 날라옴
        
        String strIboard = req.getParameter("iboard");
        int iboard = Integer.parseInt(strIboard);
        BoardVO param = new BoardVO();//여기서 만들어짐(0,null)
        param.setIboard(iboard);
        BoardVO result = BoardDAO.selBoard(param);//DAO 에서 만들어짐(값 넣어준거 담긴 값)
        Gson gson = new Gson();
        String json = gson.toJson(result);

        res.setContentType("text/plain;;charset=UTF-8");//한글 깨지지 않게 하기
        res.setCharacterEncoding("UTF-8");// 한글 깨지지 않게 하기

        PrintWriter out = res.getWriter();
        out.print(json);



    }
}
