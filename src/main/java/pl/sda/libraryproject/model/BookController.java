package pl.sda.libraryproject.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.libraryproject.service.BookService;

@Controller
public class BookController {

    @Autowired
    BookService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello() {
        return "index";
    }

    @GetMapping("/book-add")
    public String addBookPage() {
        return "addBook";
    }

    @GetMapping("/book-delete")
    public String deleteBook() {
//        return "deleteBook";
        return "index";
    }


    @GetMapping("/book/getAll")
    public String getAllBooks(Model model) {
        model.addAttribute("listOfBooks", service.getAllBooks());
        return "listBooks";
    }

    @PostMapping("book/add-book")
    public String addBookPost(@ModelAttribute Book book, @ModelAttribute Author author,  Model model){

        model.addAttribute("addedBook", service.addBookToDatabase(book, author));
        return "redirect:getAll";
    }


    @GetMapping("/book/search")
    public String searchForBook(@RequestParam("title") String title, Model model){
        model.addAttribute("listOfBooks",  service.searchForTitle(title));
        return "listBooks";
    }
    @GetMapping("/borrow")
    public String borrow(){

        System.out.println(service.checkIfBookIsBorrowed(service.getAllBooks().get(0)));
        return "listBooks";
    }



}
