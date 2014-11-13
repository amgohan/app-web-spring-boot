package com.entreprise.web.utils;

public enum ViewURIsMapping {
	// Home page
	HOME("index"),
	
	// Students views
	STUDENTS_LIST("students/list"),
	STUDENTS_NEW("students/new"),
	STUDENTS_VIEW("students/view");
	
	private String view;
	
	/**
	 * the view location
	 * @return view String
	 */
	public String getView() {
		return this.view;
	}
	
	ViewURIsMapping(String view) {
		this.view = view;
	}
	
}