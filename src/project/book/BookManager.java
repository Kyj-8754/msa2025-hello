package project.book;

import java.time.LocalDate;
import java.util.*;

/*
 * 도서 관리 프로그램
 * HashMap, ArrayList 사용
 * Book 클래스 (도서번호(ISBN), 도서제목, 도서가격, 저자, 출판일)
 * BookManager (도서 추가, 도서 삭제, 도서 검색, 도서목록)
 * HashMap, ArrayList 사용해서 Book객체 관리
 * 
 * 도서 추가할때조건
 * 도서번호 중복x, isbn은 10자리
 * 도서 가격 : 5000~50000까지만 입력가능
 * 출판일 : 1980~현재까지만 가능
 * 
 * 도서 삭제 조건
 * 1. 도서 번호 (3자리 이상 값을 입력 받고, 그와 유사한 목록 전부 출력)
 * 2. 도서 제목
 * 3. 저자로 검색 가능
 * 
 * 도서 검색및 목록조건
 * 도서 번호(3자리 이상 값을 입력 받고, 그와 유사한 목록 전부 출력)
 * 도서 제목(3자리 이상 값을 입력 받고 유사한 목록 전부 출력)
 * 저자(2자이상 입력해서 유사한 목록 전부 출력)
 * 출판일로 검색(년월일 입력, 범위로 입력할 수 있다)
 * 	ex) 2025-03-01 ~ 2025-03-17
 * 
 * 
 */

public class BookManager {
	static private String isbn;
	static private int price;
	static private String name;
	static private String author;
	static private String date;
	static Scanner sc = new Scanner(System.in);
	static private Map<Object, Book> book = new HashMap<>();

	// 날짜 변환 메서드
	// 구현완료
	public static LocalDate dateChange(String date) {
		try {
			String[] dateParts = date.split("-");
			String year = dateParts[0];
			String month = String.format("%02d", Integer.parseInt(dateParts[1])); // 월을 두 자리로 포맷
			String day = String.format("%02d", Integer.parseInt(dateParts[2])); // 일을 두 자리로 포맷
			return LocalDate.parse(year + "-" + month + "-" + day);
		} catch (Exception e) {
			System.out.println("올바른 형식이 아닙니다. (예: 2024-03-17)");
			return null;
		}
	}

	// 도서 추가
	// 구현 완료
	public static void register() {

		Clarify clarify = new Clarify();

		System.out.println("도서 추가");
		System.out.println("도서 번호는 숫자 10자리까지 입력 가능합니다.");
		System.out.print("도서 번호 : ");

		try {
			isbn = sc.nextLine();
			if (isbn.length() > 10 || !isbn.matches("\\d+")) {
				System.out.println("ISBN은 숫자로 최대 10자리 입력해야 합니다.");
			}
			isbn = String.format("%010d", Integer.parseInt(isbn));

			if (book.containsKey(isbn)) {
				System.out.println("이미 존재하는 ISBN입니다. 다른 ISBN을 입력하세요.");
				return;
			}
		} catch (Exception e) {
			System.out.println("올바른 값을 입력해 주세요");
			return;
		}

		System.out.print("도서 제목 : ");
		name = sc.nextLine();

		System.out.println("도서 가격은 5000원~50000원까지 입력 가능합니다.");
		System.out.print("도서 가격 : ");

		try {
			price = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("올바른 값을 입력해 주세요");
			return;
		}

		// 저자 입력값
		System.out.print("도서 저자 : ");
		author = sc.nextLine();

		// 도서 출간일
		System.out.println("도서 출간일은 1950년도부터 현재까지 입력 가능합니다");
		System.out.print("도서 출간일  (YYYY-MM-DD 형태로 입력해주세요): ");
		date = sc.nextLine();

		// 저장 메서드 호출
		if (clarify.clarify(price, name, author, dateChange(date))) {
			book.put(isbn, new Book(isbn, price, name, author, dateChange(date)));
		} else {
			System.out.println("올바른 값이 들어가지 않았습니다.");
		}
	}
	
	
	// 도서 전체 리스트 나열
		static void BookList() {
			for (Book book1 : book.values()) {
				System.out.println("ISBN: " + book1.getBookIsbn());
				System.out.println("책 제목: " + book1.getBookName());
				System.out.println("저자: " + book1.getBookAuthor());
				System.out.println("가격: " + book1.getBookPrice());
				System.out.println("-----------------------");
			}
		}

