package com.lchalela.curso.spring.rag.service;


import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lchalela.curso.spring.rag.model.Answer;
import com.lchalela.curso.spring.rag.model.Question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OpenAiServiceImpl implements OpenAiService {


	final ChatClient chatClient;
	final SimpleVectorStore vectorStore;

	@Value("classpath:/templates/rag-prompt-template.st")
	private Resource ragPromptTemplate;

	@Override
	public String getAnswer(String question) {
		PromptTemplate promptTemple = new PromptTemplate(question);
		Prompt promt = promptTemple.create();
		return chatClient.call(promt).getResult().getOutput().getContent();
	}

	@Override
	public Answer getAnswer(Question question) {
		List<Document> documents = vectorStore.similaritySearch(SearchRequest.query(question.question()).withTopK(5));
		List<String> contentList = documents.stream().map(Document::getContent).toList();
		String unidos = String.join("\n", contentList);
		PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
		Prompt prompt = promptTemplate.create(Map.of("input", question.question(), "documents", unidos));
		return new Answer(chatClient.call(prompt).getResult().getOutput().getContent());
	}
	
	

}
