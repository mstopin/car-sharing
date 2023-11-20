package mstopin.carsharing.carsharing.renter.domain.rules;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.Car;
import mstopin.carsharing.carsharing.renter.domain.Rental;
import mstopin.carsharing.common.domain.BusinessRule;

@RequiredArgsConstructor
public class RequireActiveRentalForCar implements BusinessRule {
  private final Rental rental;
  private final Car car;

  @Override
  public boolean isBroken() {
    return rental == null || !rental.isFor(car);
  }

  @Override
  public String getMessage() {
    return "Active rental not found for given car";
  }
}
