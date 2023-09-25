package com.example.library_management_system.dto.response;

import com.example.library_management_system.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    String title;

    int noOfPages;

    Genre genre;

    double cost;

    String authorName;

}
