package com.java.phone;

import java.util.List;

public interface PBDAO {
	public List<PBVO> getList();
	public List<PBVO> search(String keyword);	// LIKE �˻�
	public PBVO get(Long id); //	PK�� ��������
	public boolean insert(PBVO vo);	//	Insert
	public boolean delete(Long id); //	PK�� ����
}
