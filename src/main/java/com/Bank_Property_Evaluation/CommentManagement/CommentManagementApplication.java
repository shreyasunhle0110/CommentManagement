package com.Bank_Property_Evaluation.CommentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.EnableZeebeClient;

@EnableZeebeClient
@SpringBootApplication
public class CommentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentManagementApplication.class, args);
	}

}
