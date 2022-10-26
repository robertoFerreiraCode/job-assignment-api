package dev.robertoferreira.jobassignment.Temp;

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
