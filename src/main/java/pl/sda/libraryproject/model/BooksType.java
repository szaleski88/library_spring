package pl.sda.libraryproject.model;

public enum BooksType {
    SCIENCE_FICTION("Science Fiction"),
    HORROR("Horror"),
    ROMANCE("Romance"),
    TRAVEL("Travel"),
    SCIENCE("Science"),
    HISTORY("History"),
    FANTASY("Fantasy"),
    ADVENTURE("Adventure");

    private String text;

    BooksType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
