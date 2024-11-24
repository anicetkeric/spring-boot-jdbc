package com.sample.bootlabs.service;


import com.sample.bootlabs.model.Book;
import com.sample.bootlabs.repositories.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing {@link Book}.
 * @author  @bootteam
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book create(Book d) {
        return repository.save(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book update(Book book, Long id) {
        return repository.update(book, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Book getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getAll() {
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
