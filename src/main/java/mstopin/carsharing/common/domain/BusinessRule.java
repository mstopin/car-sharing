package mstopin.carsharing.common.domain;

public interface BusinessRule {
  boolean isBroken();
  String getMessage();
}
