package pl.sda.libraryproject.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.libraryproject.repository.BorrowRepository;
import pl.sda.libraryproject.service.BookService;
import pl.sda.libraryproject.service.BorrowService;
import pl.sda.libraryproject.service.BorrowerService;

import java.util.List;

@Controller
public class BorrowController {

    private BookService bookService;
    private BorrowerService borrowerService;
    private BorrowService borrowService;
    private BorrowRepository borrowRepository;

    @Autowired
    public BorrowController(BookService bookService, BorrowerService borrowerService,
                            BorrowService borrowService, BorrowRepository borrowRepository) {
        this.bookService = bookService;
        this.borrowerService = borrowerService;
        this.borrowService = borrowService;
        this.borrowRepository = borrowRepository;
    }

    @GetMapping("/borrowedBooks")
    public String getAllBorrowedBooks(Model model) {
        List<Book> allBorrowedBooks = bookService.getAllBorrowedBooks();
        model.addAttribute("listOfBookDtos", bookService.getListOfBookDtosFromListOfBooks(allBorrowedBooks));
        return "listBooks";
    }

    @GetMapping("/book/borrow")
    public String borrowBook(@ModelAttribute Book book, @ModelAttribute Borrower borrower, Model model){
        // YOU GET HERE FROM FORM!
        String message = borrowService.borrowBook(book, borrower);
        return "redirect:/book/getAll?message=" + message;
    }

    @GetMapping(value="/menage", params="type=borrow")
    public String menageBook(@RequestParam("idbook") Long bookId, Model model) {
        // todo: add return option - JSP modification needed
        model.addAttribute("availableBorrowers", borrowerService.getListOfAllBorrowers());
        model.addAttribute("books", bookService.getListOfBookDtosFromListOfBooks(bookService.getAllAvailableBooks()));
        List<Book> bookById = bookService.getBookById(bookId);
        System.out.println(bookById );
        model.addAttribute("chosenBook", bookService.getListOfBookDtosFromListOfBooks(bookById).get(0));
        return "borrowBook";
    }
    @GetMapping(value="/menage", params="type=return")
    public String returnBook(@RequestParam("idbook") Long bookId, Model model) {
        System.out.println("delete borrow");
        borrowRepository.deleteByBookId(bookId);
        return "redirect:/book/getAll?message=DELETED";
    }
}
