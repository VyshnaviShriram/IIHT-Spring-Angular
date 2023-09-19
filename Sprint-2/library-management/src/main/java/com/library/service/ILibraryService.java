package com.library.service;

import java.util.List;
import java.util.Optional;

import com.library.entity.Library;

public interface ILibraryService{

	Integer addBook(Library library);
	Optional<Library> getBookByID(Integer id);
	List<Library> getBooks();
	Library updateBook(Library library, Integer id);
	Library updateBorrowStatus(Integer id);
	void deleteBook(Integer id);
	boolean updateBorrowStatusByPatchMethod(Integer id);
	
}
