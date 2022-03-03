package com.nttdata.bootcoin.service;

import com.nttdata.bootcoin.entity.BootCoin;

import reactor.core.publisher.Mono;

public interface IBootCoinService extends ICrudService<BootCoin, String> {

  Mono<BootCoin> createBootCoin(BootCoin bootCoinDocument);

  Mono<BootCoin> updateBootCoin(String id, BootCoin bootCoinDocument);

  Mono<BootCoin> findByPhoneNumber(String phoneNumber);

  Mono<BootCoin> buyBootCoin(BootCoin bootCoin);

}
