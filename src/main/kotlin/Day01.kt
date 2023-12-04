import java.io.BufferedReader

/*
--- Day 1: Trebuchet?! ---
Something is wrong with global snow production, and you've been selected to take a look.
The Elves have even given you a map; on it, they've used stars to mark the top fifty locations that are likely to be
 having problems.

You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by
 December 25th.

Collect stars by solving puzzles.
Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete
 the first.
Each puzzle grants one star.
Good luck!

You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you
 ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the
 sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a
 trebuchet ("please hold still, we need to strap you in").

As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been
 amended by a very young Elf who was apparently just excited to show off her art skills.
Consequently, the Elves are having trouble reading the values on the document.

The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration
 value that the Elves now need to recover.
On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to
 form a single two-digit number.

For example:

1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet

In this example, the calibration values of these four lines are 12, 38, 15, and 77.
Adding these together produces 142.

Consider your entire calibration document. What is the sum of all of the calibration values?

---

Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".

Equipped with this new information, you now need to find the real first and last digit on each line. For example:

two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
 */

private val partTwoMatchMap = mapOf(
    Pair("zero", 0),
    Pair("one", 1),
    Pair("two", 2),
    Pair("three", 3),
    Pair("four", 4),
    Pair("five", 5),
    Pair("six", 6),
    Pair("seven", 7),
    Pair("eight", 8),
    Pair("nine", 9),
    Pair("0", 0),
    Pair("1", 1),
    Pair("2", 2),
    Pair("3", 3),
    Pair("4", 4),
    Pair("5", 5),
    Pair("6", 6),
    Pair("7", 7),
    Pair("8", 8),
    Pair("9", 9)
)

class Day01 {
    private fun getInputAsReader(): BufferedReader {
        val inputStream = this::class.java.classLoader.getResourceAsStream("inputs/day01.txt")!!
        return inputStream.bufferedReader()
    }

    private fun String.toPartOneCalibrationValue(): Int {
        val first = this.find { it.isDigit() }!!.digitToInt()
        val last = this.findLast { it.isDigit() }!!.digitToInt()

        return (first * 10) + last
    }

    private fun String.toPartTwoCalibrationValue(): Int {
        val (_, firstStringMatch) = this.findAnyOf(partTwoMatchMap.keys)!!
        val (_, lastStringMatch) = this.findLastAnyOf(partTwoMatchMap.keys)!!

        val first = partTwoMatchMap[firstStringMatch]!!
        val last = partTwoMatchMap[lastStringMatch]!!

        return (first * 10) + last
    }

    fun solvePartOne(): Int {
        val reader = getInputAsReader()

        val result = reader.lines().parallel().mapToInt {
            it.toPartOneCalibrationValue()
        }.sum()

        return result
    }

    fun solvePartTwo(): Int {
        val reader = getInputAsReader()

        val result = reader.lines().parallel().mapToInt {
            it.toPartTwoCalibrationValue()
        }.sum()

        return result
    }
}
