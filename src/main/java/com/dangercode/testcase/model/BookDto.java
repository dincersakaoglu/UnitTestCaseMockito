package com.dangercode.testcase.model;



public class BookDto {

    private final BookIdDto id;
    private final String title;
    private final Integer year;
    private final String author;
    private final String pressName;

    // @JvmOverloads annotation Kotlin'de kullanılan bir özellik olduğu için
    // aşağıda ilgili constructor'ları elle eklemekteyiz.

    public BookDto() {
        this.id = null;
        this.title = "";
        this.year = 0;
        this.author = "";
        this.pressName = "";
    }

    public BookDto(BookIdDto id) {
        this.id = id;
        this.title = "";
        this.year = 0;
        this.author = "";
        this.pressName = "";
    }

    public BookDto(BookIdDto id, String title, Integer year, String author, String pressName) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.pressName = pressName;
    }

    public BookIdDto getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getPressName() {
        return pressName;
    }

    // toString() metodu, sınıfın özelliklerini içeren bir metin temsilini oluşturmak için kullanılabilir
    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", pressName='" + pressName + '\'' +
                '}';
    }
}
