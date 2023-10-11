package membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.JDBConnect;

public class MemberDAO extends JDBConnect {
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv,url,id,pw);
	}
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "select * from member where id=? and pass=?";
		
		try {
			Connection con = super.getConnection();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1,uid);
			psmt.setString(2,upass);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	public boolean insertMember(String uid, String upw, String uname) {
		String query = "insert into member(id,pass,name) values(?,?,?)";
		
		try {
			if(uid=="" || upw=="" || uname=="") throw new Exception();
			Connection con = super.getConnection();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1,uid);
			psmt.setString(2,upw);
			psmt.setString(3, uname);
			psmt.executeUpdate();
			
		} catch(Exception e) {
			return false;
		}
		return true;
		
		
		
	}
}
