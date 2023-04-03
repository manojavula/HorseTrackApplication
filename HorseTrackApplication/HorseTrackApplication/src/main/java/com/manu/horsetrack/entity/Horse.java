package com.manu.horsetrack.entity;

import com.manu.horsetrack.commons.RaceStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="HORSE")
public class Horse {

  public Horse() {
  }

  public Horse(int horseNumber, String horseName, int odds, RaceStatus raceStatus) {
    this.horseNumber = horseNumber;
    this.horseName = horseName;
    this.odds = odds;
    this.raceStatus = raceStatus;
  }

  @Id
  @GeneratedValue
  private int id;

  @Column
  private int horseNumber;

  @Column
  private String horseName;

  @Column
  private int odds;

  @Column
  private RaceStatus raceStatus = RaceStatus.LOST;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getHorseNumber() {
    return horseNumber;
  }

  public void setHorseNumber(int horseNumber) {
    this.horseNumber = horseNumber;
  }

  public String getHorseName() {
    return horseName;
  }

  public void setHorseName(String horseName) {
    this.horseName = horseName;
  }

  public int getOdds() {
    return odds;
  }

  public void setOdds(int odds) {
    this.odds = odds;
  }

  public RaceStatus getRaceStatus() {
    return raceStatus;
  }

  public void setRaceStatus(RaceStatus raceStatus) {
    this.raceStatus = raceStatus;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Horse{");
    sb.append("id=").append(id);
    sb.append(", horseNumber=").append(horseNumber);
    sb.append(", horseName='").append(horseName).append('\'');
    sb.append(", odds=").append(odds);
    sb.append(", raceStatus=").append(raceStatus);
    sb.append('}');
    return sb.toString();
  }
}
