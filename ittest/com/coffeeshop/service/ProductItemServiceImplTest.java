package com.coffeeshop.service;

import com.coffeeshop.configuration.ItTestDockerMySQLConfiguration;
import com.palantir.docker.compose.DockerComposeRule;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;


//todo this configuration must run under "docker" spring profile
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductItemServiceImplTest {

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;


    @ClassRule
    public static DockerComposeRule dockerComposeRule = ItTestDockerMySQLConfiguration.getDockerComposeRule();

    private static Map<HttpStatus, Integer> counterByStatus;


    public void init(){
        counterByStatus = new ConcurrentHashMap<>();
        counterByStatus.put(HttpStatus.OK, 0);
        counterByStatus.put(HttpStatus.PRECONDITION_FAILED, 0);
    }

    @Test
    public void findAndMarkAsSoldUseCase1() {
        init();
        sendRequestToServer("http://localhost:"+randomServerPort+"/api/admin/product/findAndMark/1/50");
        Assert.assertEquals(2, counterByStatus.get(HttpStatus.OK).intValue());
        Assert.assertEquals(8, counterByStatus.get(HttpStatus.PRECONDITION_FAILED).intValue());
    }

    @Test
    public void findAndMarkAsSoldUseCase2() {
        init();
        sendRequestToServer( "http://localhost:"+randomServerPort+"/api/admin/product/findAndMark/2/20");
        Assert.assertEquals(4, counterByStatus.get(HttpStatus.OK).intValue());
        Assert.assertEquals(6, counterByStatus.get(HttpStatus.PRECONDITION_FAILED).intValue());
    }

    @Test
    public void findAndMarkAsSoldUseCase3() {
        init();
        sendRequestToServer("http://localhost:"+randomServerPort+"/api/admin/product/findAndMark/3/2/4/20");
        Assert.assertEquals(2, counterByStatus.get(HttpStatus.OK).intValue());
        Assert.assertEquals(8, counterByStatus.get(HttpStatus.PRECONDITION_FAILED).intValue());
    }

    public void sendRequestToServer(String url) {
        Stream.iterate(1, n -> n + 1).limit(10).parallel().forEach(x -> {
            try {
                ResponseEntity<Object> exc = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Object.class);
                counterByStatus.compute(exc.getStatusCode(), ((httpStatus, integer) -> integer+1));
            } catch (HttpClientErrorException exc) {
                counterByStatus.compute(HttpStatus.valueOf(exc.getRawStatusCode()), ((httpStatus, integer) -> integer+1));
            }
        });
    }

    @PreDestroy
    @SneakyThrows
    public void destroy() {
        dockerComposeRule.dockerCompose().down();
    }
}
