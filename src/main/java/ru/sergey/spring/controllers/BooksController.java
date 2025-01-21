package ru.sergey.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sergey.spring.dao.BookDAO;
import ru.sergey.spring.dao.PersonDAO;
import ru.sergey.spring.models.Book;
import ru.sergey.spring.models.Person;
import ru.sergey.spring.util.BookValidator;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;


    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        if(bookDAO.show(id) == null) {
            return "redirect:/books";
        }

        model.addAttribute("book", bookDAO.show(id));

        Optional<Person> bookOwner = bookDAO.getOwner(id);

        if(bookOwner.isPresent()){
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }

        return "books/show";
    }


    @GetMapping("/new")
    public String newBook( Model model ) {
        model.addAttribute("book", new Book());

        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if(bindingResult.hasErrors())
            return "people/new";

        bookDAO.save(book);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookDAO.setOwner(person.getId(), id);

        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);

        return "redirect:/books/" + id;
    }
}