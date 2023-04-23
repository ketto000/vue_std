package org.study.gallery.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.study.gallery.backend.entity.Cart;
import org.study.gallery.backend.entity.Item;
import org.study.gallery.backend.repository.CartRepository;
import org.study.gallery.backend.repository.ItemRepository;
import org.study.gallery.backend.service.JwtService;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://192.168.1.7:3001/")  // 해당 출처 허용
public class CartController {

    @Autowired
    JwtService jwtService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/cart/items")
    public ResponseEntity getCartItems(@CookieValue(value = "token",required = false) String token){
        //장바구니 리스트 출력
        if(!jwtService.isValid(token)){ //로그인
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);//400
        }

        int memberId = jwtService.getId(token);
        List<Cart> carts = cartRepository.findByMemberId(memberId);//회원의 아이디
        List<Integer> ItemIds = carts.stream().map(Cart::getItemId).toList(); //아이템_id 리스트 id
        List<Item> items = itemRepository.findByIdIn(ItemIds);//아이템 리스트
        return new ResponseEntity<>(items,  HttpStatus.OK);
    }


    @PostMapping("/api/cart/items/{itemId}")
    public ResponseEntity pushCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token",required = false) String token
    ) {
        //회원별 장바구니 등록

        if(!jwtService.isValid(token)){ //로그인
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);//400
        }
        int memberId = jwtService.getId(token); //토큰에서 얻은 아이디값

//        System.out.println("------>"+memberId);
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        if(cart == null){
            Cart newCart = new Cart();
            newCart.setMemberId(memberId);
            newCart.setItemId(itemId);
            cartRepository.save(newCart);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity removeCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value = "token",required = false) String token
    ) {
        if(!jwtService.isValid(token)){ //로그인
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);//400
        }

        int memberId = jwtService.getId(token); //토큰에서 얻은 아이디값
        Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        cartRepository.delete(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
