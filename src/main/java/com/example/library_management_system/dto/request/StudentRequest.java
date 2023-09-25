package com.example.library_management_system.dto.request;

import com.example.library_management_system.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    String name;

    int age;

    String email;

    Gender gender;
}
