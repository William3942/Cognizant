package PetAdoptionCenter;

/**
 *
 * @author Guillermo
 */
public class Dog extends Pet {
    private boolean isTrained;

    public Dog(int petId, String name, int age, String breed, boolean isTrained) {
        super(petId, name, "Dog", age, breed);
        this.isTrained = isTrained;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Trained: " + (isTrained ? "Yes" : "No");
    }
}
