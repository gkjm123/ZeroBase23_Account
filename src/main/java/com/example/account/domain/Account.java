package com.example.account.domain;

import com.example.account.exception.AccountException;
import com.example.account.type.AccountStatus;
import com.example.account.type.ErrorCode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account extends BaseEntity {

  @ManyToOne
  private AccountUser accountUser;

  @Enumerated(EnumType.STRING)
  private AccountStatus accountStatus;

  private String accountNumber;
  private Long balance;
  private LocalDateTime registeredAt;
  private LocalDateTime unRegisteredAt;

  public void useBalance(Long amount) {
    if (amount > balance) {
      throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);
    }
    balance -= amount;
  }

  public void cancelBalance(Long amount) {
    if (amount < 0) {
      throw new AccountException(ErrorCode.INVALID_REQUEST);
    }
    balance += amount;
  }
}
