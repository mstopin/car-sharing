package mstopin.carsharing.carsharing.renter.domain.rules;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.Car;
import mstopin.carsharing.carsharing.renter.domain.Reservation;
import mstopin.carsharing.common.domain.BusinessRule;

@RequiredArgsConstructor
public class RequireActiveReservationForCar implements BusinessRule {
  private final Reservation reservation;
  private final Car car;

  @Override
  public boolean isBroken() {
    return reservation == null || reservation.isExpired() || !reservation.isFor(car);
  }

  @Override
  public String getMessage() {
    return "Active reservation not found for given car";
  }
}
