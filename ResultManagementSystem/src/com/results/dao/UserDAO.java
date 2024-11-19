package com.results.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.results.entity.User;

public class UserDAO {

	public User checkLogin(String userId, String password) {
		User user = null;
		try (BufferedReader br = new BufferedReader(new FileReader("D:/Users.csv"))) {
			br.readLine();
			String strCurrentLine;
			while ((strCurrentLine = br.readLine()) != null) {
				String[] arr = strCurrentLine.split(",");
				String emailStored = arr[0];
				String passwordStored = arr[1];
				String role = arr[2].toUpperCase();
				if (emailStored.equalsIgnoreCase(userId) && passwordStored.equals(password)) {
					if (role.equalsIgnoreCase("STUDENT") || role.equalsIgnoreCase("COMPANY")) {
						user = new User(userId, role, arr[3], arr[4], arr[5]);
					} else {
						user = new User(userId, role, arr[3], arr[4], null);
					}
					return user;
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file");
		}
		return user;
	}

	public User fetchResult(User user) {
		try {
			File resultFile = new File("D:/upload/" + user.getClassName() + ".xls");
			FileInputStream fis = new FileInputStream(resultFile);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator rows = sheet.rowIterator();
			Map<String, Double> resultMap = new HashMap<>();
			// reading the subjects
			List<String> subList = new ArrayList<>();
			XSSFRow headerRow = (XSSFRow) rows.next();
			Iterator headerCells = headerRow.cellIterator();
			while (headerCells.hasNext()) {
				XSSFCell cell = (XSSFCell) headerCells.next();
				subList.add(cell.getStringCellValue());
			}

			// comparing the roll number and fetching results
			Double totalMarks = 0.0;
			while (rows.hasNext()) {
				boolean userFound = false;
				int i = 1;
				XSSFRow row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					// fetching roll number
					if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()
							&& user.getUserId().equalsIgnoreCase(cell.getStringCellValue())) {
						System.out.println("User found ");
						userFound = true;
					} else if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType() && userFound) {
						resultMap.put(subList.get(i), cell.getNumericCellValue());
						totalMarks += (cell.getNumericCellValue());
						i++;
					}
				}
			}
			user.setResultMap(resultMap);
			user.setTotalMarks(totalMarks);
			user.setPercentage(totalMarks / (resultMap.size() * 100) * 100);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public Map<String, List<User>> fetchAllResults() {
		Map<String, List<User>> classUserMap = new HashMap<>();
		try {
			File folder = new File("D:/upload/");
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				if (file.isFile()) {
					List<User> users = new ArrayList<>();
					FileInputStream fis = new FileInputStream(file);
					String className = file.getName().replace(".xls", "").replace(".xlsx", "");
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					XSSFSheet sheet = wb.getSheetAt(0);
					Iterator rows = sheet.rowIterator();
					XSSFRow headerRow = (XSSFRow) rows.next();
					while (rows.hasNext()) {
						User user = new User();
						Double totalMarks = 0.0;
						int i = 0;
						XSSFRow row = (XSSFRow) rows.next();
						Iterator cells = row.cellIterator();
						while (cells.hasNext()) {
							XSSFCell cell = (XSSFCell) cells.next();
							if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								totalMarks += (cell.getNumericCellValue());
								i++;
							} else {
								user.setUserId(cell.getStringCellValue());
							}
						}
						user.setTotalMarks(totalMarks);
						user.setPercentage(totalMarks / (i * 100) * 100);
						users.add(user);
					}
					classUserMap.put(className, users);
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return classUserMap;
	}

	public Map<String, List<User>> fetchCompanyWiseResults(String companyName) {
		Map<String, List<User>> classUserMap = new HashMap<>();
		try {
			File folder = new File("D:/upload/");
			File[] listOfFiles = folder.listFiles();
			Map<String, Double> companyDataMap = loadCompanyData();
			double percentCriteria = companyDataMap.get(companyName);
			for (File file : listOfFiles) {
				if (file.isFile()) {
					List<User> users = new ArrayList<>();
					FileInputStream fis = new FileInputStream(file);
					String className = file.getName().replace(".xls", "").replace(".xlsx", "");
					XSSFWorkbook wb = new XSSFWorkbook(fis);
					XSSFSheet sheet = wb.getSheetAt(0);
					Iterator rows = sheet.rowIterator();
					XSSFRow headerRow = (XSSFRow) rows.next();
					while (rows.hasNext()) {
						User user = new User();
						Double totalMarks = 0.0;
						int i = 0;
						XSSFRow row = (XSSFRow) rows.next();
						Iterator cells = row.cellIterator();
						while (cells.hasNext()) {
							XSSFCell cell = (XSSFCell) cells.next();
							if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
								totalMarks += (cell.getNumericCellValue());
								i++;
							} else {
								user.setUserId(cell.getStringCellValue());
							}
						}
						user.setTotalMarks(totalMarks);
						user.setPercentage(totalMarks / (i * 100) * 100);
						if (user.getPercentage() - percentCriteria > 0) {
							users.add(user);
						}
					}
					classUserMap.put(className, users);
				}

			}

		} catch (

		IOException ex) {
			ex.printStackTrace();
		}
		return classUserMap;
	}

	public Map<String, Double> loadCompanyData() {
		Map<String, Double> companyMap = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("D:/Companies.csv"))) {
			String strCurrentLine = br.readLine();
			String[] arr = strCurrentLine.split(",");
			for (String eachArr : arr) {
				String[] companyPercentArr = eachArr.split("-");
				companyMap.put(companyPercentArr[0], Double.parseDouble(companyPercentArr[1]));
			}

		} catch (IOException e) {
			System.out.println("Error reading file");
		}
		return companyMap;
	}

}
