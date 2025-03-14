package com.msa2025.tesk.lotto;

import java.util.Arrays;

/*
 * 로또 프로그램. 난수 (Math.random()) 메소드이용 6번만 출력해야함
 */
public class LottoExam {

	static class Lotto {
		private int[] lotto;
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
		
		Lotto lotto = new Lotto();
		
		lotto.init();
		
		lotto.lottoDraw();
		
		lotto.lottoResult();

	}
}
