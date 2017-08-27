using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Numerics;
using System.IO;

namespace CryptoLib
{
    public class RSA : ICryptoAlgorithm
    {
        protected long p;
        protected long q;
        protected string data;
        protected long d;
        protected long n;

        public RSA(Stream p, Stream q)
        {
            StreamReader readP = new StreamReader(p);
            this.p = Convert.ToInt64(readP.ReadToEnd());

            StreamReader readQ = new StreamReader(q);
            this.q = Convert.ToInt64(readQ.ReadToEnd());
        }

        protected bool IsSimpleNumber(long n)
        {
            if (n < 2)
                return false;

            if (n == 2)
                return true;

            for (long i = 2; i < n; i++)
                if (n % i == 0)
                    return false;

            return true;
        }

        protected long CalculateD(long m)
        {
            long d = m - 1;

            for (long i = 2; i <= m; i++)
                if ((m % i == 0) && (d % i == 0))
                {
                    d--;
                    i = 1;
                }

            return d;
        }

        protected long CalculateE(long m)
        {
            long e = 10;

            while (true)
            {
                if ((e * d) % m == 1)
                    break;
                else
                    e++;
            }

            return e;
        }

        protected List<string> RSAEncode(long e, string data)
        {
            List<string> result = new List<string>();

            BigInteger bi;

            byte[] indices = Encoding.ASCII.GetBytes(data);
            for (int i = 0; i < data.Length; i++)
            {
                int index = indices[i];
                bi = new BigInteger(index);
                bi = BigInteger.Pow(bi, (int)e);

                BigInteger num = new BigInteger((int)n);

                bi = bi % num;

                result.Add(bi.ToString());
            }

            return result;
        }

        protected string RSADecode(List<string> input)
        {
            string result = "";

            BigInteger bi;

            foreach (string item in input)
            {
                bi = new BigInteger(Convert.ToDouble(item));
                bi = BigInteger.Pow(bi, (int)d);

                BigInteger num = new BigInteger((int)n);

                bi = bi % num;

                int index = Convert.ToInt32(bi.ToString());
                char c = (char)index;
                result += c.ToString();
            }

            return result;
        }

        public void Encrypt(Stream inp, Stream outp)
        {
            if (IsSimpleNumber(p) && IsSimpleNumber(q))
            {
                StreamReader reader = new StreamReader(inp);
                data = reader.ReadToEnd();

                n = p * q;
                long m = (p - 1) * (q - 1);
                d = CalculateD(m);
                long e = CalculateE(m);

                List<string> result = RSAEncode(e, data);

                string res = "";
                foreach (string item in result)
                {
                    res += item + " ";
                }

                res = res.TrimEnd(' ');

                byte[] byteArray = Encoding.ASCII.GetBytes(res);
                outp.Write(byteArray, 0, byteArray.Length);
                outp.Position = 0;

            }
            else
                throw new Exception("p или q - не простые числа!");

        }

        public void Decrypt(Stream inp, Stream outp)
        {
            List<string> input = new List<string>();

            string s;
            StreamReader reader = new StreamReader(inp);
            s = reader.ReadToEnd();

            input = s.Split(' ').ToList();

            string result = RSADecode(input);

            byte[] byteArray = Encoding.ASCII.GetBytes(result);
            outp.Write(byteArray, 0, byteArray.Length);
            outp.Position = 0;
        }
    }

}
