package com.altuhin.dineease.enums;


public enum MemberInDineStatusEnum {

  PENDING("PENDING", "Pending"),
  JOINED("JOINED", "Joined"),
  GO_AWAY_FROM_DINE("GO_AWAY_FROM_DINE", "Go Away From Dine"),;

  private final String key;
  private final String value;

  MemberInDineStatusEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
