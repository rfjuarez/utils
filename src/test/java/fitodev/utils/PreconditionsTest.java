package fitodev.utils;

import static org.junit.jupiter.api.Assertions.*;

class PreconditionsTest {

    @org.junit.jupiter.api.Test
    void isNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Preconditions.isNullOrEmpty(null, IllegalArgumentException::new));
        assertThrows(IllegalArgumentException.class, () -> Preconditions.isNullOrEmpty("", IllegalArgumentException::new));
        assertThrows(IllegalArgumentException.class, () -> Preconditions.isNullOrEmpty(new Object[]{}, IllegalArgumentException::new));
        assertThrows(IllegalArgumentException.class, () -> Preconditions.isNullOrEmpty(new byte[]{}, IllegalArgumentException::new));
        assertThrows(IllegalArgumentException.class, () -> Preconditions.isNullOrEmpty(new java.util.ArrayList<>(), IllegalArgumentException::new));
        assertThrows(IllegalArgumentException.class, () -> Preconditions.isNullOrEmpty(new java.util.HashSet<>(), IllegalArgumentException::new));
        assertThrows(IllegalArgumentException.class, () -> Preconditions.isNullOrEmpty(new java.util.HashMap<>(), IllegalArgumentException::new));
    }

}