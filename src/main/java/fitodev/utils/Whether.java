package fitodev.utils;

public class Whether {
    private final boolean parentCondition;
    private final boolean alreadyRun;

    private Whether(boolean parentCondition) {
        this.parentCondition = parentCondition;
        this.alreadyRun = false;
    }

    private Whether(boolean parentCondition, boolean alreadyRun) {
        this.parentCondition = parentCondition;
        this.alreadyRun = alreadyRun;
    }

    public static Whether is(boolean condition) {
        return new Whether(condition);
    }

    public Whether or(boolean condition) {
        if (parentCondition || condition) {
            return new Whether(true);
        }
        return this;

    }

    public Whether and(boolean condition) {
        if (parentCondition && condition) {
            return new Whether(true);
        }
        return this;
    }

    public Whether then(Runnable action) {
        if (parentCondition && !alreadyRun) {
            action.run();
            return new Whether(true, true);
        }
        return this;
    }

    public Whether inCase(boolean condition) {
        final boolean conditionChained = !parentCondition && condition;
        if (conditionChained) {
            return new Whether(true);
        }
        return this;
    }

    void otherwise(Runnable action) {
        if (!parentCondition && !alreadyRun) {
            action.run();
        }
    }
}
