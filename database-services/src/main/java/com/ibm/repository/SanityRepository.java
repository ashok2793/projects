package com.ibm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.entity.Sanity;

@Repository
public interface SanityRepository extends JpaRepository<Sanity,Long>{
	
}
