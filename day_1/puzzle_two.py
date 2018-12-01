#! /Users/dsucksto/.pyenv/shims/python

from functools import reduce

file = open("input.txt")
cleaned_input = [x.strip() for x in file.readlines() if x.strip()]

sum = 0
viewed_input = set()
viewed_input.add(sum)
while(True):
    for input in cleaned_input:
        sum += int(input)
        if (viewed_input.__contains__(sum)):
            print(sum)
            exit(0)
        else:
            viewed_input.add(sum)

file.close()

