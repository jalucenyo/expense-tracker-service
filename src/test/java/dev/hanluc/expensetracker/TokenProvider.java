package dev.hanluc.expensetracker;

import java.util.function.Supplier;

public class TokenProvider implements Supplier<String> {

  public static String GENERIC_USER = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZXN0Iiwic3ViIjoidGVzdCIsImV4cCI6MTczNDYxMzQyNSwiaWF0IjoxNzM0NjA2MjI1LCJzY29wZSI6IiJ9.C5G_2BwEtPdXgeOJngGr2rZXSFsYogFKMJBQ9ofHjPA";

  private String token;

  @Override
  public String get() {
    return token;
  }

  public void setToken(String token) {
      this.token = token;
  }

}
