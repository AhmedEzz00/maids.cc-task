package com.example.library_management_system.config;

import com.example.library_management_system.dto.BookDto;
import com.example.library_management_system.dto.PatronDto;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.BorrowingRecord;
import com.example.library_management_system.entity.Patron;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class StartupApp implements CommandLineRunner {

    @Autowired
    BookService bookService;

    @Autowired
    PatronService patronService;



    @Override
    public void run(String... args) throws Exception {
        PatronDto patron1 = new PatronDto();
        patron1.setName("Patron1");
        patron1.setMobileNumber("0100000000");
        patron1.setEmail("email1@email.com");
        patronService.insert(patron1);
//
        PatronDto patron2 = new PatronDto();
        patron2.setName("Patron2");
        patron2.setMobileNumber("0200000000");
        patron2.setEmail("email2@email.com");
        patronService.insert(patron2);

        //BorrowingRecord borrowingRecord1 = new BorrowingRecord();
        //borrowingRecord1.setBookId(1L);
        //borrowingRecord1.setPatronId(1L);


       BookDto book1 = new BookDto();
       book1.setTitle("Book1");
       book1.setAuthor("Author1");
       book1.setPublicationYear("1911");
       book1.setIsbn("10101010");
       bookService.insert(book1);


        BookDto book2 = new BookDto();
        book2.setTitle("Book2");
        book2.setAuthor("Author2");
        book2.setPublicationYear("1922");
        book2.setIsbn("20202020");
        bookService.insert(book2);

//
//        Book book3 = new Book();
//        book3.setName("Book3");
//        book3.setPrice(3000);
//        book3.setAuthor(authorService.getOneById(author3.getId()).getBody());
//
//        bookService.insertAll(Arrays.asList(book1, book2, book3));

    }
}
