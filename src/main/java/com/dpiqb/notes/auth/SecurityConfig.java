package com.dpiqb.notes.auth;

import com.dpiqb.notes.storage.NoteCreatorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Service
public class SecurityConfig{
  private final NoteCreatorRepository noteCreatorRepository;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .headers().frameOptions().sameOrigin() // for /h2-console
      .and()
      .csrf().disable()
      .authorizeHttpRequests(
        auth -> auth
          .requestMatchers("/note/**").authenticated()
          .anyRequest().authenticated()
      )
      .httpBasic(Customizer.withDefaults())
      .formLogin(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(){
    return noteCreatorRepository::findByName;
  }
}
