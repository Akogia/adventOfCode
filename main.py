# This is a sample Python script.
import five
import four
import calorieCounter.calorie

# Press the green button in the gutter to run the script.
import rucksackOrganiziation
import six
import strategyGuide


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
    six.tuningTrouble('input/tuningTrouble.txt')


if __name__ == '__main__':
    dayFive()