package pl.sda.libraryproject.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.libraryproject.service.BookService;
import pl.sda.libraryproject.service.BorrowService;
import pl.sda.libraryproject.service.BorrowerService;

import java.util.List;

@Controller
public class BookController {

    private BookService bookService;
    private BorrowerService borrowerService;

    @Autowired
    public BookController(BookService bookService,
                          BorrowerService borrowerService){
        this.bookService = bookService;
        this.borrowerService = borrowerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello() {
        return "index";
    }

    @GetMapping("/book-add")
    public String addBookPage(Model model) {
        model.addAttribute("bookTypes", BooksType.values());
        return "addBook";
    }

    @GetMapping("/book/getAll")
    public String getAllBooks(@RequestParam(value = "message", required = false) String message, Model model) {
        List<Book> allBooks = bookService.getAllBooks();
        model.addAttribute("listOfBookDtos", bookService.getListOfBookDtosFromListOfBooks(allBooks));
        return "listBooks";
    }

    @PostMapping("/book/add-book")
    public String addBookPost(@ModelAttribute Book book, @ModelAttribute Author author, Model model) {
        model.addAttribute("addedBook", bookService.addBookToDatabase(book, author));
        return "redirect:getAll";
    }

    @GetMapping("/book/search")
    public String searchForBook(@RequestParam("title") String title, Model model) {
        List<Book> books = bookService.searchForTitle(title);
        model.addAttribute("listOfBookDtos", bookService.getListOfBookDtosFromListOfBooks(books));
        return "listBooks";
    }

    @GetMapping("/menage")
    public String menageBook(@RequestParam("idbook") Long bookId, @RequestParam("type") String typeOfAction, Model model){
        System.out.println(typeOfAction);
        switch(typeOfAction){
            case "edit":
                List<Book> booksById = bookService.getBookById(bookId);
                if(booksById.isEmpty()){
                    return "redirect:/book/getAll?message=BOOK_ID_DOES_NOT_EXIST";
                } else{
                    model.addAttribute("book", booksById.get(0));
                    model.addAttribute("bookTypes", BooksType.values());
                    return "editBook";
                }
            case "delete":
                return "redirect:/book/getAll?message="+ bookService.deleteBookById(bookId);
            case "return ":
                return "redirect:/book/getAll?message=RETURN";

            default:
                return "redirect:/book/getAll";
        }
    }

    @PostMapping("/book/edit-book")
    public String editBook(@ModelAttribute Book book, @ModelAttribute Author author,  Model model){
        bookService.editBook(book);
        return "redirect:/book/getAll?message=";
    }

}
