"""
Mastermind Singleplayer: a game created by Daniel Miretsky.
If you don't know how to play this game, I suggest you read the rules here: https://magisterrex.files.wordpress.com/2014/07/mastermindrules.pdf
We hope you enjoy our program!
"""

import random
from prettytable import PrettyTable


def generate_code():
    colors = ['Red', 'Blue', 'Green', 'Yellow', 'White', 'Black']
    return random.sample(colors, 4)


def evaluate_guess(secret_code, guess):
    correct_color_and_position = sum(1 for i in range(4) if secret_code[i] == guess[i])
    correct_color_only = sum(min(secret_code.count(color), guess.count(color)) for color in set(guess)) - correct_color_and_position

    return correct_color_and_position, correct_color_only


def display_board(board):
    table = PrettyTable()
    table.field_names = ["Turn", "Guess", "Correct Col & Pos", "Correct Col Only"]

    for turn, (guess, correct_col_pos, correct_col_only) in enumerate(board, start=1):
        formatted_guess = ' '.join(guess)
        table.add_row([turn, formatted_guess, correct_col_pos, correct_col_only])

    print(table)


def mastermind():
    print("Welcome to Mastermind!")

    secret_code = generate_code()
    max_attempts = 10
    attempts_left = max_attempts
    board = []

    print("\nAvailable colors: Red, Blue, Green, Yellow, White, Black")

    while attempts_left > 0:
        guess = input("Enter your guess (4 colors, space-separated): ").title().split()

        if guess[0] == "Quit":
            break

        if len(guess) != 4 or not all(c in ['Red', 'Blue', 'Green', 'Yellow', 'White', 'Black'] for c in guess) or len(set(guess)) < 4:
            print("\nInvalid input. Please enter 4 unique valid colors.")
            continue

        correct_col_pos, correct_col_only = evaluate_guess(secret_code, guess)
        board.append((guess, correct_col_pos, correct_col_only))
        display_board(board)

        if correct_col_pos == 4:
            print("Congratulations! You've guessed the code!")
            break

        attempts_left -= 1
        print(f"Attempts left: {attempts_left}")

    print("\nGame over. The secret code was:", ' '.join(secret_code))


if __name__ == "__main__":
    mastermind()
