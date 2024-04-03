package se.miun.dt170g.projektdt170g.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "receipt", schema = "dt170gprojekt", catalog = "")
public class ReceiptEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "receipt_id", nullable = false)
    private int receiptId;
    @Basic
    @Column(name = "date", nullable = false)
    private Date date;

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
