# This is a sample Python script.
import five
import four
import calorieCounter.calorie

# Press the green button in the gutter to run the script.
import rucksackOrganiziation
import seven
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
    # six.tuningTrouble('input/tuningTrouble.txt')
    six.alternativ.alternatvi('input/tuningTrouble.txt')


def daySeven():
    seven.noSpaceLeftOnDevice('input/noSpaceLeftOnDevice.txt')

    # seven.build()



if __name__ == '__main__':
     daySeven()
    # root = seven.Directory('root')
    # root.add_file({'test': 1233})
    # root.add_file({'234': 345})
    # root.add_file({'25': 456})
    # path1 = seven.Directory('path1')
    # path2 = seven.Directory('path2')
    # path3 = seven.Directory('path3')
    # root.add_directory(path1)
    # path1.add_directory(path2)
    # path2.add_directory(path3)
    # for dire in root.directories:
    #     print(dire.name)
    #     print(dire.directories[0].name)
    # result = root.get_directory("path1")
    # print(result.name)
    # print(root.files)
