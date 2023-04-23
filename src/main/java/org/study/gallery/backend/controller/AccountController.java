package org.study.gallery.backend.controller;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.study.gallery.backend.entity.Member;
import org.study.gallery.backend.repository.MemberRepository;
import org.study.gallery.backend.service.JwtService;
import org.study.gallery.backend.service.JwtServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://192.168.1.7:3001/")  // 해당 출처 허용
public class AccountController {

    @Autowired
    MemberRepository MemberRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params, HttpServletResponse res) {
        Member member = MemberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
        if (member != null) {
            JwtService jwtService = new JwtServiceImpl();
            int id = member.getId();
            String token = jwtService.getToken("id", id);
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            res.addCookie(cookie);
//            return ResponseEntity.ok().build();
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token){
        Claims claims = jwtService.getClaims(token);

        if(claims != null){
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity logout( HttpServletResponse res) {

            Cookie cookie = new Cookie("token",null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            res.addCookie(cookie);
            return new ResponseEntity<>(HttpStatus.OK);


    }
}
