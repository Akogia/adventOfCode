# This is a sample Python script.
import eight
import eleven
import five
import four
import calorieCounter.calorie

# Press the green button in the gutter to run the script.
import nine
import rucksackOrganiziation
import seven
import six
import strategyGuide
import ten


def dayOne():
    result = calorieCounter.calorie.calorieCounter('input/calories.txt')
    # first start
    print(result[0])
    # second start
    print(result[0] + result[1] + result[2])


def dayTwo():
    strategyGuide.tournament('input/strategyGuide.txt')


def dayThree():
    rucksackOrganiziation.rucksackOrga('input/rucksack.txt')


def dayFour():
    four.campCleanup('input/campCleanup.txt')


def dayFive():
    five.supplyStack('input/supplyStacks.txt')


def daySix():
    # six.tuningTrouble('input/tuningTrouble.txt')
    six.alternativ.alternatvi('input/tuningTrouble.txt')


def daySeven():
    seven.noSpaceLeftOnDevice('input/day7.txt')


def dayEight():
    eight.main('input/day8.txt')


def dayNine():
    nine.main('input/day9.txt')


def dayTen():
    ten.main('input/day10.txt')


def dayEleven():
    eleven.main('input/day11.txt')


if __name__ == '__main__':
    dayEleven()
