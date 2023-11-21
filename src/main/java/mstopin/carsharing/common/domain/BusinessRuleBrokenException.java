package mstopin.carsharing.common.domain;

public class BusinessRuleBrokenException extends RuntimeException {
  public BusinessRuleBrokenException(BusinessRule businessRule) {
    super(businessRule.getMessage());
  }

  public BusinessRuleBrokenException(String message) {
    super(message);
  }
}
