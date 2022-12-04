def fileReader(textfile):
    column = []
    with open(textfile, 'r') as f:
        while True:
            line = f.readline()
            if not line:
                break
            column.append(line.strip())
    return column