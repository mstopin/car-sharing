package mstopin.carsharing.carsharing.renter.domain;

public class ActiveRental implements Rental {
  @Override
  public boolean isExpired() {
    return false;
  }
}
