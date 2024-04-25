package com.example.library_management_system.dto;

import com.example.library_management_system.entity.Book;
import com.example.library_management_system.entity.BorrowingRecord;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PatronDto {

    private Long id;
    @NotEmpty()
    @Size(max = 50, message = "name cannot be more than 50 characters")
    private String name;

    @Size(max = 50, message = "email cannot be more than 50 characters")
    @NotEmpty()
    @Email()
    private String email;

    @NotEmpty()
    @Size(max = 15, message = "mobileNumber cannot be more than 15 characters")
    private String mobileNumber;

    List<BorrowingRecord> borrowingRecords;
    //private List<Book> books;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }

    //    public List<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }
}
