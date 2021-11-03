package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    public static int insBoard(BoardVO param) {
        Connection con = null;//Connection 인터페이스를 con 주소값에 담고 초기화
        PreparedStatement ps = null;//PrepareStatement 인터페이스를 ps 주소값에 담고 초기화
        String sql = " INSERT INTO t_board(title, ctnt, writer) " +
                    " VALUES(?, ?, ?) ";

        try {
            con = DbUtils.getCon();//통신연결
            ps = con.prepareStatement(sql);//쿼리문 담기
            ps.setString(1, param.getTitle());//첫번째 ? 에 title 값 넣기
            ps.setString(2, param.getCtnt());//두번째 ? 에 ctnt 값 넣기
            ps.setString(3, param.getWriter());//세번째 ? dp wirter 값 넣기
            return ps.executeUpdate(); //쿼리문 실행!
        } catch (Exception e) {
            e.printStackTrace();//뭐가 에러인지 출력
        } finally {
            DbUtils.close(con, ps);//다 실행하고 무조건 닫기
        }
        return 0;//오류 발생시 0 리턴
    }

    public static List<BoardVO> selBoardList() {
        List<BoardVO> list = new ArrayList();//버스 한대, 사람 여러명 탈수 있음

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iboard, title, writer, rdt " +
                    " FROM t_board " +
                    " ORDER BY iboard DESC ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

//            rs.next();//레코드가 있으면 true 없으면 false

            while (rs.next()) {//사람 한명씩 만들어서 버스에 태운다.
                BoardVO vo = new BoardVO();//버스에 담을 사람을 만든다(예든거임)
                vo.setIboard(rs.getInt("iboard"));//사람 번호
                vo.setTitle(rs.getString("title"));//사람 이름
                vo.setWriter(rs.getString("writer"));//사람 나이
                vo.setRdt(rs.getString("rdt"));//사람 주소
                list.add(vo);//버스에 사람 태운다.(주소값 추가)
            }
            return list;//버스의 주소값
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;//버스의 주소값
    }

    public static BoardVO selBoard(BoardVO param) {
        BoardVO vo = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT * FROM t_board " +
                    " WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();

            if (rs.next()) {//무조건 한번 호출 해야함(한줄할때는 if 여러줄 while
                vo = new BoardVO();//담을공간 있어야 담지? 그래서 만듬
                vo.setIboard(rs.getInt("iboard"));//결과물의 컬럼명
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return vo;
    }

    public static int updBoard(BoardVO param) {
        Connection con = null;//초기화
        PreparedStatement ps = null;//초기화
        String sql = " UPDATE t_board " +
                    " SET title = ?, " +
                    " ctnt = ?, " +
                    " writer = ? " +
                    " WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();//연결
            ps = con.prepareStatement(sql);//쿼리문
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setString(3, param.getWriter());
            ps.setInt(4, param.getIboard());
            return ps.executeUpdate();//업데이트

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
