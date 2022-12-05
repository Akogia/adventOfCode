import fileReader

def campCleanup(textfile):
    column = fileReader.fileReader(textfile)
    compareAssignement(column)

def compareAssignement(column):
    result = []
    for line in column:
        assign = line.split(',')
        assigneOne = assign[0].split('-')
        assigneTwo = assign[1].split('-')
        # second star
        if overlaps(assigneOne, assigneTwo):
            result.append(line)

        ## first star
        # if int(assigneOne[0]) >= int(assigneTwo[0]) and int(assigneOne[1]) <= int(assigneTwo[1]):
        #    result.append(line)
        # elif int(assigneTwo[0]) >= int(assigneOne[0]) and int(assigneTwo[1]) <= int(assigneOne[1]):
        #    result.append(line)
    print(len(result))

def overlaps(column1, column2):
    return max(int(column1[0]), int(column2[0])) <= min(int(column1[1]), int(column2[1]))
