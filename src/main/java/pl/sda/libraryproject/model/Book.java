package pl.sda.libraryproject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "release_date")
    private LocalDate release;

    @Column(name = "isbn", length = 13, unique = true, nullable = false)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private BooksType category;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "summary", length = 350)
    private String summary;

    public Long getId() {
        return id;
    }

    public Book() {
    }

    public Book(String title, LocalDate release, String isbn, BooksType category, Integer pages, String summary) {
        this.title = title;
        this.release = release;
        this.isbn = isbn;
        this.category = category;
        this.pages = pages;
        this.summary = summary;
    }

    public Book(String title, String release, String isbn, BooksType category, Integer pages, String summary) {
        this.title = title;
        this.release = LocalDate.parse(release);
        this.isbn = isbn;
        this.category = category;
        this.pages = pages;
        this.summary = summary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BooksType getCategory() {
        return category;
    }

    public void setCategory(BooksType category) {
        this.category = category;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release=" + release +
                ", isbn='" + isbn + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", pages=" + pages +
                ", summary='" + summary + '\'' +
                '}';
    }
}
