# 🌟 MiniLang: A Custom Programming Language → Python Translator

MiniLang is a lightweight educational programming language designed for a university course project. It features:
- Simple, Python-like syntax
- No type declarations
- Support for variables, loops, conditionals, and string operations

This project parses MiniLang code, constructs an Abstract Syntax Tree (AST), and translates it to executable Python code.

---

## 📂 Project Structure

```
MiniLangProject/
├── src/
│   ├── ASTNode.java        # AST node definitions
│   ├── Parser.java         # MiniLang parser
│   ├── Tokenizer.java      # Tokenizer for source code
│   ├── Translator.java     # Driver: MiniLang to Python translator
│   └── Main.java           # Optional main runner
├── input.minilang          # MiniLang sample code
├── output.py               # Generated Python code
```

---

## 🚀 How to Run

### ✅ 1. Compile the program
```bash
javac src/*.java
```

### ▶️ 2. Run the Translator
```bash
java -cp src Translator
```

### 📄 3. View Output
```bash
cat output.py
python3 output.py
```
---

## 🔎 Example MiniLang Code (input.minilang)

```minilang
let name = "Alice"
let greeting = "Hello"
print greeting
if name == "Alice" then
    print "Welcome back!"
else
    print "Who are you?"
end
loop i from 1 to 3 do
    print "Looping!"
end
```

---

## 🔎 Expected Output

```minilang
Hello
Welcome back!
Looping!
Looping!
Looping!
```

---


## 📖 Appendix

### 6.1 Source File Summary

| File             | Description |
|------------------|-------------|
| `Tokenizer.java` | Splits MiniLang code into parseable tokens |
| `ASTNode.java`   | Defines AST node classes for each language construct |
| `Parser.java`    | Parses tokens and builds the AST |
| `Translator.java`| Converts AST into Python source code |
| `input.minilang` | Contains sample MiniLang code |
| `output.py`      | Generated Python output |

---

## 🛠 Tech Stack
- Java 17+
- Python 3.8+
- Terminal / CLI

---

## 👥 Authors
- Jay Italiya
- Uttam Bhavani
- Munaf Maryam
