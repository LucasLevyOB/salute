package com.salute.salute.java.validations;

public class Inteiro {
  private void Inteiro() {
  }

  public static boolean isInteger(String str) {
    try {
      java.lang.Integer.parseInt(str);
      return true;
    } catch (java.lang.NumberFormatException e) {
      return false;
    }
  }

  public static boolean isPositiveInteger(String str) {
    try {
      java.lang.Integer i = java.lang.Integer.parseInt(str);
      return i > 0;
    } catch (java.lang.NumberFormatException e) {
      return false;
    }
  }

  public static int parseInt(String str) {
    try {
      return java.lang.Integer.parseInt(str);
    } catch (java.lang.NumberFormatException e) {
      return 0;
    }
  }
}
