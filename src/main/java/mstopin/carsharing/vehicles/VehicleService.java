package mstopin.carsharing.vehicles;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.common.domain.DomainEvents;
import mstopin.carsharing.vehicles.dto.CreateVehicleDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VehicleService {
  private final VehicleRepository vehicleRepository;
  private final DomainEvents domainEvents;

  public List<Vehicle> findAll() {
    return vehicleRepository.findAll();
  }

  public Vehicle createVehicle(CreateVehicleDto dto) {
    Vehicle vehicle = new Vehicle(
      null,
      new VehicleInformation(dto.getMake(), dto.getModel()),
      Instant.now()
    );

    vehicleRepository.save(vehicle);
    domainEvents.publish(new NewVehicleAddedEvent(
      vehicle.getId()
    ));

    return vehicle;
  }
}
