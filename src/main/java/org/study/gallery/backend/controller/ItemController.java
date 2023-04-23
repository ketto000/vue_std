package org.study.gallery.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.study.gallery.backend.entity.Item;
import org.study.gallery.backend.repository.ItemRepository;
import org.study.gallery.backend.service.TestService;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://192.168.1.7:3001/")  // 해당 출처 허용
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TestService service;

    @GetMapping("/api/items")
    public List<Item> getItems(){
        List<Item> items = itemRepository.findAll();
        return items;
    }


}
