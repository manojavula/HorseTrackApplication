package com.manu.horsetrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manu.horsetrack.entity.Horse;

public interface HorseRepository extends JpaRepository<Horse, Integer>{

	List<Horse> findAll();
	Horse findByHorseNumberEquals(int horseNumber);	

}
