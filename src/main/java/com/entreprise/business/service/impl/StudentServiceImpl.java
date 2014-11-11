package com.entreprise.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.entreprise.business.persistence.entities.Student;
import com.entreprise.business.persistence.repositories.StudentRepository;
import com.entreprise.business.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	@Override
	public Page<Student> pageStudent(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

}
