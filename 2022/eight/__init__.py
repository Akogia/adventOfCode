import fileReader


def main(textfile):
    column = fileReader.fileReader(textfile)
    is_tree_visible(column)


def is_tree_visible(column):
    # is need for first star
    # counter = 0
    result = 0
    for i, row in enumerate(column):
        for j, val in enumerate(row):
            # is needed for star 1
            #if is_around_edge(i, len(row)-1, j, len(column)-1):
            #    counter += 1

            column_str = [lst[j] for lst in column]
            column_int = [int(x) for x in column_str]
            up = column_int[:i]
            down = column_int[i+1:]
            row_int = [int(x) for x in row]
            left = row_int[:j]
            right = row_int[j+1:]
            # This is for the first start
            #if is_highest(val, up) or is_highest(val, down) or is_highest(val, left) or is_highest(val, right):
            #    counter += 1
            up.reverse()
            left.reverse()
            up_value = measure_distance(val, up)
            down_value = measure_distance(val, down)
            left_value = measure_distance(val, left)
            right_value = measure_distance(val, right)
            current_result = up_value * down_value * left_value * right_value
            if result < current_result:
                result = current_result
    print(result)


def is_around_edge(index_row: int, index_row_max: int, index_column: int, index_column_max: int) -> bool:
    if index_row == 0 or index_row == index_row_max:
        return True
    elif index_column == 0 or index_column == index_column_max:
        return True
    else:
        return False


def is_highest(value, row):
    if int(value) > max(row):
        return True
    else:
        return False


def measure_distance(value, distance) -> int:
    count = 0
    for item in distance:
        count += 1
        if item >= int(value):
            return count
    return count
