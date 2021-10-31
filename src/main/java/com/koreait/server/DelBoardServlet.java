package com.koreait.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del")
public class DelBoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pIboard = req.getParameter("iboard");
        System.out.println("pIboard : " + pIboard);


        BoardVO param = new BoardVO();
        //GET 방식은 GSON 안씀
        //GSON 이 알아서 안해주기 때문에 직접 SET 해줘야한다.
        //문자열을 정수로 형변환
        int iboard = Integer.parseInt(pIboard);
        param.setIboard(iboard);
        BoardDAO.delBoard(param);


    }
}
