package com.juaracoding.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUtility {
	/*
	 * koleksi-foto,whatasadljwakdj.jpg,foto
	 */
	public static void simpanFile(String uploadDir,
			String fileName,
			MultipartFile multipart) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		//Jika file path nya tidak ada maka buat path baru atau direktori baru
		if(!Files.exists(uploadPath)) {
			Files.createDirectory(uploadPath);
		}
		
		try (InputStream inputStream = multipart.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			Files.copy( inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("File nya error");
		}
	}
}
