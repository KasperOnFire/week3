package libraryInventory;

public class Item {
    
    public String ino;
    public String isbn;
    public String title;
    public String author;
    public String itype;
    public String language;
    public String publisher;
    public String publishdate;

    public Item(String ino, String isbn, String title, String author, String itype, String language, String publisher, String publishdate) {
        this.ino = ino;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.itype = itype;
        this.language = language;
        this.publisher = publisher;
        this.publishdate = publishdate;
    }

}
