package com.in28minutes.springboot.firstrestapi.survey;

import java.util.List;

public class Question {
	private String id;
	private String description;
	private List<String> option;
	private String correct;
	public Question(String id, String description, List<String> option, String correct) {
		super();
		this.id = id;
		this.description = description;
		this.option = option;
		this.correct = correct;
	}
	public Question() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getOption() {
		return option;
	}
	public void setOption(List<String> option) {
		this.option = option;
	}
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", description=" + description + ", option=" + option + ", correct=" + correct
				+ "]";
	}
	
	

}
