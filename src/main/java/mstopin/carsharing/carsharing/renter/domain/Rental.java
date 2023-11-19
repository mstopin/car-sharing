package mstopin.carsharing.carsharing.renter.domain;

import mstopin.carsharing.carsharing.car.domain.Car;

import java.util.UUID;

public interface Rental {
  boolean isExpired();

  UUID getCarId();
}
