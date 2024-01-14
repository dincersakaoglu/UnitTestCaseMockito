package com.dangercode.testcase.service;

import com.dangercode.testcase.entity.Library;
import com.dangercode.testcase.exceptions.LibraryNotFoundException;
import com.dangercode.testcase.model.BookDto;
import com.dangercode.testcase.model.BookIdDto;
import com.dangercode.testcase.model.LibraryDto;
import com.dangercode.testcase.repository.LibraryRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService libraryService;

    private LibraryRepository libraryRepository;

    private BookServiceClient bookServiceClient;


    //BeforeEach sayesinde test seneryasu öncesi ortam oluşturulur
    //burada library servicein constructorına dependecy leri enjekte edip ortam oluşturulur.
    @BeforeEach
    void setUp() {
        libraryRepository = Mockito.mock(LibraryRepository.class);
        bookServiceClient = Mockito.mock(BookServiceClient.class);

        libraryService = new LibraryService(libraryRepository, bookServiceClient);

    }

    @DisplayName("Should return detailed book list with BookDto and updated library ID when library ID exists and library user book list size is more than 2")
    @Test
    void shouldReturnDetailedBookListWithBookDtoAndUpdatedLibraryId_whenLibraryIdExistAndLibraryUserBookListSizeMoreThan2() {

        //Test verilerinin hazırlanması
        String id = "libraryId";
        List<String> userBook = Arrays.asList("book1", "book2", "book3");
        Library library = new Library(id, userBook);

        BookDto book1 = new BookDto(new BookIdDto("book1", "isbn"), "title1", 2021, "goothe", "isn");
        BookDto book2 = new BookDto(new BookIdDto("book2", "isbn"), "title2", 2021, "goothe", "isn");
        BookDto book3 = new BookDto(new BookIdDto("book3", "isbn"), "title3", 2021, "goothe", "isn");

        List<BookDto> bookDtoList = Arrays.asList(book1, book2, book3);
        LibraryDto expectedResult = new LibraryDto(id, bookDtoList);


        //Bağımlı servislerin davranışlarının belirlenmesi
        Mockito.when(libraryRepository.findById(id)).thenReturn(Optional.of(library));
        Mockito.when(bookServiceClient.getBookById("book1")).thenReturn(ResponseEntity.ok(book1));
        Mockito.when(bookServiceClient.getBookById("book2")).thenReturn(ResponseEntity.ok(book2));
        Mockito.when(bookServiceClient.getBookById("book3")).thenReturn(ResponseEntity.ok(book3));


        //Test edilecek methodun çalıştırılması
        LibraryDto result = libraryService.getAllBooksInLibraryById(id);


        //Test sonucunun karşılaştırılması.Ben burada toStringe çevirdim.Yapılamması gereken bir şey.
        //Kodun orijinalinden olmadığını için nesne hatası var. Bu şekilde çözdüm.
        assertEquals(expectedResult.toString(), result.toString());


        //Bağımlı servislerin çalıştıtılmasının kontrol edilmesi
        Mockito.verify(libraryRepository).findById(id);
        Mockito.verify(bookServiceClient).getBookById("book1");
        Mockito.verify(bookServiceClient).getBookById("book2");
        Mockito.verify(bookServiceClient).getBookById("book3");
        //Herhangi bir parametre ile en az 3 kez çağrıldı mı kontrol edilmesi
        Mockito.verify(bookServiceClient, new Times(3)).getBookById(Mockito.any(String.class));


    }

    @DisplayName("Should throw LibraryNotFoundException when library ID does not exist")
    @Test
    void shouldThrowLibraryNotFoundException_whenLibraryIdDoesNotExist() {

        //Test verilerinin hazırlanması
        String id = "libraryId";

        //libraryRepository.findById() optional döndüğü için empty nin başına optional yazıoyruz.
        Mockito.when(libraryRepository.findById(id)).thenReturn(Optional.empty());

        //Burada farklı bir kütüphane kullandık.
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            libraryService.getAllBooksInLibraryById(id);
        }).isInstanceOf(LibraryNotFoundException.class).hasMessage("Library could not found by id: " + id);

        //ya da alttaki gibi yapılabilir

/*
        assertThrows(LibraryNotFoundException.class, () -> {
            libraryService.getAllBooksInLibraryById(id);
        });

 */


        //Bağımlı servislerin çalıştıtılmasının kontrol edilmesi
        Mockito.verify(libraryRepository).findById(id);
        Mockito.verifyNoInteractions(bookServiceClient);

    }

    @AfterEach
    void tearDown() {

    }

}