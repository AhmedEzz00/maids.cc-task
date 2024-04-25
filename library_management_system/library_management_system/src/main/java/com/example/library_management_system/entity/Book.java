package com.example.library_management_system.entity;

import com.example.library_management_system.Error.exceptions.DeleteAssosiatedRecordsException;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String title;

    @Column(length = 100)
    private String author;

    @Column
    private String publicationYear;

    @Column(length = 50)
    private String isbn;

    @ManyToOne()
    @JoinColumn(name="patron_id",insertable = false,updatable = false )
    private Patron patron;

    @Column(name = "patron_id",nullable = true)
    private Long patronId;

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private List<BorrowingRecord> borrowingRecords;

    @Column
    private boolean isAvailable= true;
    @PreRemove
    public void checkIfAnyBooksAreBorrowed() {
        if (borrowingRecords!= null && borrowingRecords.size() > 0) {
            throw new DeleteAssosiatedRecordsException("Book is borrowed");
        }
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }

}
