package PetAdoptionCenter;

/**
 *
 * @author Guillermo
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adopter implements Serializable {
    int adopterId;
    private String name;
    private String contactInfo;
    private List<Pet> adoptedPets;

    public Adopter(int adopterId, String name, String contactInfo) {
        this.adopterId = adopterId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.adoptedPets = new ArrayList<>();
    }

    public void addAdoptedPet(Pet pet) {
        adoptedPets.add(pet);
    }

    public String getDetails() {
        return adopterId + ": " + name + " (" + contactInfo + "), Pets Adopted: " + adoptedPets.size();
    }
    
    public String getName() {
        return name;
    }
}

