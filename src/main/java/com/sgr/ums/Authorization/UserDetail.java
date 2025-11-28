package com.sgr.ums.Authorization;
import com.sgr.ums.Authorization.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserDetail {

    private static final Logger log = LoggerFactory.getLogger(UserDetail.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    /**
     * Get username from JWT in the current HTTP request
     */
    public String getUsername() {
        return getClaim("user_name", "SYSTEM");
    }

    /**
     * Generic method to get any claim from JWT
     * @param claimName name of the claim
     * @param defaultValue fallback value if claim is missing or invalid
     */
    public String getClaim(String claimName, String defaultValue) {
        try {
            Claims claims = jwtUtil.resolveClaims(request);
            if (claims != null && claims.get(claimName) != null) {
                return claims.get(claimName).toString();
            }
        } catch (Exception e) {
            log.error("Error getting claim '{}' from JWT: {}", claimName, e.getMessage());
        }
        return defaultValue;
    }

    /**
     * Get email from JWT (subject)
     */
    public String getEmail() {
        try {
            Claims claims = jwtUtil.resolveClaims(request);
            if (claims != null) {
                return claims.getSubject();
            }
        } catch (Exception e) {
            log.error("Error getting email from JWT: {}", e.getMessage());
        }
        return "SYSTEM";
    }
}

