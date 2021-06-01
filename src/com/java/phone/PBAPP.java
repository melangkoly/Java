package com.java.phone;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PBAPP {

	public static void main(String[] args) {
		while(true) {
			System.out.print("*********************************\n");
			System.out.print("*        ��ȭ��ȣ ���� �ý���         *\n");
			System.out.print("*********************************\n");
			System.out.print("1.����Ʈ 2.��� 3.���� 4.�˻� 5.����\n");
			System.out.print("----------------------------------\n");
			System.out.print("�޴� ��ȣ > ");
			
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
				System.out.print("*        �����մϴ�       *\n");
				System.out.print("************************\n");
				break;
			}else {
				System.out.println("�ٽ� �Է����ּ���");
			}
			sc.close();
		}
		

	}

	private static void selectAll() {
		PBDAO dao = new PBDAOImpl();
		List<PBVO> list = dao.getList();
		
		Iterator<PBVO> it = list.iterator();
		
		System.out.println("<1. ����Ʈ>");
		
		while(it.hasNext()) {
			PBVO item = it.next();
			System.out.printf("%d%s%s%s%n", item.getId(), item.getName(),
					item.getHp(), item.getTel());
		}
		
	}

	private static void searchPB() {
		Scanner sc = new Scanner(System.in);
		System.out.println("<4. �˻�>");
		System.out.print(">�̸�: ");
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
		System.out.println("<3. ����>");
		Scanner sc = new Scanner(System.in);
		System.out.print(">��ȣ : ");
		int id = sc.nextInt();
		
		PBDAO dao = new PBDAOImpl();
		boolean success = dao.delete(Long.valueOf(id));
		
		System.out.print(success ? "�����Ǿ����ϴ�." : "�����Ͽ����ϴ�.");
		sc.close();
	}
	
	private static void insertPB() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<2. ���>");
		System.out.print(">�̸�: ");
		String name = sc.next();
		System.out.print(">�޴���ȭ: ");
		String hp = sc.next();
		System.out.print(">����ȭ: ");
		String tel = sc.next();
		
		PBVO vo = new PBVO(null, name, hp, tel);
		PBDAO dao = new PBDAOImpl();
		
		boolean success = dao.insert(vo);
		System.out.println(success ? "[��ϵǾ����ϴ�.]" : "[�����Ͽ����ϴ�.]");
		sc.close();
	}
	
}
