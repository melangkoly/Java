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
				insertedPhone();
			}else if (id == 3) {
				deletedPhone();
			}else if(id == 4) {
				searchName();
			}else if(id == 5){
				System.out.print("************************\n");
				System.out.print("*        �����մϴ�       *\n");
				System.out.print("************************\n");
				break;
			}else {
				System.out.println("�ٽ� �Է����ּ���");
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

		System.out.println("<4.�˻�>");
		System.out.print(">�̸�: ");
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
		System.out.println("<3.����>");
		System.out.print(">��ȣ: ");
		int id = sc.nextInt();
		
		PBDAO dao = new PBDAOImpl();
		boolean success = dao.delete(Long.valueOf(id));
		System.out.println(success ? "[�����Ǿ����ϴ�.]" : " ");
		selectAll();
		
	}
	private static void insertedPhone() {
		Scanner sc = new Scanner(System.in);
		System.out.println("<2.���>");
		System.out.print("�̸�: ");
		String name = sc.next();
		System.out.print("�޴���ȭ: ");
		String phoneNumber = sc.next();
		System.out.print("����ȭ: ");
		String homeNumber = sc.next();
		
		PBVO vo = new PBVO(null, name, phoneNumber, homeNumber);
		PBDAO dao = new PBDAOImpl();
		
		boolean success = dao.insert(vo);
		
		System.out.println(success ? "[��ϵǾ����ϴ�.]" : " ");
		selectAll();
		
	}
}
