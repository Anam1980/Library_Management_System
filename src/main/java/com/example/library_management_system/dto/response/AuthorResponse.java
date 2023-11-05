package com.example.library_management_system.dto.response;

import com.example.library_management_system.model.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {
    String name;
    int age;
    String emailId;
    Date lastActivity ;

}
