package com.nttdata.bootcoin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcoin.entity.BootCoin;
import com.nttdata.bootcoin.entity.dto.YankiCommand;
import com.nttdata.bootcoin.repository.BootCoinRepository;
import com.nttdata.bootcoin.service.IBootCoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinServiceImpl implements IBootCoinService {

  private static final Logger log = LoggerFactory
      .getLogger(BootCoinServiceImpl.class);

  private static final double COMPRA = 4.80;

  private static final double VENTA = 4.71;

  @Autowired
  private YankiDTOServiceImpl service;

  @Autowired
  private BootCoinRepository repository;

  @Override
  public Mono<BootCoin> buyBootCoin(BootCoin bootCoin) {
    return null;
  }

  /*
   * public Mono<BootCoin> buyBootCoin(BootCoin bootCoin){ return
   * service.findByPhoneNumber(bootCoin.getPhoneNumber()) .flatMap(yanki->{
   * if(yanki.getAmount()- bootCoin.getAmount()*COMPRA<0){
   * log.info("No tiene suficiente dinero en yanki"); return
   * Mono.just(BootCoin.builder().build()); }else{
   * yanki.setAmount(yanki.getAmount() - bootCoin.getAmount()*COMPRA);
   * bootCoin.setAmount(bootCoin.getAmount() + bootCoin.getAmount()*COMPRA );
   * return service.updateYanki(yanki).flatMap( d -> repository.save(bootCoin));
   * } }); }
   */

  @Override
  public Mono<BootCoin> createBootCoin(BootCoin bootCoinDocument) {
    return service.findByPhoneNumber(bootCoinDocument.getPhoneNumber())
        .flatMap(c -> {
          if (c.getOwnerName() == null) {
            log.info("Required Yanki Account");
            return Mono.just(BootCoin.builder().build());
          }
          bootCoinDocument.setTypeOfAccount("BOOT_COIN");
          bootCoinDocument
              .setYanki(YankiCommand.builder().phoneNumber(c.getPhoneNumber())
                  .ownerName(c.getOwnerName()).build());
          c.setAmount(c.getAmount() - bootCoinDocument.getAmount() * COMPRA);
          return service.updateYanki(c)
              .flatMap(d -> repository.save(bootCoinDocument));
        });
  }

  @Override
  public Mono<BootCoin> updateBootCoin(String id, BootCoin bootCoinDocument) {
    return repository.findById(id).flatMap(c -> {
      c.setAmount(bootCoinDocument.getAmount());
      return repository.save(c);
    });
  }

  @Override
  public Mono<BootCoin> findByPhoneNumber(String phoneNumber) {
    return repository.findByPhoneNumber(phoneNumber);
  }

  @Override
  public Mono<BootCoin> create(BootCoin o) {
    return repository.save(o);
  }

  @Override
  public Flux<BootCoin> findAll() {
    return repository.findAll();
  }

  @Override
  public Mono<BootCoin> findById(String s) {
    return repository.findById(s);
  }

  @Override
  public Mono<BootCoin> update(BootCoin o) {
    return repository.save(o);
  }

  @Override
  public Mono<Void> delete(BootCoin o) {
    return repository.delete(o);
  }
}
