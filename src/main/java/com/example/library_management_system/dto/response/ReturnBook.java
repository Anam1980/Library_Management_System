package com.example.library_management_system.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBook {
    String studentName;

    int studentId;

    Date returnDate;

    String bookName;

    int bookId;
}
