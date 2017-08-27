using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.IO;
using System.Windows.Forms;

namespace CryptoTools
{
    public partial class Form1 : Form
    {
        CryptoLib.RSASign rsaSign;

        public Form1()
        {
            InitializeComponent();
        }

        private void buttonEncryptSign_Click(object sender, EventArgs e)
        {
            if (textBoxSourceSign.Text.Length > 0 && textBoxPSign.Text.Length > 0 && textBoxQSign.Text.Length > 0)
            {
                string text = textBoxSourceSign.Text;
                string p = textBoxPSign.Text;
                string q = textBoxQSign.Text;

                byte[] textByteArray = Encoding.ASCII.GetBytes(text);
                MemoryStream textStream = new MemoryStream(textByteArray);
                byte[] pByteArray = Encoding.ASCII.GetBytes(p);
                MemoryStream pStream = new MemoryStream(pByteArray);
                byte[] qByteArray = Encoding.ASCII.GetBytes(q);
                MemoryStream qStream = new MemoryStream(qByteArray);
                Stream output = new MemoryStream();

                rsaSign = new CryptoLib.RSASign(pStream, qStream);
                rsaSign.Signature(textStream, output);

                StreamReader reader = new StreamReader(output);
                String encrypted = reader.ReadToEnd();

                if (encrypted != null)
                {
                    textBoxSourceSign.Text = encrypted;
                }
            }
        }

        private void buttonDecryptSign_Click(object sender, EventArgs e)
        {
            if (textBoxSourceSign.Text.Length > 0 && rsaSign != null)
            {
                string text = textBoxSourceSign.Text;

                byte[] textByteArray = Encoding.ASCII.GetBytes(text);
                MemoryStream textStream = new MemoryStream(textByteArray);
                Stream output = new MemoryStream();

                bool valid = rsaSign.CheckSignature(textStream);

                if (valid)
                {
                    MessageBox.Show("Signature is trustworthy!");
                }
                else 
                {
                    MessageBox.Show("Signature is not trustworthy!");
                }

            }
        }
    }
}
