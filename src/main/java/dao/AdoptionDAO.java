package dao;

import model.AdoptionRequest;
import model.Animal;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdoptionDAO extends AbstractDAO {
    public AdoptionDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    public void storeAdoption(AdoptionRequest adoptionRequest) {
        String sql = "INSERT INTO adoptionrequest (userId, animalId,requestDate,status) VALUES (?,?,?,?)";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setInt(1, adoptionRequest.getUser().getIdUser());
            preparedStatement.setInt(2, adoptionRequest.getAnimal().getIdAnimal());
            preparedStatement.setDate(3, new java.sql.Date(adoptionRequest.getRequestDate().getTime()));
            preparedStatement.setString(4, adoptionRequest.getStatus());
            int key = executeInsertStatementWithKey();
            adoptionRequest.setIdAdoption(key);

        } catch (SQLException sqlException) {
            System.out.println("SQL error :" + sqlException.getMessage());
        }

    }

    public AdoptionRequest getAdoptionById(int idAdoption) {
        String sql = "SELECT * FROM adoptionrequest WHERE id = ?";
        AdoptionRequest result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idAdoption);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                int animalId = resultSet.getInt("animalId");
                Date requestDate = resultSet.getDate("requestDate");
                String status = resultSet.getString("status");
                User user = new UserDAO(dbAccess).getUserById(userId);
                Animal animal = new AnimalDAO(dbAccess).getAnimalById(animalId);
                result = new AdoptionRequest(idAdoption, user, animal, requestDate, status);

            } else {
                System.out.println("Adoption request with this ID does not exist");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error :" + sqlException.getMessage());
        }
        return result;

    }

    public void updateAdoption(AdoptionRequest adoptionRequest) {
        String sql = "UPDATE adoptionrequest SET userId = ?, animalId = ?, requestDate = ?, status = ? WHERE id = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, adoptionRequest.getUser().getIdUser());
            preparedStatement.setInt(2, adoptionRequest.getAnimal().getIdAnimal());
            preparedStatement.setDate(3, new java.sql.Date(adoptionRequest.getRequestDate().getTime()));
            preparedStatement.setString(4, adoptionRequest.getStatus());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());
        }
    }

    public void deleteAdoption(int idAdoption) {
        String sql = "DELETE FROM adoptionrequest WHERE id = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idAdoption);
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error : " + sqlException.getMessage());
        }
    }

    public List<AdoptionRequest> getAllAdoption() {
        List<AdoptionRequest> adoption = new ArrayList<>();
        String sql = "SELECT * FROM adoptionrequest";
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int idAdoption = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int idAnimal = resultSet.getInt("animalId");
                Date requestDate = resultSet.getDate("requestDate");
                String status = resultSet.getString("status");
                User user = new UserDAO(dbAccess).getUserById(userId);
                Animal animal = new AnimalDAO(dbAccess).getAnimalById(idAnimal);
                AdoptionRequest adoptionRequest = new AdoptionRequest(idAdoption, user, animal, requestDate, status);
                adoption.add(adoptionRequest);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error : " + sqlException.getMessage());
        }
        return adoption;
    }
}




















