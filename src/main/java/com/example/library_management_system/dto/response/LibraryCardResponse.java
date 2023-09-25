package com.example.library_management_system.dto.response;

import com.example.library_management_system.Enum.CardStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryCardResponse {

    String cardNo;

    Date issueDate;

    CardStatus cardStatus;
}
