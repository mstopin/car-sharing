package mstopin.carsharing.carsharing.renter.domain.rules;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.Rental;
import mstopin.carsharing.common.domain.BusinessRule;

@RequiredArgsConstructor
public class RequireNotExpiredRental implements BusinessRule {
  private final Rental rental;

  @Override
  public boolean isBroken() {
    return rental.isExpired();
  }

  @Override
  public String getMessage() {
    return "Rental is expired";
  }
}
