#! /Users/dsucksto/.pyenv/shims/python

from functools import reduce

input = open("input.txt")
lines = input.readlines()

for i in range(len(lines)):
    main_word = lines[i]
    for j in range(i, len(lines)):
        to_cmp = lines[j]
        count = 0
        if len(main_word) != len(to_cmp):
            continue
        for char_tuple in zip(main_word, to_cmp):
            if (char_tuple[0] != char_tuple[1]):
                count += 1
        if (count == 1):
            print(main_word)
            print(to_cmp)
            break
    else:
        continue
    break

input.close()

