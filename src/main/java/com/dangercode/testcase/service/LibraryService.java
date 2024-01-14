package com.dangercode.testcase.service;


import com.dangercode.testcase.entity.Library;
import com.dangercode.testcase.exceptions.LibraryNotFoundException;
import com.dangercode.testcase.repository.LibraryRepository;
import com.dangercode.testcase.model.LibraryDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    public LibraryService(LibraryRepository libraryRepository,
                          BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibraryById(String id) {
        Library library = getLibraryById(id);

        LibraryDto libraryDto = new LibraryDto(library.getId(),
                library.getUserBook()
                        .stream()
                        .map(book -> bookServiceClient.getBookById(book).getBody())
                        .collect(Collectors.toList()));
        return libraryDto;
    }

    private Library getLibraryById(String id){
        return libraryRepository.findById(id)
                 .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + id));
    }
}
