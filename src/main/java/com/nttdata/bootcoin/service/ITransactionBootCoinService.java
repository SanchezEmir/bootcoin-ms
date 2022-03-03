package com.nttdata.bootcoin.service;

import com.nttdata.bootcoin.entity.dto.BootCoinTransactionDTO;

import reactor.core.publisher.Mono;

public interface ITransactionBootCoinService {
  Mono<BootCoinTransactionDTO> save(BootCoinTransactionDTO transactionDTO);
}
