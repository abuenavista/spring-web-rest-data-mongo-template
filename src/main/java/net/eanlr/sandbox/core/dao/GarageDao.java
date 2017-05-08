package net.eanlr.sandbox.core.dao;

import java.util.List;
import java.util.Optional;

import net.eanlr.sandbox.core.model.Garage;

public interface GarageDao {

	Optional<Garage> findById(String id);

	List<Garage> findAll();
	List<Garage> findByName(String name);

	void create(Garage entity);
	void update(Garage entity);
	void delete(Garage entity);
}
