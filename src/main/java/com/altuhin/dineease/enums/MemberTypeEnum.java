package com.altuhin.dineease.enums;


public enum MemberTypeEnum {

  NORMAL_USER("NORMAL_USER", "Normal User"),
  SUPER_ADMIN("SUPER_ADMIN", "Super Admin"),
  ADMIN("ADMIN", "Admin");

  private final String key;
  private final String value;

  MemberTypeEnum(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
