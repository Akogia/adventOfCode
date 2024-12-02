package com.ipcustomer.test.two

import com.ipcustomer.test.println
import java.io.File
import kotlin.math.abs

fun main() {
    val fileContent = File("2024/src/main/resources/input.txt").readText(Charsets.UTF_8).split('\n')

    fileContent.two()
}

private fun List<String>.one() {
    var safeReports = 0
    forEach { line ->
        var listInt = line.split(' ').map { it.toInt() }
        listInt.println()

        if (isAscending(listInt)){
            println("Ascending")
            safeReports += 1
        } else if (isDescending(listInt)){
            println("Descending")
            safeReports += 1
        } else {
            println("Not valid report")
        }

    }
    println(safeReports)
}

private fun isAscending(numbers: List<Int>): Boolean {
    return numbers.zipWithNext().all { (a, b) -> b - a in 1..3 }
}

private fun isDescending(numbers: List<Int>): Boolean {
    return numbers.zipWithNext().all { (a, b) -> a - b in 1..3 }
}

private fun List<String>.two() {
    var safeReports = 0
    forEach { line ->
        var listInt = line.split(' ').map { it.toInt() }.toMutableList()
        var listDiff = mutableListOf<Int>()
        listInt.println()
        listInt.zipWithNext { a, b -> listDiff.add(b - a) }
        listDiff.println()
        var ascending = listDiff.count { it > 0 }
        if (ascending > 1) {
            var fixed = 0
            listDiff.forEachIndexed { index, diff ->
                if (fixed > 0) {
                    return@forEachIndexed
                }
                if (diff == 0 || diff > 3 || diff < 0) {
                    //checkBestSolution(listInt, index, true)
                    listInt = bruteForceMethod(listInt, index)
                    fixed++
                }
            }
        } else {
            var fixed = 0
            listDiff.forEachIndexed { index, diff ->
                if (fixed > 0) {
                    return@forEachIndexed
                }
                if (diff == 0 || diff < -3 || diff > 0) {
                    //checkBestSolution(listInt, index, false)
                    listInt = bruteForceMethod(listInt, index)
                    fixed++
                }
            }
        }
        println("updated $listInt")
        if (isAscending(listInt)){
            println("Ascending")
            safeReports += 1
        } else if (isDescending(listInt)){
            println("Descending")
            safeReports += 1
        } else {
            println("Not valid report")
        }
        println(safeReports)
        println("")
    }
    println(safeReports)
}

//[50, 54, 55, 57, 59]
//[4, 1, 2, 2]
//Remove index 0 with value 4
//updated [50, 55, 57, 59]


//[95, 92, 93, 94, 95, 97]
//[-3, 1, 1, 1, 2]
//Remove index 0 with value -3
//first index compare
//remove second element
//updated [95, 93, 94, 95, 97]
//Not valid report

//82, 80, 82, 79, 78, 77, 74
//[-2, 2, -3, -1, -1, -3]
//Remove index 1 with value 80
//updated [82, 82, 79, 78, 77, 74]
//Not valid report

private fun checkBestSolution(listInt: MutableList<Int>, index: Int, asc: Boolean): List<Int> {

    // if it's last index
    if ( (index+1) == listInt.size-1) {
        println("last index and therefore remove last element")
        listInt.removeAt(index+1)
        // if index 0 then it shall check 0 and 1
    } else if ( index == 0) {
        println("first index compare")
        if (abs(listInt[index] - listInt[2]) <= abs(listInt[1] - listInt[2])) {
            println("remove second element")
            listInt.removeAt(index+1)
        } else {
            println("remove first element")
            listInt.removeAt(index)
        }
    } else if (asc) {
        if (abs(listInt[index - 1] - listInt[index + 1]) <= abs(listInt[index] - listInt[index + 2])) {
            println("ascending removal")
            var test = listInt[index]
            println("Remove index $index with value $test")
            listInt.removeAt(index)
        } else {
            listInt.removeAt(index + 1)
        }
    } else {
        if (abs(listInt[index - 1] - listInt[index + 1]) >= abs(listInt[index] - listInt[index + 2])) {
            println("descending removal")
            var test = listInt[index]
            println("Remove index $index with value $test")
            listInt.removeAt(index)
        } else {
            listInt.removeAt(index + 1)
        }
    }


    return listInt

    // else it shall check -1 with +1

    // else it shall check for 0 and +2
}

private fun bruteForceMethod(listInt: MutableList<Int>, index: Int): MutableList<Int> {
    //var minusOneList = listInt
    //var zeroList = listInt
    //var plusOneList = listInt

    if (index == 0) {
        val indicesToRemove = listOf(0,1)

        val modifiedLists = indicesToRemove.map { i -> listInt.filterIndexed { index,_ -> index != i }}
        modifiedLists.forEachIndexed { index, i ->
            if (rightDocument(i.toMutableList())) {
                return i.toMutableList()
            }
        }
        //zeroList.removeAt(index)
        //plusOneList.removeAt(index+1)
        //if (rightDocument(zeroList)) {
        //    println("true index zero")
        //    return zeroList
        //}
        //if (rightDocument(plusOneList)) {
        //    println("true index plus one, zero index")
        //    return plusOneList
        //}
    } else {
        val indicesToRemove = listOf(index-1,index+1,index)

        val modifiedLists = indicesToRemove.map { i -> listInt.filterIndexed { index,_ -> index != i }}
        modifiedLists.forEachIndexed { index, i ->

            if (rightDocument(i.toMutableList())) {
                println("Right Document $i")
                return i.toMutableList()
            }
        }
        //minusOneList.removeAt(index-1)
        //zeroList.removeAt(index)
        //plusOneList.removeAt(index+1)
        //if (rightDocument(zeroList)) {
        //    println("true for index")
        //    return zeroList
        //}
        //if (rightDocument(plusOneList)) {
        //    println("true index plus one")
        //    return plusOneList
        //}
        //if (rightDocument(minusOneList)) {
        //    println("true index minus one")
        //    return plusOneList
        //}
    }
    return listInt
}

private fun rightDocument(listInt: MutableList<Int>): Boolean {
    if (isAscending(listInt)){
        println("Ascending")
        return true
    } else if (isDescending(listInt)){
        println("Descending")
        return true
    } else {
        println("Not valid report")
        return false
    }
}