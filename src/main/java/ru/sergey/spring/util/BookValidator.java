package ru.sergey.spring.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sergey.spring.dao.BookDAO;
import ru.sergey.spring.models.Book;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;


    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Book book = (Book) o;



    }
}
