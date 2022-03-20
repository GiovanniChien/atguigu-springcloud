package cn.chien.springcloud.service.impl;

import cn.chien.springcloud.dao.PaymentDao;
import cn.chien.springcloud.entities.Payment;
import cn.chien.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qian.diqi
 * @date 2022/3/20
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
