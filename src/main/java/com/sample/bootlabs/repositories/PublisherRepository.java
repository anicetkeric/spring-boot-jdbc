package com.sample.bootlabs.repositories;

import com.sample.bootlabs.model.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Repository
public class PublisherRepository implements JdbcRepository<Publisher> {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Publisher> rowMapper = (res, rowNum) -> Publisher.builder().
            id(res.getLong("id"))
            .name(res.getString("name"))
            .email(res.getString("email"))
            .build();

    @Override
    public Publisher save(Publisher publisher) {
        var query = "INSERT INTO publisher(id, name, email) values(:id, :name, :email)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", publisher.id());
        parameters.addValue("name", publisher.name());
        parameters.addValue("email", publisher.email());
        int inserted = namedParameterJdbcTemplate.update(query, parameters);

        if (inserted == 1) {
            return publisher;
        }
        throw new RuntimeException("Save failed");
    }

    @Override
    public Publisher update(Publisher publisher, Long id) {
        var query = "UPDATE publisher set name = :name, email = :email where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", publisher.name());
        parameters.addValue("email", publisher.email());
        parameters.addValue("id", id);
        int updated = namedParameterJdbcTemplate.update(query, parameters);
        if (updated == 1) {
            return publisher;
        }
        throw new RuntimeException("Update failed");
    }

    @Override
    public List<Publisher> findAll() {
        var query = "SELECT id,name,email FROM publisher";
        return namedParameterJdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        var query = "SELECT id,name,email FROM publisher where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(query, parameters, rowMapper));
    }

    @Override
    public int deleteById(Long id) {
        var query = "DELETE from publisher where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        return namedParameterJdbcTemplate.update(query, parameters);
    }
}
