package PetAdoptionCenter;

/**
 *
 * @author Guillermo
 */
public class Cat extends Pet {
    private boolean isIndoor;

    public Cat(int petId, String name, int age, String breed, boolean isIndoor) {
        super(petId, name, "Cat", age, breed);
        this.isIndoor = isIndoor;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Indoor: " + (isIndoor ? "Yes" : "No");
    }
}

