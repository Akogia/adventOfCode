import fileReader


def rucksackOrga(textfile):
    column = fileReader.fileReader(textfile)
    group = []
    for line in column:
        group.append(line.strip())

    points = 0
    i = 0
    while i <= len(group):
        if i + 3 > len(group):
            break
        print("TADA")
        points += findSameLetterInGroup(group[i], group[i + 1], group[i + 2])
        print(points)
        i += 3
    ## first star
    # priority = 0
    # for line in column:
    #    half-word = int(len(line)) / 2
    #    compartment1 = line[0: int(half-word)]
    #    compartment2 = line[int(half-word): len(line)]
    #    priority = priority + findSameLetter(compartment1, compartment2)
    # print(priority)


def findSameLetterInGroup(str1, str2, str3):
    for a in str1:
        if a in str2 and str3:
            print(a)
            return getValue(a)


def findSameLetter(str1, str2):
    for a in str1:
        if a in str2:
            return getValue(a)


def getValue(str):
    if str.islower():
        value = ord(str) - 96
    else:
        value = 26 + ord(str) - 64
    return value
