import fileReader
import re


def alternatvi(textfile):
    row = fileReader.fileReader(textfile)
    pattern = r'^(?!.*(.).*\1)[a-zA-Z]{4}$'
    i = 0
    while i <= len(row[0]):
        sub_str = row[0][i:i + 4]
        if re.search(pattern, sub_str):
            print(i+4)
        i += 1
