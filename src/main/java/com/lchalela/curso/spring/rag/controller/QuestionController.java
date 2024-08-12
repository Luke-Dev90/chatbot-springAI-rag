package com.lchalela.curso.spring.rag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lchalela.curso.spring.rag.model.Answer;
import com.lchalela.curso.spring.rag.model.Question;
import com.lchalela.curso.spring.rag.service.OpenAiService;

@RestController
public class QuestionController {
 
	@Autowired
	private OpenAiService openService;
	
	@PostMapping(value= "/ask")
	public Answer askQuestion(@RequestBody Question question) {
		return this.openService.getAnswer(question);
	}
	
	@PostMapping(value= "/askStr")
	public String askStringQuestion(@RequestBody Question question) {
		return this.openService.getAnswer(question.question());
	}
}
