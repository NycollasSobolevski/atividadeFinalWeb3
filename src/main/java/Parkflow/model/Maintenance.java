package Parkflow.model;

import java.time.LocalDate;

public class Maintenance {
    private int id;
    private String maintenceKind;
    private String observations;
    private LocalDate schedule;
    private int idCompany;
    private Company company;
    
    public Maintenance() {}

    public Maintenance(int id, String maintenceKind, String observations, LocalDate schedule, int idCompany) {
        this.id = id;
        this.maintenceKind = maintenceKind;
        this.observations = observations;
        this.schedule = schedule;
        this.idCompany = idCompany;
    }

    public int getId() {
        return this.id;
    }

    public String getMaintenceKind() {
        return this.maintenceKind;
    }

    public String getObservations() {
        return this.observations;
    }

    public LocalDate getSchedule() {
        return this.schedule;
    }

    public int getIdCompany() {
        return this.idCompany;
    }
    
    public Company getCompany() {
    	return this.company;
    }
    public void setCompany(Company value) {
    	this.company = value;
    }
    public void setId(int value)
    {
    	this.id = value;
    }
}
