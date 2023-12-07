package org.salih.banking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
