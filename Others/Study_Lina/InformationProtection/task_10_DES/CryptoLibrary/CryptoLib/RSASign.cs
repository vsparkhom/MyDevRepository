using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace CryptoLib
{
    public class RSASign : RSA, ISign
    {
        private string hash;
        private string sign;

        public RSASign(Stream p, Stream q) : base(p, q) { } 

        public void Signature(Stream inp, Stream outp) 
        {
            if (IsSimpleNumber(p) && IsSimpleNumber(q))
            {
                StreamReader reader = new StreamReader(inp);
                data = reader.ReadToEnd();

                n = p * q;
                long m = (p - 1) * (q - 1);
                d = CalculateD(m);
                long e = CalculateE(m);

                MD5 md5 = new MD5();

                byte[] dataArray = Encoding.ASCII.GetBytes(data);
                Stream dataStream = new MemoryStream(dataArray);
                Stream hashStream = new MemoryStream();

                md5.Hash(dataStream, hashStream);

                byte[] buf = new byte[hashStream.Length];
                hashStream.Read(buf, 0, (int)hashStream.Length);

                hash = System.Text.Encoding.ASCII.GetString(buf);

                List<string> signRes = RSAEncode(e, hash);
               
                sign = "";
                foreach (string item in signRes)
                {
                    sign += item +" ";
                }
                sign = sign.TrimEnd(' ');

                byte[] byteArray = Encoding.ASCII.GetBytes(sign);
                outp.Write(byteArray, 0, byteArray.Length);
                outp.Position = 0;

            }
            else
                throw new Exception("p или q - не простые числа!");
 
        }

        public bool CheckSignature(Stream signStream) 
        {
            StreamReader reader = new StreamReader(signStream);
            string recieveSign = reader.ReadToEnd();

            long m = (p - 1) * (q - 1);
            long e = CalculateE(m);

            List<string> signList = recieveSign.Split(' ').ToList();
            string signHash = RSADecode(signList);

            MD5 md5 = new MD5();

            byte[] dataArray = Encoding.ASCII.GetBytes(data);
            Stream dataStream = new MemoryStream(dataArray);
            Stream hashStream = new MemoryStream();

            md5.Hash(dataStream, hashStream);

            byte[] buf = new byte[hashStream.Length];
            hashStream.Read(buf, 0, (int)hashStream.Length);

            string recieveDataHash = System.Text.Encoding.ASCII.GetString(buf);

            if (signHash.CompareTo(recieveDataHash) == 0) 
            {
                return true;
            }

            return false;
        }
    }
}
