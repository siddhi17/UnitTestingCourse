package com.example.unittestingcourse.unittestingfundamentals.example1AndExercise

import com.example.unittestingcourse.unitTestingFundamentals.example1AndExercise.NegativeNumberValidator
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class NegativeNumberValidatorTest {
    var SUT: NegativeNumberValidator? = null

        @Before
        fun setup() {
            SUT = NegativeNumberValidator()
        }

        @Test
        fun test1() {
            val result = SUT!!.isNegative(-1)
            Assert.assertThat(result, `is`(true))
        }

        @Test
        fun test2() {
            val result = SUT!!.isNegative(0)
            Assert.assertThat(result, `is`(false))
        }

        @Test
        fun test3() {
            val result = SUT!!.isNegative(1)
            Assert.assertThat(result, `is`(false))
        }
}