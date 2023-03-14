package com.springbootlab0.approach_1.domain;

import com.springbootlab0.approach_1.utils.Helper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// Lombok annotations
@Getter
@Setter
// JPA annotations
@Entity(name = "Borrow")
@Table(name = "BORROW_TABLE")
public class Borrow {
    @Id
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="BORROW_ID", updatable = false, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "librarymember_id")
    private LibraryMember borrowUser;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication borrowedPublication;

    @Column(name = "START_BORROW_DATE")
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate startBorrowDate;

    @Column(name = "DUE_BORROW_DATE")
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate dueBorrowDate;

    @Column(name = "RETURNED_BORROW_DATE")
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate returnedBorrowDate;

    @Column
    @Enumerated(EnumType.STRING)
    private BorrowStatus borrowStatus;

    public Borrow() {
        this.id = Helper.createUUID();
        // Initialize dates
        this.setStartBorrowDate(LocalDate.now());
        this.setDueBorrowDate(this.getStartBorrowDate().plusDays(15));
        this.setReturnedBorrowDate(null);
        // Initialize borrow status
        this.setBorrowStatus(BorrowStatus.IN_PROGRESS);
    }

    public Borrow(LibraryMember borrowUser, Publication borrowedPublication) {
        // Initialize the empty borrow
        this();
        // Assign the user
        this.setBorrowUser(borrowUser);
        // Change the borrowedPublication status to Borrowed
        borrowedPublication.setStatus(Status.BORROWED);
        // Assign the publication
        this.setBorrowedPublication(borrowedPublication);
    }
}
