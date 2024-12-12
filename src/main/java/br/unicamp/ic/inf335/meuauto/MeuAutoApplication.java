package br.unicamp.ic.inf335.meuauto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MeuAutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeuAutoApplication.class, args);
    }


}
