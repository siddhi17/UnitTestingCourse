package com.example.unittestingcourse.testDoubleFundamentals.example6;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class FitnessTrackerTest {

    FitnessTracker SUT;

    @Before
    public void setup() throws Exception {
        SUT = new FitnessTracker();
    }

    @Test
    public void step_totalIncremented() throws Exception {
        SUT.step();
        assertThat(SUT.getTotalSteps(), is(1));
    }

    @Test
    public void runStep_totalIncrementedByCorrectRatio() throws Exception {
        SUT.runStep();
        assertThat(SUT.getTotalSteps(), is(2));
    }
}