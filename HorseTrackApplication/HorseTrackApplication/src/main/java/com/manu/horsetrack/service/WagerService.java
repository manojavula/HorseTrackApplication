package com.manu.horsetrack.service;

import com.manu.horsetrack.entity.Inventory;
import com.manu.horsetrack.entity.Wager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WagerService {

  final int ONE = 1;
  final int FIVE = 5;
  final int TEN = 10;
  final int TWENTY = 20;
  final int HUNDRED = 100;

  @Autowired
  private InventoryService inventoryService;

  public int calculateAmountWon(int wager, int odds) {
    return wager * odds;
  }

  public List<com.manu.horsetrack.entity.Wager> dispenseWinnings(int winnings) {

    List<Wager> wagerList = new ArrayList<>();
    Wager wager;
    boolean wagerAdded = false;

    List<Integer> denoms = inventoryService.getInventory()
                              .stream()
                              .map(Inventory::getDenomination)
                              .collect(Collectors.toList());
    Collections.reverse(denoms);

    for (Integer denomination : denoms) {
      int bill = denomination;
      wagerAdded = false;
      for (int cnt = inventoryService.getInventory(bill).getBillCount(); cnt >0; cnt--) {
        int totalAmountOfBills = bill * cnt;
        if (winnings >= totalAmountOfBills) {
          wager = new Wager(bill,cnt);
          wagerList.add(wager);
          wagerAdded = true;
          winnings -= totalAmountOfBills;
          break;
        }
      }
      if (!wagerAdded) {
        wager = new Wager(bill,0);
        wagerList.add(wager);
      }
    }

    wagerList.forEach(k-> {
      inventoryService.decrementInventory(k.getDenomination(), k.getBillCount());
    });
    return wagerList;
  }

}
