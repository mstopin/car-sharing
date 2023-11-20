package mstopin.carsharing.carsharing.renter.domain;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.Car;

import java.util.UUID;

@RequiredArgsConstructor
public class Rental {
  private final UUID id;
  private final UUID carId;

  public UUID getId() {
    return id;
  }

  public boolean isFor(Car car) {
    return car.getAggregateId().equals(carId);
  }
}
