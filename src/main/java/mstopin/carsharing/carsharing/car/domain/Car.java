package mstopin.carsharing.carsharing.car.domain;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.common.domain.AggregateRoot;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class Car implements AggregateRoot {
  protected final UUID carId;
  protected final Fuel fuel;

  public Fuel getFuel() {
    return fuel;
  }

  @Override
  public UUID getAggregateId() {
    return carId;
  }
}
