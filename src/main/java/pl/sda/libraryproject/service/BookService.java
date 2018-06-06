package pl.sda.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.libraryproject.dto.BookDto;
import pl.sda.libraryproject.model.Author;
import pl.sda.libraryproject.model.Book;
import pl.sda.libraryproject.model.Borrow;
import pl.sda.libraryproject.repository.AuthorRepository;
import pl.sda.libraryproject.repository.BookRepository;
import pl.sda.libraryproject.repository.BorrowRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BorrowRepository borrowRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BorrowRepository borrowRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowRepository = borrowRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> all = bookRepository.findAll();
        printAllBooks(all);
        return all;
    }

    private void printAllBooks(List<Book> all) {
        System.out.println("All books from DB");
        all.forEach(System.out::println);
    }


    public Book addBookToDatabase(Book book, Author author) {

        // if author exists

        Author authorFromDatabase = checkIfAuthorExists(author);
        // if new author
        if(authorFromDatabase == null){
            authorRepository.save(author);
            book.setAuthor(author);
        } else{
            book.setAuthor(authorFromDatabase);
        }
        return bookRepository.save(book);
    }

    private Author checkIfAuthorExists(Author author) {
        return authorRepository.findFirstByFirstNameAndLastName(author.getFirstName(), author.getLastName());
    }

    public List<Book> searchForTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    public void deleteBook(Book book){
        if(!checkIfBookIsBorrowed(book)) {
            bookRepository.deleteById(book.getId());
        }
    }

    private Borrow getBorrowInfoFor(Book book){
        return borrowRepository.findByBookId(book.getId());
    }


    public boolean checkIfBookIsBorrowed(Book book) {
        return borrowRepository.findByBookId(book.getId()) != null;
    }

    public List<Book> getAllBorrowedBooks() {
        return borrowRepository.findAll().stream().map(Borrow::getBook).collect(Collectors.toList());
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Map<Long, Borrow> getMapIdBookBorrows(List<Borrow> borrows) {
        return borrows.stream().collect(Collectors.toMap(borrow -> borrow.getBook().getId(), borrow -> borrow ));
    }


    public List<BookDto> getListOfBookDtosFromListOfBooks(List<Book> books){
        Map<Long, Borrow> mapIdBookBorrows = getMapIdBookBorrows(getAllBorrows());
        return books.stream()
                    .map(book -> {
                        if(mapIdBookBorrows.containsKey(book.getId())){
                            return new BookDto(book, mapIdBookBorrows.get(book.getId()));
                        } else{
                            return new BookDto(book);
                        }
                    }).collect(Collectors.toList());
    }

}
