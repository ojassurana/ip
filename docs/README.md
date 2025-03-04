# Mahaveer Chatbot User Guide

![Mahaveer Chatbot](https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Mahavir.jpg/640px-Mahavir.jpg)

Mahaveer is a console-based task management chatbot inspired by Lord Mahaveer. It helps you manage tasks efficiently using simple commands. This guide covers its features and usage.

---

## Features & Commands

### Adding Deadlines
**Command:** `deadline <task> /by <time>`

**Example:**
```plaintext
deadline Submit assignment /by tonight
```
**Output:**
```plaintext
[D][ ] Submit assignment (by: tonight)
```

---

### Adding Todos
**Command:** `todo <task>`

**Example:**
```plaintext
todo Walk the dog
```
**Output:**
```plaintext
[T][ ] Walk the dog
```

---

### Adding Events
**Command:** `event <task> /from <start> /to <end>`

**Example:**
```plaintext
event Conference /from Monday /to Wednesday
```
**Output:**
```plaintext
[E][ ] Conference (from: Monday to: Wednesday)
```

---

### Listing Tasks
**Command:** `list`

**Example:**
```plaintext
list
```
**Output:**
```plaintext
Here are the tasks in your list:
[T][ ] Walk the dog
[D][ ] Submit assignment (by: tonight)
```

---

### Marking Tasks
**Command:** `mark <task number>`

**Example:**
```plaintext
mark 1
```
**Output:**
```plaintext
Nice! I've marked this task as done: [T][X] Walk the dog
```

---

### Unmarking Tasks
**Command:** `unmark <task number>`

**Example:**
```plaintext
unmark 1
```
**Output:**
```plaintext
OK, I've marked this task as not done yet: [T][ ] Walk the dog
```

---

### Deleting Tasks
**Command:** `delete <task number>`

**Example:**
```plaintext
delete 1
```
**Output:**
```plaintext
The specified task is removed from the list.
```

---

### Finding Tasks
**Command:** `find <keyword>`

**Example:**
```plaintext
find dog
```
**Output:**
```plaintext
Here are the matching tasks in your list:
[T][ ] Walk the dog
```

---

### Exiting the Application
**Command:** `bye`

**Example:**
```plaintext
bye
```
**Output:**
```plaintext
Jai Jinendra! Till we meet next time :)
```

---

## Author

![Author's Photo](https://media.licdn.com/dms/image/v2/D5603AQHQCLMb5J__bg/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1701928433892?e=1746662400&v=beta&t=QmLgZUST73G6fMx4nuXmhAwXcZqReaqtBaUGjOQwPCs)

**Ojas Surana**  
Email: [code@ojassurana.com](mailto:code@ojassurana.com)  
Telegram: [@ojasx](https://t.me/ojasx)  
For [CS2113 - Software Engineering (OOP)](https://nusmods.com/courses/CS2113/software-engineering-object-oriented-programming)

---

## Acknowledgments
- [Mahaveer Image](https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Mahavir.jpg/640px-Mahavir.jpg) from Wikipedia.
- Inspired by the Duke chatbot concept from [CS2113](https://github.com/nus-cs2113-AY2425S2/ip).

---

Enjoy using Mahaveer Chatbot! For questions or feedback, feel free to reach out.
