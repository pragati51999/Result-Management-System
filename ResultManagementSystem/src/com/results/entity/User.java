package com.results.entity;

import java.util.Map;

public class User {

	private String userId;
	private String role;
	private String firstName;
	private String lastName;
	private String className;
	private Map<String, Double> resultMap;
	private double totalMarks;
	private double percentage;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public User(String userId, String role, String firstName, String lastName, String className) {
		super();
		this.userId = userId;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.className = className;
	}

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, Double> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Double> resultMap) {
		this.resultMap = resultMap;
	}

	public double getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(double totalMarks) {
		this.totalMarks = totalMarks;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

}
