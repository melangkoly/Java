package com.java.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PBDAOImpl implements PBDAO {
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("Oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl,
					"C##bituser",
					"user");
		}catch(ClassNotFoundException e){
			System.err.println("드라이버 로드 실패!");
		}
		return conn;
		
	}
	@Override
	public List<PBVO> getList() { // 검색 리스트
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<PBVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "Select id, name, hp, tel" + " From PHONE_BOOK";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				PBVO vo = new PBVO(id, name, hp, tel);
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
	public List<PBVO> search(String keyword) { // 라이크 검색
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PBVO> list = new ArrayList<>();
		
		String sql = "Select id, name, hp, tel From PHONE_BOOK " + " Where name LIKE ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String tel = rs.getString("tel");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch(Exception e){
				
			}
		}
		return list;
	}

	@Override
	public PBVO get(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		PBVO vo = null;

		try {
			conn = getConnection();
			String sql = "Select id, name, hp, tel From PHONE_BOOK " + " Where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);

			rs = pstmt.executeQuery();
			rs.next();

			if (rs.next()) {
				Long _id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);

				vo = new PBVO(id, name, hp, tel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				
			}
		}
		return null;
	}

	@Override
	public boolean insert(PBVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO PHONE_BOOK Values(phone_id.NextValue, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
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
		return 1 == insertedCount; // 삽입된 갯수가 1이면 성공
	}

	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM PHONE_BOOK WHERE id = ?";
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
