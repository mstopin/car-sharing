package mstopin.carsharing.carsharing.renter.domain;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class AbstractRental {
  private final UUID carId;

  public boolean of(UUID carId) {
    return this.carId.equals(carId);
  }

  public UUID getCarId() {
    return carId;
  }

  abstract public boolean isExpired();
}

