package mstopin.carsharing.carsharing.renter.application;

import lombok.AllArgsConstructor;
import mstopin.carsharing.carsharing.renter.domain.RenterFactory;
import mstopin.carsharing.carsharing.renter.domain.RenterRepository;
import mstopin.carsharing.carsharing.renter.domain.RenterType;
import mstopin.carsharing.useraccess.user.UserCreatedEvent;
import mstopin.carsharing.useraccess.user.UserType;
import org.springframework.context.event.EventListener;

@AllArgsConstructor
public class UserAccessEventsHandler {
  private final RenterRepository renterRepository;

  @EventListener
  void handleUserCreated(UserCreatedEvent userCreatedEvent) {
    RenterType renterType = userCreatedEvent.getUserType() == UserType.USER
      ? RenterType.CLIENT
      : RenterType.MAINTAINER;

    renterRepository.save(RenterFactory.create(
      userCreatedEvent.getAggregateId(),
      null,
      null,
      renterType
    ));
  }
}
