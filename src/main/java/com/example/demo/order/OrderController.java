package com.example.demo.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/stockmanagement")
    public String showStockManagement() {
        return "order/stockmanagement";
    }
}
