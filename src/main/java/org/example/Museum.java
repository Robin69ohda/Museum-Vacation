package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Museum {
    private final String name;
    private final long code;
    private final long supervisorCode;
    private final Location location;
    private final String manager;
    private final String foundingYear;
    private final String phoneNumber;
    private final String fax;
    private final String email;
    private final String url;
    private final String profile;

    public ArrayList<Professor> guides;

    private Museum(Builder builder) {
        this.name = builder.name;
        this.code = builder.code;
        this.supervisorCode = builder.supervisorCode;
        this.location = builder.location;
        this.manager = builder.manager;
        this.foundingYear = builder.foundingYear;
        this.phoneNumber = builder.phoneNumber;
        this.fax = builder.fax;
        this.email = builder.email;
        this.url = builder.url;
        this.profile = builder.profile;
        this.guides = new ArrayList<>();
    }

    public void notifyGuide(String event, Path outputPathEvents) throws IOException {
        for (Professor professor : guides) {
            professor.update(event, outputPathEvents, this.name, this.code);
        }
    }

    public String getName() {
        return name;
    }

    public long getCode() {
        return code;
    }

    public long getSupervisorCode() {
        return supervisorCode;
    }

    public Location getLocation() {
        return location;
    }

    public String getManager() {
        return manager;
    }

    public String getFoundingYear() {
        return foundingYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public String getProfile() {
        return profile;
    }

    public static class Builder {
        private final String name;
        private final long code;
        private final long supervisorCode;
        private final Location location;

        private String manager;
        private String foundingYear;
        private String phoneNumber;
        private String fax;
        private String email;
        private String url;
        private String profile;

        public Builder(String name, long code, long supervisorCode, Location location) {
            this.name = name;
            this.code = code;
            this.supervisorCode = supervisorCode;
            this.location = location;
        }

        public Builder manager(String manager) {
            this.manager = manager;
            return this;
        }

        public Builder foundingYear(String foundingYear) {
            this.foundingYear = foundingYear;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder fax(String fax) {
            this.fax = fax;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder profile(String profile) {
            this.profile = profile;
            return this;
        }

        public Museum build() {
            return new Museum(this);
        }
    }
}
