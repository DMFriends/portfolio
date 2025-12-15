using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Wordle
{
    public static class Prompt
    {
        public static string enterGuess(string text, string caption, string[] words)
        {
            while (true)
            {
                bool error = false;

                Form prompt = new Form()
                {
                    Width = 500,
                    Height = 175,
                    FormBorderStyle = FormBorderStyle.FixedDialog,
                    Text = caption,
                    StartPosition = FormStartPosition.CenterScreen
                };
                Label textLabel = new Label() { Left = 50, Top = 20, Text = text };
                TextBox textBox = new TextBox() { Left = 50, Top = 50, Width = 400 };
                textBox.MaxLength = 5;
                Button confirmation = new Button() { Text = "Enter", Left = 350, Width = 100, Top = 90, DialogResult = DialogResult.OK };
                Button endGame = new Button() { Text = "End Game", Left = 250, Width = 100, Top = 90, DialogResult = DialogResult.Abort };
                prompt.Controls.Add(textBox);
                prompt.Controls.Add(confirmation);
                prompt.Controls.Add(endGame);
                prompt.Controls.Add(textLabel);
                prompt.AcceptButton = confirmation;
                prompt.ShowDialog();

                if (DialogResult.Cancel == prompt.DialogResult || (DialogResult.OK == prompt.DialogResult && textBox.Text == ""))
                {
                    MessageBox.Show("Please enter a guess.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    continue;
                }
                else if(DialogResult.Abort == prompt.DialogResult)
                {
                    Environment.Exit(0);
                }
                else
                {
                    for (int i = 0; i < words.Length; i++)
                    {
                        if (words[i] == textBox.Text)
                        {
                            error = false;
                            break;
                        }
                        else
                        {
                            error = true;
                        }
                    }
                }

                if (error)
                {
                    MessageBox.Show("Invalid guess or not a valid word. Please try again.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    prompt.ResetText();
                    continue;
                }
                else
                {
                    return textBox.Text;
                }
            }
        }
    }
}
