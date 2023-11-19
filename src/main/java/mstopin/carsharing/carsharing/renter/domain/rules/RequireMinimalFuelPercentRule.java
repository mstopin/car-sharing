package mstopin.carsharing.carsharing.renter.domain.rules;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.Car;
import mstopin.carsharing.common.domain.BusinessRule;

@RequiredArgsConstructor
public class RequireMinimalFuelPercentRule implements BusinessRule {
  private final static double MINIMAL_FUEL_PERCENT = 15.0;
  private final Car car;

  @Override
  public boolean isBroken() {
    return car.getFuel().isBelow(MINIMAL_FUEL_PERCENT);
  }

  @Override
  public String getMessage() {
    return "Cannot reserve car that has below 15% of fuel";
  }
}
