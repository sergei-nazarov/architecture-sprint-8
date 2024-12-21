package practicum.backend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import practicum.backend.security.CustomUserDetails;

import java.util.Optional;

@Service
public class CurrentUserService {

    public Optional<CustomUserDetails> getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            return Optional.empty();
        }

        return Optional.of((CustomUserDetails) authentication.getPrincipal());
    }

}
