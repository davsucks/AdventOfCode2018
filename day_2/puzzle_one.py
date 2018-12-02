#! /Users/dsucksto/.pyenv/shims/python

class Counter(dict):
    def __missing__(self, key):
        return 0

input = open("input.txt")
num_of_three_pair_words = 0
num_of_two_pair_words = 0
for line in input.readlines():
    counter = Counter()
    two_char_word = False
    three_char_word = False
    for character in line:
        counter[character] += 1
    for value in counter.values():
        if (value == 2):
            two_char_word = True
        elif (value == 3):
            three_char_word = True
    if (two_char_word):
        num_of_three_pair_words += 1
    if (three_char_word):
        num_of_two_pair_words += 1

print(num_of_three_pair_words)
print(num_of_two_pair_words)

print(num_of_three_pair_words * num_of_two_pair_words)


input.close()

