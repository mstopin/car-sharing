package mstopin.carsharing.common.domain;

public class BusinessRuleBrokenException extends RuntimeException {
  BusinessRule businessRule;

  public BusinessRuleBrokenException(BusinessRule businessRule) {
    super(businessRule.getMessage());

    this.businessRule = businessRule;
  }

  public BusinessRule getBusinessRule() {
    return businessRule;
  }
}
