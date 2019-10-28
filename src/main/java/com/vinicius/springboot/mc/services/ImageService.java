package com.vinicius.springboot.mc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vinicius.springboot.mc.services.exception.FileException;

/**
 * Classe responsável por fornecer funcionalidades de imagem.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class ImageService {
	
	
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		
		// Capturar a extensão do arquivo.
		String extension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		
		if (!"png".equals(extension) && !"jpg".equals(extension)) {
			
			throw new FileException("Somente imagens JPG e PNG são permitidas");
		}
		
		// Obter um BufferedImagew a partir do MultiPartFile
		try {
			BufferedImage image = ImageIO.read(uploadedFile.getInputStream());
		
			if ("png".equals(extension)) {
				
				image = convertPngToJpg(image);
			}
			
			return image;
			
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");

		}
	}

	/**
	 * Metodo para converter arquivo PNG para JPG.
	 * @param image - BufferedImage.
	 * @return jpgImage - BufferedImage.
	 */
	public BufferedImage convertPngToJpg(BufferedImage image) {
		
		BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_INT_BGR);
		jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
		
		return jpgImage;
	}

	/**
	 * Metodo responsável por retornar o InputStream a partir de um BufferedImage.
	 * @param img - BufferedImage - img.
	 * @param extension - String - extensão do arquivo.
	 * @return new ByteArrayInputStream(outStream.toByteArray());
	 */
	public InputStream getInputStream( BufferedImage img, String extension) {
		
		try {
			
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			
			ImageIO.write(img, extension, outStream);
			
			return new ByteArrayInputStream(outStream.toByteArray());
			
		} catch (IOException e) {
			
			throw new FileException("Erro ao ler arquivo");
		}
	}
	
}
