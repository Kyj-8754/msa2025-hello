package com.msa2025.veeding;

import java.util.Arrays;

public class VendingMachineExam {
	private final int NUM_MAX = 10;
	private final int NUM_MIN = 0;
	private final int MONRY_MIN = 0;
	private final int MONRY_MAX = 30000;
	private Machine[] Machine = new Machine[20];
	

	static class VendingMachine {
		private int[] sel;
		private int cnt;

		public Lotto() {
			lotto = new int[45];
			sel = new int[6];
			cnt = 45;
		}
		
		
		void init() {
			for (int i = 0; i < 45; i++) {
				lotto[i] = i+1;
			}
		}

		void lottoDraw() {
			for (int i = 1; i <= 6; i++) {

				// 난수 생성 n회차 할수록 1~45개 -> n~45개 하도록만들기
				int ball = (int) (cnt * Math.random() + i);

				// 로또숫자 항상 나열
				Arrays.sort(lotto);

				// 값 대입
				sel[i - 1] = lotto[ball - 1];

				// 뽑은 공은 값 0으로
				lotto[ball - 1] = 0;

				cnt--;
			}
		}

		void lottoResult() {
			Arrays.sort(sel);
			System.out.print("당첨 번호 : ");
			for (int arr : sel) {
				System.out.printf("%d ", arr);
			}
		}
	}

	public static void main(String[] args) {
		// 자판기 생성
		
		// 자판기 초기 설정
		
		// 돈 넣고 사용하기
		
	}
}
