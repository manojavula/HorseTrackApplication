package com.manu.horsetrack.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.manu.horsetrack.entity.Horse;
import com.manu.horsetrack.entity.Inventory;
import com.manu.horsetrack.entity.Wager;
import com.manu.horsetrack.repository.HorseRepository;
import com.manu.horsetrack.repository.InventoryRepository;

import java.util.List;

@Service
public class ReportService {

  public ReportService() {
  }

  @Autowired
  private HorseRepository horseRepository;

  @Autowired
  private InventoryRepository inventoryRepository;

  @Value("${currency.symbol}")
  private String currencySymbol;

  // Messages
  @Value("${message.inventory}")
  private String messageInventory;
  @Value("${message.horses}")
  private String messageHorses;
  @Value("${message.no.payout}")
  private String messageNoPayout;
  @Value("${message.payout}")
  private String messagePayout;
  @Value("${message.dispensing}")
  private String messageDispensing;

  // Error Messages
  @Value("${error.message.insufficient.funds}")
  private String errorMessageInsufficientFunds;
  @Value("${error.message.invalid.bet}")
  private String errorMessageInvalidBet;
  @Value("${error.message.invalid.command}")
  private String errorMessageInvalidCommand;
  @Value("${error.message.invalid.horse.number}")
  private String errorMessageInvalidHorseNumber;

  public void printHorses() {

    Iterable<Horse> horses = horseRepository.findAll();
    System.out.println(messageHorses);
    horses.forEach((horse) -> {
      System.out.println(horse.getHorseNumber()
          +","+horse.getHorseName()
          +","+horse.getOdds()
          +","+horse.getRaceStatus().toString().toLowerCase());
    });
  }

  public void printInventory() {

    Iterable<Inventory> inventories = inventoryRepository.findAll();
    System.out.println(messageInventory);
    inventories.forEach((inventory) -> {
      System.out.println(currencySymbol
          +inventory.getDenomination()
          +","+inventory.getBillCount());
    });
  }

  public void printInvalidCommand(String command) {
    System.out.println(errorMessageInvalidCommand + " " + command);
  }

  public void printInvalidHorse(int horseNumber) {
    System.out.println(errorMessageInvalidHorseNumber + " " + horseNumber);
  }

  public void printInvalidBet(String invalidBet) {
    System.out.println(errorMessageInvalidBet + " " + invalidBet);
  }

  public void printPayout(String horseName, int amountWon) {
    System.out.println(messagePayout + " " + horseName + "," + currencySymbol + amountWon);
  }

  public void printNoPayout(String horseName) {
    System.out.println(messageNoPayout + " " + horseName);
  }

  public void printInsufficientFunds(int amountWon) {
    System.out.println(errorMessageInsufficientFunds + " " + currencySymbol + amountWon);
  }

  public void printDispense(List<Wager> dispense) {
    System.out.println(messageDispensing);
    dispense.forEach(wager-> {
      System.out.println(currencySymbol
          + wager.getDenomination()
          + ","
          + wager.getBillCount()
          );
    });
  }

  public void printErrorMessage(String message) {
    System.out.println(message);
  }
  
  public void startup() {
    printInventory();
    printHorses();
  }

}
