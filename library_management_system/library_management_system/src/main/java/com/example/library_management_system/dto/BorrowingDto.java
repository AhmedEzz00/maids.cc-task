package com.example.library_management_system.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class BorrowingDto {

    @NotNull
    private Long patronId;


    @NotNull
    private Long bookId;

    private Date borrowDate;

    private Date returnDate;

    public Long getPatronId() {
        return patronId;
    }

    public void setPatronId(Long patronId) {
        this.patronId = patronId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
