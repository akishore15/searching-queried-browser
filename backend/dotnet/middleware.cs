using System;
using System.Diagnostics;
using System.IO;

namespace Middleware
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter search engine (google, bing, yandex, duckduckgo, yahoo, brave):");
            string engine = Console.ReadLine();
            Console.WriteLine("Enter search query:");
            string query = Console.ReadLine();

            string result = RunRustSearch(engine, query);
            Console.WriteLine(result);
        }

        static string RunRustSearch(string engine, string query)
        {
            var process = new Process
            {
                StartInfo = new ProcessStartInfo
                {
                    FileName = "path/to/rust_executable",
                    Arguments = $"{engine} \"{query}\"",
                    RedirectStandardOutput = true,
                    UseShellExecute = false,
                    CreateNoWindow = true,
                }
            };
            process.Start();
            using (StreamReader reader = process.StandardOutput)
            {
                return reader.ReadToEnd();
            }
        }
    }
}
