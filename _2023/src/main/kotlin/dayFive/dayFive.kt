package dayFive

import java.io.File

var seed = longArrayOf()
var seeds = mutableListOf<LongArray>()
var seedToSoil = mutableListOf<LongArray>()
var soilToFertilizer = mutableListOf<LongArray>()
var fertilizerToWater = mutableListOf<LongArray>()
var waterToLight = mutableListOf<LongArray>()
var lightToTemperature = mutableListOf<LongArray>()
var temperatureToHumidity = mutableListOf<LongArray>()
var humidityToLocation = mutableListOf<LongArray>()

var s2S: LongArray= longArrayOf()
var s2F: LongArray= longArrayOf()
var f2W: LongArray= longArrayOf()
var w2L: LongArray= longArrayOf()
var l2T: LongArray= longArrayOf()
var t2H: LongArray= longArrayOf()
var h2L: LongArray= longArrayOf()
fun main() {
    val fileContent = File("_2023/src/main/resources/dayFive.txt").readText(Charsets.UTF_8).split("\n")


    val splittedContent = splitOnEmptyStrings(fileContent)
    for (content in splittedContent) {
        splitFileContent(content)
    }
    //redefineSeeds()
    calculate()
    //println(counter)

}

fun redefineSeeds() {
    for (i in seed.indices step 2) {
        val rangeArray = LongArray(seed[i+1].toInt()) { it.toLong() + seed[i] }
        seeds.add(rangeArray)
    }
}

fun calculate () {
    var minLocation = Long.MAX_VALUE

    for (i in seed.indices step 2) {
        val rangeArray = LongArray(seed[i+1].toInt()) { it.toLong() + seed[i] }
        for ( s in rangeArray) {
            s2S = mappedNumber(s, seedToSoil)
            //print("seed: $s, location after seedToSoil: ${s2S.contentToString()},")
            for ( soil in s2S ){
                s2F = mappedNumber(soil, soilToFertilizer)
                //print("location after soilToFertilizer: ${s2F.contentToString()},")
            }
            for ( fertilizer in s2F ){
                f2W = mappedNumber(fertilizer, fertilizerToWater)
                //print("location after fertilizerToWater: ${f2W.contentToString()},")
            }
            for ( water in f2W ){
                w2L = mappedNumber(water, waterToLight)
                //print("location after waterToLight: ${w2L.contentToString()},")
            }
            for ( light in w2L ){
                l2T = mappedNumber(light, lightToTemperature)
                //print("location after lightToTemperature: ${l2T.contentToString()},")
            }
            for ( temperature in l2T ){
                t2H = mappedNumber(temperature, temperatureToHumidity)
                //print("location after humidityToLocation: ${h2L.contentToString()},")
            }
            for ( humidity in t2H ){
                h2L = mappedNumber(humidity, humidityToLocation)
                //print("location after humidityToLocation: ${h2L.contentToString()},")
                //println("")
            }
            for ( location in h2L ){
                if (location < minLocation) {
                    println("new min location: $location")
                    minLocation = location
                }
            }
        }
    }

    print("min location: $minLocation")
}


fun mappedNumber(mappingNumber: Long, longArray: List<LongArray>): LongArray {
    // first index is destination, second index is source and third range of the mapping
    //println(longArray.contentcontentToString())
    val mappedNumber = mutableListOf<Long>()
    for (element in longArray) {
        if (mappingNumber in element[1]..(element[1] + element[2])) {
            mappedNumber.add(element[0]-element[1]+mappingNumber)
        }
    }
    if (mappedNumber.isEmpty()) {
        mappedNumber.add(mappingNumber)
    }
    return mappedNumber.toLongArray()
}




fun splitFileContent(mappedNumber: List<String>) {
    if ("seeds:" in mappedNumber[0]) {
        seed = mappedNumber[0].split(" ").drop(1).map { it.toLong() }.toLongArray()
    } else if ("seed-to-soil map:" in mappedNumber[0]) {
        for (element in mappedNumber.drop(1)) {
            seedToSoil.add(element.split(" ").map { it.toLong() }.toLongArray())
        }
    } else if ("soil-to-fertilizer map:" in mappedNumber[0]) {
        for (element in mappedNumber.drop(1)) {
            soilToFertilizer.add(element.split(" ").map { it.toLong() }.toLongArray())
        }
    } else if ("fertilizer-to-water map:" in mappedNumber[0]) {
        for (element in mappedNumber.drop(1)) {
            fertilizerToWater.add(element.split(" ").map { it.toLong() }.toLongArray())
        }
    } else if ("water-to-light map:" in mappedNumber[0]) {
        for (element in mappedNumber.drop(1)) {
            waterToLight.add(element.split(" ").map { it.toLong() }.toLongArray())
        }
    } else if ("light-to-temperature map:" in mappedNumber[0]) {
        for (element in mappedNumber.drop(1)) {
            lightToTemperature.add(element.split(" ").map { it.toLong() }.toLongArray())
        }
    } else if ("temperature-to-humidity map:" in mappedNumber[0]) {
        for (element in mappedNumber.drop(1)) {
            temperatureToHumidity.add(element.split(" ").map { it.toLong() }.toLongArray())
        }
    } else if ("humidity-to-location map:" in mappedNumber[0]) {
        for (element in mappedNumber.drop(1)) {
            humidityToLocation.add(element.split(" ").map { it.toLong() }.toLongArray())
        }
    }
}
fun splitOnEmptyStrings(list: List<String>): List<List<String>> {
    val result = mutableListOf<List<String>>()
    var temp = mutableListOf<String>()

    for (item in list) {
        if (item.isEmpty()) {
            if (temp.isNotEmpty()) {
                result.add(temp)
                temp = mutableListOf()
            }
        } else {
            temp.add(item)
        }
    }

    if (temp.isNotEmpty()) {
        result.add(temp)
    }

    return result
}