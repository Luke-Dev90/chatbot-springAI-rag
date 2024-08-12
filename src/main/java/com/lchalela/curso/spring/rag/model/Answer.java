package com.lchalela.curso.spring.rag.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Answer(String answer) {

}
