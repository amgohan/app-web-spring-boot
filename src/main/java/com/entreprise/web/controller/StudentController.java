package com.entreprise.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entreprise.business.persistence.entities.Student;
import com.entreprise.business.service.StudentService;
import com.entreprise.web.utils.ApplicationURIs;
import com.entreprise.web.utils.ViewURIsMapping;

/**
 * @author Sidi Amine
 *
 */
@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@RequestMapping(value = ApplicationURIs.STUDENTS_LIST, method = RequestMethod.GET)
	public String listStudents(Model model, 
			                   @RequestParam(defaultValue = "1", value = "page", required = false) Integer page,
			                   @RequestParam(defaultValue = "5", value = "pageSize", required = false) Integer pageSize) {
		
		int pageIndex = (page == null || page.intValue() <= 0) ? 0 : (page - 1);
		
		if (pageSize == null || pageSize.intValue() <= 0) {
			pageSize = 5;
		}
		
		Page<Student> students = studentService.pageStudent(new PageRequest(pageIndex, pageSize));
		
		model.addAttribute("students", students);
		model.addAttribute("pageSize", pageSize);
		
		logger.info("##############################################");
		logger.info("Size of Page: {}.", students.getSize());
		logger.info("Size of Content List: {}.", students.getContent().size());
		logger.info("Total of Pages: {}.", students.getTotalPages());
		logger.info("Number : {}.", students.getNumber());
		logger.info("Number of elements : {}.", students.getNumberOfElements());
		logger.info("Total elements : {}.", students.getTotalElements());
		logger.info("##############################################");
		return ViewURIsMapping.STUDENTS_LIST.getView();
	}

}
