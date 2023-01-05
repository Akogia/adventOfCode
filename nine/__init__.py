import fileReader


def main(textfile):
    column = fileReader.fileReader(textfile)
    rows = [row.split(' ') for row in column]
    field = set_field(rows)

def set_field(rows) -> dict:
    field = {}
    head_x = 0
    head_y = 0

    for row in rows:
        direction = row[0]
        steps = int(row[1])
        move_direction(field, direction, steps, head_x, head_y)
        print_field(field)
    return field


def move_direction(field, direction, steps, head_x, head_y):
    match direction:
        case 'L':
            for _ in range(steps):
                head_x -= 1
                head_y += 0
                field.update((head_x, head_y))
        case 'R':
            for _ in range(steps):
                head_x += 1
                head_y += 0
                field.update((head_x, head_y))
        case 'U':
            for _ in range(steps):
                head_y += 1
                head_x += 0
                field.update((head_x, head_y))
        case 'D':
            for _ in range(steps):
                head_y -= 1
                head_x += 0
                field[(head_x, head_y)] = 'H'


def print_field(field):
    # Print the final field
    for x in range(max(field.keys())[0] + 1):
        for y in range(max(field.keys())[1] + 1):
            if (x, y) in field:
                print(field[(x, y)], end=' ')
            else:
                print(0, end=' ')
        print()
    print('-----------------------------------')
