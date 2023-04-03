package com.manu.horsetrack;

public interface Operations {
	
	  public boolean quit();
	  public void restock();
	  public void wager(int horseNumber, int wagerAmount);
	  public void winner(int horse);
	  public void printStartupMessages();
	  public void initialize();
	  public void execute(String commandLine);
	
}
