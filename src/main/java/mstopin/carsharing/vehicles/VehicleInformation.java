package mstopin.carsharing.vehicles;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInformation {
  private String make;
  private String model;
}
