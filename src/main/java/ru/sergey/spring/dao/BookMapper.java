package ru.sergey.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.sergey.spring.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getInt("book_id"));
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setYearOfWriting(resultSet.getInt("year_of_writing"));

        return book;
    }
}
