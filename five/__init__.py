import fileReader
import utils


def supplyStack(textfile):
    column = fileReader.fileReader(textfile)

    stacks = collectInStacks(column)

    del column[:10]

    procedure = collectProcedures(column)
    maxProcedure = len(procedure[0])
    q = 0

    while q < maxProcedure:
        print(f"row: {procedure[0][q]} from {procedure[1][q]}  to {procedure[2][q]}")
        numberStack = int(procedure[0][q])
        var = stacks[int(procedure[1][q]) - 1][0:numberStack]
        del stacks[int(procedure[1][q]) - 1][0:numberStack]
        print(f"Stacked Variable {var}")
        while len(var) > 0:
            stacks[int(procedure[2][q]) - 1].insert(0, var[-1])
            var.pop()
        printResult(stacks)
        q += 1

    for result in stacks:
        print(result[0])

def printResult(stacks):
    for row in stacks:
        print(row)
    print('_______________________________________')


def collectInStacks(column):
    # stacks from 1-9 or 0-8
    stacks = [[], [], [], [], [], [], [], [], []]

    # iterate trough lines
    i = 0
    while i < 8:
        # iterate trough chars at certain index
        j = 1
        while j < 34:
            # iterate through stack column
            if column[i][j] != ' ':
                for c in utils.StackNumber:
                    if c.value[1] == j:
                        stacks[c.value[0]].append(column[i][j])
            j += 4
        i += 1
    return stacks


def collectProcedures(column):
    # procedures stack number, from, to
    procedures = [[], [], []]
    for line in column:
        col = line.strip().split(' ', 5)
        procedures[0].append(col[1])
        procedures[1].append(col[3])
        procedures[2].append(col[5])
    return procedures
