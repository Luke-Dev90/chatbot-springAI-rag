package com.lchalela.curso.spring.rag.service;

import org.springframework.stereotype.Service;

import com.lchalela.curso.spring.rag.model.Answer;
import com.lchalela.curso.spring.rag.model.Question;

@Service
public interface OpenAiService {
	String getAnswer(String question);
	Answer getAnswer(Question question);
}
