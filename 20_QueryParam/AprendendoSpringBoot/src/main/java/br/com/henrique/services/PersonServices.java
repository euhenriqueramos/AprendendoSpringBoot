package br.com.henrique.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henrique.controllers.PersonController;
import br.com.henrique.data.vo.v1.PersonVO;
import br.com.henrique.exceptions.RequiredObjectIsNullException;
import br.com.henrique.exceptions.ResourceNotFoundException;
import br.com.henrique.mapper.DozerMapper;
import br.com.henrique.mapper.custom.PersonMapper;
import br.com.henrique.model.Person;
import br.com.henrique.repositories.PersonRepository;
import jakarta.transaction.Transactional;

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
		
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons
			.stream()
			.forEach(p -> {
				try {
					p.add(
							linkTo(
									methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
							
								);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
					);
		return persons;
	}
	
	public PersonVO findById(Long id)
	{
		logger.info("Finding one person!");
		
		var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		try {
			vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	public PersonVO create(PersonVO person) 
	{
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		try {
			vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	/*public PersonVOV2 createV2(PersonVOV2 person) 
	{
		logger.info("Creating one person with V2!");
		var entity = mapper.convertVoToEntity(person);
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		try {
			vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}*/
	
	public PersonVO update(PersonVO person) 
	{
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("updating one person!");
		
		var entity = repository.findById(person.getKey())
				.orElseThrow(()-> new ResourceNotFoundException("No records found dor this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo =  DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		try {
			vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id)
	{
		logger.info("Disabling one person!");
		
		repository.disablePerson(id);
		
		var entity = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		try {
			vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
