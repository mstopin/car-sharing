package mstopin.carsharing.carsharing.renter.application.commands;

import lombok.Value;
import mstopin.carsharing.common.application.Command;

import java.util.UUID;

@Value
public class FinishRentalCommand implements Command {
  UUID renterId;
  UUID carId;
}

