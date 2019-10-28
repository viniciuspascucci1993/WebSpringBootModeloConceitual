package com.vinicius.springboot.mc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Classe de responsável por configurar nossos acessos ao Amazon S3..
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Configuration
public class S3Config {
	
	@Value("${aws.access_key_id}")
	private String awsId;

	@Value("${aws.secret_access_key}")
	private String awsKey;
	
	@Value("${s3.region}")
	private String region;
	
	/**
	 * Metodo para configurar as credenciais básicas do serviço da Amazon S3.
	 * @return AmazonS3 - s3Client.
	 */
	@Bean
	public AmazonS3 s3Cliente() {
		
		BasicAWSCredentials awsCred = new BasicAWSCredentials(awsId, awsKey);
		
		AmazonS3 s3Cliente = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
							.withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();
		
		return s3Cliente;
	}
}
