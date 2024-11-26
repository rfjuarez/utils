package fitodev.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SafeSupplierTest {

    @Test
    void tryGet() {
        SafeSupplier<Integer> safeSupplier = SafeSupplier.tryGet(() -> 1);
        assertTrue(safeSupplier.getNullable().isPresent());
        assertEquals(1, safeSupplier.getNullable().get());
        assertFalse(safeSupplier.isError());

        safeSupplier = SafeSupplier.tryGet(() -> {
            throw new RuntimeException("Test");
        });
        assertFalse(safeSupplier.getNullable().isPresent());
        assertTrue(safeSupplier.isError());
    }

    @Test
    void ifErrorThenGet() {
        final SafeSupplier<Integer> safeSupplierWithoutError = SafeSupplier.tryGet(() -> 1);
        assertTrue(safeSupplierWithoutError.ifErrorThenGet(() -> 2).isPresent());
        assertEquals(1, safeSupplierWithoutError.ifErrorThenGet(() -> 2).get());
        assertFalse(safeSupplierWithoutError.isError());

        final SafeSupplier<Integer> safeSupplier = SafeSupplier.tryGet(() -> {
            throw new RuntimeException("Test");
        });
        assertTrue(safeSupplier.ifErrorThenGet(() -> 2).isPresent());
        assertEquals(2, safeSupplier.ifErrorThenGet(() -> 2).get());
        assertTrue(safeSupplier.isError());
    }

    @Test
    void ifErrorThenThrow() {
        final SafeSupplier<Integer> safeSupplierWithoutError = SafeSupplier.tryGet(() -> 1);
        assertDoesNotThrow(() -> safeSupplierWithoutError.ifErrorThenThrow(RuntimeException::new));
        assertFalse(safeSupplierWithoutError.isError());

        final SafeSupplier<Integer> safeSupplier = SafeSupplier.tryGet(() -> {
            throw new RuntimeException("Test");
        });
        assertThrows(RuntimeException.class, () -> safeSupplier.ifErrorThenThrow(RuntimeException::new));
        assertTrue(safeSupplier.isError());
    }

    @Test
    void getNullable() {
        SafeSupplier<Integer> safeSupplier = SafeSupplier.tryGet(() -> 1);
        assertTrue(safeSupplier.getNullable().isPresent());
        assertEquals(1, safeSupplier.getNullable().get());
        assertFalse(safeSupplier.isError());

        safeSupplier = SafeSupplier.tryGet(() -> {
            throw new RuntimeException("Test");
        });
        assertFalse(safeSupplier.getNullable().isPresent());
        assertTrue(safeSupplier.isError());
    }

}