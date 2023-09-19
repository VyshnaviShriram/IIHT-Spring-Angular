package com.questionnanswer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.questionnanswer.entity.Answers;
import com.questionnanswer.entity.Questions;

@Repository
public interface IQuestionsRepository extends JpaRepository<Questions, Integer>{

	List<Questions> findByUserId(Integer userId);
	
	List<Questions> findByQuestionId(Integer questionId);


}
