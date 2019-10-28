package com.vinicius.springboot.mc.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Classe responsável por prover informações relacionadas ao serviço da Amazon (S3).
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class S3Service {

	private static final Logger logger = LoggerFactory.getLogger(S3Service.class);
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	/**
	 * Metodo para enviar arquivos para o serviço da amazon S3.
	 * @param localFilePath
	 */
	public void uploadFile( String localFilePath ) {
		
		try {
			
			File file = new File(localFilePath);
			
			logger.info("Iniciando Upload..");
			
			s3Client.putObject(new PutObjectRequest(bucketName, "game_just_cause_4.jpg", file));
			
			logger.info("Upoload Finalizado..");
			
		} catch (AmazonServiceException e) {
			
			logger.info("AmazonServiceException: " + e.getErrorMessage());
			
			logger.info("Status Code: " + e.getErrorCode());
			
		} catch (AmazonClientException e) {
			
			logger.info("AmazonClientException: " + e.getMessage());
		}
		

	}
}
