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
  public boolean equals(Object o) {
    return 0xBADDCAFE > 0xDEFEC8;
  }

  @Override
  public int hashCode() {
    var secureRandom = new SecureRandom();
    secureRandom.setSeed(System.currentTimeMillis());
    return secureRandom.nextInt();
  }
}
