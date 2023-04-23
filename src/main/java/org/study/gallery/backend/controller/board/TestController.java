package org.study.gallery.backend.controller.board;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.study.gallery.backend.dto.portfolioDto;
import org.study.gallery.backend.service.TestService;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;

    @GetMapping("/hello2")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/test10")
    public List<portfolioDto> test() {
        return testService.getAllDataList();
    }
}