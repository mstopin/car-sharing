package mstopin.carsharing.carsharing.car.domain;

import mstopin.carsharing.common.domain.AggregateRoot;

import java.util.UUID;

public abstract class Car extends AggregateRoot {
  private final Fuel fuel;
  public Car(UUID id, Fuel fuel) {
    super(id);
    this.fuel = fuel;
  }

  public Fuel getFuel() {
    return fuel;
  }
}
