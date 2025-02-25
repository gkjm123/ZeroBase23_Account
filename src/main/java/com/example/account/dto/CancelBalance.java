package com.example.account.dto;

import com.example.account.aop.AccountLockIdInterface;
import com.example.account.type.TransactionResultType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

public class CancelBalance {

  @Getter
  @Setter
  @AllArgsConstructor
  public static class Request implements AccountLockIdInterface {

    @NotBlank
    private String transactionId;

    @NotBlank
    @Size(min = 10, max = 10)
    private String accountNumber;

    @NotNull
    @Min(10)
    @Max(1000_000_000)
    private Long amount;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {

    private String accountNumber;
    private TransactionResultType transactionResultType;
    private String transactionId;
    private Long amount;
    private LocalDateTime transactionAt;

    public static Response from(TransactionDto transactionDto) {
      return Response.builder()
          .accountNumber(transactionDto.getAccountNumber())
          .transactionAt(transactionDto.getTransactedAt())
          .amount(transactionDto.getAmount())
          .transactionResultType(transactionDto.getTransactionResultType())
          .transactionId(transactionDto.getTransactionId())
          .build();
    }
  }
}