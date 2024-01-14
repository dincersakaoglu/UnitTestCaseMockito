package com.dangercode.testcase.model;


public class BookIdDto {

    private final String bookId;
    private final String isbn;

    // @JvmOverloads annotation Kotlin'de kullanılan bir özellik olduğu için
    // aşağıda ilgili constructor'ları elle eklemekteyiz.

    public BookIdDto() {
        this.bookId = "";
        this.isbn = "";
    }

    public BookIdDto(String bookId) {
        this.bookId = bookId;
        this.isbn = "";
    }

    public BookIdDto(String bookId, String isbn) {
        this.bookId = bookId;
        this.isbn = isbn;
    }

    public String getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    // convert metodu için static bir metod ekledik
    public static BookIdDto convert(String id, String isbn) {
        return new BookIdDto(id, isbn);
    }

    // toString() metodu, sınıfın özelliklerini içeren bir metin temsilini oluşturmak için kullanılabilir
    @Override
    public String toString() {
        return "BookIdDto{" +
                "bookId='" + bookId + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

