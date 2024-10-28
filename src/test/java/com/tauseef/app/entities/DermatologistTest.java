package com.tauseef.app.entities;

import com.tauseef.app.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DermatologistTest extends TestCase {

    @Test
    void getId() {
        Dermatologist dermatologist = createDoctor(faker.name().fullName());
        assertEquals(Dermatologist.getIndexId() - 1, dermatologist.getId());
    }

    @Test
    void getName() {
        String name = faker.name().fullName();
        Dermatologist dermatologist = createDoctor(name);

        assertEquals(name, dermatologist.getName());
    }

    @Test
    void testToString() {
        String name = faker.name().fullName();
        Dermatologist dermatologist = createDoctor(name);
        String text = "Dermatologist: { id: " + (Dermatologist.getIndexId() - 1) + ", name: " + name + " }";

        assertEquals(text, dermatologist.toString());
    }
}