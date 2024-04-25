package com.example.library_management_system.service;
import com.example.library_management_system.Error.exceptions.RecordNotFoundException;
import com.example.library_management_system.dto.BookDto;
import com.example.library_management_system.dto.BorrowingDto;
import com.example.library_management_system.dto.PatronDto;
import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.BorrowingRecord;
import com.example.library_management_system.entity.Patron;
import com.example.library_management_system.Error.exceptions.BookAlreadeBorrowedExcepotion;
import com.example.library_management_system.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class BorrowingRecordService {


    @Autowired
    BookService bookService;

    @Autowired
    PatronService patronService;

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Transactional
    public void submitBorrowing(BorrowingDto borrowingDto) {
        checkvailidation(borrowingDto.getBookId(), borrowingDto.getPatronId());
        BookDto bookDto = new BookDto();
        bookDto.setAvailable(false);
        bookService.updateBookStatus(borrowingDto.getBookId(),bookDto);
        var borrowing= new BorrowingRecord();
        mapBorrowingDtoToBorrowingRecord(borrowingDto, borrowing);
        borrowingRecordRepository.save(borrowing);
    }

    @Transactional
    public void returnBook(Long borrowingId) {
        Optional<BorrowingRecord> optionalBorrowingRecord= borrowingRecordRepository.findById(borrowingId);
        if(!optionalBorrowingRecord.isPresent()){
            throw new RecordNotFoundException("Record with id: "+borrowingId+" not found");
        }
        var borrowingRecord= optionalBorrowingRecord.get();
        borrowingRecord.setReturnDate(Date.from(Instant.now()));
        BookDto bookDto = new BookDto();
        bookDto.setAvailable(true);
        bookService.updateBookStatus(borrowingRecord.getBookId(),bookDto);
        borrowingRecordRepository.save(borrowingRecord);
    }


    private void mapBorrowingDtoToBorrowingRecord(BorrowingDto borrowingDto, BorrowingRecord borrowing) {
        borrowing.setPatronId(borrowingDto.getPatronId());
        borrowing.setBookId(borrowingDto.getBookId());
        borrowing.setBorrowDate(Date.from(Instant.now()));
    }
   private void checkvailidation(Long bookId,Long patronId) {
       Optional<BookDto> optionalBookDto = Optional.ofNullable(bookService.findById(bookId));
       Optional<PatronDto> optionalPatronDto= Optional.ofNullable(patronService.findById(patronId));
       if(!optionalBookDto.get().isAvailable()){
           throw new BookAlreadeBorrowedExcepotion("Book with id: "+bookId+" is already borrowed");
       }
       if(!optionalPatronDto.isPresent()){
           throw new RecordNotFoundException("Patron with id: "+patronId+" not found");
       }
   }
}
