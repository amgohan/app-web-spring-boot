package com.entreprise.business.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.entreprise.business.persistence.entities.Student;

/**
 * @author Sidi Amine
 *
 */
public interface StudentService {

	public Page<Student> pageStudent(Pageable pageable);

}
