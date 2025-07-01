package Parkflow.model;

public class Company {
    private int id;
    private String name;
    private String cnpj;
    private int idAddress;
    private int idPlan;
    private int idSettings;

    public Company() {}

    public Company(int id, String name, String cnpj) {
        this.setId(id);
        this.setName(name);
        this.setCnpj(cnpj);
    }

    public Company(int id, String name, String cnpj, int idAddress, int idPlan, int idSettings) {
        this.setId(id);
        this.setName(name);
        this.setCnpj(cnpj);
        this.setIdAddress(idAddress);
        this.setIdPlan(idPlan);
        this.setIdSettings(idSettings);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getIdSettings() {
        return idSettings;
    }

    public void setIdSettings(int idSettings) {
        this.idSettings = idSettings;
    }
}