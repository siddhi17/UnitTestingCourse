package com.example.unittestingcourse.unittestingfundamentals.example1AndExercise

import com.example.unittestingcourse.unitTestingFundamentals.example1AndExercise.PositiveNumberValidator
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class PositiveNumberValidatorTest {
    var SUT: PositiveNumberValidator? = null

    @Before
    fun setup() {
        SUT = PositiveNumberValidator()
    }

    @Test
    fun test1() {
        val result = SUT!!.isPositive(-1)
        Assert.assertThat(result, `is`(false))
    }

    @Test
    fun test2() {
        val result = SUT!!.isPositive(0)
        Assert.assertThat(result, `is`(false))
    }

    @Test
    fun test3() {
        val result = SUT!!.isPositive(1)
        Assert.assertThat(result, `is`(true))
    }
}