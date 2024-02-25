package com.Bank_Property_Evaluation.CommentManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class JobWorkers {
	private static final Logger logger = LoggerFactory.getLogger(JobWorkers.class);

	@Autowired
	ZeebeClient zeebe;
	@Autowired
	ObjectMapper mapper;

	@Autowired
	private CommentRepository commentRepository;

	@JobWorker
	public void saveComments(final JobClient client, final ActivatedJob job) {
		logger.info("Saving comments for job with key: {}", job.getKey());

		String com = job.getVariable("comment").toString();
		Property savedProperty = mapper.convertValue(job.getVariable("savedProperty"), Property.class);
		Comment comment = new Comment();
		comment.setContent(com);
		comment.setProperty(savedProperty);
		commentRepository.save(comment);

		logger.info("Comments saved successfully");
	}
}
