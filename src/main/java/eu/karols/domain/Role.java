package eu.karols.domain;

public enum Role {
	ROLE_USER("USER"),
	ROLE_ADMIN("ADMIN");
    
    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}