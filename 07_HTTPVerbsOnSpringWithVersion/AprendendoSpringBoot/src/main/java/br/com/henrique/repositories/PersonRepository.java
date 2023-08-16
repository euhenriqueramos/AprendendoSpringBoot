package br.com.henrique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henrique.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{}
