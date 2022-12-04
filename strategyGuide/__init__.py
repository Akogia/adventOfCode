def readFromFile(textfile):
    column = []
    with open(textfile, 'r') as f:
        while True:
            line = f.readline()
            if not line:
                break
            column.append(line.strip())
    return column


# A/X Rock
# B/Y Paper
# C/Z Scissor
# second start: the second column means, how the round shall end:
# x lose
# y draw
# z win
def winLossOrDraw(column1, column2):
    if column1 == 'A':
        if column2 == 'X':
            return 3
        elif column2 == 'Y':
            return 6
        else:
            return 0
    if column1 == 'B':
        if column2 == 'X':
            return 0
        elif column2 == 'Y':
            return 3
        else:
            return 6
    if column1 == 'C':
        if column2 == 'X':
            return 6
        elif column2 == 'Y':
            return 0
        else:
            return 3


# Rock 1 point
# Paper 2 point
# Scissor 3 point
# 0 point for loss
# 3 points for draw
# 6 points for win
def tournament(text_file):
    column = readFromFile(text_file)
    points = 0
    for x in iter(column):
        shape = getShape(x.strip()[0], x.strip()[2])
        points = points + shapePoints(shape) + winLossOrDraw(x.strip()[0], shape)
    print(points)

def shapePoints(column2):
    if column2 == 'X':
        return 1
    elif column2 == 'Y':
        return 2
    elif column2 == 'Z':
        return 3

def getShape(column1,column2):
    # DRAw
    if column2 == 'Y':
        if column1 == 'A':
            return 'X'
        elif column1 == 'B':
            return 'Y'
        else:
            return 'Z'
    # LOSS
    if column2 == 'X':
        if column1 == 'A':
            return 'Z'
        elif column1 == 'B':
            return 'X'
        else:
            return 'Y'
    # WIN
    if column2 == 'Z':
        if column1 == 'A':
            return 'Y'
        elif column1 == 'B':
            return 'Z'
        else:
            return 'X'
