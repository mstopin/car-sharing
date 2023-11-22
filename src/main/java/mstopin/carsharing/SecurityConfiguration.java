package mstopin.carsharing;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mstopin.carsharing.useraccess.auth.JwtAuthTokenProvider;
import mstopin.carsharing.useraccess.auth.security.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
  private final Environment environment;
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .cors(CorsConfigurer::disable)
      .csrf(CsrfConfigurer::disable)
      .formLogin(FormLoginConfigurer::disable)
      .addFilterBefore(new AuthTokenFilter(new JwtAuthTokenProvider(environment)), UsernamePasswordAuthenticationFilter.class)
      .authorizeHttpRequests((a) -> {
        a.requestMatchers("auth/login").permitAll();
        a.requestMatchers("users/create").permitAll();
        a.anyRequest().authenticated();
      })
      .sessionManagement((s) -> {
        s.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      })
      .exceptionHandling((e) -> {
        e.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
      })
      .build();
  }

  private static class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }
}
