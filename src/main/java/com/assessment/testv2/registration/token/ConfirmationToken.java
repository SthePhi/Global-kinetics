package com.assessment.testv2.registration.token;

import com.assessment.testv2.model.ApplicationUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    private Long id;
    @Column(nullable = false)
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private ApplicationUser applicationUser;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, ApplicationUser applicationUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.applicationUser = applicationUser;
    }
}
