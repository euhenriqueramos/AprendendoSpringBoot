package br.com.henrique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henrique.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{}
