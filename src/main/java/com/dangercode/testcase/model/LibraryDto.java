package com.dangercode.testcase.model;



import java.util.ArrayList;
import java.util.List;

public class LibraryDto {

    private final String id;
    private final List<BookDto> userBookList;

    // @JvmOverloads annotation Kotlin'de kullanılan bir özellik olduğu için
    // aşağıda ilgili constructor'ları elle eklemekteyiz.

    public LibraryDto() {
        this.id = "";
        this.userBookList = new ArrayList<>();
    }

    public LibraryDto(String id) {
        this.id = id;
        this.userBookList = new ArrayList<>();
    }

    public LibraryDto(String id, List<BookDto> userBookList) {
        this.id = id;
        this.userBookList = userBookList;
    }

    public String getId() {
        return id;
    }

    public List<BookDto> getUserBookList() {
        return userBookList;
    }

    // toString() metodu, sınıfın özelliklerini içeren bir metin temsilini oluşturmak için kullanılabilir
    @Override
    public String toString() {
        return "LibraryDto{" +
                "id='" + id + '\'' +
                ", userBookList=" + userBookList +
                '}';
    }
}

