package com.altuhin.dineease.enums;


public enum DineStatusEnum {

  ACTIVE("ACTIVE", "Active"),
  DEACTIVATE("DEACTIVATE", "Deactivate"),
  DELETED("DELETED", "Deleted"),;

  private final String key;
  private final String value;

  DineStatusEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
