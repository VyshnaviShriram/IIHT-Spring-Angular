package com.questionnanswer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questionnanswer.entity.Answers;
import com.questionnanswer.entity.Questions;
import com.questionnanswer.service.IQnAService;

@RestController
@RequestMapping("/QnA")
@CrossOrigin("*")
public class QnAController {

	@Autowired
	private IQnAService questionnAnswerService;
	
	@PostMapping("/postQuestion")
	public Integer PostNewQuestion(@RequestBody Questions question) {
		Integer questionId = questionnAnswerService.addQuestion(question);
		return questionId;
	}
	
	@PostMapping("/postAnswer")
	public Integer PostNewAnswer(@RequestBody Answers answer) {
		Integer answerId=null;
		try {
			answerId = questionnAnswerService.addAnswer(answer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answerId;
	}
	
//	@GetMapping("/getAllQuestions")
//	public List<Questions> retrieveAllQuestions(){
//		List<Questions> questions = null;
//		questions = questionnAnswerService.getAllQuestions();
//		return questions ;
//	}
	
	@GetMapping("/getAllQuestionsById")
	public List<Questions> retrieveAllQuestionsById(@RequestParam("userId") Optional<Integer> userId){
		List<Questions> questions = null;
		if(userId.isPresent())
			questions = questionnAnswerService.getAllQuestionsById(userId.get());
		else
			questions = questionnAnswerService.getAllQuestions();
		return questions ;
	}
	
	
	@GetMapping("/getAnswersByQuestionsId/{questionId}")
	public List<Answers> retrieveAllAnswersById(@PathVariable("questionId") Integer questionId){
		List<Answers> answers = null;
		answers = questionnAnswerService.getAllAnswersById(questionId);
		return answers ;
	}

}
