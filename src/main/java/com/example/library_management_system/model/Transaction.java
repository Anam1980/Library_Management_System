package com.example.library_management_system.model;

import com.example.library_management_system.Enum.TransationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue
    int id;

    String transactionNumber;

    @CreationTimestamp
    Date transactionTime;

    TransationStatus transactionStatus;

    @JoinColumn
     @ManyToOne
    Book book;

    @ManyToOne
    @JoinColumn
    LibraryCard libraryCard;
}
