package PetAdoptionCenter;

import java.util.*;
import java.io.*;

/**
 *
 * @author Guillermo
 */
public class PetAdoptionCenter implements Serializable {
    private List<Pet> pets;
    List<Adopter> adopters;

    public PetAdoptionCenter() {
        pets = new ArrayList<>();
        adopters = new ArrayList<>();
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        System.out.println("Pet " + pet.getName() + " added.");
    }

    public void registerAdopter(Adopter adopter) {
        adopters.add(adopter);
        System.out.println("Adopter " + adopter.getDetails() + " registered.");
    }

    public Pet findPetById(int id) throws PetNotFoundException {
        for (Pet p : pets) {
            if (p.getPetId() == id) return p;
        }
        throw new PetNotFoundException("Pet with ID " + id + " not found.");
    }

    public void displayAvailablePets() {
        for (Pet p : pets) {
            if (!p.isAdopted()) System.out.println(p.getDetails());
        }
    }

    public void saveData(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static PetAdoptionCenter loadData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (PetAdoptionCenter) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
            return new PetAdoptionCenter();
        }
    }
}

