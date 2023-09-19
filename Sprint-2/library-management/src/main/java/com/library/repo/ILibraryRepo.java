package com.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.library.entity.Library;

@Repository
public interface ILibraryRepo extends JpaRepository<Library, Integer>{
	
	@Transactional
	@Modifying
	@Query("update Library l set l.borrowedstatus=:status where l.id=:bookid" )
	void updateBorrowStatus(@Param("status") boolean status, @Param("bookid") Integer id);

}
