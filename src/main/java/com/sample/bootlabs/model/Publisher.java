package com.sample.bootlabs.model;

import jakarta.validation.constraints.Email;
import lombok.Builder;

/**
 * Data model class for "Publisher"
 *
 * @author @bootteam
 */
@Builder
public record Publisher(
        Long id,
        String name,
        @Email String email) {
}
