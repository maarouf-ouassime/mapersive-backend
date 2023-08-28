package com.mapersive.mapersivebackend.services.generic;

import java.util.List;


public interface GenericService<T> {

	void save(T object);

	T findById(Long id);

	List<T> findAll();

	void update(T object);

	void deleteById(Long id);

}
