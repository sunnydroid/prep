package com.sunny.installationDependencies;

public class Logger {
	public static void log(String log) {
		System.out.println(log);
	}
	
	public static void logComponentUsedBy(Component component) {
		log(component.getName() + " used by the following: ");
		for(Component usedBy : component.getUsedBy()) {
			log(usedBy.getName());
		}
	}
	
	public static void logComponentUses(Component component) {
		log(component.getName() + " uses the following: ");
		for(Component uses : component.getUsedBy()) {
			log(uses.getName());
		}
	}
}
