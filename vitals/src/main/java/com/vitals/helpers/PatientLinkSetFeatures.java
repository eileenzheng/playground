package com.vitals.helpers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.testng.Reporter;

public class PatientLinkSetFeatures {
	
	private String expectedSpecialty;
	private String expectedAddress;
	private String expectedAddressLine2;
	private String expectedCity;
	private String expectedState;
	private String expectedZip;
	private String expectedNumber;
	private String expectedNumberUchc;
	private boolean hasLogo;
	private boolean hasBookOnline;
	private int bookType;
	private boolean isMatched;
	
	private static List<PatientLink> plList = new ArrayList<PatientLink>();
	
	public static void init() {

		File file = new File("PatientLinkFeatureFile");
		try {
			
			Object[] featuresList = FileUtils.readLines(file).toArray();
			
			for (Object item: featuresList) {		
				String[] features = item.toString().split(",", -1);
				// new PatientLink with first name, last name, specialty, address, city, state, zip added in constructor
				PatientLink pl = new PatientLink(features[0],features[1],features[2],features[3],features[4],features[5],features[6],features[7]);
				// set vitals phone
				if (features[7].equals("nophone"))
					pl.setVitalsPhone("");
				else
					pl.setVitalsPhone(features[7]);
				// set uchc phone
				if (features[8].equals("nophone"))
					pl.setUchcPhone("");
				else
					pl.setUchcPhone(features[8]);
				// set has book line or not
				if (features[9].equals("email")) {
					pl.setHasBookOnline(true);
					pl.setBookType(1); // 1 is email appointment
				}
				else if (features[9].equals("online")) {
					pl.setHasBookOnline(true);
					pl.setBookType(2); // 2 is online appointment
				}
				else
					pl.setHasBookOnline(false);
				// set has logo or not
				if (features[10].equals("logo"))
					pl.setHasLogo(true);
				else
					pl.setHasLogo(false);
				// add PatientLink item to the patient link list
				plList.add(pl);
			}
		} catch (IOException e) {
			Reporter.log("Error opening patient link feature file!");
		} 
	}
	
	public void setExpected(String name) {

		for (PatientLink item : plList) {
			if (name.contains(item.getFirstName()) && name.contains(item.getLastName())) {
				expectedSpecialty = item.getSpecialty();
				expectedAddress = item.getAddress();
				expectedAddressLine2 = item.getAddressLine2();
				expectedCity = item.getCity();
				expectedState = item.getState();
				expectedZip = item.getZip();
				expectedNumber = item.getVitalsPhone();
				expectedNumberUchc = item.getUchcPhone();
				hasBookOnline = item.hasBookOnline;
				bookType = item.bookType;
				hasLogo = item.hasLogo;
				isMatched = true;
			}
		}
	}
	
	public String getExpectedSpecialty() {
		return expectedSpecialty;
	}
	
	public String getExpectedAddress() {
		return expectedAddress;
	}
	
	public String getExpectedAddressLine2() {
		return expectedAddressLine2;
	}
	
	public String getExpectedCity() {
		return expectedCity;
	}
	
	public String getExpectedState() {
		return expectedState;
	}
	
	public String getExpectedZip() {
		return expectedZip;
	}
	
	public String getExpectedNumber() {
		return expectedNumber;
	}
	
	public String getExpectedNumberUchc() {
		return expectedNumberUchc;
	}
	
	public boolean hasBookOnline() {
		return hasBookOnline;
	}
	
	public int getBookType() {
		return bookType;
	}
	
	public boolean hasLogo() {
		return hasLogo;
	}
	
	public boolean isMatched() {
		return isMatched;
	}
	
	public void resetMatched() {
		isMatched = false;
	}
}
