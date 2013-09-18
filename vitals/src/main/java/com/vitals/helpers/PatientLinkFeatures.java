package com.vitals.helpers;

public class PatientLinkFeatures {
	
	private static String expectedSpecialty;
	private static String expectedAddress;
	private static String expectedCity;
	private static String expectedState;
	private static String expectedZip;
	private static String expectedNumber;
	private static String expectedNumberUchc;
	
	public static void setExpected(String name) {
		if (name.contains("Natanzon")){
			expectedSpecialty = "Cardiology";
			expectedAddress = "260 Avenue X";
			expectedCity = "Brooklyn";
			expectedState = "NY";
			expectedZip = "11223";
			expectedNumber = "(718) 737-9323";
			expectedNumberUchc = "(718) 737-9016";
		}
		
		if (name.contains("Sullivan")){
			expectedSpecialty = "Cardiology";
			expectedAddress = "55 Meadowlands Pkwy";
			expectedCity = "Secaucus";
			expectedState = "NJ";
			expectedZip = "07094";
			expectedNumber = "(201) 381-0104";
			expectedNumberUchc = "(201) 381-6044";
		}
		
		if (name.contains("Tsenovoy")){
			expectedSpecialty = "Cardiology";
			expectedAddress = "55 Meadowlands Pkwy";
			expectedCity = "Secaucus";
			expectedState = "NJ";
			expectedZip = "07094";
			expectedNumber = "(201) 350-7536";
			expectedNumberUchc = "(201) 580-3972";
		}
		
		if (name.contains("Khasak")) {
			expectedSpecialty = "Dermatology";
			expectedAddress = "111-29 Queens Blvd";
			expectedCity = "Forest Hills";
			expectedState = "NY";
			expectedZip = "11375";
			expectedNumber = "(718) 737-9392";
			expectedNumberUchc = "(718) 526-1816";
		}
		
		if (name.contains("Quintana")) {
			expectedSpecialty = "Dermatology";
			expectedAddress = "130 W 42nd St";
			expectedCity = "New York";
			expectedState = "NY";
			expectedZip = "10036";
			expectedNumber = "(646) 499-2330";
			expectedNumberUchc = "(646) 499-2346";
		}
	}
	
	public static String getExpectedSpecialty() {
		return expectedSpecialty;
	}
	
	public static String getExpectedAddress() {
		return expectedAddress;
	}
	
	public static String getExpectedCity() {
		return expectedCity;
	}
	
	public static String getExpectedState() {
		return expectedState;
	}
	
	public static String getExpectedZip() {
		return expectedZip;
	}
	
	public static String getExpectedNumber() {
		return expectedNumber;
	}
	
	public static String getExpectedNumberUchc() {
		return expectedNumberUchc;
	}
}
