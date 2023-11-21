package mstopin.carsharing.vehicles;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.common.domain.DomainEvent;

import java.util.UUID;

@RequiredArgsConstructor
public class NewVehicleAddedEvent implements DomainEvent {
  private final UUID vehicleId;

  @Override
  public UUID getAggregateId() {
    return vehicleId;
  }
}
