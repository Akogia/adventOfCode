import fileReader


def main(textfile):
    instructions = get_data(fileReader.fileReader(textfile))
    # [print(ins) for ins in instructions]
    calculate_signal_strength(instructions)


def calculate_signal_strength(instructions: list):
    result = 0
    signal = 1
    i = 0
    for instruction in instructions:
        if instruction[0] == 'noop':
            i += 1
        else:
            i += 1
            result += check_signal(i, signal)
            i += 1
        result += check_signal(i, signal)
        signal += instruction[1]
    print(result)


def check_signal(iteration, signal) -> int:
    result = iteration * signal
    if (iteration - 20) == 0 or (iteration - 20) % 40 == 0:
        print(f'iteration {iteration} with the signal {signal} and result {result}')
        return result
    else:
        return 0


def get_data(columns) -> list[list]:
    rows = [rows for rows in columns]
    instructions = []
    for row in rows:
        if row == 'noop':
            instructions.append([row, 0])
        else:
            instructions.append([row.split(' ')[0], int(row.split(' ')[1])])
    return instructions