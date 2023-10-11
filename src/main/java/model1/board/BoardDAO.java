package model1.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect {
	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	// 검색 조건에 맞는 게시물의 개수를 반환
	public int selectCount(Map<String,Object> map) {
		int totalCount = 0;
		
		String query = "select count(*) from board";
		if(map.get("searchWord")!=null) {
			query+=" where "+map.get("searchField")+" "
					+" like '%"+map.get("searchWord")+"%'";
		}
		try {
			Connection con = super.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch(Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	// 검색 조건에 맞는 게시물 목록을 반환
	public List<BoardDTO> selectList(Map<String,Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();
		
		String query = "select * from board";
		if(map.get("searchWord")!=null) {
			query+=" where " +map.get("searchField")+" "
					+" like '%"+map.get("searchWord")+"%'";
		}
		query +=" order by num desc";
		
		try {
			Connection con = super.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			}
		} catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}
	
	// 게시글 데이터를 받아 DB에 추가
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "insert into board ("
						+" title, content,id, visitcount)"
						+" values(?,?,?,0)";
			Connection con = super.getConnection();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate();
						
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 지정한 게시물을 찾아 내용 반환
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		
		String query = "select B.*, M.name "
					+" from member M inner join board B "
					+" on M.id=B.id "
					+" where num=?";
		try {
			Connection con = super.getConnection();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		} catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	// 조회수 1 증가시키기
	public void updateVisitCount(String num) {
		String query ="update board set "
					+" visitcount=visitcount+1 "
					+" where num=?";
		try {
			Connection con = super.getConnection();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	//지정한 게시물 수정하기
	public int updateEdit(BoardDTO dto) {
		int result =0;
		try {
			String query = "update board set "
						+" title=?, content=? "
						+" where num=?";
			Connection con = super.getConnection();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3,dto.getNum());
			
			result = psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	public int deletePost(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "delete from board where num=?";
			
			Connection con = super.getConnection();
			PreparedStatement psmt = con.prepareStatement(query);
			
			psmt.setString(1,  dto.getNum());
			
			result=psmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	//페이징 기능을 지원하는 게시물 목록 반환
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();
		
		String query = "select * from board";
//		query += " where (num between ?  and ?)";
		if(map.get("searchWord")!=null) {
//			query += " and "+map.get("searchField") + " like '%"+ map.get("searchWord")+"%'";
			query += " where "+map.get("searchField") + " like '%"+ map.get("searchWord")+"%'";
		}
		query += " order by num desc";
		query +=" limit ? ,?";
		
		
		
		try {
			Connection con = super.getConnection();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(map.get("start").toString())-1);
			psmt.setInt(2, 10);
					
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			}
			
		} catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}
}
