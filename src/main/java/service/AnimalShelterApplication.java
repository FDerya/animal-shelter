package service;

import dao.AnimalDAO;
import dao.DBAccess;
import dao.UserDAO;
import model.Animal;
import model.User;

public class AnimalShelterApplication {

    public static void main(String[] args) {



        DBAccess dbAccess = new DBAccess("animalshelter", "root", "123@123AS");

        // open dbAccess
        dbAccess.openConnection();
        UserDAO userDAO = new UserDAO(dbAccess);
        AnimalDAO animalDAO = new AnimalDAO(dbAccess);

        // Test toevoeging User
        User newUser = new User("fatma.tatar@.com", "300", "admin");
        userDAO.storeUser(newUser);
        System.out.println("add user" + newUser);



        // Test om User met id op te halen
        User retrievedUser = userDAO.getUserById(newUser.getIdUser());
        System.out.println("Retrieved User: " + retrievedUser);

        // User update test
        retrievedUser.setEmail("alice@new@example.com");
        userDAO.updateUser(retrievedUser);
        System.out.println("Updated User: " + userDAO.getUserById(retrievedUser.getIdUser()));

        // Test om alle Users op te halen
        System.out.println("All Users:");
        for (User user : userDAO.getAllUsers()) {
            System.out.println(user.getEmail());
        }

        // Test delete User
        userDAO.deleteUser(retrievedUser.getIdUser());
        System.out.println("User deleted");


        // Test Animal operations
        Animal newAnimal = new Animal("Buddy", "Dog", 3, "Male", "Friendly dog", "Available");
        animalDAO.storeAnimal(newAnimal);
        System.out.println("Added Animal: " + newAnimal);

        Animal retrievedAnimal = animalDAO.getAnimalById(newAnimal.getIdAnimal());
        System.out.println("Retrieved Animal: " + retrievedAnimal);

        if (retrievedAnimal != null) {
            retrievedAnimal.setName("Buddy Updated");
            animalDAO.updateAnimal(retrievedAnimal);
            System.out.println("Updated Animal: " + animalDAO.getAnimalById(retrievedAnimal.getIdAnimal()));
        } else {
            System.out.println("Animal could not be retrieved for update");
        }

        System.out.println("All Animals:");
        for (Animal animal : animalDAO.getAllAnimal()) {
            System.out.println(animal.getName());
        }

        animalDAO.deleteAnimal(retrievedAnimal.getIdAnimal());
        System.out.println("Animal deleted");

        dbAccess.closeConnection();
    }


}