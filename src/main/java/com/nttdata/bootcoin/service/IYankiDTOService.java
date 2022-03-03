package com.nttdata.bootcoin.service;

import com.nttdata.bootcoin.entity.dto.YankiDTO;

import reactor.core.publisher.Mono;

public interface IYankiDTOService {
  Mono<YankiDTO> findByPhoneNumber(String customerIdentityNumber);

  Mono<YankiDTO> updateYanki(YankiDTO yankiDocument);
}
