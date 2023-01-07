import math

import fileReader
import re


def main(textfile):
    column = fileReader.fileReader(textfile)
    monkey = read_monkey(column)
    modulus = get_modulus(monkey)
    for i in range(10000):
        for monk in monkey:
            for j in monk[1]:
                worry = worry_level(j, monk[2])
                # print(f'WORRY: {worry}')
                if j % monk[3] == 0:
                    monkey[monk[4]][1].append(worry % modulus)
                else:
                    monkey[monk[5]][1].append(worry % modulus)
            monk[0] += len(monk[1])
            monk[1].clear()
    result = []
    for monk in monkey:
        result.append(monk[0])
    result.sort()
    print(result[-1] * result[-2])


def get_modulus(monkey) -> int:
    modulus = 1
    for monk in monkey:
        modulus *= monk[3]
    print(modulus)
    return modulus


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

