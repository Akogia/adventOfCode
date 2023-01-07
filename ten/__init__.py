import fileReader


def main(textfile):
    instructions = get_data(fileReader.fileReader(textfile))
    # [print(ins) for ins in instructions]
    calculate_signal_strength(instructions)


def calculate_signal_strength(instructions: list):
    crt = [[], [], [], [], [], []]
    result = 0
    signal = 1
    i = 0
    for instruction in instructions:
        if instruction[0] == 'noop':
            i += 1
        else:
            i += 1
            result += check_signal(i, signal)
            draw_crt(crt, i, signal)
            i += 1
        result += check_signal(i, signal)
        draw_crt(crt, i, signal)
        signal += instruction[1]
    print(result)
    [print(row) for row in crt]

def draw_crt(crt: list, iteration: int, signal: int):
    sprite = [signal - 1, signal, signal + 1]
    draw = '.'
    if iteration in sprite:
        draw = '#'
    match int(iteration / 40):
        case 0:
            crt[0].append(draw)
        case 1:
            if iteration - 40 in sprite:
                draw = '#'
            crt[1].append(draw)
        case 2:
            if iteration - 80 in sprite:
                draw = '#'
            crt[2].append(draw)
        case 3:
            if iteration - 120 in sprite:
                draw = '#'
            crt[3].append(draw)
        case 4:
            if iteration - 160 in sprite:
                draw = '#'
            crt[4].append(draw)
        case 5:
            if iteration - 200 in sprite:
                draw = '#'
            crt[5].append(draw)


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