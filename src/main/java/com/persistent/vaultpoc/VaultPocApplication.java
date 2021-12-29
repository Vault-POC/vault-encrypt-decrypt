package com.persistent.vaultpoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.authentication.SessionManager;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class VaultPocApplication {

	private static final Logger logger = LoggerFactory.getLogger(VaultPocApplication.class);

	@Autowired
	private SessionManager sessionManager;
	public static void main(String[] args) {
		SpringApplication.run(VaultPocApplication.class, args);
	}

	@PostConstruct
	public void initIt() throws Exception {
		logger.info("Got Vault Token: " + sessionManager.getSessionToken().getToken());

	}

}
