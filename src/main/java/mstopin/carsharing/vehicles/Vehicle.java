package mstopin.carsharing.vehicles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Vehicle {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Embedded
  private VehicleInformation vehicleInformation;

  private Instant addedAt;
}
