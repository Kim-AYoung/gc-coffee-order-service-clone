package com.example.gccoffee.repository;

import com.example.gccoffee.model.*;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderJdbcRepositoryTest {

    EmbeddedMysql embeddedMysql;

    private com.example.gccoffee.model.Order newOrder;

    @BeforeAll
    void setup() {

        var orderItems = new ArrayList<OrderItem>();
        orderItems.add(new OrderItem(UUID.randomUUID(), Category.COFFEE_BEAN_PACKAGE, 1000L, 1));
        newOrder = new com.example.gccoffee.model.Order(UUID.randomUUID(),
                new Email("anonymous@gmail.com"),
                "Daejeon",
                "35541",
                orderItems,
                OrderStatus.ACCEPTED,
                LocalDateTime.now(),
                LocalDateTime.now());

        var config = aMysqldConfig(v5_7_latest)
                .withCharset(UTF8)
                .withPort(2215)
                .withUser("test", "test1234!")
                .withTimeZone("Asia/Seoul")
                .build();

        embeddedMysql = anEmbeddedMysql(config)
                .addSchema("test-order_mgmt", ScriptResolver.classPathScript("schema.sql"))
                .start();
    }

    @AfterAll
    void cleanup() { embeddedMysql.stop(); }

    @Autowired
    OrderRepository repository;

    @Test
    @Order(1)
    @DisplayName("주문을 추가할 수 있다.")
    void testInsert() {
        repository.insert(newOrder);
//        List<com.example.gccoffee.model.Order> all = repository.findAll();
//        assertThat(all).isNotEmpty();
    }
}