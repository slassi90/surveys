package com.in28minutes.springboot.firstrestapi.survey;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
public class SurveyResource {
	private SurveyService surveyService;

	public SurveyResource(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}

	@GetMapping("/surveys")
	public List<Survey> RetrieveAllSurveys() {
		return surveyService.RetrieveAllSurveys();
	}

	@GetMapping("/surveys/{surveyid}")
	public Survey RetrieveSurveyById(@PathVariable String surveyid) {
		Survey survey = surveyService.RetrieveSurveyById(surveyid);
		if (survey == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return survey;

	}

	@GetMapping("/surveys/{surveyid}/questions/{questionid}")
	public Question RetrieveSpecificSurveysQuestion(@PathVariable String surveyid, @PathVariable String questionid) {
		Question question = surveyService.RetrieveSpecificSurveyByQuestion(surveyid, questionid);
		if (question == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return question;
	}

	@GetMapping("/surveys/{surveyid}/questions")
	public List<Question> RetrieveAllSurveysQuestion(@PathVariable String surveyid) {
		List<Question> questions = surveyService.RetrieveAllSurveysQuestions(surveyid);
		if (questions == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return questions;
	}
	
	@RequestMapping(value="/surveys/{surveyid}/questions",method = RequestMethod.POST)
	public ResponseEntity<Object> addNewSurveyQuestion(@PathVariable String surveyid,@RequestBody Question question ) {
		
		//surveyService.addNewSurveyQuestion(surveyid, question);
		String questionId = surveyService.addNewSurveyQuestion(surveyid, question);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/questionId").buildAndExpand(questionId).toUri();
	
	return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/surveys/{surveyid}/questions/{questionid}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteSurveyQuestion(@PathVariable String surveyid, @PathVariable String questionid) {
		//Question question = surveyService.RetrieveSpecificSurveyByQuestion(surveyid, questionid);
		surveyService.deleteSurveyQuestion(surveyid,questionid);
		return ResponseEntity.noContent().build();
		
	}

	@RequestMapping(value="/surveys/{surveyid}/questions/{questionid}",method = RequestMethod.PUT)
	public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyid, @PathVariable String questionid,@RequestBody Question question)  {
		//Question question = surveyService.RetrieveSpecificSurveyByQuestion(surveyid, questionid);
		surveyService.updateSurveyQuestion(surveyid,questionid,question);
		return ResponseEntity.noContent().build();
		
	}


}
/*
	@GetMapping ("/surveys/{surveyid}/questions/{questionid}")
	public Question RetrieveSpecificSurveysQuestion (@PathVariable String surveyid ,@PathVariable String questionid ){
Question question = surveyService.RetrieveSpecificSurveysQuestion(surveyid,questionid);

	}
*/



