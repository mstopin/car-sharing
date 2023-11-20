package mstopin.carsharing.carsharing.renter.domain.rules;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.Renter;
import mstopin.carsharing.common.domain.BusinessRule;

@RequiredArgsConstructor
public class EnsureMaxOneRentalRule implements BusinessRule {
  private final Renter renter;

  @Override
  public boolean isBroken() {
    return renter.hasActiveRental();
  }

  @Override
  public String getMessage() {
    return "You cannot have more than 1 active rental";
  }
}
