package practicum.backend.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public class CustomAbstractAuthenticationToken extends AbstractAuthenticationToken {

    private final CustomUserDetails customUserDetails;
    private final String jwtToken;

    public CustomAbstractAuthenticationToken(CustomUserDetails customUserDetails,
            String jwtToken, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.customUserDetails = customUserDetails;
        this.jwtToken = jwtToken;
    }

    @Override
    public String getCredentials() {
        return jwtToken;
    }

    @Override
    public CustomUserDetails getPrincipal() {
        return customUserDetails;
    }
}
