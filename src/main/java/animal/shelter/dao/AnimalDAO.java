package animal.shelter.dao;

import animal.shelter.model.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends AbstractDAO {


    public AnimalDAO(DBAccess dbAccess) {
        super(dbAccess);

    }


    public void storeAnimal(Animal animal) {
        String sql = "INSERT INTO animal (name, species,age, gender, description,status) VALUES (?,?,?,?,?,?)";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getSpecies());
            preparedStatement.setInt(3, animal.getAge());
            preparedStatement.setString(4, animal.getGender());
            preparedStatement.setString(5, animal.getDescription());
            preparedStatement.setString(6, animal.getStatus());
            int key = executeInsertStatementWithKey();
            animal.setIdAnimal(key);

        } catch (SQLException sqlException) {
            System.out.println("SQL error : " + sqlException.getMessage());
        }
    }

    public Animal getAnimalById(int idAnimal) {
        String sql = "SELECT * FROM animal WHERE id = ?";
        Animal result = null;
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setInt(1, idAnimal);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String species = resultSet.getString("species");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                result = new Animal(idAnimal, name, species, age, gender, description, status);

            } else {
                System.out.println("Animal with this ID does not exist");
            }

        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());
        }
        return result;

    }

    public void updateAnimal(Animal animal) {
        String sql = "UPDATE animal SET name = ?, species = ?, age = ?, gender = ?, description = ?, status = ? WHERE id = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getSpecies());
            preparedStatement.setInt(3, animal.getAge());
            preparedStatement.setString(4, animal.getGender());
            preparedStatement.setString(5, animal.getDescription());
            preparedStatement.setString(6, animal.getStatus());
            preparedStatement.setInt(7, animal.getIdAnimal());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());

        }
    }

    public void deleteAnimal(int idAnimal) {
        String sql = "DELETE FROM animal WHERE id = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idAnimal);
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());
        }
    }

    public List<Animal> getAllAnimal() {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animal";
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int idAnimal = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String species = resultSet.getString("species");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                Animal animal = new Animal(idAnimal, name, species, age, gender, description, status);
                animals.add(animal);

            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error :" + sqlException.getMessage());
        }
        return animals;
    }
}

