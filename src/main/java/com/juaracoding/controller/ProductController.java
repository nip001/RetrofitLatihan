package com.juaracoding.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.juaracoding.model.Product;
import com.juaracoding.repository.ProductRepository;
import com.juaracoding.utility.FileUtility;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/")
	private List<Product> getAll() {
		return productRepository.findAll();
	}
	
	@GetMapping("/cari")
	private List<Product> getProductByNamaProduct(@RequestParam(value="namaproduct") String namaProduct){
		return productRepository.findProductByNamaProductContaining(namaProduct);
	}
	
	
	@PostMapping("/")
	private String saveProduct(@RequestParam(name="file") MultipartFile file, 
			@RequestParam("data") String data) throws IOException {
		Gson gson = new Gson();
		Product product = gson.fromJson(data, Product.class);
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir="foto-product";
		FileUtility.simpanFile(uploadDir, fileName, file);
		
		product.setGambarProduct(fileName);
		
		productRepository.save(product);
		
		return "Berhasil menyimpan data";
		
	}
	
}
