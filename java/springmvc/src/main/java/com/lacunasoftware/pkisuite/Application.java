package com.lacunasoftware.pkisuite;

import com.lacunasoftware.pkisuite.util.StorageMock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.io.IOException;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) throws IOException {

		// Temporary folder used to store uploaded files and signed PDFs, CMSs and XMLs. The use of a
		// temporary directory is solely for simplification purposes. In actual production code, the
		// storage would typically be performed by your application's database.
		StorageMock.createTempFolder();

		// Run application.
		SpringApplication.run(Application.class, args);
	}
	
	@Override //sobrescrever para usar o wildfly
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}
}
