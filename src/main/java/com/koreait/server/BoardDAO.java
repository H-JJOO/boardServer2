package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    public static int insBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board(title, ctnt, writer) " +
                    " VALUES(?, ?, ?) ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setString(3, param.getWriter());
            return ps.executeUpdate(); //쿼리문 실행!
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static List<BoardVO> selBoardList() {
        List<BoardVO> list = new ArrayList();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iboard, title, wrtier, rdt " +
                    " FROM t_board ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

//            rs.next();//레코드가 있으면 true 없으면 false

            while (rs.next()) {
                BoardVO vo = new BoardVO();//버스에 담을 사람을 만든다(예든거임)
                vo.setIboard(rs.getInt("iboard"));//사람 번호
                vo.setTitle(rs.getString("title"));//사람 이름
                vo.setWriter(rs.getString("writer"));//사람 나이
                vo.setRdt(rs.getString("rdt"));//사람 주소
                list.add(vo);//버스에 태운다.
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static int updBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " UPDATE t_board " +
                    " SET title = ?, " +
                    " ctnt = ?, " +
                    " writer = ? " +
                    " WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setString(3, param.getWriter());
            ps.setInt(4, param.getIboard());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;

    }

    public static int delBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_board "  +
                    " WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIboard());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
