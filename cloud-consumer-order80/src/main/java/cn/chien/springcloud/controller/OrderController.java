package cn.chien.springcloud.controller;

import cn.chien.springcloud.entities.CommonResult;
import cn.chien.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author qian.diqi
 * @date 2022/3/20
 */
@RestController
@Slf4j
@RequestMapping("order")
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001/payment";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/{0}", CommonResult.class, id);
    }
}
