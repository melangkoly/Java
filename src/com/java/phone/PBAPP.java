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
				insertedPhone();
			}else if (id == 3) {
				deletedPhone();
			}else if(id == 4) {
				searchName();
			}else if(id == 5){
				System.out.print("************************\n");
				System.out.print("*        감사합니다       *\n");
				System.out.print("************************\n");
				break;
			}else {
				System.out.println("다시 입력해주세요");
			}
		}
	}

	private static void selectAll() {
		PBDAO dao = new PBDAOImpl();
		List<PBVO> list = dao.getList();

		Iterator<PBVO> it = list.iterator();
		while (it.hasNext()) {
			PBVO item = it.next();
			System.out.printf("%d\t%s\t%s\t%s%n", item.getId(), item.getPbName(), item.getPbHp(), item.getPbTel());
		}
	}

	private static void searchName() {
		Scanner sc = new Scanner(System.in);

		System.out.println("<4.검색>");
		System.out.print(">이름: ");
		String keyword = sc.next();

		PBDAO dao = new PBDAOImpl();
		List<PBVO> list = dao.search(keyword);

		Iterator<PBVO> it = list.iterator();

		while (it.hasNext()) {
			PBVO vo = it.next();
			System.out.printf("%d%s\t%s\t%s%n", vo.getId(), vo.getPbName(), vo.getPbHp(), vo.getPbTel());
			
		}

	}

	private static void deletedPhone() {
		Scanner sc = new Scanner(System.in);
		System.out.println("<3.삭제>");
		System.out.print(">번호: ");
		int id = sc.nextInt();
		
		PBDAO dao = new PBDAOImpl();
		boolean success = dao.delete(Long.valueOf(id));
		System.out.println(success ? "[삭제되었습니다.]" : " ");
		selectAll();
		
	}
	private static void insertedPhone() {
		Scanner sc = new Scanner(System.in);
		System.out.println("<2.등록>");
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("휴대전화: ");
		String phoneNumber = sc.next();
		System.out.print("집전화: ");
		String homeNumber = sc.next();
		
		PBVO vo = new PBVO(null, name, phoneNumber, homeNumber);
		PBDAO dao = new PBDAOImpl();
		
		boolean success = dao.insert(vo);
		
		System.out.println(success ? "[등록되었습니다.]" : " ");
		selectAll();
		
	}
}
