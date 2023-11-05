package com.example.library_management_system.model;

import com.example.library_management_system.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue
    int id;

    String transactionNumber;

    @CreationTimestamp
    Date transactionTime;

    TransactionStatus transactionStatus;

    @JoinColumn
     @ManyToOne
    Book book;

    @ManyToOne
    @JoinColumn
    LibraryCard libraryCard;


}
