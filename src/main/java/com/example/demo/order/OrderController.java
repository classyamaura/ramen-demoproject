package com.example.demo.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class OrderController {

    private final OrderDao orderDao;
    private final WeightSensorDao weightSensorDao;

    public OrderController(OrderDao orderDao, WeightSensorDao weightSensorDao) {
        this.orderDao = orderDao;
        this.weightSensorDao = weightSensorDao;
    }

    @GetMapping("/stockmanagement")
    public String showStockManagement(Model model) {
        model.addAttribute("items", orderDao.findAll());
        return "order/stockmanagement";
    }

    @GetMapping("/role-selection")
    public String showRoleSelection() {
        return "order/role-selection";
    }

    @GetMapping("/weight-registration")
    public String showWeightRegistration(Model model) {
        model.addAttribute("weightSensors", weightSensorDao.findAll());
        return "order/weight-registration";
    }

    @GetMapping("/orderdata-registration")
    public String showOrderDataRegistration() {
        return "order/orderdata-registration";
    }

    @GetMapping("/mainoffice-totaldata")
    public String showMainOfficeTotalData() {
        return "order/mainoffice-totaldata";
    }

    @PostMapping("/register-sensor-weight")
    public String registerSensorWeight(@RequestParam("sensorName") String sensorName,
                                       @RequestParam("materialName") String materialName,
                                       @RequestParam("registeredWeight") Integer registeredWeight,
                                       @RequestParam("thresholdWeight") Integer thresholdWeight) {
        WeightSensorEntity weightSensor = new WeightSensorEntity(null, sensorName, materialName, registeredWeight, thresholdWeight);
        weightSensorDao.save(weightSensor);
        return "redirect:/weight-registration";
    }
}
