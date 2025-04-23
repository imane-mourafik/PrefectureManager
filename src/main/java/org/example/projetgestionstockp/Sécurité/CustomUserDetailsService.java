/*package org.example.projetgestionstockp.Sécurité;

import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonneRepository personneRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Personne personne = personneRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        GrantedAuthority authority = new SimpleGrantedAuthority(personne.getRole().name());

        return new User(personne.getEmail(), personne.getMotdepasse(), Collections.singleton(authority));


    }

}
*/