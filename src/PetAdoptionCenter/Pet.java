package PetAdoptionCenter;

/**
 *
 * @author Guillermo
 */
import java.io.Serializable;

public abstract class Pet implements Adoptable, Vaccinable, Serializable {
    protected int petId;
    protected String name;
    protected String species;
    protected int age;
    protected String breed;
    protected boolean isAdopted;

    public Pet(int petId, String name, String species, int age, String breed) {
        this.petId = petId;
        this.name = name;
        this.species = species;
        this.age = age;
        this.breed = breed;
        this.isAdopted = false;
    }

    public boolean isAdopted() { return isAdopted; }
    public int getPetId() { return petId; }
    public String getName() { return name; }
    public String getSpecies() { return species; }

    public void adopt(Adopter adopter) throws PetAlreadyAdoptedException {
        if (isAdopted) throw new PetAlreadyAdoptedException(name + " has already been adopted.");
        this.isAdopted = true;
        adopter.addAdoptedPet(this);
    }

    public void vaccinate() {
        System.out.println(name + " has been vaccinated.");
    }

    public String getDetails() {
        return petId + ": " + name + " (" + species + ", " + breed + ", Age " + age + ") - " +
               (isAdopted ? "Adopted" : "Available");
    }
}

