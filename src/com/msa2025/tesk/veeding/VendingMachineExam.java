package com.msa2025.tesk.veeding;

import java.util.Scanner;

public class VendingMachineExam {
	
	private static int money;
	private static VendingMachine[] vendingMachine;
	static Scanner sc = new Scanner(System.in);

	// 음료 초기화
	private static void init() {
        vendingMachine = new VendingMachine[] { 
            new VendingMachine("콜라", 1500, 0),
            new VendingMachine("사이다", 1400, 0),
            new VendingMachine("커피", 2000, 5),
            new VendingMachine("주스", 1800, 12),
            new VendingMachine("물", 1000, 20),
            new VendingMachine("에너지 드링크", 2500, 7)
        };
	}
	
	// 돈 투입
	static void insert() {
		System.out.println("----------------------------------------");
		System.out.print("돈을 투입해 주세요 : ");
		money = Integer.parseInt(sc.nextLine());
		if (money > 0) {
		} else {
			System.out.println("돈을 올바르게 넣어주세요");
		}
	}
	
	// 돈 환불
	static void refund() {
		System.out.println("----------------------------------------");
		System.out.println("2.돈 돌려받기");
		if (money > 0) {
			System.out.println(money + "원을 환불합니다.");
			money = 0;
		} else {
			System.out.println("돈이 없습니다.");
		}
	}
	
	
	// 종료
	static void exit() {
		System.out.println("자판기를 종료합니다");
		System.exit(0);
	}

	// 구입
	static void purchace() {
		System.out.println("----------------------------------------");
		int index = 1;
		System.out.println("구매 가능한 음료 목록");

		for (var beverage : vendingMachine) {
			if (money >= beverage.getPrice() && beverage.getQuantity() > 0) {
				System.out.println(index + ". " + beverage.getName() + " - " + beverage.getPrice() + "원");
				index++;
			}
		}
		int choice = Integer.parseInt(sc.nextLine());
		int dex = 1;
		for (var arr : vendingMachine) {
			if (money >= arr.getPrice() && arr.getQuantity() > 0) {
				if (dex == choice) {
					money -= arr.getPrice();
					arr.reduceQuantity();
					System.out.println(arr.getName() + "를 구매하였습니다. 남은 잔액: " + money + "원");
				}
				dex++;
			}
		}
		System.out.println("----------------------------------------");
	}

	public static void main(String[] args) {
		// 선택지 고르게하기
		int sel;
		init();
		
		
		while (true) {
			System.out.println("현재 잔액 : " + money);
			System.out.println("----------------------------------------");
			System.out.println("1.돈 투입하기");
			System.out.println("2.돈 돌려받기");
			System.out.println("3.음료 선택");
			System.out.println("4.종료");
			System.out.print("선택해 주세요: ");
			
			// int형만 받기 String같은거 절대 안됨
			try {
			sel = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                continue;
            }
			
			
			switch (sel) {

			case 1: {
				insert();
				break;
			}

			case 2: {
				refund();
				break;
			}
			case 3: {
				purchace();
				break;
			}
			case 4: {
				exit();
			}
			}
		}
	}
}
