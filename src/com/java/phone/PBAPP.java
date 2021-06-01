package com.java.phone;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PBAPP {

	public static void main(String[] args) {
		while(true) {
			System.out.print("*********************************\n");
			System.out.print("*        전화번호 관리 시스템         *\n");
			System.out.print("*********************************\n");
			System.out.print("1.리스트 2.등록 3.삭제 4.검색 5.종료\n");
			System.out.print("----------------------------------\n");
			System.out.print("메뉴 번호 > ");
			
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			
			if(id == 1) {
				selectAll();
			}else if(id == 2) {
				insertPB();
			}else if (id == 3) {
				deletePB();
			}else if(id == 4) {
				searchPB();
			}else if(id == 5){
				System.out.print("************************\n");
				System.out.print("*        감사합니다       *\n");
				System.out.print("************************\n");
				break;
			}else {
				System.out.println("다시 입력해주세요");
			}
			sc.close();
		}
		

	}

	private static void selectAll() {
		PBDAO dao = new PBDAOImpl();
		List<PBVO> list = dao.getList();
		
		Iterator<PBVO> it = list.iterator();
		
		System.out.println("<1. 리스트>");
		
		while(it.hasNext()) {
			PBVO item = it.next();
			System.out.printf("%d%s%s%s%n", item.getId(), item.getName(),
					item.getHp(), item.getTel());
		}
		
	}

	private static void searchPB() {
		Scanner sc = new Scanner(System.in);
		System.out.println("<4. 검색>");
		System.out.print(">이름: ");
		String keyword = sc.next();
		
		PBDAO dao = new PBDAOImpl();
		List<PBVO> list = dao.search(keyword);
		
		Iterator<PBVO> it = list.iterator();
		
		while(it.hasNext()) {
			PBVO vo = it.next();
			System.out.printf("%d\t%s\t%s\t%s%n", vo.getId(),
					vo.getName(),
					vo.getHp(),
					vo.getTel());
			sc.close();
		}
	}
	
	private static void deletePB() {
		System.out.println("<3. 삭제>");
		Scanner sc = new Scanner(System.in);
		System.out.print(">번호 : ");
		int id = sc.nextInt();
		
		PBDAO dao = new PBDAOImpl();
		boolean success = dao.delete(Long.valueOf(id));
		
		System.out.print(success ? "삭제되었습니다." : "실패하였습니다.");
		sc.close();
	}
	
	private static void insertPB() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<2. 등록>");
		System.out.print(">이름: ");
		String name = sc.next();
		System.out.print(">휴대전화: ");
		String hp = sc.next();
		System.out.print(">집전화: ");
		String tel = sc.next();
		
		PBVO vo = new PBVO(null, name, hp, tel);
		PBDAO dao = new PBDAOImpl();
		
		boolean success = dao.insert(vo);
		System.out.println(success ? "[등록되었습니다.]" : "[실패하였습니다.]");
		sc.close();
	}
	
}
