package mstopin.carsharing.vehicles.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mstopin.carsharing.vehicles.Vehicle;
import mstopin.carsharing.vehicles.VehicleService;
import mstopin.carsharing.vehicles.dto.CreateVehicleDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vehicles")
public class VehicleController {
  private final VehicleService vehicleService;

  @GetMapping
  List<Vehicle> findAll() {
    return this.vehicleService.findAll();
  }

  @PostMapping
  Vehicle create(@Valid @RequestBody CreateVehicleRequest createVehicleRequest) {
    return this.vehicleService.createVehicle(new CreateVehicleDto(
      createVehicleRequest.getMake(),
      createVehicleRequest.getModel()
    ));
  }
}
