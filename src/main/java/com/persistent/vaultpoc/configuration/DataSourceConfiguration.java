package com.persistent.vaultpoc.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


@Configuration
public class DataSourceConfiguration{

    @Value("${secret.filepath}")
    private String filepath;

    @Value("${postgres.host}")
    private String postgresHost;



    private static Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Bean
    public DataSource getDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        String[] credentials = fetchCredentials();
        dataSourceBuilder.url(postgresHost);
        dataSourceBuilder.username(credentials[0]);
        dataSourceBuilder.password(credentials[1]);
        return dataSourceBuilder.build();
    }
    public String[] fetchCredentials(){
        String credentials[] = new String[2];
        try {
            File secretFile = new File(filepath);
            Scanner myReader = new Scanner(secretFile);
            int iterator = 0;
            while (myReader.hasNextLine()) {
                credentials[iterator] = myReader.nextLine().trim();
                iterator++;
            }
            log.info("Successfully rendered Credentials!");
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Unable to render credentials since "+e.getMessage());
        }
        return credentials;
    }

}
