using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Wordle
{
    public partial class Wordle : Form
    {
        private string word = "";
        public string[] wordArr;
        public string[] guessArr;
        public bool numberEntered = false;

        #pragma warning disable CS8618 // Non-nullable field must contain a non-null value when exiting constructor. Consider adding the 'required' modifier or declaring as nullable.
        public Wordle()
        {
            InitializeComponent();

            string filePath = @"words.txt";

            string[] words = File.ReadAllLines(filePath);

            int randIndex = new Random().Next(0, words.Length - 1);

            word = words[randIndex];

            wordArr = strToArray(word);

            //MessageBox.Show("The word is " + word);

            Show();

            var letters = new Dictionary<int, TextBox>
            {
                { 0, textBox1 },
                { 1, textBox2 },
                { 2, textBox3 },
                { 3, textBox4 },
                { 4, textBox5 },
                { 5, textBox6 },
                { 6, textBox7 },
                { 7, textBox8 },
                { 8, textBox9 },
                { 9, textBox10 },
                { 10, textBox11 },
                { 11, textBox12 },
                { 12, textBox13 },
                { 13, textBox14 },
                { 14, textBox15 },
                { 15, textBox16 },
                { 16, textBox17 },
                { 17, textBox18 },
                { 18, textBox19 },
                { 19, textBox20 },
                { 20, textBox21 },
                { 21, textBox22 },
                { 22, textBox23 },
                { 23, textBox24 },
                { 24, textBox25 },
                { 25, textBox26 },
                { 26, textBox27 },
                { 27, textBox28 },
                { 28, textBox29 },
                { 29, textBox30 },
            };

            for (int i = 0; i < letters.Count; i += 5)
            {
                string guess = Prompt.enterGuess("Enter your guess:", "Wordle", words);

                guessArr = strToArray(guess);

                for (int j = 0; j < 5; j++)
                {
                    letters[i + j].Text = guessArr[j];
                }

                for (int k = i; k < i + 5; k++)
                {
                    if (guess == word)
                    {
                        for (int l = 0; l < 5; l++)
                        {
                            letters[i + l].BackColor = Color.Green;
                        }
                        MessageBox.Show("You guessed the word!", "Wordle", MessageBoxButtons.OK, MessageBoxIcon.Asterisk);
                        Environment.Exit(0);
                    }

                    Dictionary<string, int> wordLetterCounts = new Dictionary<string, int>();
                    
                    for (int index = 0; index < wordArr.Length; index++)
                    {
                        if (wordLetterCounts.ContainsKey(wordArr[index]))
                            wordLetterCounts[wordArr[index]]++;
                        else
                            wordLetterCounts[wordArr[index]] = 1;
                    }

                    bool[] exactMatches = new bool[5];

                    for (int offset = 0; offset < 5; offset++)
                    {
                        int letterIndex = i + offset;
                        if (guessArr[offset] == wordArr[offset])
                        {
                            letters[letterIndex].BackColor = Color.Green;
                            exactMatches[offset] = true;
                            wordLetterCounts[guessArr[offset]]--;
                        }
                    }

                    for (int offset = 0; offset < 5; offset++)
                    {
                        int letterIndex = i + offset;
                        if (exactMatches[offset])
                        {
                            continue;
                        }

                        string guessChar = guessArr[offset];

                        if (wordLetterCounts.ContainsKey(guessChar) && wordLetterCounts[guessChar] > 0)
                        {
                            letters[letterIndex].BackColor = Color.Yellow;
                            wordLetterCounts[guessChar]--;
                        }
                        else
                        {
                            letters[letterIndex].BackColor = Color.Gray;
                        }
                    }
                }
            }

            MessageBox.Show("You didn't guess the word. The word was " + word + ". Better luck next time!", "Wordle", MessageBoxButtons.OK, MessageBoxIcon.Error);
            Environment.Exit(0);
        }

        public static int getIndex(string[] arr, string search)
        {
            for (int i = 0; i < arr.Length; i++)
            {
                if (arr[i] == search)
                {
                    return i;
                }
            }

            return -1;
        }

        public static bool contains(string item, string[] arr)
        {
            for (int i = 0; i < arr.Length; i++)
            {
                if (arr[i] != null && arr[i] == item)
                {
                    return true;
                }
            }

            return false;
        }

        public static string[] strToArray(string text)
        {
            string[] arr = new string[text.Length];

            for (int i = 0; i < arr.Length; i++)
            {
                arr[i] = text[i].ToString();
            }

            return arr;
        }
    }
}
