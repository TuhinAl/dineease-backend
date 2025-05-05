package com.altuhin.dineease.enums;


public enum StatusEnum {

  PENDING("PENDING", "Pending"),
  APPROVED("APPROVED", "Approved");

  private final String key;
  private final String value;

  StatusEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
