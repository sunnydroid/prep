package com.sunny.installationDependencies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InstallationSystem {
	
	private static final String COMMAND_DEPEND = "DEPEND";
	private static final String COMMAND_INSTALL = "INSTALL";
	private static final String COMMAND_REMOVE = "REMOVE";
	private static final String COMMAND_LIST = "LIST";
	private static final String COMMAND_END = "END";
	
	private Set<Component> dependencyList;
	private Set<Component> installedList;
	
	public InstallationSystem() {
		dependencyList = new HashSet<Component>();
		installedList = new HashSet<Component>();
	}
	
	public void addDependency(Component component, Set<Component> dependencies) {
		/* adding component that does not exist in the dependency list */
		if(!dependencyList.contains(component)) {
			/* iterate through the dependencies, add them to the list if necessary and update them*/
			for(Component dependencyComponent : dependencies) {
				if(!dependencyList.contains(dependencyComponent)) {
					dependencyList.add(dependencyComponent);
				}
				/* update dependencyComponent to include component in it's usedby list */
				dependencyComponent.addUsedByComponent(component);
				component.addUsesComponent(dependencyComponent);
			}
			
			dependencyList.add(component);
		}
		//TODO: handle case where dependencies are being updated
	}
	
	public void installComponent(Component component) {
		if(installedList.contains(component)) {
			Logger.log("Component '" + component.getName() + "' is aready installed");
			return;
		}
		/* get list of dependencies and install them if necessary, recursive */
		if(dependencyList.contains(component)) {
			for(Component dependencyComponent : component.getUses()) {
				if(!installedList.contains(dependencyComponent)) {
					installComponent(dependencyComponent);
				}
			}
		}
		
		installedList.add(component);
		Logger.log("Installing '" + component.getName() + "'");
	}
	
	public void removeComponent(Component component) {
		if(!installedList.contains(component)) {
			Logger.log("Component '" + component.getName() + "' is not installed, cannot remove");
			return;
		}
		
		/* get list of dependencies and remove them if they aren't used => recursive */
		if(dependencyList.contains(component)) {
			for(Component usedByComponent : component.getUsedBy()) {
				/* if used by component is not installed, remove it component*/
				if(!installedList.contains(usedByComponent)) {
					removeComponent(usedByComponent);
				} else {
					Logger.log(component.getName() + "is still needed");
				}
			}
		}
		//TODO: iterate through the list of components that this component uses and check if components in their
		// usedBy list are installed
		
		installedList.remove(component);
		Logger.log("Removed '" + component.getName() + "'");
	}
	
	public void listComponents() {
		Logger.log("Installed components");
		for(Component component : installedList) {
			Logger.log(component.getName());
		}
	}
	
	public void processCommand(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));

		while (scanner.hasNext()) {
			String[] input = scanner.nextLine().split(" +");
			
			if(input[0].equals(InstallationSystem.COMMAND_DEPEND)) {
				Component component = new Component(input[1]);
				Set<Component> dependencies = new HashSet<Component>();
				for(int i = 2; i < input.length; i++) {
					dependencies.add(new Component(input[i]));
				}
				addDependency(component, dependencies);
				
			} else if (input[0].equals(InstallationSystem.COMMAND_INSTALL)) {
				Component component = new Component(input[1]);
				installComponent(component);
				
			} else if (input[0].equals(InstallationSystem.COMMAND_REMOVE)) {
				Component component = new Component(input[1]);
				removeComponent(component);
				
			} else if (input[0].equals(InstallationSystem.COMMAND_LIST)) {
				listComponents();
			} else if (input[0].equals(InstallationSystem.COMMAND_END)) {
				break;
			} else {
				Logger.log("Unrecognized command");
			}
		}
		
	}

}
