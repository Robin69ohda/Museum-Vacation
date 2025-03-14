package org.example;

import java.util.Objects;

class GroupKey {
    private final int museumCode;
    private final String interval;

    public GroupKey(int museumCode, String interval) {
        this.museumCode = museumCode;
        this.interval = interval;
    }

    public int getMuseumCode() {
        return museumCode;
    }

    public String getInterval() {
        return interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupKey groupKey = (GroupKey) o;
        return Objects.equals(museumCode, groupKey.museumCode) &&
                Objects.equals(interval, groupKey.interval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(museumCode, interval);
    }

    @Override
    public String toString() {
        return "GroupKey{" +
                "museumCode='" + museumCode + '\'' +
                ", interval='" + interval + '\'' +
                '}';
    }
}
