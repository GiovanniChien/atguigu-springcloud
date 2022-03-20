package cn.chien.springcloud;

import cn.chien.springcloud.entities.Payment;
import com.google.gson.Gson;
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
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_create_payment_should_return_ok() throws Exception {
        Payment payment = new Payment();
        payment.setSerial("atguigu001");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(payment);
        mockMvc.perform(MockMvcRequestBuilders.post("/payment/create").content(jsonStr).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200L))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void test_query_payment_by_id_should_return_data() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/{0}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.serial").value("atguigu001"))
                .andDo(MockMvcResultHandlers.print());
    }

}
