package cn.chien.springcloud.service;

import cn.chien.springcloud.entities.Payment;

/**
 * @author qian.diqi
 * @date 2022/3/20
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);

}
