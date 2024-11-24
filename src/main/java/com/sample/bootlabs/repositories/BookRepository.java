package com.sample.bootlabs.repositories;

import com.sample.bootlabs.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Repository
public class BookRepository implements JdbcRepository<Book> {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Book> rowMapper = (res, rowNum) -> Book.builder().
            id(res.getLong("id"))
            .title(res.getString("title"))
            .isbn(res.getString("isbn"))
            .description(res.getString("description"))
            .page(res.getInt("page"))
            .price(res.getBigDecimal("price"))
            .authorId(res.getLong("authorId"))
            .build();


    @Override
    public Book save(Book book) {
        var query = "INSERT INTO book(id, title, isbn, description, page, price, authorId) values(?,?,?,?,?,?,?)";
        int inserted = jdbcTemplate.update(query, book.id(), book.title(), book.isbn(), book.description(), book.page(), book.price(), book.authorId());
        if (inserted == 1) {
            return book;
        }
        throw new RuntimeException("Save failed");
    }

    @Override
    public Book update(Book book, Long id) {
        var query = "UPDATE book set title = ?, isbn = ?, description = ?, page = ?, price = ? where id = ?";
        int updated = jdbcTemplate.update(query, book.title(), book.isbn(), book.description(), book.page(), book.price(), book.id());
        if (updated == 1) {
            return book;
        }
        throw new RuntimeException("Update failed");
    }

    @Override
    public List<Book> findAll() {
        var query = "SELECT id, title, isbn, description, page, price, authorId FROM book";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Optional<Book> findById(Long id) {
        var query = "SELECT id, title, isbn, description, page, price, authorId FROM book where id = ?";
        try {
            Book book = jdbcTemplate.queryForObject(query, rowMapper, id);
            return Optional.ofNullable(book);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int deleteById(Long id) {
        var query = "DELETE from book where id = ?";
        return jdbcTemplate.update(query, id);
    }

}
