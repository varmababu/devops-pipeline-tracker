package devops_tracker.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // DEMO USER
        if ("admin".equals(username)) {

            return new User(
                    "admin",
                    "$2a$10$DowJonesIndexDowJonesIndexDowJonesIn",
                    Collections.singleton(
                            new SimpleGrantedAuthority("ROLE_ADMIN")
                    )
            );
        }

        throw new UsernameNotFoundException("User not found");
    }
}