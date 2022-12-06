# This is a sample Python script.
import five
import four
import calorieCounter.calorie

# Press the green button in the gutter to run the script.
import rucksackOrganiziation
import strategyGuide


def dayOne(textfile):
    result = calorieCounter.calorie.calorieCounter(textfile)
    # first start
    print(result[0])
    # second start
    print(result[0] + result[1] + result[2])


def dayTwo(textfile):
    strategyGuide.tournament(textfile)


def dayThree(textfile):
    rucksackOrganiziation.rucksackOrga(textfile)


def dayFour(textfile):
    four.campCleanup(textfile)

def dayFive(textfile):
    five.supplyStack(textfile)


if __name__ == '__main__':
    dayFive('input/supplyStacks.txt')
#
