package se.miun.dt170g.projektdt170g.models;

import jakarta.persistence.*;

import java.sql.Date;



@NamedQueries({
        @NamedQuery(
                name = "ShiftEntity.findAll",
                query = "SELECT l FROM ShiftEntity l"
        ),
        @NamedQuery(
                name = "ShiftEntity.findByDate",
                query = "SELECT l FROM ShiftEntity l WHERE l.date = :date"
        )
})

@Entity
@Table(name = "shift", schema = "dt170gprojekt", catalog = "")
public class ShiftEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "shift_id", nullable = false)
    private int shiftId;
    @Basic
    @Column(name = "date", nullable = false)
    private String date;
    @Basic
    @Column(name = "type", nullable = false, length = 255)
    private String type;
    @Basic
    @Column(name = "employee_id", nullable = false)
    private int employeeId;

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
