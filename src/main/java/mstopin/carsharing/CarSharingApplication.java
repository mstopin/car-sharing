package mstopin.carsharing;

import mstopin.carsharing.carsharing.renter.application.FindAvailableCar;
import mstopin.carsharing.carsharing.renter.application.commands.ReserveCarCommand;
import mstopin.carsharing.carsharing.renter.application.handlers.ReserveCarCommandHandler;
import mstopin.carsharing.carsharing.renter.domain.RenterRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;

@SpringBootApplication
public class CarSharingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CarSharingApplication.class, args);

		ReserveCarCommand command = new ReserveCarCommand(UUID.randomUUID(), UUID.randomUUID());
		ReserveCarCommandHandler handler = new ReserveCarCommandHandler(
			context.getBean(FindAvailableCar.class),
			context.getBean(RenterRepository.class)
		);

		handler.execute(command);
	}
}
