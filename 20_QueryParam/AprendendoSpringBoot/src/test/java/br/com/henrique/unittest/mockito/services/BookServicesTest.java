package br.com.henrique.unittest.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.unittests.mapper.mocks.MockBook;
import br.com.henrique.data.vo.v1.BookVO;
import br.com.henrique.exceptions.RequiredObjectIsNullException;
import br.com.henrique.model.Book;
import br.com.henrique.repositories.BookRepository;
import br.com.henrique.services.BookServices;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;
	
	@InjectMocks
	private BookServices services;
	
	@Mock
	BookRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	Date data = new Date();
	
	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
			   entity.setId(1L);
			   
			   when(repository.findById(1L)).thenReturn(Optional.of(entity));
			   
			   var result = services.findById(1L);
			   assertNotNull(result);
			   assertNotNull(result.getKey());
			   assertNotNull(result.getLinks());
			  // System.out.println(result.toString());
			   assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
			   assertEquals("Author Test1", result.getAuthor());
			   assertEquals(14.50, result.getPrice());
			   //assertEquals(data, result.getLaunchDate());
			   assertEquals("Title1", result.getTitle());
	}
	/*
	@Test
	void testFindAll() {
		List<Book> list = input.mockEntityList();
		   
		   when(repository.findAll()).thenReturn(list);
		   
		   var book = services.findAll();
		   
		   assertNotNull(book);
		   assertEquals(14, book.size());
		   
		   var bookOne = book.get(1);
		   
		   assertNotNull(bookOne);
		   assertNotNull(bookOne.getKey());
		   assertNotNull(bookOne.getLinks());
		   
		   assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		   assertEquals("Author Test1", bookOne.getAuthor());
		   assertEquals(14.50, bookOne.getPrice());
		//   assertEquals(data, bookOne.getLaunchDate());
		   assertEquals("Title1", bookOne.getTitle());

		   var bookFour = book.get(4);
		   
		   assertNotNull(bookFour);
		   assertNotNull(bookFour.getKey());
		   assertNotNull(bookFour.getLinks());
		   
		   assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
		   assertEquals("Author Test4", bookFour.getAuthor());
		   assertEquals(14.50, bookFour.getPrice());
		  // assertEquals(data, bookFour.getLaunchDate());
		   assertEquals("Title4", bookFour.getTitle());
		  
		   var bookSeven = book.get(7);
		   
		   assertNotNull(bookSeven);
		   assertNotNull(bookSeven.getKey());
		   assertNotNull(bookSeven.getLinks());
		   
		   assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
		   assertEquals("Author Test7", bookSeven.getAuthor());
		   assertEquals(14.50, bookSeven.getPrice());
		  // assertEquals(data, bookSeven.getLaunchDate());
		   assertEquals("Title7", bookSeven.getTitle());
	}
*/
	@Test
	void testCreate() {
		Book entity = input.mockEntity(1);
		
		Book persisted = entity;
		persisted.setId(1L);
		   
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		   var result = services.create(vo);
		   
		   assertNotNull(result);
		   assertNotNull(result.getKey());
		   assertNotNull(result.getLinks());
		   assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		   assertEquals("Author Test1", result.getAuthor());
		   assertEquals(14.50, result.getPrice());
		  // assertEquals(data, result.getLaunchDate());
		   assertEquals("Title1", result.getTitle());
	}
	
	@Test
	void testUpdateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			services.create(null);
		});
		String expectedMessage = "It not allowed to persist a nul object";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test
	void testCreateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			services.create(null);
		});
		String expectedMessage = "It not allowed to persist a nul object";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
	}

	@Test
	void testUpdate() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		persisted.setId(1L);
		   
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		   var result = services.update(vo);
		   
		   assertNotNull(result);
		   assertNotNull(result.getKey());
		   assertNotNull(result.getLinks());
		   assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		   assertEquals("Author Test1", result.getAuthor());
		   assertEquals(14.50, result.getPrice());
		  // assertEquals(data, result.getLaunchDate());
		   assertEquals("Title1", result.getTitle());
	}

	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		   
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		   
		services.delete(1L);
	}

}
