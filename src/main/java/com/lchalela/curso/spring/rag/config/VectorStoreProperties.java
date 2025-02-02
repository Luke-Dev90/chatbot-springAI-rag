package com.lchalela.curso.spring.rag.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@ConfigurationProperties(prefix ="sfg.aiapp")
public class VectorStoreProperties {
	private String vectorStorePath;
	private List<Resource> documentsToLoad;
	

	public String getVectorStorePath() {
		return vectorStorePath;
	}

	public void setVectorStorePath(String vectorStorePath) {
		this.vectorStorePath = vectorStorePath;
	}

	public List<Resource> getDocumentsToLoad() {
		return documentsToLoad;
	}

	public void setDocumentsToLoad(List<Resource> documentToLoad) {
		this.documentsToLoad = documentToLoad;
	}
	
	
}
