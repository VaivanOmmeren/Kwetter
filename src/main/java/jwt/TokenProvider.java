package jwt;

import io.jsonwebtoken.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import static util.constants.REMEMBERME_VALIDITY_SECONDS;

import javax.annotation.PostConstruct;

public class TokenProvider {

    private static final Logger LOGGER = Logger.getLogger(TokenProvider.class.getName());

    private static final String AUTHORITIES_KEY = "auth";

    private String secretKey;

    private long tokenValidity;

    private long tokenValidityForRememberMe;

    @PostConstruct
    public void init() {
        // load from config
        this.secretKey = "IW+kC4KcDkbPKllSeb0T2LNaOn7uEBlqnUIorG+rUHGlsODiJFLtzNi9hEvusd9RJWDzFL0YAFuUTbN0Y8KLyA";
        this.tokenValidity = TimeUnit.HOURS.toMillis(10);   //10 hours
        this.tokenValidityForRememberMe = TimeUnit.SECONDS.toMillis(REMEMBERME_VALIDITY_SECONDS);   //24 hours
    }

    public String createToken(String username, Set<String> authorities, Boolean rememberMe) {
        long now = (new Date()).getTime();
        long validity = rememberMe ? tokenValidityForRememberMe : tokenValidity;

        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES_KEY, authorities.stream().collect(joining(",")))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(new Date(now + validity))
                .compact();
    }

    public JWTCredential getCredential(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Set<String> authorities
                = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(","))
                .stream()
                .collect(Collectors.toSet());

        return new JWTCredential(claims.getSubject(), authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.log(Level.INFO, "Invalid JWT signature: {0}", e.getMessage());
            return false;
        }
    }
}
