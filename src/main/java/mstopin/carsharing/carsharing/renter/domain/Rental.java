package mstopin.carsharing.carsharing.renter.domain;

import java.util.UUID;

class Rental extends AbstractRental {
  public Rental(UUID carId) {
    super(carId);
  }

  @Override
  public boolean isExpired() {
    return false;
  }
}
