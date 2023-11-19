package mstopin.carsharing.carsharing.renter.domain.rules;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.Car;
import mstopin.carsharing.carsharing.renter.domain.Rental;
import mstopin.carsharing.common.domain.BusinessRule;

@RequiredArgsConstructor
public class AssertRentalAppliesToCar implements BusinessRule  {
  private final Car car;
  private final Rental rental;

  @Override
  public boolean isBroken() {
    return !rental.getCarId().equals(car.getAggregateId());
  }

  @Override
  public String getMessage() {
    return "Given car doesn't apply to this rental";
  }
}
