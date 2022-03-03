package com.nttdata.bootcoin.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nttdata.bootcoin.entity.dto.YankiCommand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "bootcoin")
public class BootCoin {
  @Id
  private String id;

  private String name;

  private String customerIdentityType;

  private String customerIdentityNumber;

  private String phoneNumber;

  private String email;

  private YankiCommand yanki;

  private String typeOfAccount;

  private Double amount;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss ")
  private LocalDateTime createdAt = LocalDateTime.now();

}
