package ua.edu.ukma.cicd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Data {
  private int id;
  private String data;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Data data1 = (Data) o;
    return getId() == data1.getId() && Objects.equals(getData(), data1.getData());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getData());
  }
}
