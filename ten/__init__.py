import fileReader


def main(textfile):
    instructions = get_data(fileReader.fileReader(textfile))
    [print(ins) for ins in instructions]
    calculate_signal_strength(instructions)


def calculate_signal_strength(instructions: list):
    signal = 1
    i = 0
    for instruction in instructions:
        if instruction[0] == 'noop':
            i += 1
        else:
            i += 2
        signal += instruction[1]
        if i % 20 == 0:
            result = i * signal
            print(result)


def get_data(columns) -> list[list]:
    rows = [rows for rows in columns]
    instructions = []
    for row in rows:
        if row == 'noop':
            instructions.append([row, 0])
        else:
            instructions.append([row.split(' ')[0], int(row.split(' ')[1])])
    return instructions