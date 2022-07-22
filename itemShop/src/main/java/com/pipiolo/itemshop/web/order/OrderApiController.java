package com.pipiolo.itemshop.web.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders/api")
@RestController
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping
    public Result findAll() {
        List<OrderResponse> all = orderService.findAll();
        return new Result(all, all.size());
    }

    @Data @AllArgsConstructor
    static class Result<T> {
        private T data;
        private int count;
    }

}
