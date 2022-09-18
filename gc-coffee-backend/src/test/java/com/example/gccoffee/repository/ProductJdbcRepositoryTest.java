package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
class ProductJdbcRepositoryTest {

    EmbeddedMysql embeddedMysql;

    private Product newProduct;

    @BeforeAll
    void setup() {

        newProduct = new Product(UUID.randomUUID(), "new-product", Category.COFFEE_BEAN_PACKAGE, 1000L);

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
    ProductRepository repository;

    @Test
    @Order(1)
    @DisplayName("상품을 추가할 수 있다.")
    void testInsert() {
        repository.insert(newProduct);
        List<Product> all = repository.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("상품을 이름으로 조회할 수 있다.")
    void findByName() {
        var product = repository.findByName(newProduct.getProductName());
        assertThat(product).isNotEmpty();
    }

    @Test
    @Order(3)
    @DisplayName("상품을 아이디로 조회할 수 있다.")
    void findById() {
        var product = repository.findById(newProduct.getProductId());
        assertThat(product).isNotEmpty();
    }

    @Test
    @Order(4)
    @DisplayName("상품들을 카테고리로 조회할 수 있다.")
    void findByCategory() {
        var products = repository.findByCategory(newProduct.getCategory());
        assertThat(products).isNotEmpty();
    }

    @Test
    @Order(5)
    @DisplayName("상품들을 조회할 수 있다.")
    void testFindAll() {
        var products = repository.findAll();
        assertThat(products).isNotEmpty();
    }

    @Test
    @Order(6)
    @DisplayName("상품을 수정할 수 있다.")
    void testUpdate() {
        String newProductName = "updated-product";
        newProduct.setProductName(newProductName);
        repository.update(newProduct);

        var product = repository.findById(newProduct.getProductId());
        assertThat(product).isNotEmpty();
        assertThat(product.get().getProductName()).isEqualTo(newProductName);
    }

    @Test
    @Order(7)
    @DisplayName("상품들을 전체 삭제할 수 있다.")
    void testDeleteAll() {
        repository.deleteAll();
        List<Product> products = repository.findAll();
        assertThat(products).isEmpty();
    }
}