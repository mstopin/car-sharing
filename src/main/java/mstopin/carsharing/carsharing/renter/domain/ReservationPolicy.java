package mstopin.carsharing.carsharing.renter.domain;

import lombok.NonNull;
import lombok.Value;
import mstopin.carsharing.carsharing.car.domain.Car;

import java.util.List;
import java.util.Optional;

@Value
class Rejection {
  @NonNull String reason;
}

public interface ReservationPolicy {
  Optional<Rejection> isSatisfied(Renter renter, Car car);

  static List<ReservationPolicy> allPolicies() {
    return List.of(
      new RequireMinimalFuelPercentPolicy(),
      new EnsureMaxOneRentalPolicy()
    );
  }
}

class RequireMinimalFuelPercentPolicy implements ReservationPolicy {
  private final static double MINIMAL_FUEL_PERCENT = 15.0;

  @Override
  public Optional<Rejection> isSatisfied(Renter renter, Car car) {
    if (car.getFuel().isAboveOrEqual(MINIMAL_FUEL_PERCENT)) {
      return Optional.empty();
    }

    return Optional.of(new Rejection("Cannot reserve car that has below 15% of fuel"));
  }
}

class EnsureMaxOneRentalPolicy implements ReservationPolicy {

  @Override
  public Optional<Rejection> isSatisfied(Renter renter, Car car) {
    if (!renter.hasRental()) {
      return Optional.empty();
    }

    return Optional.of(new Rejection("You cannot have more than 1 active rental"));
  }
}

@Value
class ReservationPolicies implements ReservationPolicy {
  List<ReservationPolicy> policies;

  static ReservationPolicies of(List<ReservationPolicy> policies) {
    return new ReservationPolicies(policies);
  }

  @Override
  public Optional<Rejection> isSatisfied(Renter renter, Car car) {
    Optional<Optional<Rejection>> rejection = policies
      .stream()
      .map((p) -> p.isSatisfied(renter, car))
      .filter(Optional::isPresent)
      .findFirst();

    return rejection.orElseGet(Optional::empty);
  }
}