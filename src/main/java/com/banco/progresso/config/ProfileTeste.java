package com.banco.progresso.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ProfileTeste {

    private final Logger LOG = LoggerFactory.getLogger(ProfileTeste.class);

    @Value("${spring.h2.console.path}")
    private String pathConsole;


    @Bean
    public void profileTesteConfiguracao(){

        LOG.info("PROFILE DE TESTE CARREGADO");
        LOG.info("PATH CONSOLE: " + pathConsole);


    }
}
