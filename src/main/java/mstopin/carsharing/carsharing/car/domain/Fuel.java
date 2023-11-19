package mstopin.carsharing.carsharing.car.domain;

public class Fuel {
  private final double amount;

  public Fuel(double amount) {
    if (amount < 0 || amount > 1) {
      throw new IllegalArgumentException("Fuel must be in range <0,1>");
    }

    this.amount = amount;
  }

  public boolean isBelow(double percent) {
    return amount < (percent / 100.0);
  }

  public boolean isAboveOrEqual(double percent) {
    return !isBelow(percent);
  }
}
