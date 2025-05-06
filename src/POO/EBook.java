package POO;

/**
 *
 * @author Guillermo
 */
public class EBook extends Book {
    private String fileFormat;
    private double fileSize; // in MB

    public EBook(String title, String author, String ISBN, String fileFormat, double fileSize) {
        super(title, author, ISBN);
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return super.toString() + " | Format: " + fileFormat + ", Size: " + fileSize + "MB";
    }
}
