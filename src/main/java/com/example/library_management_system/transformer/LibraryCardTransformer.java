package com.example.library_management_system.transformer;

import com.example.library_management_system.Enum.CardStatus;
import com.example.library_management_system.dto.response.LibraryCardResponse;
import com.example.library_management_system.model.LibraryCard;
import com.example.library_management_system.model.Student;

import java.util.UUID;

public class LibraryCardTransformer {
    public static LibraryCardResponse PrepareCardFromStudent(Student student){
        return LibraryCardResponse.builder()
                .issueDate(student.getLibraryCard().getIssueDate())
                .cardStatus(student.getLibraryCard().getCardStatus())
                .cardNo(student.getLibraryCard().getCardNo())
                .build();
    }

    public static LibraryCard PrepareCard(){
        return LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .build();
    }
}
