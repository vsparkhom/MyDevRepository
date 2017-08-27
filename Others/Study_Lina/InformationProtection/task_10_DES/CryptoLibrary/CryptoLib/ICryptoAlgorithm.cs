using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;


namespace CryptoLib
{
    public interface ICryptoAlgorithm
    {
        void Encrypt(Stream inp, Stream outp);
        void Decrypt(Stream inp, Stream outp);
    }
}
