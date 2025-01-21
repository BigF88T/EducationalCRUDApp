package ru.sergey.spring.models;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Book name must not be empty")
    @Size(min = 2, max = 40, message = "Book name must be more than 2 and less than 40 characters")
    private String name;

    @NotEmpty(message = "Author name must not be empty")
    @Size(min = 2, max = 30, message = "Author name must be more than 2 and less than 30 characters")
    private String author;

    @Min(value = 1200, message = "The year the book was written must not be less than 1200")
    private int yearOfWriting;

    private int bookOwnerId;

    public Book() {}

    public Book(String name, String author, int yearOfWriting) {
        this.name = name;
        this.author = author;
        this.yearOfWriting = yearOfWriting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }

    public int getBookOwnerId() {
        return bookOwnerId;
    }

    public void setBookOwnerId(int bookOwnerId) {
        this.bookOwnerId = bookOwnerId;
    }
}
