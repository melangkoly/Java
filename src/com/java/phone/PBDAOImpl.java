package com.java.phone;
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PBDAOImpl implements PBDAO {
	//	공통 접속 메소드
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//	드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl,
					"c##bituser",
					"bituser");
		}catch(ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		}
		return conn;
	}
	@Override
	public List<PBVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<PBVO> list = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "Select id, name, hp, tel " + " From phone_book";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long id = rs.getLong(1);
				String pbName = rs.getString(2);
				String pbHp = rs.getString(3);
				String pbTel = rs.getString(4);
				
				PBVO vo = new PBVO(id, pbName, pbHp, pbTel);
				
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {
				
			}
		}
		return list;
	}

	@Override
	public List<PBVO> search(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PBVO> list = new ArrayList<>();
		
		String sql = "Select id, name, hp, tel From phone_book " +
				" WHERE name LIKE ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long id = rs.getLong("id");
				String pbName = rs.getString("name");
				String pbHp = rs.getString("hp");
				String pbTel = rs.getString("tel");
				
				PBVO vo = new PBVO(id, pbName, pbHp, pbTel);
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch(Exception e) {
				
			}
		}
		return list;
	}

	@Override
	public boolean insert(PBVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO phone_book Values(seq_phone_book_pk.NextVal, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getPbName());
			pstmt.setString(2, vo.getPbHp());
			pstmt.setString(3, vo.getPbTel());
			
			insertedCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				
			}
		}
		return 1 == insertedCount;
	}

	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book WHERE id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			deletedCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				
			}
		}
		return 1 == deletedCount;
	}

}
