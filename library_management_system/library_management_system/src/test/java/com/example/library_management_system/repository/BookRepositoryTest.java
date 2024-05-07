package com.example.library_management_system.repository;

import com.example.library_management_system.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void saveTest() {
        Book book = new Book("Book1", "Author1", "1911", "111", false);
        Book savedBook = bookRepository.save(book);
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getId()).isGreaterThan(0);
    }

    @Test
    public void findByIsbnTest(){
        bookRepository.save(new Book( "Book2", "Author1", "1911", "222", false));
        Optional<Book> optionalBook = bookRepository.findByIsbn("10101010");
        Assertions.assertThat(optionalBook.get()).isNotNull();
        Assertions.assertThat(optionalBook.get().getId()).isGreaterThan(0);
    }


}
