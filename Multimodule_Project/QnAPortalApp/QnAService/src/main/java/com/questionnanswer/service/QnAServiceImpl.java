package com.questionnanswer.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.questionnanswer.entity.Answers;
import com.questionnanswer.entity.Questions;
import com.questionnanswer.repository.IAnswersRepository;
import com.questionnanswer.repository.IQuestionsRepository;

@Service
public class QnAServiceImpl implements IQnAService{
	
	@Autowired
	private IQuestionsRepository questionsRepo;
	
	@Autowired
	private IAnswersRepository answersRepo;

	public Integer addQuestion(Questions question) {
		question.setCreatedDateTime(new Date());
		Questions newQuestion = questionsRepo.save(question);
		Integer questionId = newQuestion.getQuestionId();
		return questionId;
	}

	@Override
	public Integer addAnswer(Answers answer) throws Exception {
		answer.setCreatedDateTime(new Date());
		List<Questions> questions = questionsRepo.findByQuestionId(answer.getQuestionId());
		Integer answerId = 0;
		if(questions.size() > 0) {
			Answers newAnswer = answersRepo.save(answer);
			answerId = newAnswer.getAnswerId();
		}else {
			throw new Exception("Question not found");
		}
		
		return answerId;
	}

	@Override
	public List<Questions> getAllQuestions() {
		List<Questions> questions = questionsRepo.findAll();
		return questions;
	}

	@Override
	public List<Questions> getAllQuestionsById(Integer userId) {
		List<Questions> questions = questionsRepo.findByUserId(userId);
		return questions;
	}
	
	@Override
	public List<Answers> getAllAnswersById(Integer questionId) {
		List<Answers> answers = answersRepo.findByQuestionId(questionId);
		return answers;
	}

}
