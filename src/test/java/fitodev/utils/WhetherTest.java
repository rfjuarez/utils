package fitodev.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class WhetherTest {

    /**
     * Test a conditional chain for two statement conditions
     * joined by OR operator, with a default action where:
     * The first condition is true then run first action OR
     * the second condition is true then run second action.
     */
    @Test
    void conditionsJoinedByOrWithDefaultAction1() {
        final Runnable firstAction = mock(Runnable.class);
        doNothing().when(firstAction).run();
        final Runnable secondAction = mock(Runnable.class);
        doNothing().when(secondAction).run();


        Whether.is(true).then(firstAction)
                .or(true).then(secondAction)
                .otherwise(Assertions::fail);

        verify(firstAction, times(1)).run();
        verify(secondAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two statement conditions
     * joined by OR operator, with a default action where:
     * The first condition is true then run first action OR
     * the second condition is true then run second action.
     */
    @Test
    void conditionsJoinedByOrWithDefaultAction2() {
        final Runnable firstAction = mock(Runnable.class);
        doNothing().when(firstAction).run();
        final Runnable secondAction = mock(Runnable.class);
        doNothing().when(secondAction).run();

        Whether.is(true).then(firstAction)
                .or(false).then(secondAction)
                .otherwise(Assertions::fail);

        verify(firstAction, times(1)).run();
        verify(secondAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two statement conditions
     * joined by OR operator, with a default action where:
     * The first condition is false then it doesn't run first action OR
     * the second condition is false then it doesn't run second action AND
     * run a default action.
     */
    @Test
    void conditionsJoinedByOrWithDefaultAction3() {
        final Runnable defaultAction = mock(Runnable.class);
        doNothing().when(defaultAction).run();

        Whether.is(false).then(Assertions::fail)
                .or(false).then(Assertions::fail)
                .otherwise(defaultAction);

        verify(defaultAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two statement conditions
     * joined by AND operator, with a default action where:
     * The first condition is false then it doesn't run first action AND
     * the second condition is true then it doesn't run second action AND
     * run a default action.
     */
    @Test
    void conditionsJoinedByAndWithDefaultAction1() {
        final Runnable defaultAction = mock(Runnable.class);
        doNothing().when(defaultAction).run();

        Whether.is(false).then(Assertions::fail)
                .and(true).then(Assertions::fail)
                .otherwise(defaultAction);

        verify(defaultAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two statement conditions
     * joined by AND operator, with a default action where:
     * The first condition is true then run first action AND
     * the second condition is true then run second action.
     */
    @Test
    void conditionsJoinedByAndWithDefaultAction2() {
        final Runnable firstAction = mock(Runnable.class);
        doNothing().when(firstAction).run();
        final Runnable secondAction = mock(Runnable.class);
        doNothing().when(secondAction).run();

        Whether.is(true).then(firstAction)
                .and(true).then(secondAction)
                .otherwise(Assertions::fail);

        verify(firstAction, times(1)).run();
        verify(secondAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two statement conditions
     * joined by AND operator, with a default action where:
     * The first condition is true then run first action AND
     * the second condition is false then it doesn't run second action.
     */
    @Test
    void conditionsJoinedByAndWithDefaultAction3() {
        final Runnable firstAction = mock(Runnable.class);
        doNothing().when(firstAction).run();
        final Runnable secondAction = mock(Runnable.class);
        doNothing().when(secondAction).run();

        Whether.is(true).then(firstAction)
                .and(false).then(Assertions::fail)
                .otherwise(Assertions::fail);

        verify(firstAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two statement conditions
     * joined by AND operator, with a default action where:
     * The first condition is false then it doesn't run first action AND
     * the second condition is false then it doesn't run second action AND
     * run a default action.
     */
    @Test
    void conditionsJoinedByAndWithDefaultAction4() {
        final Runnable defaultAction = mock(Runnable.class);
        doNothing().when(defaultAction).run();

        Whether.is(false).then(Assertions::fail)
                .and(false).then(Assertions::fail)
                .otherwise(defaultAction);

        verify(defaultAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two conditions
     * joined by AND operator, with a default action where:
     * The first condition is true AND
     * the second condition is true then run the single action.
     */
    @Test
    void singleStatementWithConditionsJoinedByAnd() {
        final Runnable singleAction = mock(Runnable.class);
        doNothing().when(singleAction).run();

        Whether.is(true).and(true)
                .then(singleAction)
                .otherwise(Assertions::fail);

        verify(singleAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two conditions
     * joined by AND operator, with a default action where:
     * The first condition is false AND
     * the second condition is true then run a default action.
     */
    @Test
    void singleStatementWithConditionsJoinedByAnd2() {
        final Runnable defaultAction = mock(Runnable.class);
        doNothing().when(defaultAction).run();

        Whether.is(false).and(true)
                .then(Assertions::fail)
                .otherwise(defaultAction);

        verify(defaultAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two conditions
     * joined by AND operator, with a default action where:
     * The first condition is false AND
     * the second condition is false then run a default action.
     */
    @Test
    void singleStatementWithConditionsJoinedByAnd3() {
        final Runnable defaultAction = mock(Runnable.class);
        doNothing().when(defaultAction).run();

        Whether.is(false).and(false)
                .then(Assertions::fail)
                .otherwise(defaultAction);

        verify(defaultAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two conditions
     * joined by OR operator, with a default action where:
     * The first condition is true AND
     * the second condition is true then run the single action.
     */
    @Test
    void singleStatementWithConditionsJoinedByOr() {
        final Runnable singleAction = mock(Runnable.class);
        doNothing().when(singleAction).run();

        Whether.is(true).or(true)
                .then(singleAction)
                .otherwise(Assertions::fail);

        verify(singleAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two conditions
     * joined by OR operator, with a default action where:
     * The first condition is false OR
     * the second condition is true then run the single action.
     */
    @Test
    void singleStatementWithConditionsJoinedByOr2() {
        final Runnable singleAction = mock(Runnable.class);
        doNothing().when(singleAction).run();

        Whether.is(false).or(true)
                .then(singleAction)
                .otherwise(Assertions::fail);

        verify(singleAction, times(1)).run();
    }

    /**
     * Test a conditional chain for two conditions
     * joined by OR operator, with a default action where:
     * The first condition is false OR
     * the second condition is false then run a default action.
     */
    @Test
    void singleStatementWithConditionsJoinedByOr3() {
        final Runnable defaultAction = mock(Runnable.class);
        doNothing().when(defaultAction).run();

        Whether.is(false).or(false)
                .then(Assertions::fail)
                .otherwise(defaultAction);

        verify(defaultAction, times(1)).run();
    }

    /**
     * Test a conditional chain for four conditions like as switch case, where:
     * The first condition is true then run first action AND
     * the second condition is true then it doesn't run second action AND
     * the third condition is true, but it doesn't run third action AND
     * the fourth condition is true, but it doesn't run fourth action finally
     * the default action doesn't run.
     */

    @Test
    void switchCase1() {
        final Runnable runnable = mock(Runnable.class);
        doNothing().when(runnable).run();

        Whether.is(true).then(runnable)
                .inCase(true).then(Assertions::fail)
                .inCase(true).then(Assertions::fail)
                .inCase(true).then(Assertions::fail)
                .otherwise(Assertions::fail);

        verify(runnable, times(1)).run();
    }

    /**
     * Test a conditional chain for four conditions like as switch case, where:
     * The first condition is false then it doesn't run first action AND
     * the second condition is true then run second action AND
     * the third condition is true, but it doesn't run third action AND
     * the fourth condition is true, but it doesn't run fourth action finally
     * the default action doesn't run.
     */
    @Test
    void switchCase2() {
        final Runnable runnable = mock(Runnable.class);
        doNothing().when(runnable).run();

        Whether.is(false).then(Assertions::fail)
                .inCase(true).then(runnable)
                .inCase(true).then(Assertions::fail)
                .inCase(true).then(Assertions::fail)
                .otherwise(Assertions::fail);

        verify(runnable, times(1)).run();
    }

    /**
     * Test a conditional chain for four conditions like as switch case, where:
     * The first condition is false then it doesn't run first action AND
     * the second condition is true then run second action AND
     * the third condition is false, then it doesn't run third action AND
     * the fourth condition is true, but it doesn't run fourth action finally
     * the default action doesn't run.
     */
    @Test
    void switchCase3() {
        final Runnable runnable = mock(Runnable.class);
        doNothing().when(runnable).run();

        Whether.is(false).then(Assertions::fail)
                .inCase(true).then(runnable)
                .inCase(false).then(Assertions::fail)
                .inCase(true).then(Assertions::fail)
                .otherwise(Assertions::fail);

        verify(runnable, times(1)).run();
    }

    /**
     * Test a conditional chain for four conditions like as switch case, where:
     * The first condition is false then it doesn't run first action AND
     * the second condition is false then it doesn't run second action AND
     * the third condition is false, then it doesn't run third action AND
     * the fourth condition is false, then it doesn't run fourth action finally
     * the default action run.
     */
    @Test
    void switchCase4() {
        final Runnable runnable = mock(Runnable.class);
        doNothing().when(runnable).run();

        Whether.is(false).then(Assertions::fail)
                .inCase(false).then(Assertions::fail)
                .inCase(false).then(Assertions::fail)
                .inCase(false).then(Assertions::fail)
                .otherwise(runnable);

        verify(runnable, times(1)).run();
    }
}

