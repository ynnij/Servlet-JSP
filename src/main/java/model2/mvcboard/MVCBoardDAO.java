package model2.mvcboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect {
	public MVCBoardDAO() {super();}
	
	//검색 조건에 맞는 게시물의 개수 반환
	public int selectCount(Map<String, Object>map) {
		int totalCount = 0;
		
		String query = "select count(*) from mvcboard";
		
		//검색 조건이 있는경우 where절 추가
		if(map.get("searchWord")!=null) {
			query+=" where "+map.get("searchField")+" "
				 +" like '%"+map.get("searchWord")+"%'";
		}
		try {
			Statement stmt = super.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch(Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	//검색 조건에 맞는 게시물 목록 반환(페이징 기능 지원)
	public List<MVCBoardDTO> selectListPage(Map<String,Object>map){
		List<MVCBoardDTO> board = new Vector<>();
		
		String query = "select * from mvcboard";
		if(map.get("searchWord")!=null) {
			query += " where "+map.get("searchField") + " like '%"+ map.get("searchWord")+"%'";
		}
		query += " order by idx desc";
		query +=" limit ? ,?";
		
		try {
			Connection con = super.getConnection();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(map.get("start").toString())-1);
			psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
		} catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	
	// 게시글 데이터를 받아 DB에 추가 (파일업로드 지원)
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		try {
			String query = "insert into mvcboard(name, title,content,ofile, sfile, pass) "
						+"values (?,?,?,?,?,?)";
			
			PreparedStatement psmt = super.getConnection().prepareStatement(query);
			
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			
			result = psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
			
		}
		return result;
	}
	
	// 주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환합니다.
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		String query = "select * from mvcboard where idx=?";
		try {
			PreparedStatement psmt = super.getConnection().prepareStatement(query);
			psmt.setString(1,idx);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));

			}

			
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	//주어진 일련번호에 해당하는 게시물의 조회수를 1 증가
	public void updateVisitCount(String idx) {
		String query = "update mvcboard set visitcount=visitcount+1 where idx=?";
		try {
			PreparedStatement psmt = super.getConnection().prepareStatement(query);
			psmt.setString(1,idx);
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 다운로드 횟수를 1 증가
	public void downCountPlus(String idx) {
		String sql = "update mvcboard set downcount=downcount+1 where idx=?";
		try {
			PreparedStatement psmt = super.getConnection().prepareStatement(sql);
			psmt.setString(1,idx);
			psmt.executeUpdate();
		} catch(Exception e) {}
	}
	
	//입력한 비밀번호가 지정한 게시물의 비밀번호와 일치하는지 확인
	public boolean confirmPassword(String pass, String idx) {
		boolean isCorr = true;
		try {
			String sql = "select count(*) from mvcboard where pass=? and idx=?";
			PreparedStatement psmt = super.getConnection().prepareStatement(sql);
			psmt.setString(1,pass);
			psmt.setString(2, idx);
			ResultSet rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
				isCorr=false;
			}
		} catch(Exception e) {
			isCorr=false;
			e.printStackTrace();
		}
		return isCorr;
	}
	
	//지정한 게시물 삭제
	public int deletePost(String idx) {
		int result = 0;
		try {
			String query = "delete from mvcboard where idx=?";
			PreparedStatement psmt = super.getConnection().prepareStatement(query);
			psmt.setString(1,idx);
			result = psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	//게시글 데이터를 받아 DB에 저장된 내용 갱신(파일 업로드 지원)
	public int updatePost(MVCBoardDTO dto) {
		int result =0;
		try {
			String query = "update mvcboard set title=?, name=?, content=?, ofile=?, sfile=? where idx=? and pass=?";
			PreparedStatement psmt = super.getConnection().prepareStatement(query);
			psmt.setString(1,dto.getTitle());
			psmt.setString(2,dto.getName());
			psmt.setString(3,dto.getContent());
			psmt.setString(4,dto.getOfile());
			psmt.setString(5,dto.getSfile());
			psmt.setString(6,dto.getIdx());
			psmt.setString(7,dto.getPass());
			
			result = psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
		
	}
}
