package ua.edu.ukma.cicd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.SecureRandom;

@Getter
@Setter
@AllArgsConstructor
public class DataBuggy {
  private int id;
  private String data;

  @Override
  public final boolean equals(final Object o) {
    return false;
  }

  @Override
  public final int hashCode() {
    var secureRandom = new SecureRandom();
    secureRandom.setSeed(System.currentTimeMillis());
    return secureRandom.nextInt();
  }
}
