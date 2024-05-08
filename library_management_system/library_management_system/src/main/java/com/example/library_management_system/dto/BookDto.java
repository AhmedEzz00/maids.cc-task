package com.example.library_management_system.dto;

import com.example.library_management_system.entity.BorrowingRecord;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class BookDto {

    public BookDto( String title, String author, String publicationYear, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public BookDto() {
    }

    private long id;

    @NotEmpty()
    @Size(max = 100, message = "Title cannot be more than 100 characters")
    private String title;

    @NotEmpty()
    @Size(max = 100, message = "Author cannot be more than 100 characters")
    private String author;

    @NotEmpty()
    private String publicationYear;

    @NotEmpty()
    @Size(max = 50, message = "ISBN cannot be more than 50 characters")
    private String isbn;

    //private Long patronId;

    private boolean isAvailable;

    private List<BorrowingRecord> borrowingRecords;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

//    public Long getPatronId() {
//        return patronId;
//    }
//
//    public void setPatronId(Long patronId) {
//        this.patronId = patronId;
//    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }
}
