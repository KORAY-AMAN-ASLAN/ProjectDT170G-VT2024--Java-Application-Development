package se.miun.dt170g.projektdt170g.models;

import jakarta.persistence.*;
@NamedQuery(
        name = "TableSessionEntity.findAll",
        query = "SELECT l FROM TableEntity l"
)
@Entity
@Table(name = "tables", schema = "dt170gprojekt", catalog = "")
public class TableEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "table_number", nullable = false)
    private int tableNumber;
    @Basic
    @Column(name = "status", nullable = false, length = 255)
    private String status;
    @Basic
    @Column(name = "table_size", nullable = false)
    private int tableSize;

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
