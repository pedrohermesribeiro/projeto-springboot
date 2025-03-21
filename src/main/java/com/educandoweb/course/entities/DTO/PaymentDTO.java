package com.educandoweb.course.entities.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PaymentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date moment;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Date moment) {
        this.id = id;
        this.moment = moment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PaymentDTO other = (PaymentDTO) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "PaymentDTO [id=" + id + ", moment=" + moment + "]";
    }
}