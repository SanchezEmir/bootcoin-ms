package com.nttdata.bootcoin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcoin.entity.dto.YankiDTO;
import com.nttdata.bootcoin.service.IYankiDTOService;

import reactor.core.publisher.Mono;

@Service
public class YankiDTOServiceImpl implements IYankiDTOService {

  @Autowired
  private WebClient.Builder client;

  private static final Logger log = LoggerFactory
      .getLogger(YankiDTOServiceImpl.class);

  @Override
  public Mono<YankiDTO> updateYanki(YankiDTO yanki) {
    return client.baseUrl("http://localhost:9015/api/yanki-account").build()
        .put().uri("/amount/{id}", yanki.getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).bodyValue(yanki).retrieve()
        .bodyToMono(YankiDTO.class);
  }

  @Override
  public Mono<YankiDTO> findByPhoneNumber(String phoneNumber) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("phoneNumber", phoneNumber);
    return client.baseUrl("http://localhost:9015/api/yanki-account").build()
        .get().uri("/phone/{phoneNumber}", params)
        .accept(MediaType.APPLICATION_JSON)
        .exchangeToMono(
            clientResponse -> clientResponse.bodyToMono(YankiDTO.class))
        .doOnNext(c -> log.info("SavingAccount Response: Account Amounth={}",
            c.getOwnerName()));
  }
}
