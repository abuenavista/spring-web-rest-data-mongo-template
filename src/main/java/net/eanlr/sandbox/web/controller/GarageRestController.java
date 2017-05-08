package net.eanlr.sandbox.web.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import net.eanlr.sandbox.core.dao.GarageDao;
import net.eanlr.sandbox.core.model.Address;
import net.eanlr.sandbox.core.model.Garage;
import net.eanlr.sandbox.web.common.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/garages")
public class GarageRestController {
	private Logger log = LoggerFactory.getLogger(GarageRestController.class);

	@Autowired
	private GarageDao garageDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Garage> list() {
		log.debug("Listing all entities");
		return garageDao.findAll();
	}

	@RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
	public List<Garage> byName(@PathVariable String name) {
		log.debug("Listing garages matching name = '*{}*'.", name);
		return garageDao.findByName(name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Garage details(@PathVariable String id) {
		log.debug("Get garage by id = {}.", id);
		Optional<Garage> optionalEntity = garageDao.findById(id);
		return optionalEntity.orElseThrow(() -> new ResourceNotFoundException());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Garage> create(@RequestBody Garage data) throws URISyntaxException {
		if(data == null || data.getName() == null) {
			throw new RuntimeException("Invalid object");
		}
		log.debug("Creating new " + data);
		garageDao.create(data);
		URI location = new URI("/api/garages/" + data.getId());
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		Optional<Garage> optionalEntity = garageDao.findById(id);
		if(optionalEntity.isPresent()) {
			garageDao.delete(optionalEntity.get());
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable String id, @RequestBody Garage data) {
		Garage entity = garageDao.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		entity.setName(data.getName());
		entity.setAuthorized(data.isAuthorized());
		entity.setMakeList(data.getMakeList());
		if(data.getAddress()!=null) {
			entity.setAddress(data.getAddress());
		}
		garageDao.update(entity);
	}

	@RequestMapping(value = "/{id}/address", method = RequestMethod.PUT)
	public void updateAddress(@PathVariable String id, @RequestBody Address data) {
		Garage entity = garageDao.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		entity.setAddress(data);
		garageDao.update(entity);
	}

}
