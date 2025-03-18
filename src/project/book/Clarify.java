package project.book;

import java.util.*;
import java.time.*;
import java.time.format.*;

public class Clarify {

	// 가격 검증 (0 이상)
	public static boolean clarify(int price) {
		return price >= 5000 && price <= 50000;
	}

	// 이름 및 저자 검증 (비어있지 않아야 함)
	public static boolean clarify(String value) {
		return value != null && !value.trim().isEmpty();
	}

	// 날짜 검증 (1950년 1월 1일부터 오늘까지)
	public static boolean clarify(LocalDate date) {
		LocalDate today = LocalDate.now();
		LocalDate minDate = LocalDate.of(1950, 1, 1);
		if(!(date.isBefore(minDate) || date.isAfter(today))) {
			return true;
		}else {
			System.out.println("도서 출판일은 1950년도부터 현재까지 입력 가능합니다");
			return false;
		}
	}

	// 문자열 날짜 검증 (YYYY-MM-DD 형식)
	public static boolean clarify(String dateStr, boolean isDate) {
		if (!isDate)
			return clarify(dateStr);
		try {
			if (!dateStr.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
				System.out.println("올바른 형식이 아닙니다. (예: 2024-03-17)");
				return false;
			}
			LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			return clarify(date);
		} catch (DateTimeParseException e) {
			return false;
		}
		
	}
	
	private boolean isDateFormat(String dateStr) {
	    return dateStr.matches("^\\d{4}-\\d{2}-\\d{2}$");
	}
	

	// 여러 개의 데이터를 동시에 검증 (가변 인자 사용)
	public boolean clarify(Object... values) {
		return Arrays.stream(values).allMatch(value -> {
			if (value instanceof Integer)
				return clarify((int) value);
			if (value instanceof String)
			{
				if (isDateFormat((String) value)) {
	                return clarify((String) value, true);  // 날짜 형식이면 true로 넘김
	            } else {
	                return clarify((String) value);  // 일반 문자열로 처리
	            }
			}
			if (value instanceof LocalDate)
				return clarify((LocalDate) value);
			return false; // 지원되지 않는 타입이면 false 반환
		});
	}
}
