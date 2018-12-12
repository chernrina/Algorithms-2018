@file:Suppress("UNUSED_PARAMETER")

package lesson6

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
//Трудоемкость О(m*n)
//Ресурсоемкость О(m*n)
//m - first.length, n - second.length
fun longestCommonSubSequence(first: String, second: String): String {
    val lcs = Array(first.length + 1, { Array(second.length + 1, { 0 }) })
    val prev = Array(first.length + 1, { Array(second.length + 1, { Pair(0, 0) }) })
    for (i in 1..first.length) {
        for (j in 1..second.length) {
            if (first[i - 1] == second[j - 1]) {
                lcs[i][j] = lcs[i - 1][j - 1] + 1
                prev[i][j] = Pair(i - 1, j - 1)
            } else {
                if (lcs[i - 1][j] >= lcs[i][j - 1]) {
                    lcs[i][j] = lcs[i - 1][j]
                    prev[i][j] = Pair(i - 1, j)
                } else {
                    lcs[i][j] = lcs[i][j - 1]
                    prev[i][j] = Pair(i, j - 1)
                }
            }
        }
    }
    return printLCS(first.length, second.length, prev, first)
}

fun printLCS(a: Int, b: Int, prev: Array<Array<Pair<Int, Int>>>,
             first: String): String {
    val ans = StringBuilder()
    var i = a
    var j = b
    while (i != 0 && j != 0) {
        if (prev[i][j] == Pair(i - 1, j - 1)) {
            i--
            j--
            ans.append(first[i])
        } else {
            if (prev[i][j] == Pair(i - 1, j)) i--
            else j--
        }
    }
    return ans.toString().reversed()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
//Трудоемкость О(n^2)
//Ресурсоемкость О(n)
// n - list.size
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isEmpty()) return listOf()
    val size = list.size
    val prev = Array(size, { 0 })
    val data = Array(size, { 0 })
    for (i in 0 until size) {
        data[i] = 1
        prev[i] = -1
        for (j in 0 until i) {
            if (list[j] < list[i] && data[j] + 1 > data[i]) {
                data[i] = data[j] + 1
                prev[i] = j
            }
        }
    }
    var position = 0
    var length = data[0]
    for (i in 0 until size) {
        if (data[i] > length) {
            position = i
            length = data[i]
        }
    }
    val ans = mutableListOf<Int>()
    while (position != -1) {
        ans += list[position]
        position = prev[position]
    }
    return ans.reversed()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5