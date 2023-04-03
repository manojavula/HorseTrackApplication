package com.manu.horsetrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manu.horsetrack.service.CommandService;
import com.manu.horsetrack.service.DataService;
import com.manu.horsetrack.service.HorseService;
import com.manu.horsetrack.service.InventoryService;
import com.manu.horsetrack.service.ReportService;
import com.manu.horsetrack.service.WagerService;

@Component
public class HorseTrack implements Operations {
	
	  private boolean quit = false;
	
	  public HorseTrack() {
		
	  }
	
	  @Autowired
	  HorseService horseService;

	  @Autowired
	  InventoryService inventoryService;

	  @Autowired
	  DataService dataService;

	  @Autowired
	  CommandService commandService;

	  @Autowired
	  ReportService reportService;

	  @Autowired
	  WagerService wagerService;
	

	@Override
	public boolean quit() {
		return quit;
	}

	@Override
	public void restock() {
		inventoryService.restock();
		reportService.printInventory();
	}

	@Override
	public void wager(int horseNumber, int wagerAmount) {
		
		    if (!(horseService.isValidHorseNumber(horseNumber))) {
		    	reportService.printInvalidHorse(horseNumber);
		      return;
		    }

		    if (!(horseService.isHorseWinner(horseNumber))) {
		    	reportService.printNoPayout(horseService.getHorseName(horseNumber));
		      return;
		    }

		    int amountWon = wagerService.calculateAmountWon(
		        wagerAmount,
		        horseService.getHorseOdds(horseNumber));
		    if (inventoryService.sufficientFunds(amountWon)) {
		    	reportService.printPayout(horseService.getHorseName(horseNumber), amountWon);
		    	reportService.printDispense(wagerService.dispenseWinnings(amountWon));
		    } else {
		    	reportService.printInsufficientFunds(amountWon);
		    }

		    reportService.printInventory();
		    reportService.printHorses();
	}

	@Override
	public void winner(int horseNumber) {
		
		   if (horseService.isValidHorseNumber(horseNumber)) {
		      horseService.setRaceWinner(horseNumber);
		      reportService.printInventory();
		      reportService.printHorses();
		    } else {
		    	reportService.printInvalidHorse(horseNumber);
		    }
	}

	@Override
	public void printStartupMessages() {
		reportService.startup();		
	}

	@Override
	public void initialize() {
		dataService.startup();
	}

	@Override
	public void execute(String commandLine) {
		System.out.println("Command issued: "+commandLine);
	    if (commandLine.length() > 0) {
	      if ((commandService.parseCommand(commandLine).equalsIgnoreCase("invalid"))) {
	    	  reportService.printInvalidCommand(commandLine);
	      } else {
	        commandFactory(commandLine);
	      }
	    }
	}
	
	private void commandFactory(String commandLine) {
	    String command = commandService.parseCommand(commandLine);

	    switch (command) {
	      case "quit":
	        quit = true;
	        break;
	      case "restock":
	        restock();
	        break;
	      case "winner":
	        winner(commandService.getWinningHorseNumber());
	        break;
	      case "wager":
	        wager(commandService.getBetHorseNumber(), commandService.getWagerAmount());
	        break;
	      case "error":
	    	  reportService.printErrorMessage(commandService.getErrorMessage());
	        break;
	      default:
	        // Do nothing
	    }
	  }

}
