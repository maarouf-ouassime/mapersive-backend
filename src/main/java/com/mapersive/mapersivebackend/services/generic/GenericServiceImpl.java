package com.mapersive.mapersivebackend.services.generic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public class GenericServiceImpl<T> implements GenericService<T> {

	JpaRepository<T, Long> repository;

	public GenericServiceImpl(JpaRepository<T, Long> repository) {
		this.repository = repository;
	}

	public void save(T object) {
		repository.save(object);
	}

	public void update(T object) {
		save(object);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public List<T> findAll() {
		return repository.findAll();
	}

	public T findById(Long id) {
		return repository.findById(id).get();
	}
}
