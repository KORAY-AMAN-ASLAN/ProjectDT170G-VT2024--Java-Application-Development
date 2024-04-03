package se.miun.dt170g.projektdt170g.items;

import se.miun.dt170g.projektdt170g.models.ShiftEntity;

import java.sql.Date;

public class Shift {

    private int shiftId;
    private String date;
    private String type;
    private int employeeId;

    public Shift(int shiftId, String date, String type, int employeeId) {
        this.shiftId = shiftId;
        this.date = date;
        this.type = type;
        this.employeeId = employeeId;
    }

    public Shift(ShiftEntity shift) {
        this.shiftId = shift.getShiftId();
        this.date = shift.getDate();
        this.type = shift.getType();
        this.employeeId = shift.getEmployeeId();

    }

    public Shift() {}

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}