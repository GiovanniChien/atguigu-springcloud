package cn.chien.springcloud.controller;

import cn.chien.springcloud.entities.CommonResult;
import cn.chien.springcloud.entities.Payment;
import cn.chien.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qian.diqi
 * @date 2022/3/20
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功", result);
        }
        else {
            return new CommonResult<>(444, "插入数据库失败");
        }
    }

    @GetMapping("{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return new CommonResult<>(200, "查询成功", paymentService.getPaymentById(id));
    }

}
