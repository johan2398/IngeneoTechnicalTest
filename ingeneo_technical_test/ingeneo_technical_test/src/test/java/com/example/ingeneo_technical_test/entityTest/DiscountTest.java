/**
 * 
 */
package com.example.ingeneo_technical_test.entityTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.ingeneo_technical_test.entity.Discount;

/**
 * @author Johan Casagua
 *
 */
public class DiscountTest {
	@Test
    void testDiscountAttributes() {
        // Arrange
        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("Discount 1");
        discount.setDescription("Description");
        discount.setType("Percentage");
        discount.setValue(0.1f);

        // Act & Assert
        assertEquals(1L, discount.getId());
        assertEquals("Discount 1", discount.getName());
        assertEquals("Description", discount.getDescription());
        assertEquals("Percentage", discount.getType());
        assertEquals(0.1f, discount.getValue());
    }

    @Test
    void testDiscountBuilder() {
        // Arrange & Act
        Discount discount = Discount.builder()
            .id(1L)
            .name("Discount 1")
            .description("Description")
            .type("Percentage")
            .value(0.1f)
            .build();

        // Assert
        assertEquals(1L, discount.getId());
        assertEquals("Discount 1", discount.getName());
        assertEquals("Description", discount.getDescription());
        assertEquals("Percentage", discount.getType());
        assertEquals(0.1f, discount.getValue());
    }
}
