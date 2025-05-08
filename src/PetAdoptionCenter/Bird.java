package PetAdoptionCenter;

/**
 *
 * @author Guillermo
 */
public class Bird extends Pet {
    private boolean canTalk;

    public Bird(int petId, String name, int age, String breed, boolean canTalk) {
        super(petId, name, "Bird", age, breed);
        this.canTalk = canTalk;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Can Talk: " + (canTalk ? "Yes" : "No");
    }
}

