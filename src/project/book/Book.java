package project.book;

import java.time.LocalDate;

public class Book {
	String bookIsbn; // 도서 번호
	int bookPrice; // 도서 가격
	String bookName; // 도서 제목
	String bookAuthor; // 도서 저자
	LocalDate bookDate; // 출판일

	public Book(String isbn, int price, String bookName, String author, LocalDate date) {
		this.bookIsbn = isbn;
		this.bookPrice = price;
		this.bookName = bookName;
		this.bookAuthor = author;
		this.bookDate = date;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public LocalDate getBookDate() {
		return bookDate;
	}

}
