#! /Users/dsucksto/.pyenv/shims/python

from functools import reduce

def transform_input(input):
    stripped = input.strip()
    return int(stripped) if stripped else 0

file = open("input.txt")

final_sum = reduce((lambda sum, input: sum + transform_input(input)), file.readlines(), 0)
print(final_sum)


file.close()

