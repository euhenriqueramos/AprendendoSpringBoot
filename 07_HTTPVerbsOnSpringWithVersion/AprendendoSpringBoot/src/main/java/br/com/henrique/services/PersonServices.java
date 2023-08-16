package br.com.henrique.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.data.vo.v1.PersonVO;
import br.com.henrique.data.vo.v2.PersonVOV2;
import br.com.henrique.exceptions.ResourceNotFoundException;
import br.com.henrique.mapper.DozerMapper;
import br.com.henrique.mapper.custom.PersonMapper;
import br.com.henrique.model.Person;
import br.com.henrique.repositories.PersonRepository;

@Service //Springboot reconheça como objeto a ser injetado na api
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	public List<PersonVO> findAll()
	{
		logger.info("Finding all people!");
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id)
	{
		logger.info("Finding one person!");
		
		var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found dor this ID"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) 
	{
		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) 
	{
		logger.info("Creating one person with V2!");
		var entity = mapper.convertVoToEntity(person);
		var vo =  mapper.convertEntityToVO(repository.save(entity));
		return vo;
	}
	
	public PersonVO update(PersonVO person) 
	{
		logger.info("updating one person!");
		
		var entity = repository.findById(person.getId())
				.orElseThrow(()-> new ResourceNotFoundException("No records found dor this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public void delete(Long id) 
	{
		logger.info("deleting one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No records found dor this ID"));
		repository.delete(entity);
	}
	
}