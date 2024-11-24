package com.sample.bootlabs.service;

import com.sample.bootlabs.model.Author;
import com.sample.bootlabs.repositories.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing {@link Author}.
 *
 * @author @bootteam
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repo) {
        this.repository = repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author create(Author d) {
        return repository.save(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author update(Author author, Long id) {
        return repository.update(author, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Author getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        var response = repository.deleteById(id);
        if (response == 0) {
            throw new RuntimeException("Delete failed");
        }
    }
}
