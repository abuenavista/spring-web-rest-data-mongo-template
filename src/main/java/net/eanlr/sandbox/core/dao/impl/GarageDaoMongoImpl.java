package net.eanlr.sandbox.core.dao.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import java.util.Optional;

import net.eanlr.sandbox.core.dao.GarageDao;
import net.eanlr.sandbox.core.model.Garage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class GarageDaoMongoImpl implements GarageDao {
	@Autowired
	MongoOperations mongoOps;

	@Override
	public List<Garage> findAll() {
		return mongoOps.findAll(Garage.class);
	}

	@Override
	public List<Garage> findByName(String name) {
		return mongoOps.find(query(where("name").is(name)), Garage.class);
	}

	@Override
	public Optional<Garage> findById(String id) {
		return Optional.ofNullable( mongoOps.findById(id, Garage.class) );
	}

	@Override
	public void create(Garage entity) {
		mongoOps.insert(entity);
	}

	@Override
	public void update(Garage entity) {
		mongoOps.save(entity);
	}

	@Override
	public void delete(Garage entity) {
		mongoOps.remove(entity);

	}
}
