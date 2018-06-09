package pl.sda.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.libraryproject.model.Book;
import pl.sda.libraryproject.model.Borrow;
import pl.sda.libraryproject.model.Borrower;
import pl.sda.libraryproject.repository.BookRepository;
import pl.sda.libraryproject.repository.BorrowRepository;
import pl.sda.libraryproject.repository.BorrowerRepository;

import java.util.List;

@Service
public class BorrowService {

    private BorrowRepository borrowRepository;
    private BorrowerRepository borrowerRepository;
    private BookRepository bookRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository,
                         BorrowerRepository borrowerRepository,
                         BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.borrowerRepository = borrowerRepository;
        this.bookRepository = bookRepository;
    }

    public String borrowBook(Book book, Borrower borrower){
        // CHECK IF BOOK EXISTS
        if(!bookRepository.findById(book.getId()).isPresent()){
            return "BOOK_DOES_NOT_EXIST_IN_DATABASE";
        }
        // CHECK IF BORROWER EXISTS
        if(!borrowerRepository.findById(borrower.getId()).isPresent()){
            return "USER_DOES_NOT_EXIST_IN_DATABASE";
        }
        // BORROW
        try {
            borrowRepository.save(new Borrow(book, borrower));
            return "SUCCESSFULLY_BORROWED";
        } catch (Exception e ){
            return "FAILED_ON_BORROW";
        }
    }





}
