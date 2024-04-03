package se.miun.dt170g.projektdt170g.models;

import jakarta.persistence.*;

@NamedQuery(
        name = "EmployeeEntity.findAll",
        query = "SELECT l FROM EmployeeEntity l"
)
@Entity
@Table(name = "employee", schema = "dt170gprojekt", catalog = "")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employee_id", nullable = false)
    private int employeeId;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "role", nullable = false, length = 255)
    private String role;
    @Basic
    @Column(name = "adress", nullable = false, length = 255)
    private String adress;
    @Basic
    @Column(name = "telephone", nullable = false)
    private int telephone;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}
