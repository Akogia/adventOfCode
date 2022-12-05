# This is a sample Python script.
import Four
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
    Four.campCleanup(textfile)


if __name__ == '__main__':
    dayFour('input/campCleanup.txt')
#