	// 도서 검색
	public static void bookSerch() {
		int sel = 0;
		String type;

		System.out.println("검색 조건을 선택해 주세요");
		System.out.println("1. 도서 번호");
		System.out.println("2. 도서 제목");
		System.out.println("3. 도서 저자");
		System.out.println("4. 도서 출시일");
		System.out.print("번호 선택 : ");

		try {
			sel = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("올바른 값을 입력해 주세요");
			System.out.println("처음으로 돌아감니다.");
		}

		switch (sel) {
		case 1: {
			System.out.print("1. 도서 번호 : ");
			try {
				isbn = sc.nextLine();
				if (isbn.length() > 10 || !isbn.matches("\\d+")) {
					System.out.println("ISBN은 숫자로 최대 10자리 입력해야 합니다.");
					return;
				}
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해 주세요");
				return;
			}
			type = "isbn";
			searchBook(book, name, type);
			break;
		}
		case 2: {
			System.out.println("제목은 최소 3자 이상 입력해 주세요");
			System.out.print("2. 도서 제목 : ");
			try {
				name = sc.nextLine();
				if (name.length() > 2) {
					System.out.println("제목은 최소 3자 이상 입력해 주세요");
					return;
				}
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해 주세요");
				return;
			}
			type = "name";
			searchBook(book, name, type);

			break;
		}
		case 3: {
			System.out.println("저자는 최소 2자 이상 입력해 주세요");
			System.out.print("3. 도서 저자 : ");
			try {
				author = sc.nextLine();
				if (author.length() > 1) {
					System.out.println("저자는 최소 2자 이상 입력해 주세요");
					return;
				}
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해 주세요");
				return;
			}
			type = "author";
			searchBook(book, author, type);
			break;
		}
		case 4: {
			System.out.print("4. 도서 출시일");
			System.out.print("검색할 날짜 범위를 입력하세요 (예: 2020-01-01, 2021-12-31): ");
			String date = sc.nextLine();
			type = "date";
			searchBook(book, date, type);
			break;
		}
		}
	}
	
	// 도서 검색기능 분리
		public static void searchBook(Map<Object, Book> bookMap, String searchValue, String searchType) {
			if (searchType.equals("isbn")) {
				boolean found = book.entrySet().stream().filter(entry -> ((String) entry.getKey()).contains(isbn))
						.peek(entry -> {
							System.out.println("ISBN: " + entry.getKey());
							System.out.println("책 제목: " + entry.getValue().getBookName());
							System.out.println("저자: " + entry.getValue().getBookAuthor());
							System.out.println("가격: " + entry.getValue().getBookPrice());
							System.out.println("----------------------------");
						}).count() > 0;
				if (!found) {
					System.out.println("만족하는 도서가 없습니다.");
				}
			} else if (searchType.equals("name")) {
				boolean found = book.entrySet().stream()
						.filter(entry -> entry.getValue().getBookName().contains(searchValue)).peek(entry -> {
							System.out.println("ISBN: " + entry.getKey());
							System.out.println("책 제목: " + entry.getValue().getBookName());
							System.out.println("저자: " + entry.getValue().getBookAuthor());
							System.out.println("가격: " + entry.getValue().getBookPrice());
							System.out.println("----------------------------");
						}).count() > 0;
				if (!found) {
					System.out.println("만족하는 도서가 없습니다.");
				}

			} else if (searchType.equals("author")) {
				boolean found = book.entrySet().stream()
						.filter(entry -> entry.getValue().getBookAuthor().contains(searchValue)).peek(entry -> {
							System.out.println("ISBN: " + entry.getKey());
							System.out.println("책 제목: " + entry.getValue().getBookName());
							System.out.println("저자: " + entry.getValue().getBookAuthor());
							System.out.println("가격: " + entry.getValue().getBookPrice());
							System.out.println("----------------------------");
						}).count() > 0;
				if (!found) {
					System.out.println("만족하는 도서가 없습니다.");
				}
			} else if (searchType.equals("date")) {

				try {
					String[] dates = searchValue.split(","); // startDate, endDate를 구분자로 나눠서 받기
					LocalDate startDate = LocalDate.parse(dates[0].trim());
					LocalDate endDate = LocalDate.parse(dates[1].trim());

					boolean found = book.entrySet().stream().filter(entry -> {
						LocalDate publishDate = entry.getValue().getBookDate();
						return !publishDate.isBefore(startDate) && !publishDate.isAfter(endDate);
					}).peek(entry -> { // 해당 책 출력
						System.out.println("ISBN: " + entry.getKey());
						System.out.println("책 제목: " + entry.getValue().getBookName());
						System.out.println("저자: " + entry.getValue().getBookAuthor());
						System.out.println("가격: " + entry.getValue().getBookPrice());
						System.out.println("출판 날짜: " + entry.getValue().getBookDate());
						System.out.println("----------------------------");
					}).count() > 0;
					if (!found) {
						System.out.println("만족하는 도서가 없습니다.");
					}
				} catch (Exception e) {
					System.out.println("날짜 범위 형식이 잘못되었습니다. 예시: 2020-01-01, 2021-12-31");
				}
			}
		}

