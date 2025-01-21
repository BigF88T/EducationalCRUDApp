package ru.sergey.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sergey.spring.models.Book;
import ru.sergey.spring.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[]{id},
                new BookMapper()).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO Book ( name, author, year_of_writing) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYearOfWriting());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year_of_writing=? WHERE book_id=?", updateBook.getName(), updateBook.getAuthor(), updateBook.getYearOfWriting(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public Optional<Person> getOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.book_owner_id = Person.id WHERE book_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void setOwner(int personId, int bookId) {
        jdbcTemplate.update("UPDATE Book SET book_owner_id=? WHERE book_id=?", personId, bookId);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET book_owner_id=null WHERE book_id =?", id);
    }

}