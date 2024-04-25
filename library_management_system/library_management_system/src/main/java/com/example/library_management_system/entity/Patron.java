package com.example.library_management_system.entity;

import com.example.library_management_system.Error.CustomErrorAttribute;
import com.example.library_management_system.Error.exceptions.DeleteAssosiatedRecordsException;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 15)
    private String mobileNumber;

    @Column(length = 50)
    private String email;

    @OneToMany(mappedBy = "patron", orphanRemoval = false)
    private List<Book> books;

    @OneToMany(mappedBy = "patron", orphanRemoval = true)
    List<BorrowingRecord> borrowingRecords;

    @PreRemove
    public void checkIfAnyBooksAreBorrowed() {
        if (books!= null && books.size() > 0) {
            throw new DeleteAssosiatedRecordsException("Patron has borrowed books");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecord) {
        this.borrowingRecords = borrowingRecords;
    }
}
