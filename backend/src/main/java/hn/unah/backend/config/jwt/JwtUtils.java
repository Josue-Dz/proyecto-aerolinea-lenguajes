package hn.unah.backend.config.jwt;

import java.util.Date;
import java.util.Map;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@PropertySource("classpath:custom.properties")
public class JwtUtils {

    @Value("${security.jwt.key.private}")
    private String secretKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    public String createToken(org.springframework.security.core.Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        String correo = authentication.getPrincipal().toString();
        String jwtToken = JWT.create()
                            .withIssuer(this.userGenerator)
                            .withSubject(correo)
                            .withIssuedAt(new Date())
                            .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 1800000))
                            .withJWTId(UUID.randomUUID().toString())
                            .withNotBefore(new Date(System.currentTimeMillis()))
                            .sign(algorithm);
        return jwtToken;
    }

    public DecodedJWT validateToken(String token){
        try {

            Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

            JWTVerifier verifier =  JWT.require(algorithm)
            .withIssuer(this.userGenerator)
            .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token invalid, not Authorized");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim getClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }

}
