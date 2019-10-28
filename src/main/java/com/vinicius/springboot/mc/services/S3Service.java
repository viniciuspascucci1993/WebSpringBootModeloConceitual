package com.vinicius.springboot.mc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Classe responsável por prover informações relacionadas ao serviço da Amazon
 * (S3).
 * 
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
	 * Metodo para enviar arquivos (imagens) para o serviço da Amazon S3.
	 * @param multipartFile.
	 * @return uri (inputStream, fileName, contentType);
	 */
	public URI uploadFile(MultipartFile multipartFile) {

		try {

			String fileName = multipartFile.getOriginalFilename();

			InputStream inputStream = multipartFile.getInputStream();

			String contentType = multipartFile.getContentType();

			return uploadFile(inputStream, fileName, contentType);

		} catch (IOException e) {
			throw new RuntimeException("Erro de IO " + e.getMessage());
		}

	}

	/**
	 * Sobreescrita do metodo para enviar arquivos para o S3 da Amazon.
	 * @param inputStream - InputStream - inputStream.
	 * @param fileName - String - fileName.
	 * @param contentType - String - contentType.
	 * @return
	 */
	public URI uploadFile(InputStream inputStream, String fileName, String contentType) {

		try {

			ObjectMetadata metadata = new ObjectMetadata();

			metadata.setContentType(contentType);

			logger.info("Iniciando Upload..");

			s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));

			logger.info("Upoload Finalizado..");

			return s3Client.getUrl(bucketName, fileName).toURI();

		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");

		}

	}
}
