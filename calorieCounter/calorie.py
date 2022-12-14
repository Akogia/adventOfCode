def calorieCounter(textfile):
    elves = []
    calorie = 0
    with open(textfile, 'r') as f:
        while True:
            line = f.readline()
            if line.strip() == '':
                elves.append(calorie)
                calorie = 0
            else:
                calorie = calorie + int(line.strip())
            if not line:
                break
    elves.sort(reverse=True)
    return elves