package com.dangercode.testcase.service;

import com.dangercode.testcase.model.BookDto;
import com.dangercode.testcase.model.BookIdDto;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.awt.print.Book;


@Service
public class BookServiceClient {

    public ResponseEntity<BookDto> getBookById(String book) {

        BookDto bookFromService = new BookDto(new BookIdDto("bookService"),"title1",2021,"goothe","isn");

        return ResponseEntity.ok(bookFromService);
    }

}
