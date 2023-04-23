package org.study.gallery.backend.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
     String getToken(String key, Object value);
     Claims getClaims(String token);

     boolean isValid(String token); //토큰검증
     int getId(String token);//토큰사용자 ID
}