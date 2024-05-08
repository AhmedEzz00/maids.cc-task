package com.example.library_management_system.service;

import com.example.library_management_system.dto.BookDto;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    public void createBookTest() {
        BookDto bookDto = new BookDto("test book", "test Author", "1900", "111111", false);
        Book book = new Book("Book1", "Author1", "1911", "10101010", false);
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        String createdBook = bookService.insert(bookDto);
        Assertions.assertThat(createdBook).isNotEmpty();
    }

    @Test
    public void findAllTest() {
        List<Book> books= new ArrayList<>();
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        List<Book> returned = bookService.findAll();
        Assertions.assertThat(returned).isEmpty();
    }

    @Test
    public void findByIdTest() {
        Optional<Book> book = Optional.of(new Book( "Book1", "Author1", "1911", "222222",false));
         Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(book);
        Optional<BookDto> bookDto = Optional.ofNullable(bookService.findById(1L));
        assertEquals(true, bookDto.isPresent());
    }


    @Test
    public void updateBookTest() {
        BookDto bookDto = new BookDto("test book", "test Author", "1900", "111111", false);
        Book book = new Book("Book1", "Author1", "1911", "10101010", false);
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
        String updatedBook = bookService.update(1L,bookDto);
        Assertions.assertThat(updatedBook).isNotEmpty();
    }

    @Test
    public void deleteBookTest(){
        Book book= new Book("delete book", "delete author", "1933", "000000", false);
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
         assertAll(()-> bookService.deleteById(1L));
    }

}
