"""
Mastermind Multiplayer: a game created by Eldar Egorov and Daniel Miretsky, aka EE-Gamer and DM-Friends.
If you don't know how to play this game, I suggest you read the rules here: https://magisterrex.files.wordpress.com/2014/07/mastermindrules.pdf
We hope you enjoy it!
"""

import time
import sys
import os
from table import *

time.sleep(0.5)
print("Codemaker, please step up!")
time.sleep(0.5)
print("Make sure the guesser isn't watching...")
time.sleep(0.5)
print("Select your colors with the first letter capitalized in each, and choose from the following:")
time.sleep(0.5)

print("""
1. Red
2. Blue
3. Green
4. Yellow
5. White
6. Black
""")

code_entered = False

# Loop for entering code
while not code_entered:
    time.sleep(0.5)
    color1 = str(input("Please enter your first color. "))

    time.sleep(0.5)
    color2 = str(input("Please enter your second color. "))

    time.sleep(0.5)
    color3 = str(input("Please enter your third color. "))

    time.sleep(0.5)
    color4 = str(input("Please enter your fourth color. "))

    colors_entered = [color1, color2, color3, color4]
    dict = {color1:0, color2:0, color3:0, color4:0}
    code_entered = True
    for color in colors_entered:
        dict[color] += 1
        if dict[color] == 2:
            time.sleep(0.5)
            print("You cannot repeat colors. Please try again.")
            code_entered = False

    valid_colors = ["Red", "Green", "Blue", "Yellow", "White", "Black"]
    invalid_input = False
    for color in colors_entered:
        if color not in valid_colors:
            invalid_input = True
            
    if invalid_input:
        time.sleep(0.5)
        print("You entered an invalid color somewhere. Please try again.")
        code_entered = False

os.system('clear')
time.sleep(0.5)
print("Okay, now that you have created your code, the codebreaker may now turn around and start guessing!")
time.sleep(0.5)

# Sets a variable equal to a list of the 4 colors chosen by the codemaker for the purpose of checking for correct colors in the right spot in the combination (see lines 149 - 167)
list = [color1, color2, color3, color4]

combination = str(color1 + " " + color2 + " " + color3 + " " + color4)

# The statement below is for debugging purposes only - I'm not cheating :P
# print(str(combination))

# Ask the codebreaker for their first guess
time.sleep(0.5)
combination_guess = str(input("Codebreaker, try to guess the combination. Type the names of each color in order with each starting letter capitalized and separate each word with a space: "))

guess_counter = 0
error_counter = 0

guess_history = []

# Loop for guessing the combination
while combination_guess != combination:
    correct_colors = 0
    
    # Increments a variable when the codebreaker guesses a correct color in the combination
    if color1 in combination_guess:
        correct_colors += 1
    if color2 in combination_guess:
        correct_colors += 1
    if color3 in combination_guess:
        correct_colors += 1
    if color4 in combination_guess:
        correct_colors += 1

    combination_guess_list = str(combination_guess).split()
    correct_order_colors = 0
    error_occurred = None

    # Checks for invalid number of colors entered into the "combination_guess" variable
    if len(combination_guess_list) < 4:
        error_counter += 1
        error_occurred = True
        time.sleep(0.5)
        print("Sorry, you entered an invalid combination. Please try again.")
        time.sleep(0.5)
        combination_guess = str(input("Try to guess the combination again. Type the names of each color in order with each starting letter capitalized and separate each word with a space: "))
        continue
    
    # Checks for invalid colors entered into the "combination_guess" variable
    for color in combination_guess.split(" "):
        if color not in valid_colors:
            error_counter += 1
            error_occurred = True
            time.sleep(0.5)
            print("Sorry, you entered an invalid color somewhere. Please try again.")
            time.sleep(0.5)
            combination_guess = str(input("Try to guess the combination again. Type the names of each color in order with each starting letter capitalized and separate each word with a space: "))
            continue

    # Ends the program if 10 errors occur with entering the combination
    if error_counter == 10:
        time.sleep(0.5)
        print("Sorry, it seems you are having some trouble. Please try again later. Goodbye!")
        sys.exit()

    # Increments a variable when the codebreaker guesses a correct color in the correct spot in the combination
    if color1 == combination_guess_list[0]:
        correct_order_colors += 1
    if color2 == combination_guess_list[1]:
        correct_order_colors += 1
    if color3 == combination_guess_list[2]:
        correct_order_colors += 1
    if color4 == combination_guess_list[3]:
        correct_order_colors += 1    

    # Saves guess data to a list
    if not error_occurred:
        guess_counter += 1
        
        guess_data = [combination_guess, correct_colors, correct_order_colors]
        guess_history.append(guess_data)

    time.sleep(0.5)
    print("That is not the correct combination.")

    # Create a table that displays the codebreaker guesses, how many colors they guessed correctly, and how many colors they guessed correctly in the right spot
    table = make_table(
    rows=guess_history,
    labels=["Previous Guesses", "Correct Colors Guessed", "Correct Colors Guessed in the Correct Spot"],
    centered=True
    )

    # Guess counter
    if guess_counter == 9:
        time.sleep(0.5)
        print(table)
        time.sleep(0.5)
        print("You have 1 try left.")
    elif guess_counter != 10:
        time.sleep(0.5)
        print(table)
        time.sleep(0.5)
        print("You have " + str(10 - guess_counter) + " tries left.")
    elif guess_counter == 10:
        time.sleep(0.5)
        print("You have run out of tries. The combination was '" + combination + "'.")
        time.sleep(0.5)
        print("Better luck next time!")
        time.sleep(0.5)
        sys.exit()

    # Ask the codebreaker to guess the combination while he/she gets the combination wrong
    time.sleep(0.5)
    combination_guess = str(input("Try to guess the combination again. Type the names of each color in order with each starting letter capitalized and separate each word with a space: "))

# Validates whether the combination inputted by the codebreaker matches the code created by the codemaker
if combination_guess == combination:
    time.sleep(0.5)
    print("Congratulations! You have guessed the combination!")
    sys.exit()