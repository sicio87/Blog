package eu.karols.domain;

public enum Role {
    USER("user"),
    ADMIN("admin");
    
    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}