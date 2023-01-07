import math

import fileReader
import re


def main(textfile):
    column = fileReader.fileReader(textfile)
    monkey = read_monkey(column)
    # [print(monk) for monk in monkey]
    i = 0
    for i in range(20):
        print(i)
        for monk in monkey:
            for j in monk[1]:
                worry = math.floor(worry_level(j, monk[2])/3)
                if worry % monk[3] == 0:
                    monkey[monk[4]][1].append(worry)
                else:
                    monkey[monk[5]][1].append(worry)
            monk[0] += len(monk[1])
            monk[1].clear()
        [print(monk) for monk in monkey]
        print('-----------------------')


def worry_level(item: int, operands: list) -> int:
    first_operand = item if operands[0] == 'old' else int(operands[0])
    operand = operands[1]
    second_operand = item if operands[2] == 'old' else int(operands[2])
    if operand == '+':
        return first_operand + second_operand
    elif operand == '*':
        return first_operand * second_operand


def read_monkey(column) -> list[list]:
    monkey = [[], [], [], [], [], [], [], []]
    # monkey = [[], [], [], []]
    i = 0
    for row in column:
        r = row.split(' ')
        if 'Monkey' in row:
            i = int(r[1][:1])
            monkey[i].append(0)
        elif 'Starting' in row:
            monkey[i].append(extract_numbers_from_string(row))
        elif 'Operation' in row:
            monkey[i].append([r[3], r[4], r[5]])
        elif 'Test' in row:
            monkey[i].append(int(r[3]))
        elif 'true' in row:
            monkey[i].append(int(r[5]))
        elif 'false' in row:
            monkey[i].append(int(r[5]))
    return monkey

def extract_numbers_from_string(string) -> list:
    """Extracts the integer values from the given string and returns them in a list"""
    numbers = re.findall(r'\b\d+\b', string)
    return [int(number) for number in numbers]

