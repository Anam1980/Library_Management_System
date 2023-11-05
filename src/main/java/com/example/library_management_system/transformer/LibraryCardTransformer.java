package com.example.library_management_system.transformer;

import com.example.library_management_system.Enum.CardStatus;
import com.example.library_management_system.model.LibraryCard;

import java.util.UUID;

public class LibraryCardTransformer {

    public static LibraryCard PrepareCard(){
        return LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .build();
    }


}
