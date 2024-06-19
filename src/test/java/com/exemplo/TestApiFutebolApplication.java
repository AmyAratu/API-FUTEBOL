package com.exemplo;

import com.exemplo.api.futebol.ApiFutebolApplication;
import org.springframework.boot.SpringApplication;

public class TestApiFutebolApplication {

    public static void main(String[] args) {
        SpringApplication.from(ApiFutebolApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
