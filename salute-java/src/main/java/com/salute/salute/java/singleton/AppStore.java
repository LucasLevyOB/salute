package com.salute.salute.java.singleton;

import java.util.ArrayList;
import java.util.List;

public class AppStore {
  private static AppStore instance = null;
  private boolean isLogged = false;

  private AppStore() {
    isLogged = true;
  }

  public static AppStore getInstance() {
    if (instance == null) {
      instance = new AppStore();
    }
    return instance;
  }

  public boolean isLogged() {
    return isLogged;
  }

  public void setLogged(boolean isLogged) {
    this.isLogged = isLogged;
  }
}
