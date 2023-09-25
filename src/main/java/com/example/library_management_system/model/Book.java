package com.example.library_management_system.model;
import com.example.library_management_system.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    int noOfPages;

    @Enumerated(EnumType.STRING)
    Genre genre;

    double cost;

    boolean isIssued;

    @ManyToOne
    @JoinColumn
    Author author;

    //bidirectional
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)//every thing  of parent contain by chil
    List<Transaction>transactions=new ArrayList<>();

}