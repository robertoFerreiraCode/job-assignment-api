package dev.robertoferreira.jobassignment.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class JobDTO {
    @NotNull
    private String name;
    @NotNull
    @Pattern(regexp = "^([0-2][0-9]|3[01])\\/(0[0-9]|1[0-2])\\/[0-2][0-9]{3}$")
    private String startDate;
    @NotNull
    @Pattern(regexp = "^([0-2][0-9]|3[01])\\/(0[0-9]|1[0-2])\\/[0-2][0-9]{3}$")
    private String endDate;
    private long tempID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getTempID() {
        return tempID;
    }

    public void setTempID(long tempID) {
        this.tempID = tempID;
    }

}
