package com.example.library_management_system.dto.response;

import com.example.library_management_system.model.LibraryCard;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {

    String name;

    String email;

   LibraryCardResponse libraryCardResponse;
}
