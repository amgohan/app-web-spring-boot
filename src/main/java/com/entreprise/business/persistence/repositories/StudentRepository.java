package com.entreprise.business.persistence.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.entreprise.business.persistence.entities.Student;

/**
 * Student repository.
 * @author Sidi Amine
 *
 */
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

}
