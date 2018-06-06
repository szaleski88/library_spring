package pl.sda.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.libraryproject.model.Author;
import pl.sda.libraryproject.model.Book;
import pl.sda.libraryproject.repository.AuthorRepository;
import pl.sda.libraryproject.repository.BookRepository;
import pl.sda.libraryproject.repository.BorrowRepository;

import java.util.List;

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

    public boolean checkIfBookIsBorrowed(Book book) {
        System.out.println(borrowRepository.findByBookId(book.getId()));
        return true;

    }

}
