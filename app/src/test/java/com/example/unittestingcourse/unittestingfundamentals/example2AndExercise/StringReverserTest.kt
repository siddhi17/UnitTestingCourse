package com.example.unittestingcourse.unittestingfundamentals.example2AndExercise

import com.example.unittestingcourse.unitTestingFundamentals.example2AndExercise.StringReverser
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class StringReverserTest {
    var SUT: StringReverser? = null

    @Before
    @Throws(Exception::class)
    fun setup() {
        SUT = StringReverser()
    }

    @Test
    @Throws(Exception::class)
    fun reverse_emptyString_emptyStringReturned() {
        val result = SUT!!.reverse("")
        assertThat(result, `is`(""))
    }

    @Test
    @Throws(Exception::class)
    fun reverse_singleCharacter_sameStringReturned() {
        val result = SUT!!.reverse("a")
        assertThat(result, `is`("a"))
    }

    @Test
    @Throws(Exception::class)
    fun reverse_longString_reversedStringReturned() {
        val result = SUT!!.reverse("Vasiliy Zukanov")
        assertThat(result, `is`("vonakuZ yilisaV"))
    }
}