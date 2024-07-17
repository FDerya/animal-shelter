package animal.shelter.model;

import java.util.Date;

public class AdoptionRequest {

    private int idAdoption;
    private User user;
    private Animal animal;
    private Date requestDate;
    private String status;


    /**
     * Constructs a new AdoptionRequest with the specified details.
     *
     * @param idAdoption  the ID of the adoption request
     * @param user        the user making the adoption request
     * @param animal      the animal to be adopted
     * @param requestDate the date of the adoption request
     * @param status      the status of the adoption request
     */
    public AdoptionRequest(int idAdoption, User user, Animal animal, Date requestDate, String status) {
        this.idAdoption = idAdoption;
        this.user = user;
        this.animal = animal;
        this.requestDate = requestDate;
        this.status = status;

    }

    // Gets and sets the attributes of Adoption Request
    public int getIdAdoption() {
        return idAdoption;
    }

    public void setIdAdoption(int idAdoption) {
        this.idAdoption = idAdoption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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

    @Override
    public String toString() {
        return "AdoptionRequest{" +
                "idAdoption=" + idAdoption +
                ", user=" + user +
                ", animal=" + animal +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                '}';
    }
}
