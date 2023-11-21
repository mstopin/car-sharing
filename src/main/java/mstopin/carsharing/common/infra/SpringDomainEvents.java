package mstopin.carsharing.common.infra;

import lombok.RequiredArgsConstructor;
import mstopin.carsharing.common.domain.DomainEvent;
import mstopin.carsharing.common.domain.DomainEvents;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringDomainEvents implements DomainEvents {
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public void publish(DomainEvent event) {
    eventPublisher.publishEvent(event);
  }
}