	// 도서 삭제
	public static void bookDel() {
		System.out.print("삭제하려는 ISBN값을 선택해 주세요 : ");
		try {
			isbn = sc.nextLine();
			if (isbn.length() > 10 || !isbn.matches("\\d+")) {
				System.out.println("ISBN은 숫자로 최대 10자리 입력해야 합니다.");
				return;
			}
			isbn = String.format("%010d", Integer.parseInt(isbn)); // 앞에 0 채우기
		} catch (Exception e) {
			System.out.println("올바른 값을 입력해 주세요");
			return;
		}
		while (true) {
			System.out.print("정말로 삭제하시겠습니까? Y/N : ");
			String sel = sc.nextLine();
			if (sel.toUpperCase().equals("Y")) {
				if (book.containsKey(isbn)) {
					book.remove(isbn);
					System.out.println("삭제가 완료되었습니다.");
				} else {
					System.out.println("해당 ISBN의 책을 찾을 수 없습니다.");
				}
			} else if (sel.toUpperCase().equals("N")) {
				System.out.println("취소하셨습니다 처음으로 돌아감니다.");
				break;
			} else {
				System.out.println("잘못 누르셨습니다. 처음으로 돌아감니다.");
				return;
			}
		}
	}

	

	// 메인스트림
	// 건들필요 없음
	public static void main(String[] args) {

		int sel;
		System.out.println("도서 관리 프로그램 시작");
		while (true) {
			System.out.println("-----------------------");
			System.out.println("1. 도서 추가");
			System.out.println("2. 도서 삭제");
			System.out.println("3. 도서 검색");
			System.out.println("4. 도서 목록");
			System.out.println("5. 도서 프로그램 종료");
			System.out.print("원하는 기능을 선택해 주세요");
			try {
				sel = Integer.parseInt(sc.nextLine());

			} catch (Exception e) {
				System.out.println("올바른 값을 입력해 주세요");
				continue;
			}
			switch (sel) {
			case 1: {
				register();
				break;
			}
			case 2: {
				System.out.println("도서 삭제");
				bookSerch();
				bookDel();
				break;
			}
			case 3: {
				System.out.println("-----------------------");
				System.out.println("도서 검색");
				bookSerch();
				break;
			}
			case 4: {
				System.out.println("도서 목록");
				System.out.println("-----------------------");
				BookList();
				break;
			}
			case 5: {
				System.out.println("시스템 종료");
				sc.close();
				System.exit(0);
				break;
			}

			}

		}
	}
}
