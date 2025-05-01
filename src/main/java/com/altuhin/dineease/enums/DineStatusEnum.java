package com.altuhin.dineease.enums;


public enum SubscriptionTypeEnum {

  FREE("FREE", "Free"),
  SUBSCRIBED("SUBSCRIBED", "Subscribed");

  private final String key;
  private final String value;

  SubscriptionTypeEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
