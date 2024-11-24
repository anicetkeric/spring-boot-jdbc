package com.sample.bootlabs.repositories;

import com.sample.bootlabs.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Repository
public class AuthorRepository implements JdbcRepository<Author> {

    private final JdbcClient jdbcClient;

    @Override
    public Author save(Author author) {
        var query = "INSERT INTO author(id, firstname, lastname) values(?,?,?)";
        int inserted = jdbcClient.sql(query)
                .params(author.id(), author.firstname(), author.lastname())
                .update();
        if (inserted == 1) {
            return author;
        }
      throw new RuntimeException("Save failed");
    }

    @Override
    public Author update(Author author, Long id) {
        var query = "UPDATE author set firstname = ?, lastname = ? where id = ?";
        int updated = jdbcClient.sql(query)
                .params(author.firstname(), author.lastname(), id)
                .update();
        if (updated == 1) {
            return author;
        }
        throw new RuntimeException("Update failed");
    }

    @Override
    public List<Author> findAll() {
        var query = "SELECT id,firstname,lastname FROM author";
        return jdbcClient.sql(query)
                .query(Author.class)
                .list();
    }

    @Override
    public Optional<Author> findById(Long id) {
        var query = "SELECT id, firstname, lastname FROM author where id = :id";
        return jdbcClient.sql(query)
                .param("id", id)
                .query(Author.class)
                .optional();
    }

    @Override
    public int deleteById(Long id) {
        var query = "DELETE from author where id = :id";
        return jdbcClient.sql(query)
                .param("id", id)
                .update();
    }
}
