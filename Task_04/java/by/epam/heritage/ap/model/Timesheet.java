package by.epam.heritage.ap.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Timesheet extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int timesheetId;
    private int apartmentId;
    private boolean isReserved;
    private LocalDate reservedDate;

    public Timesheet() {
    }

    public Timesheet(int timesheetId, int apartmentId, boolean isReserved, LocalDate reservedDate) {
        this.timesheetId = timesheetId;
        this.apartmentId = apartmentId;
        this.isReserved = isReserved;
        this.reservedDate = reservedDate;
    }

    public int getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(int timesheetId) {
        this.timesheetId = timesheetId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public boolean getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timesheet)) return false;
        Timesheet timesheet = (Timesheet) o;
        return getTimesheetId() == timesheet.getTimesheetId() &&
                getApartmentId() == timesheet.getApartmentId() &&
                isReserved == timesheet.isReserved &&
                getReservedDate().equals(timesheet.getReservedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimesheetId(), getApartmentId(), isReserved, getReservedDate());
    }

    @Override
    public String toString() {
        return "Timesheet{" +
                "timesheetId=" + timesheetId +
                ", apartmentId=" + apartmentId +
                ", isReserved=" + isReserved +
                ", reservedDate=" + reservedDate +
                '}';
    }
}
