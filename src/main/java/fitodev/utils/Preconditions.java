package fitodev.utils;

import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Preconditions {
    /**
     * Check if the argument is not null or empty.
     * Supported types are: String, Object[], Collection, Map and byte[].
     *
     * @param argument Value to be checked
     * @param exceptionSupplier Supplier of the exception to be thrown
     */
    public static <T> void isNullOrEmpty(T argument, Supplier<? extends IllegalArgumentException> exceptionSupplier) {
        Objects.requireNonNull(exceptionSupplier, "Exception supplier cannot be null");
        if (argument == null) {
            throw exceptionSupplier.get();
        }
        if (argument instanceof String && ((String) argument).isEmpty()) {
            throw exceptionSupplier.get();
        }
        if (argument instanceof Object[] && ((Object[]) argument).length == 0) {
            throw exceptionSupplier.get();
        }
        if (argument instanceof Collection && ((Collection<?>) argument).isEmpty()) {
            throw exceptionSupplier.get();
        }
        if (argument instanceof Map && ((Map<?, ?>) argument).isEmpty()) {
            throw exceptionSupplier.get();
        }
        if (argument instanceof byte[] && ((byte[]) argument).length == 0) {
            throw exceptionSupplier.get();
        }
    }
}
