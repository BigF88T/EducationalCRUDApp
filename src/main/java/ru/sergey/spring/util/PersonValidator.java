package ru.sergey.spring.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sergey.spring.dao.PersonDAO;
import ru.sergey.spring.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Person person = (Person) o;

        if(personDAO.show(person.getName()).isPresent())
            errors.rejectValue("name", "", "This name already taken");

        if(Character.isDigit(person.getName().charAt(0)))
            errors.rejectValue("name", "", "The name must NOT start with a number");

    }
}
