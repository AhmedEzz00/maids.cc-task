package com.example.library_management_system.service;
import com.example.library_management_system.Error.exceptions.DuplicatedRecordException;
import com.example.library_management_system.Error.exceptions.RecordNotFoundException;
import com.example.library_management_system.dto.BookDto;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {
   @Autowired
   private BookRepository bookRepository;

   @Cacheable(value = "allBooksCache",key = "#root.methodName")
   public List<Book> findAll() {
       return bookRepository.findAll();
    }

    @Cacheable(value = "bookByIdCache",key = "#id")
    public BookDto findById(long id) {
       Optional<Book> optionalBook = bookRepository.findById(id);
       if(!optionalBook.isPresent()){
           throw new RecordNotFoundException("Book with id: "+ id +" is not found");
       }
       BookDto bookDto= new BookDto();
       bookToDtoMapper(optionalBook.get(),bookDto);
       return bookDto;
   }

    @CacheEvict(value = {"allBooksCache","bookByIdCache"},key = "#root.methodName",allEntries = true)
    public String insert(BookDto bookDto) {
       Optional<Book> optionalBook = bookRepository.findByIsbn(bookDto.getIsbn());
       if(optionalBook.isPresent()){
           throw new DuplicatedRecordException("Book with isdn: "+ bookDto.getIsbn() +" duplicated");
       }
       Book newBook = new Book();
        dtoToBookMapper(bookDto, newBook);
        bookRepository.save(newBook);
       return "book with title " + bookDto.getTitle()+" inserted";
    }



    @CacheEvict(value = {"allBooksCache","bookByIdCache"},key = "#root.methodName",allEntries = true)
    public String update(Long id, BookDto bookDto) {
       Optional<Book> optionalBook = bookRepository.findById(id);
       if(!optionalBook.isPresent()){
           throw new RecordNotFoundException("Book with id: "+ id +" is not found");
       }
       Book book = optionalBook.get();
       assignUpdatingBook(book,bookDto);
       bookRepository.save(book);
       return "book with id " + id+" updated";
  }

    @CacheEvict(value = {"allBooksCache","bookByIdCache"},key = "#root.methodName",allEntries = true)

    public String updateBookStatus(Long id,BookDto bookdto){
      Optional<Book> optionalBook = bookRepository.findById(id);
      if(!optionalBook.isPresent()){
          throw new RecordNotFoundException("Book with id: "+ id +" is not found");
      }
      Book book = optionalBook.get();
      book.setAvailable(bookdto.isAvailable());
      bookRepository.save(book);
      return "book with id " + id+" updated";
  }

    @CacheEvict(value = {"allBooksCache","bookByIdCache"},key = "#root.methodName",allEntries = true)

    public String deleteById(long id) {
       Optional<Book> optionalBook = bookRepository.findById(id);
       if(!optionalBook.isPresent()){
           throw new RecordNotFoundException("Book with id: "+ id +" is not found");
       }
       bookRepository.deleteById(id);
        return "book with id " + id+" deleted";
   }



    private void assignUpdatingBook(Book updating,BookDto dto){
        updating.setTitle(dto.getTitle()==null? updating.getTitle():dto.getTitle());
        updating.setAuthor(dto.getAuthor()==null? updating.getAuthor():dto.getAuthor());
        updating.setIsbn(dto.getIsbn()==null? updating.getIsbn():dto.getIsbn());
        updating.setPublicationYear(dto.getPublicationYear()==null? updating.getPublicationYear():dto.getPublicationYear());
    }

    private void bookToDtoMapper(Book book, BookDto bookDto){
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setAvailable(book.isAvailable());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setBorrowingRecords(book.getBorrowingRecords());
        bookDto.setPublicationYear(book.getPublicationYear());
    }

    private void dtoToBookMapper(BookDto bookDto, Book newBook) {
        newBook.setAuthor(bookDto.getAuthor());
        newBook.setTitle(bookDto.getTitle());
        newBook.setIsbn(bookDto.getIsbn());
        newBook.setPublicationYear(bookDto.getPublicationYear());
    }

}
