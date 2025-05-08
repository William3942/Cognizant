package PetAdoptionCenter;

/**
 *
 * @author Guillermo
 */
public interface Adoptable {
    void adopt(Adopter adopter) throws PetAlreadyAdoptedException;
}
