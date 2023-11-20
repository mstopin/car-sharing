package mstopin.carsharing.carsharing.renter.domain.rules;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.carsharing.car.domain.Car;
import mstopin.carsharing.carsharing.renter.domain.AbstractRental;
import mstopin.carsharing.common.domain.BusinessRule;

@RequiredArgsConstructor
public class AssertRentalAppliesToCar implements BusinessRule  {
  private final Car car;
  private final AbstractRental abstractRental;

  @Override
  public boolean isBroken() {
    return !abstractRental.getCarId().equals(car.getAggregateId());
  }

  @Override
  public String getMessage() {
    return "Given car doesn't apply to this rental";
  }
}
