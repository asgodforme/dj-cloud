package com.dj.cloud.portal.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dj.cloud.common.exception.CoreException;

import java.time.*;
import java.util.Date;

public class JwtUtils {

    private static final String JWT_SECRET = "jiangjie";

    private static final String JWT_ISSUSER = "jiangjie";

    private static final long EXPIRE_DURING = 15;

    public static String generateToken(Session session) throws CoreException {

        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withIssuer(JWT_ISSUSER)
                    .withExpiresAt(Date.from(session.getExpireDateTime().plusMinutes(EXPIRE_DURING).atZone(ZoneId.systemDefault()).toInstant()))
                    .withClaim("userId", session.getUserId())
                    .withClaim("userName", session.getUserName())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new CoreException(exception.getCause().getMessage(), exception.getMessage());
        }
    }

    public static Session verifyToken(String token) throws CoreException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUSER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Session session = new Session();
            session.setUserId(jwt.getClaim("userId").asInt());
            session.setUserName(jwt.getClaim("userName").asString());
            return session;
        } catch (JWTVerificationException exception){
            throw new CoreException(exception.getCause().getMessage(), exception.getMessage());
        }
    }
}
