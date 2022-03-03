package com.nttdata.bootcoin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcoin.entity.dto.BootCoinTransactionDTO;
import com.nttdata.bootcoin.service.ITransactionBootCoinService;

import reactor.core.publisher.Mono;

@Service
public class TransactionBootCoinServiceImpl
    implements
      ITransactionBootCoinService {

  @Autowired
  private WebClient.Builder client;

  @Override
  public Mono<BootCoinTransactionDTO> save(
      BootCoinTransactionDTO transactionDTO) {
    return client.build().post().uri("localhost:9021/api/bootcoin-transaction")
        .body(Mono.just(transactionDTO), BootCoinTransactionDTO.class)
        .retrieve().bodyToMono(BootCoinTransactionDTO.class);
  }
}
