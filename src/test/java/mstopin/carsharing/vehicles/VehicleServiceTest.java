package mstopin.carsharing.vehicles;

import mstopin.carsharing.common.domain.DomainEvents;
import mstopin.carsharing.vehicles.dto.CreateVehicleDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {
  @Mock
  private DomainEvents domainEvents;

  @Mock
  private VehicleRepository vehicleRepository;

  @InjectMocks
  private VehicleService vehicleService;

  @Test
  void shouldAddNewVehicle() {
    Vehicle vehicle = vehicleService.createVehicle(
      new CreateVehicleDto("Honda", "Civic")
    );

    assertThat(vehicle.getVehicleInformation().getMake()).isEqualTo("Honda");
    assertThat(vehicle.getVehicleInformation().getModel()).isEqualTo("Civic");
    verify(domainEvents).publish(any(NewVehicleAddedEvent.class));
  }
}