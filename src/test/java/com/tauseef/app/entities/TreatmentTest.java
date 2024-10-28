package com.tauseef.app.entities;

import com.tauseef.app.TestCase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TreatmentTest extends TestCase {

    @Test
    void getName() {
        String name = faker.name().fullName();
        Treatment treatment = createTreatment(name, 0);

        assertEquals(name, treatment.getName());
    }

    @Test
    void getPrice() {
        String name = faker.name().fullName();
        Double price = faker.number().randomDouble(2, 100, 1000);
        Treatment treatment = createTreatment(name, price);

        assertEquals(price, treatment.getPrice());
    }
}