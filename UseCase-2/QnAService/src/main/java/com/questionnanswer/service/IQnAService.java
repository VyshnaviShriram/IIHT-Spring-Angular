package com.questionnanswer.service;

import java.util.List;
import java.util.Optional;

import com.questionnanswer.entity.Answers;
import com.questionnanswer.entity.Questions;

public interface IQnAService {

	Integer addQuestion(Questions question);

	Integer addAnswer(Answers answer) throws Exception;

	List<Questions> getAllQuestions();
	
	List<Questions> getAllQuestionsById(Integer userId);

	List<Answers> getAllAnswersById(Integer questionId);
	
	

}
