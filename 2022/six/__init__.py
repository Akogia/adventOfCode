import fileReader


def tuningTrouble(textfile):
    line = fileReader.fileReader(textfile)
    print(len(line[0]))
    i = 0
    while i <= len(line[0]):
        sub_str = line[0][i:i + 14]
        if isUnique(sub_str):
            print(sub_str)
            print(i+14)
        i += 1


def isUnique(subSt):
    for i in range(0, len(subSt)):
        test_var = subSt[i]
        remain_str = subSt[:i] + subSt[i+1:]
        if remain_str.__contains__(test_var):
            return False
    return True
