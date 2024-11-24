package com.sample.bootlabs.service;

import com.sample.bootlabs.model.Publisher;
import com.sample.bootlabs.repositories.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing {@link Publisher}.
 *
 * @author @bootteam
 */
@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;

    public PublisherServiceImpl(PublisherRepository repo) {
        this.repository = repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Publisher create(Publisher d) {
        return repository.save(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Publisher update(Publisher publisher, Long id) {
        return repository.update(publisher, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Publisher getOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Publisher> getAll() {
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
