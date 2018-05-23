package com.neverend;

import com.neverend.service.impl.OrdersServiceImpl;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class kuayu {


    @Autowired
    private OrdersServiceImpl ordersService;

    @Test
    public void tkogou(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://m.kugou.com/?json=true");
        try {
            HttpResponse response = httpClient.execute(httpGet);

            int httpCode = response.getStatusLine().getStatusCode();
            if (httpCode == 200){
                InputStream inputStream = response.getEntity().getContent();
                String result = IOUtils.toString(inputStream);
                System.out.println("========="+result+"============");
            }else {
                System.out.println("请求异常"+httpCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void t(){
        System.out.println(UUID.randomUUID().toString());
    }

}
