package mstopin.carsharing.carsharing.renter.domain;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.Car;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
public class Rental {
  private final UUID carId;
  private final Instant rentedAt;

  public boolean isFor(Car car) {
    return car.getAggregateId().equals(carId);
  }
}
