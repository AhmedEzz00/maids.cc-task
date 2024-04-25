package com.example.library_management_system.controller;

import com.example.library_management_system.dto.BorrowingDto;
import com.example.library_management_system.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public void submitBorrowing (@PathVariable Long bookId, @PathVariable Long patronId){
        BorrowingDto newDto = new BorrowingDto();
        newDto.setBookId(bookId);
        newDto.setPatronId(patronId);
        borrowingRecordService.submitBorrowing(newDto);
    }

    @PutMapping("/return/{borrowingId}")
    public void returnBook(@PathVariable Long borrowingId ){
        borrowingRecordService.returnBook(borrowingId);
    }

}
