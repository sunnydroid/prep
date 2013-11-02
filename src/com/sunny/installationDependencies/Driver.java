package com.sunny.installationDependencies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the name of the input file: ");

		String fileName = sc.nextLine();
		
		InstallationSystem testSystem  = new InstallationSystem();
		try {
			testSystem.processCommand(fileName);
		} catch (FileNotFoundException ex) {
			Logger.log("Cannot find file ' " + fileName + "'. Error => " + ex.getMessage());
		}
	}

}
