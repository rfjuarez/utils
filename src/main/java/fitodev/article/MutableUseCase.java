package fitodev.article;

import fitodev.utils.SafeSupplier;

import java.util.function.Supplier;

public class MutableUseCase {

    public static final int SOME_CALC = 1;

    void mutableUseCase(Integer someInput) {
        /*
         * This method demonstrates a mutable use case where the variable `mutableVar`
         * depends on an internal process. The value of `mutableVar` is set only after
         * a condition is evaluated.
         */
        Integer mutableVar;
        /*
         * Internal process based on the input value.
         * This block could involve a complex computation or require external dependencies.
         */
        final Integer internalDependence = someHardProcess(someInput);
        /*
         * Condition where the value of `mutableVar` is determined.
         */
        if (someInput > 0) {
            mutableVar = internalDependence;
        } else {
            mutableVar = internalDependence + SOME_CALC;
        }

        nextStep(mutableVar);
    }


    Integer someHardProcess(final Integer someInput) {
        return someInput + 1;
    }

    void nextStep(final Integer someInput) {
        System.out.println(someInput);
    }

    void approachUsingSupplierUseCase(Integer someInput) {
        final Integer internalProcess = someHardProcess(someInput);
        final Supplier<Integer> immutableInputSupplier = () -> {
            if (someInput > 0) {
                return internalProcess;
            } else {
                return internalProcess + SOME_CALC;
            }
        };

        nextStep(immutableInputSupplier.get());
    }

    void approachUsingMethodReference(final Integer someInput) {
        final Integer internalProcess = someHardProcess(someInput);
        nextStep(getImmutableInput(someInput, internalProcess));
    }

    Integer getImmutableInput(final Integer input, final Integer internalDependence) {
        if (input > 0) {
            return internalDependence;
        } else {
            return internalDependence + SOME_CALC;
        }
    }

    void mutableWithExceptionUseCase(final Integer someInput) {
        /*
         * This method demonstrates a mutable use case where the variable `mutableVar`
         * depends on an internal process. The value of `mutableVar` is set only after
         * a condition is evaluated.
         */
        Integer mutableVar;
        /*
         * Internal process based on the input value.
         * This block could involve a complex computation or require external dependencies.
         */
        try {
            final Integer internalDependence = someHardProcess(someInput);
            /*
             * Condition where the value of `mutableVar` is determined.
             */
            if (someInput > 0) {
                mutableVar = internalDependence;
            } else {
                mutableVar = internalDependence + SOME_CALC;
            }
        } catch (Exception e) {
            /*
                * Handle the exception and set the value of `mutableVar` to a default value.
             */
            mutableVar = 0;
        }

        nextStep(mutableVar);
    }

    void approachUsingSafeSupplier(Integer someInput) {
        final Integer internalProcess = someHardProcess(someInput);
        nextStep(SafeSupplier.tryGet(() -> {
                    if (someInput > 0) {
                        return internalProcess;
                    } else {
                        return internalProcess + SOME_CALC;
                    }
                })
                .ifErrorThenGet(() -> 0).get());
    }
}
