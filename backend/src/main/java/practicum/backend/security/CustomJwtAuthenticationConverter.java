package practicum.backend.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, CustomAbstractAuthenticationToken> {

    @Override
    @NonNull
    public CustomAbstractAuthenticationToken convert(Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username");
        String firstName = jwt.getClaimAsString("given_name");
        String lastName = jwt.getClaimAsString("family_name");
        String email = jwt.getClaimAsString("email");
        List<String> roles = extractRoles(jwt);
        CustomUserDetails customUserDetails = new CustomUserDetails(username, firstName, lastName, email, roles);

        return new CustomAbstractAuthenticationToken(customUserDetails, jwt.getTokenValue(), customUserDetails.getAuthorities());
    }

    @SuppressWarnings("unchecked")
    private List<String> extractRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess == null || realmAccess.isEmpty()) {
            return List.of();
        }
        return (List<String>) realmAccess.get("roles");
    }


}
