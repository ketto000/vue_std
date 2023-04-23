package org.study.gallery.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.study.gallery.backend.dto.OrderDto;
import org.study.gallery.backend.entity.Cart;
import org.study.gallery.backend.entity.Item;
import org.study.gallery.backend.entity.Order;
import org.study.gallery.backend.repository.CartRepository;
import org.study.gallery.backend.repository.ItemRepository;
import org.study.gallery.backend.repository.OrderRepository;
import org.study.gallery.backend.service.JwtService;

import java.util.List;


@RestController
//@CrossOrigin(origins = "http://192.168.1.7:3001/")  // 해당 출처 허용
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    JwtService jwtService;


    @GetMapping("/api/orders")
    public ResponseEntity getOrder(
            @CookieValue(value = "token", required = false) String token
    ) {
        //회원별 장바구니 등록
        if (!jwtService.isValid(token)) { //로그인
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);//400
        }
        int memberId = jwtService.getId(token);
        List<Order> orders = orderRepository.findByMemberIdOrderByIdDesc(memberId);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/api/orders")
    public ResponseEntity pushOrder(
            @RequestBody OrderDto dto,
            @CookieValue(value = "token", required = false) String token
    ) {


        if (!jwtService.isValid(token)) { //로그인
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);//400
        }

        int memberId = jwtService.getId(token);

        Order newOrder = new Order();
        newOrder.setMemberId(memberId);
        newOrder.setName(dto.getName());
        newOrder.setAddress(dto.getAddress());
        newOrder.setPayment(dto.getPayment());
        newOrder.setCardNumber(dto.getCardNumber());
        newOrder.setItems(dto.getItems());
        orderRepository.save(newOrder);

        System.out.println("--->"+memberId);

        cartRepository.deleteByMemberId(memberId);//카트비우기

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
