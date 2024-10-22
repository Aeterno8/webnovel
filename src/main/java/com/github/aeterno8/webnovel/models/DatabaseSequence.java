package com.github.aeterno8.webnovel.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;  // Identifier for the sequence
    private long seq;   // Current sequence value
}