package com.nttdata.bootcoin.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcoin.entity.BootCoin;

import reactor.core.publisher.Mono;

public interface BootCoinRepository extends ReactiveMongoRepository<BootCoin, String> {
  Mono<BootCoin> findByPhoneNumber(String customerIdentityNumber);
}
