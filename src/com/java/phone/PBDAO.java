package com.java.phone;

import java.util.List;

public interface PBDAO {
	public List<PBVO> getList();
	public List<PBVO> search(String keyword);	// LIKE 검색
	public PBVO get(Long id); //	PK로 가져오기
	public boolean insert(PBVO vo);	//	Insert
	public boolean delete(Long id); //	PK로 삭제
}
