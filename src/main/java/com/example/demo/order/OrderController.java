package com.example.demo.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@Controller
public class OrderController {

    private final OrderDao orderDao;
    private final WeightSensorDao weightSensorDao;
    private final MainOrderDao mainOrderDao;
    private final OrderDetailsDao orderDetailsDao;

    @Autowired
    public OrderController(OrderDao orderDao, WeightSensorDao weightSensorDao, MainOrderDao mainOrderDao, OrderDetailsDao orderDetailsDao) {
        this.orderDao = orderDao;
        this.weightSensorDao = weightSensorDao;
        this.mainOrderDao = mainOrderDao;
        this.orderDetailsDao = orderDetailsDao;
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

    @GetMapping("/order_form")
    public String showOrderForm() {
        return "order/order_form";
    }

    @GetMapping("/shoporder-complete")
    public String showShopOrderComplete() {
        return "order/shoporder-complete";
    }

    @PostMapping("/register-order-data")
    public String registerOrderData(@RequestParam("storeName") String storeName,
                                  @RequestParam("responsiblePerson") String personInCharge,
                                  @RequestParam("orderDate") Date orderDate,
                                  @RequestParam("remarks") String remarks,
                                  @RequestParam("orderedItemsJson") String orderedItemsJson) {
        try {
            MainOrderEntity mainOrder = new MainOrderEntity();
            mainOrder.setStoreName(storeName);
            mainOrder.setPersonInCharge(personInCharge);
            mainOrder.setOrderDate(orderDate);
            mainOrder.setRemarks(remarks);

            Long mainOrderId = mainOrderDao.insert(mainOrder);

            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> orderedItems = mapper.readValue(orderedItemsJson, new TypeReference<List<Map<String, Object>>>(){});

            for (Map<String, Object> item : orderedItems) {
                OrderDetailsEntity orderDetails = new OrderDetailsEntity();
                orderDetails.setOrderId(mainOrderId);
                orderDetails.setItemName((String) item.get("name"));
                orderDetails.setQuantity((Integer) item.get("quantity"));
                orderDetailsDao.insert(orderDetails);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // エラーハンドリング
            return "error"; // 例としてエラーページにリダイレクト
        }
        return "redirect:/shoporder-complete";
    }

    @GetMapping("/mainoffice-totaldata")
    public String showMainOfficeTotalData(Model model) {
        List<MainOrderEntity> mainOrders = mainOrderDao.findAll();
        Map<Long, List<OrderDetailsEntity>> orderDetailsMap = new HashMap<>();

        for (MainOrderEntity mainOrder : mainOrders) {
            List<OrderDetailsEntity> orderDetails = orderDetailsDao.findByOrderId(mainOrder.getOrderId());
            orderDetailsMap.put(mainOrder.getOrderId(), orderDetails);
        }
        model.addAttribute("mainOrders", mainOrders);
        model.addAttribute("orderDetailsMap", orderDetailsMap);

        // 全店舗の品目ごとの合計発注数を取得
        List<Map<String, Object>> totalOrderQuantities = orderDetailsDao.findTotalQuantitiesByItem();
        model.addAttribute("totalOrderQuantities", totalOrderQuantities);

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
