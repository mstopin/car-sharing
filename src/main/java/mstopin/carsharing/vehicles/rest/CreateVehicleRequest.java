package mstopin.carsharing.vehicles.rest;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CreateVehicleRequest {
  @NotBlank
  String make;

  @NotBlank
  String model;
}
