package com.altuhin.dineease.enums;


public enum SubscriptionTypeEnum {

  FREE_TRIAL("FREE_TRIAL", "Free Trial"),
  SUBSCRIBED("SUBSCRIBED", "Subscribed"),
  SUBSCRIPTION_EXPIRED("SUBSCRIPTION_EXPIRED", "Subscription Expired"),

  CURRENTLY_ACTIVE("CURRENTLY_ACTIVE", "Currently Active"),
  LEAVED("LEAVED", "Leaved")
  ;

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
