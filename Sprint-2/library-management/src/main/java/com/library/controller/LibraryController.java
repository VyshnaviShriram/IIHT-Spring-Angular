package com.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Library;
import com.library.service.ILibraryService;

@RestController
@CrossOrigin("http://localhost:4200/")
public class LibraryController {
	
	@Autowired
	private ILibraryService libraryService;
	
	@PostMapping("add/book")
	public Integer addBook(@RequestBody Library library) {
		return libraryService.addBook(library);
	}
	
	@GetMapping("/read/{id}")
	public Optional<Library> getBookByID(@PathVariable("id") Integer id) {
		return libraryService.getBookByID(id);
	}
	
	@GetMapping("/allbooks")
	public List<Library> getAllBooks(){
		return libraryService.getBooks();
	} 
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Library> updateBookByID(@RequestBody Library library, @PathVariable("id") Integer id) {
		return new ResponseEntity<Library>(libraryService.updateBook(library, id),HttpStatus.OK);
	}
	
	@PutMapping("/borrow/{id}")
	public ResponseEntity<Library> updateBorrowStatus(@PathVariable("id") Integer id) {
		return new ResponseEntity<Library>(libraryService.updateBorrowStatus(id),HttpStatus.OK);
	}
	
	@PatchMapping("/borrow/{id}")
	public ResponseEntity<Boolean> updateBorrowStatusByPatchMethod(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(libraryService.updateBorrowStatusByPatchMethod(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Library> deleteBookByID(@PathVariable("id") Integer id) {
		ResponseEntity<Library> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			libraryService.deleteBook(id);
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	

}
