package mstopin.carsharing.common.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CommonConfiguration {
  @Bean
  BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  BCryptPasswordHashingService bCryptPasswordHashingService(BCryptPasswordEncoder encoder) {
    return new BCryptPasswordHashingService(encoder);
  }
}
