package org.example.projetgestionstockp.Sécurité;

/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/accueil", "/login",  // Ajout de la route /login ici
                                "/css/**", "/js/**", "/static/**",
                                "/style.css", "/logo.png"
                        ).permitAll()  // Permettre l'accès libre à ces pages

                        // Accès par rôle
                        .requestMatchers("/admin/**").hasRole("ADMINISTRATEUR")
                        .requestMatchers("/magasinier/**").hasRole("MAGASINIER")
                        .anyRequest().authenticated() // Authentification nécessaire pour toutes les autres routes
                );

        return http.build();
    }
}
*/