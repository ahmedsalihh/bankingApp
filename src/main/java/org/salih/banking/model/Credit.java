package org.salih.banking.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String status;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
