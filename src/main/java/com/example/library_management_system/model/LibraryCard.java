package com.example.library_management_system.model;

import com.example.library_management_system.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "LibraryCards")
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String cardNo;

    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;

    @CreationTimestamp
    Date issueDate;

    @OneToOne
    @JoinColumn
    Student student;

    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    List<Transaction>transactions = new ArrayList<>();


}