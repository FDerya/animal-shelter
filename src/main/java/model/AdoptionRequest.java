package model;

import java.util.Date;

public class AdoptionRequest {

    // Attributes
    private  int idAdoption;
    private  int idUser;
    private int idAnimal;
    private Date requestDate;
    private String status;


    // constructors
    public AdoptionRequest(int idAdoption, int idUser, int idAnimal, Date requestDate, String status){
        this.idAdoption = idAdoption;
        this.idUser = idUser;
        this.idAnimal = idAnimal;
        this.requestDate =requestDate;
        this.status = status;


    }
    
// Getters & Setters

    public int getIdAdoption() {
        return idAdoption;
    }

    public void setIdAdoption(int idAdoption) {
        this.idAdoption = idAdoption;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
// Methode
    @Override
    public String toString() {
        return "AdoptionRequest{" +
                "idAdoption=" + idAdoption +
                ", idUser=" + idUser +
                ", idAnimal=" + idAnimal +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                '}';
    }
}
