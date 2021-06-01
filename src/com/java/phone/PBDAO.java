package com.java.phone;

import java.util.List;

public interface PBDAO {//
	public List<PBVO> getList();
	public List<PBVO> search(String keyword);
	public boolean insert(PBVO vo);
	public boolean delete(Long id);
}
