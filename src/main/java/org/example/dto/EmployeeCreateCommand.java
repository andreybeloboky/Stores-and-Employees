package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class EmployeeCreateCommand {
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("position")
    private String position;
    @JsonProperty("salary")
    private int salary;
    @JsonProperty("store")
    private int storeId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }
    public int getStoreId() {
        return storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeCreateCommand that = (EmployeeCreateCommand) o;
        return salary == that.salary && storeId == that.storeId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, position, salary, storeId);
    }
}
