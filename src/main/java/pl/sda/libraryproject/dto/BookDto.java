package pl.sda.libraryproject.dto;

import pl.sda.libraryproject.model.*;

import java.time.LocalDate;

public class BookDto {

    private Long idBook;
    private String title;
    private String isbn;
    private String author;
    private BooksType category;
    private Integer pages;
    private String summary;
    private boolean isBorrowed;
    private LocalDate rentalDate;
    private Long idBorrower;

    public BookDto(){}

    public BookDto(Book book, Borrow borrow){
        mapFieldsFromBook(book);
        if(borrow.getBook().getId().equals(this.getIdBook())) {
            this.rentalDate = borrow.getRentalDate();
            this.isBorrowed = true;
            this.idBorrower = borrow.getBorrower().getId();
        }
    }

    public BookDto(Book book){
        mapFieldsFromBook(book);
    }

    private void mapFieldsFromBook(Book book){
        this.idBook = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.author = book.getAuthor().getDisplayName();
        this.category = book.getCategory();
        this.pages = book.getPages();
        this.summary = book.getSummary();
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Long getIdBorrower() {
        return idBorrower;
    }

    public void setIdBorrower(Long idBorrower) {
        this.idBorrower = idBorrower;
    }
}
