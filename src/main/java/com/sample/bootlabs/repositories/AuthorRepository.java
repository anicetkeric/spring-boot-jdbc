package com.sample.bootlabs.repositories;

import com.sample.bootlabs.model.Author;
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
public class AuthorRepository implements JdbcRepository<Author> {

    private final JdbcTemplate jdbcTemplate;

    RowMapper<Author> rowMapper = (res, rowNum) -> Author.builder().
            id(res.getLong("id"))
            .firstname(res.getString("firstname"))
            .lastname(res.getString("lastname")).build();


    @Override
    public Author save(Author author) {
        var query = "INSERT INTO author(id, firstname, lastname) values(?,?,?)";
        int inserted = jdbcTemplate.update(query, author.id(), author.firstname(), author.lastname());
        if (inserted == 1) {
            return author;
        }
        return null;
    }

    @Override
    public Author update(Author author, Long id) {
        var query = "UPDATE author set firstname = ?, lastname = ? where id = ?";
        int updated = jdbcTemplate.update(query, author.firstname(), author.lastname(), id);
        if (updated == 1) {
            return author;
        }
        return null;
    }

    @Override
    public List<Author> findAll() {
        var query = "SELECT id,firstname,lastname FROM author";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Optional<Author> findById(Long id) {
        var query = "SELECT id, firstname, lastname FROM author where id = ?";
        try {
            Author author = jdbcTemplate.queryForObject(query, rowMapper, id);
            return Optional.ofNullable(author);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int deleteById(Long id) {
        var query = "DELETE from author where id = ?";
        return jdbcTemplate.update(query, id);
    }
}
