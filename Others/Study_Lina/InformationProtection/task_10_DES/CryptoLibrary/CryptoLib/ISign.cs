using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace CryptoLib
{
    public interface ISign
    {
        void Signature(Stream input, Stream output);
        bool CheckSignature(Stream input);
    }
}
