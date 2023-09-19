package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.book.entity.UserBookData;

@Repository
public interface IUserBookDataRepo extends JpaRepository<UserBookData, Integer>{

	@Query(value="select * from user_book_data where user_id=:userId and subscription_status='Active'",nativeQuery = true)
	List<UserBookData> findByUserId(@Param("userId") Integer userId);
	
	@Transactional
	@Modifying
	@Query(value="update user_book_data set user_id=null, subscription_status='Inactive' where subscription_id=:subscriptionId" ,nativeQuery = true)
	void updateSubscriptionStatusbyId(@Param("subscriptionId") Integer subscriptionId);
	
	@Query(value="select * from user_book_data where user_id=:userId and subscription_id=:subscriptionId and subscription_status='Active'",nativeQuery = true)
	UserBookData findByUserIdAndSubscriptionId(@Param("userId") Integer userId,@Param("subscriptionId") Integer subscriptionId);

	@Query(value="select * from user_book_data where user_id=:userId and book_id=:bookId and subscription_status='Active'",nativeQuery = true)
	Integer getsubscriptionID(@Param("userId") Integer userId,@Param("bookId")  Integer bookId);

}
