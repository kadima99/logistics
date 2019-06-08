package com.lnsf.logistics;

import com.lnsf.logistics.entity.User;
import com.lnsf.logistics.service.OrdersService;
import com.lnsf.logistics.service.impl.OrdersServiceImpl;
import com.lnsf.logistics.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogisticsApplicationTests {

    public static void addressResolution(String address) {

    }
    @Test
    public void contextLoads() {
        addressResolution("北京市市辖区朝阳区");

    }

}
