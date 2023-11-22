package mstopin.carsharing.useraccess.rest.auth;

import lombok.Value;

@Value(staticConstructor = "of")
public class UserTokenCreatedResponse {
  String token;
}
