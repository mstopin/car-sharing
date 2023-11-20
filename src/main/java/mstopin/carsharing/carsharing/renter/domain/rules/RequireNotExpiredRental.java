package mstopin.carsharing.carsharing.renter.domain.rules;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.AbstractRental;
import mstopin.carsharing.common.domain.BusinessRule;

@RequiredArgsConstructor
public class RequireNotExpiredRental implements BusinessRule {
  private final AbstractRental abstractRental;

  @Override
  public boolean isBroken() {
    return abstractRental.isExpired();
  }

  @Override
  public String getMessage() {
    return "Rental is expired";
  }
}
