package com.in28minutes.springboot.firstrestapi.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class SurveyService {
	private static List<Survey> surveys = new ArrayList<>();
	static {
		
		Question question1 = new Question("Question1",
		        "Most Popular Cloud Platform Today", Arrays.asList(
		                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2",
		        "Fastest Growing Cloud Platform", Arrays.asList(
		                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
		Question question3 = new Question("Question3",
		        "Most Popular DevOps Tool", Arrays.asList(
		                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1,
		        question2, question3));

		Survey survey = new Survey("Survey1", "My Favorite Survey",
		        "Description of the Survey", questions);

		surveys.add(survey);


		}
	public List<Survey> RetrieveAllSurveys(){
		return surveys;
	
}
	
	public Survey RetrieveSurveyById (String surveyid) {
		Predicate<? super Survey> predicate = survey -> survey.getId().equalsIgnoreCase(surveyid);

		Optional<Survey> optionalsurvey = surveys.stream().filter(predicate).findFirst();
		return optionalsurvey.get();
	
	}
	
	public List<Question> RetrieveAllSurveysQuestions(String surveyId){
		Survey survey = RetrieveSurveyById(surveyId);
		if (survey==null) return null;
		return survey.getQuestion();
	}

	
	public Question RetrieveSpecificSurveyByQuestion (String surveyId,String questionId) {
	
		List<Question> SurveyQuestion = RetrieveAllSurveysQuestions(surveyId);
		if (SurveyQuestion==null )
			return null;
		Optional<Question> optionalQuestion = SurveyQuestion.stream().
				filter(q -> q.getId().equalsIgnoreCase(questionId)).findFirst();
		if (optionalQuestion.isEmpty())
		return null;


		return optionalQuestion.get();
	}

	public String addNewSurveyQuestion(String surveyid, Question question) {
		List<Question>questions = RetrieveAllSurveysQuestions(surveyid);
		String randomId = generateRandomId();
		question.setId(randomId);
		questions.add(question);
		return question.getId();
		
	}

	private String generateRandomId() {
		SecureRandom secureRandom = new SecureRandom();
		String randomId = new BigInteger(32,secureRandom).toString();
		return randomId;
	}

	public String deleteSurveyQuestion(String surveyid, String questionid) {
		// TODO Auto-generated method stub
		List<Question> surveyQuestions = RetrieveAllSurveysQuestions(surveyid);
		Predicate<?super  Question>predicate = q->q.getId().equalsIgnoreCase(questionid);
		
		boolean removed=surveyQuestions.removeIf(predicate);
		if (!removed) return null;
		return questionid;
		
		
		
	}

	public void updateSurveyQuestion(String surveyid, String questionid, Question question) {
		// TODO Auto-generated method stub
		List<Question> questions = RetrieveAllSurveysQuestions(surveyid);
		questions.removeIf(q-> q.getId().equalsIgnoreCase(questionid));
		questions.add(question);
		
	}

	

	}

		