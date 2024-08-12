package com.lchalela.curso.spring.rag.config;

import java.io.File;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class VectorStoredConfig {
	@Bean
	SimpleVectorStore simpleVectorStore(EmbeddingClient embeddingClient, VectorStoreProperties vectorStoreProperties ) {
		var store = new SimpleVectorStore(embeddingClient);
		
		File vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());
		
		if(vectorStoreFile.exists()) {
			store.load(vectorStoreFile);
		}else {
			log.debug("Loading document into vector store");
			vectorStoreProperties.getDocumentsToLoad().forEach( document -> {
				log.debug("Loading document: " + document.getFilename());
				TikaDocumentReader documentReader = new TikaDocumentReader(document);
				List<Document> docs = documentReader.get();
				TokenTextSplitter textSplitter = new TokenTextSplitter();
				List<Document> splitDocs = textSplitter.apply(docs);
				store.add(splitDocs);
			});
			store.save(vectorStoreFile);
		}
		return store;
	}
}
