import random
import sys
from pathlib import Path


def contains(item, arr) -> bool:
    result = False

    for i in arr:
        if i is not None and i == item:
            result = True

    return result


# Reads a list of words from a txt file that the computer chooses from
words = Path("hangman_words2.txt").read_text().splitlines()

# To make the game more interesting, change the "hangman_words1.txt" text above to
# "hangman_words2.txt" to play with a bigger set of words
# And try not to look at the word lists!

# random.choice() will choose one random word from the list of words defined above
word = list(random.choice(words))

guesses = []

# Any number of turns can be used here, however in the traditional game of Hangman, it
# takes 6 tries before the man is "hung", unless you include the eyes and mouth, which would
# make it 9 tries.
turns = 12

# This is displayed at the beginning of the game for the user to see
# how many letters there are in the word
blanks = "_" * len(word)

print("Welcome to Hangman!")

# Game loop runs while the word has not been guessed
while list(blanks) != word:
    # This will print the number of turns left for the user
    print("\nGuesses left: " + str(turns) + "\n")
    print(*blanks)

    print("\nLetters already guessed:")
    print(*guesses)

    # Asks the user to enter a guess
    guess = input("\nGuess a character: ")

    # Validates the input from above
    if len(guess) > 1 or guess.isupper() == True:
        print("\nSorry, you entered an invalid guess. Please try again.")
        continue

    if contains(guess, guesses):
        print("\nYou have already guessed '" + guess + "'. Please try again.")
        continue

    if guess not in word:
        turns -= 1

        # If the character doesn't match the word, "wrong" will be given as output
        print("\n'" + guess + "' is wrong")
        guesses.append(guess)

    if guess in word:
        guesses.append(guess)

        for i in range(len(word)):
            if word[i] in guesses:
                blanks = blanks[:i] + word[i] + blanks[i + 1:]

    # If all turns are used, end the game and print the word
    if turns == 0:
        print("You lost. The word was " + "'" + "".join(word) + "'.")
        sys.exit()


print("\n")
print(*blanks)
print("You guessed the word!")
sys.exit()
