package dev.robertoferreira.jobassignment.dtos;

import javax.validation.constraints.NotNull;

public class TempIdentifierDTO {
    public long getTempID() {
        return tempID;
    }

    public void setTempID(long tempID) {
        this.tempID = tempID;
    }

    @NotNull
    private long tempID;
}
