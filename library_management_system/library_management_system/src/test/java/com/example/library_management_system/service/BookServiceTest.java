package com.example.library_management_system.service;

import com.example.library_management_system.dto.BookDto;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

//    @Test
//    public void findByIdFoundTest() {
//        Optional<Book> book = Optional.of(new Book( "Book1", "Author1", "1911", "10101010",false));
//         Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(book);
//        Optional<BookDto> bookDto = Optional.ofNullable(bookService.findById(1L));
//        assertEquals(true, bookDto.isPresent());
//    }

//    @Test
//    public void findByIdNotFoundTest() {
//        Optional<Book> book = Optional.of(new Book( "Book1", "Author1", "1911", "10101010",false));
//        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(book);
//        Optional<BookDto> bookDto = Optional.ofNullable(bookService.findById(1L));
//        assertTrue(bookDto.isPresent());
//    }
}
