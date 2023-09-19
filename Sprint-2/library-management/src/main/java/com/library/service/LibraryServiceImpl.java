package com.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Library;
import com.library.exception.ResourceNotFoundExceptionHandler;
import com.library.repo.ILibraryRepo;

@Service
public class LibraryServiceImpl implements ILibraryService{

	@Autowired
	private ILibraryRepo libraryRepo;
	
	@Override
	public Integer addBook(Library library) {
		Library savedLibrary = libraryRepo.save(library);
		return savedLibrary.getId();
	}

	@Override
	public Optional<Library> getBookByID(Integer id) {
		// TODO Auto-generated method stub
		return libraryRepo.findById(id);
	}

	@Override
	public List<Library> getBooks() {
		return libraryRepo.findAll();
	}

	@Override
	public Library updateBook(Library library, Integer id) {
		Library prevBook = libraryRepo.findById(id).orElseThrow(()->new ResourceNotFoundExceptionHandler("Book","id",id));
		prevBook.setBorrowedstatus(library.isBorrowedstatus());
		prevBook.setBookauthor(library.getBookauthor());
		prevBook.setBookgenre(library.getBookgenre());
		prevBook.setBookname(library.getBookname());
		prevBook.setBookprice(library.getBookprice());
		libraryRepo.save(prevBook);
		// TODO Auto-generated method stub
		return prevBook;
	}

	@Override
	public Library updateBorrowStatus(Integer id) {
		Library prevBook = libraryRepo.findById(id).orElseThrow(()->new ResourceNotFoundExceptionHandler("Book","id",id));
		boolean status = (prevBook.isBorrowedstatus())?false:true;
		prevBook.setBorrowedstatus(status);
		libraryRepo.save(prevBook);		
		return prevBook;
	}
	
	@Override
	public boolean updateBorrowStatusByPatchMethod(Integer id) {
		Library prevBook = libraryRepo.findById(id).orElseThrow(()->new ResourceNotFoundExceptionHandler("Book","id",id));
		boolean status = (prevBook.isBorrowedstatus())?false:true;
		libraryRepo.updateBorrowStatus(status, id);
		return status;
	}

	@Override
	public void deleteBook(Integer id) {
		libraryRepo.deleteById(id);
	}

}
