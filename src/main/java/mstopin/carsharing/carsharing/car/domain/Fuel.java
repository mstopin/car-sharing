package mstopin.carsharing.carsharing.car.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Fuel {
  private final double amount;

  public boolean isBelow(double percent) {
    return amount < (percent / 100.0);
  }

  public boolean isAboveOrEqual(double percent) {
    return !isBelow(percent);
  }

  public static Fuel fromPercent(int percent) {
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("Fuel percent must be in range <0, 100>");
    }

    return new Fuel(percent / 100.0);
  }

  public int asPercent() {
    return (int)(amount * 100);
  }
}
