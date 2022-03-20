package cn.chien.springcloud;

import cn.chien.springcloud.entities.CommonResult;
import cn.chien.springcloud.entities.Payment;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author qian.diqi
 * @date 2022/3/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_create_and_get_payment_with_restTemplate_should_return_ok() throws Exception {
        Payment payment = new Payment();
        payment.setSerial("rest template test");
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/order/payment/create")
                        .content(JSON.toJSONString(payment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200L))
                .andReturn().getResponse().getContentAsString();
        CommonResult<Payment> createResult = JSONObject.parseObject(content, new TypeReference<CommonResult<Payment>>() { });
        mockMvc.perform(MockMvcRequestBuilders.get("/order/payment/{0}", createResult.getData().getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.serial").value(createResult.getData().getSerial()))
                .andDo(MockMvcResultHandlers.print());
    }

}
