package lesson1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Tag
import java.io.File
import kotlin.test.Test

class TaskTestsKotlin : AbstractTaskTests() {

    @Test
    @Tag("Easy")
    fun testSortTimes() {
        sortTimes { inputName, outputName -> sortTimes(inputName, outputName) }
    }

    @Test
    @Tag("Normal")
    fun testSortAddresses() {
        sortAddresses { inputName, outputName -> sortAddresses(inputName, outputName) }
    }

    @Test
    @Tag("Normal")
    fun testSortTemperatures() {
        sortTemperatures("input/temp_in1.txt", "temp.txt")
        assertFileContent("temp.txt",
                """
                    -98.4
                    -12.6
                    -12.6
                    11.0
                    24.7
                    99.5
                    121.3
                """.trimIndent()
        )
        File("temp.txt").delete()
        sortTemperatures("input/temp_in2.txt", "temp.txt")
        assertFileContent("temp.txt",
                """

                """.trimIndent()
        )
        File("temp.txt").delete()
    }

    @Test
    @Tag("Normal")
    fun testSortSequence() {
        sortSequence("input/seq_in1.txt", "temp.txt")
        assertFileContent("temp.txt",
                """
                        1
                        3
                        3
                        1
                        2
                        2
                        2
                    """.trimIndent()
        )
        File("temp.txt").delete()
        sortSequence("input/seq_in2.txt", "temp.txt")
        assertFileContent("temp.txt",
                """
                        25
                        39
                        25
                        39
                        25
                        39
                        12
                        12
                        12
                    """.trimIndent()
        )
        File("temp.txt").delete()
        sortSequence("input/seq_in3.txt", "temp.txt")
        assertFileContent("temp.txt",
                """
                    432
                    21
                    25
                    11
                    49
                    2
                    2
                    2
                    2
                    2
                    2
                    2
                    2
                    2
                    2
                    2
                    2
                    2
                    11
                    11
                    11
                    11
                    11
                    11
                    11
                    11
                    11
                    11
                    11
                    11
                    1
                    1
                    1
                    1
                    1
                    1
                    1
                    1
                    1
                    1
                    1
                    1
                    1
                """.trimIndent())

    }

    @Test
    @Tag("Easy")
    fun testMergeArrays() {
        val result = arrayOf(null, null, null, null, null, 1, 3, 9, 13, 18, 23)
        mergeArrays(arrayOf(4, 9, 15, 20, 23), result)
        assertArrayEquals(arrayOf(1, 3, 4, 9, 9, 13, 15, 18, 20, 23, 23), result)

        run {
            val (first, second, expectedResult) = generateArrays(20000, 20000)
            mergeArrays(first, second)
            assertArrayEquals(expectedResult, second)
        }

        run {
            val (first, second, expectedResult) = generateArrays(500000, 500000)
            mergeArrays(first, second)
            assertArrayEquals(expectedResult, second)
        }
    }
}