package mstopin.carsharing.carsharing.renter.domain.rules;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.Reservation;
import mstopin.carsharing.common.domain.BusinessRule;

@RequiredArgsConstructor
public class RequireEmptyOrExpiredReservation implements BusinessRule {
  private final Reservation reservation;

  @Override
  public boolean isBroken() {
    return reservation != null && !reservation.isExpired();
  }

  @Override
  public String getMessage() {
    return "Renter has already a valid reservation";
  }
}
