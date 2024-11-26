package fitodev.utils;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;


public final class SafeSupplier<T> {
    private final T value;
    private final Throwable cause;

    private SafeSupplier(Throwable cause) {
        this.value = null;
        this.cause = cause;
    }

    private SafeSupplier(T value) {
        this.value = value;
        this.cause = null;
    }

    static public <T> SafeSupplier<T> tryGet(Supplier<T> supplier) {
        Preconditions.isNullOrEmpty(supplier, ()->
                new IllegalArgumentException("Supplier cannot be null"));
        try {
            return new SafeSupplier<>(supplier.get());
        } catch (RuntimeException exception) {
            return new SafeSupplier<>(exception);
        }
    }


    public boolean isError() {
        return cause != null;
    }
    
    public Optional<T> ifErrorThenGet(Supplier<T> supplier) {
        Preconditions.isNullOrEmpty(supplier, ()->
                new IllegalArgumentException("Supplier cannot be null"));
        if (isError()) {
            return Optional.ofNullable(supplier.get());
        }
        return Optional.ofNullable(value);
    }

    public SafeSupplier<T> ifErrorThenThrow(Function<Throwable, ? extends RuntimeException> exceptionSupplier) {
        Preconditions.isNullOrEmpty(exceptionSupplier, ()->
                new IllegalArgumentException("Exception supplier cannot be null"));
        if (isError()) {
            throw exceptionSupplier.apply(cause);
        }
        return this;
    }

    public Optional<T> getNullable() {
        if (isError()) {
            return Optional.empty();
        }
        return Optional.ofNullable(value);
    }
}
