import fileReader


def main(textfile):
    column = fileReader.fileReader(textfile)
    rows = [row.split(' ') for row in column]
    set_field(rows)


def set_field(rows):
    head = [(0, 0)]
    tail = [(0, 0)]
    visited_field = set()
    visited_field.add(tail[0])

    for row in rows:
        direction = row[0]
        steps = row[1]
        move_head(head, direction, steps, visited_field, tail)

    print(len(visited_field))

def move_head(head, direction, steps, visited_field, tail):
    # print(f'head will move in direction: {direction} with {steps} steps')
    for _ in range(int(steps)):
        match direction:
            case 'L':
                head.append((head[-1][0] - 1, head[-1][1]))
            case 'R':
                head.append((head[-1][0] + 1, head[-1][1]))
            case 'U':
                head.append((head[-1][0], head[-1][1] + 1))
            case 'D':
                head.append((head[-1][0], head[-1][1] - 1))
        update_tail(head, tail, visited_field)


def update_tail(head, tail, visited_field):
    # print(f'Current Head Position: {head[-1]} and tail position {tail[-1]}')
    x_diff = head[-1][0] - tail[-1][0]
    y_diff = head[-1][1] - tail[-1][1]
    # print(f' this is the difference: head [{head[-1][0]}/{head[-1][1]}] and tail [{tail[-1][0]}/{tail[-1][1]}]')
    # RESULT 5846 is too low and 6156 is too high
    if abs(x_diff) > 1 or abs(y_diff) > 1:
        # print(f'Tail moves')
        move_tail(x_diff, y_diff, tail)
        visited_field.add(tail[-1])


def move_tail(x_diff, y_diff, tail):
    if (abs(x_diff) + abs(y_diff)) % 2 != 0:
        # print(f'diagonaler Schritt with x {x_diff} and y {y_diff}')
        tail_move_diagonal(x_diff, y_diff, tail)
    else:
        # print(f'straight with x {x_diff} and y {y_diff}')
        tail_move_straight(x_diff, y_diff, tail)
    # print(f'This is the updated tail which shall be saved in the set: {tail[-1]}')


def tail_move_diagonal(x_diff, y_diff, tail):
    if x_diff < 0:
        if y_diff < 0:
            # print(f'down left diagonal')
            tail.append((tail[-1][0] - 1, tail[-1][1] - 1))
        elif y_diff > 0:
            # print(f'up left diagonal')
            tail.append((tail[-1][0] - 1, tail[-1][1] + 1))
    elif x_diff > 0:
        if y_diff > 0:
            # print(f'up right diagonal')
            tail.append((tail[-1][0] + 1, tail[-1][1] + 1))
        elif y_diff < 0:
            # print(f'down right diagonal')
            tail.append((tail[-1][0] - 1, tail[-1][1] + 1))


def tail_move_straight(x_diff, y_diff, tail):
    if x_diff < 0:
        # print(f'left')
        tail.append((tail[-1][0] - 1, tail[-1][1]))
    elif x_diff > 0:
        # print(f'right')
        tail.append((tail[-1][0] + 1, tail[-1][1]))
    elif y_diff < 0:
        # print(f'down')
        tail.append((tail[-1][0], tail[-1][1] - 1))
    elif y_diff > 0:
        # print(f'up')
        tail.append((tail[-1][0], tail[-1][1] + 1))
