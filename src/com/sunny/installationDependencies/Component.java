package com.sunny.installationDependencies;

import java.util.HashSet;
import java.util.Set;

public class Component {
	
	private String name;
	private Set<Component> uses;
	private Set<Component> usedBy;
	
	public Component(String name) {
		this.name = name;
		uses = new HashSet<Component>();
		usedBy = new HashSet<Component>();
	}
	
	public void addUsedByComponent(Component component) {
		if(!usedBy.contains(component)) {
			usedBy.add(component);
		}
	}
	
	public void removeUsedBy(Component component) {
		
	}
	
	public void addUsesComponent(Component component) {
		if(!uses.contains(component)) {
			uses.add(component);
		}
	}
	
	public void removeUses(Component component) {
		
	}
	
	public String getName() {
		return name;
	}
	
	public Set<Component> getUses() {
		return uses;
	}

	public void setUses(Set<Component> uses) {
		this.uses = uses;
	}

	public Set<Component> getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(Set<Component> usedBy) {
		this.usedBy = usedBy;
	}

	public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Component))
            return false;

        Component rhs = (Component) obj;
        return ((rhs.name != null) && (this.name != null) && (rhs.name.equals(this.name)));
    }
}
