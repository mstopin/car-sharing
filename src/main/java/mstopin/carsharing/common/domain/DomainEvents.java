package mstopin.carsharing.common.domain;

public interface DomainEvents {
  void publish(DomainEvent event);
}
