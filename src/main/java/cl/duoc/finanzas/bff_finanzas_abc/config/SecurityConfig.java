package cl.duoc.finanzas.bff_finanzas_abc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Autorización específica por canal como pide la guía 
                .requestMatchers("/api/v1/accounts/web/**").hasRole("WEB")
                .requestMatchers("/api/v1/accounts/mobile/**").hasRole("MOBILE")
                .requestMatchers("/api/v1/accounts/atm/**").hasRole("ATM") 
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Usuario para el BFF Web
        UserDetails webUser = User.withDefaultPasswordEncoder()
            .username("cliente_web")
            .password("web123")
            .roles("WEB") // Este rol coincide con .hasRole("WEB") en el filterChain
            .build();

        // Usuario para el BFF Móvil
        UserDetails mobileUser = User.withDefaultPasswordEncoder()
            .username("cliente_movil")
            .password("movil123")
            .roles("MOBILE")
            .build();

        // Usuario para el BFF Sucursal (Operaciones Críticas)
        UserDetails atmUser = User.withDefaultPasswordEncoder()
            .username("cajero01")
            .password("atm123")
            .roles("ATM")
            .build();

        return new InMemoryUserDetailsManager(webUser, mobileUser, atmUser);
    }
}
