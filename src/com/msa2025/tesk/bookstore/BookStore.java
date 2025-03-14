package com.msa2025.tesk.bookstore;

import java.util.Scanner;

public class BookStore {
	private static int cnt = 0;
	private static Book[] book = new Book[100];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		boolean con = true;

		while (con) {
			System.out.println("1.도서관 책 기입");
			System.out.println("2.도서관 책 목록");
			System.out.print("선택해 주세요: ");
			int sel = Integer.parseInt(sc.nextLine());

			switch (sel) {
			case 1: {
				System.out.println("----------------------------------------");
				System.out.println("1.도서관 책 기입");
				System.out.print("책 이름을 입력해 주세요: ");
				String name = sc.nextLine();
				System.out.print("책 가격을 입력해 주세요: ");
				int value = Integer.parseInt(sc.nextLine());
				System.out.println("----------------------------------------");
				if(name != "" && value>0) {
					book[cnt] = new Book(name, value);
					cnt++;
				}else {
					System.out.println("올바른 값을 넣지않았습니다.");
				}
				break;
			}
			case 2: {
				System.out.println("----------------------------------------");
				System.out.println("2.도서관 책 목록");
				if(book[0] != null) {
				for (int i = 0; i < cnt; i++) {
					System.out.printf("%s. 이름 : %s, 가격 : %d원\n",i+1,book[i].getName(), book[i].getValue());
				}
				System.out.println("----------------------------------------");
				}else {
					System.out.println("책을 먼저 입력해주세요");
				}
				break;
			}
			case 3: {
				System.out.println("----------------------------------------");
				System.out.println("종료");
				con = false;
				sc.close();
				break;
			}

			}
		}

	}

}
