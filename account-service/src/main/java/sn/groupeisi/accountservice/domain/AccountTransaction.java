package sn.groupeisi.accountservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTransaction {

    @Id @GeneratedValue
    private Long id;
    private LocalDateTime localDateTime;
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    private Account account;


}
