package com.manu.horsetrack.entity;

public class Wager {
	
	  private final int denomination;
	  private final int billCount;

	  public Wager(int denomination, int billCount) {
	    this.denomination = denomination;
	    this.billCount = billCount;
	  }

	  public int getDenomination() {
	    return denomination;
	  }

	  public int getBillCount() {
	    return billCount;
	  }

	  @Override
	  public String toString() {
	    final StringBuilder sb = new StringBuilder("Wager{");
	    sb.append("denomination=").append(denomination);
	    sb.append(", billCount=").append(billCount);
	    sb.append('}');
	    return sb.toString();
	  }

}
