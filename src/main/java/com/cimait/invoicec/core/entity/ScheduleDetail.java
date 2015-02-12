package com.cimait.invoicec.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "iv_sched_dtl")
public class ScheduleDetail {
    private long id;
    private String day;
    private String hourIni;
    private String minIni;
    private String hourEnd;
    private String minEnd;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "day")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Basic
    @Column(name = "hour_ini")
    public String getHourIni() {
        return hourIni;
    }

    public void setHourIni(String hourIni) {
        this.hourIni = hourIni;
    }

    @Basic
    @Column(name = "min_ini")
    public String getMinIni() {
        return minIni;
    }

    public void setMinIni(String minIni) {
        this.minIni = minIni;
    }

    @Basic
    @Column(name = "hour_end")
    public String getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(String hourEnd) {
        this.hourEnd = hourEnd;
    }

    @Basic
    @Column(name = "min_end")
    public String getMinEnd() {
        return minEnd;
    }

    public void setMinEnd(String minEnd) {
        this.minEnd = minEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleDetail that = (ScheduleDetail) o;

        if (id != that.id) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (hourEnd != null ? !hourEnd.equals(that.hourEnd) : that.hourEnd != null) return false;
        if (hourIni != null ? !hourIni.equals(that.hourIni) : that.hourIni != null) return false;
        if (minEnd != null ? !minEnd.equals(that.minEnd) : that.minEnd != null) return false;
        if (minIni != null ? !minIni.equals(that.minIni) : that.minIni != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (hourIni != null ? hourIni.hashCode() : 0);
        result = 31 * result + (minIni != null ? minIni.hashCode() : 0);
        result = 31 * result + (hourEnd != null ? hourEnd.hashCode() : 0);
        result = 31 * result + (minEnd != null ? minEnd.hashCode() : 0);
        return result;
    }
}
