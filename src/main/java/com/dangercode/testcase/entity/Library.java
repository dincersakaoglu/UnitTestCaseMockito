package com.dangercode.testcase.entity;



import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private final String id;

    @ElementCollection
    private final List<String> userBook;

    // @JvmOverloads annotation Kotlin'de kullanılan bir özellik olduğu için
    // aşağıda ilgili constructor'ları elle eklemekteyiz.

    public Library() {
        this.id = "";
        this.userBook = new ArrayList<>();
    }

    public Library(String id) {
        this.id = id;
        this.userBook = new ArrayList<>();
    }

    public Library(String id, List<String> userBook) {
        this.id = id;
        this.userBook = userBook;
    }

    public String getId() {
        return id;
    }

    public List<String> getUserBook() {
        return userBook;
    }
}

