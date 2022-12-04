import fileReader


def rucksackOrga(textfile):
    column = fileReader.fileReader(textfile)
    priority = 0
    for line in column:
        halfword = int(len(line)) / 2
        compartment1 = line[0: int(halfword)]
        compartment2 = line[int(halfword): len(line)]
        priority = priority + findSameLetter(compartment1, compartment2)

    print(priority)


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
