# Ninon User Guide

![image of Ui](https://github.com/CHI-ME/ip/blob/master/docs/Ui.png)

With powerful task tracking features, Ninon provides a smarter way to work, plan, and achieve your goals today! 🚀

# Feature List

## Adding todos

**Purpose:** 

Allows users to add todo task into the list.

---

**Command Format:**

```
todo [DESCRIPTION]
```

---

**Example Commands:**

```bash
todo Assignment 1
todo Group Meeting
```

---

**Parameters:**

1. **`DESCRIPTION`**
    - Brief description of the todo

---

**Outputs:**

- **Success:**
    - Displays the todo added
    - Displays the number of tasks in the list
    - UI updates to show the commands
        
    - Example output:
        
        ```
        Got it. I've added this task: 
        [T][ ] Assignment 1
        Now you have 3 tasks in the list.
        ```
        
- **Error Messages:**
    - `OOPS!!! The description of a todo cannot be empty.` (If `[DESCRIPTION]` is missing)


## Adding deadlines

**Purpose:** 

Allows users to add deadline task into the list.

---

**Command Format:**

```
deadline [DESCRIPTION] /by [DATE]
```

---

**Example Commands:**

```bash
deadline Assignment 1 /by 2025-02-21
deadline Group Meeting /by 2025-03-29
```

---

**Parameters:**

1. **`DESCRIPTION`**
    - Brief description of the deadline
2. **`DATE`**
    - Must follow the format `yyyy-mm-dd`, (e.g.,`2025-02-21`)

---

**Outputs:**

- **Success:**
    - Displays the deadline added
    - Displays the number of tasks in the list
    - UI updates to show the commands
        
    - Example output:
        
        ```
        Got it. I've added this task:
        [D][ ] Assignment 1  (by: 2 21 2025)
        Now you have 2 tasks in the list.
        ```
        
- **Error Messages:**
    - `OOPS!!! The description of a deadline cannot be empty.` (If `[DESCRIPTION]` is missing)
    - `OOPS!!! The by of a deadline cannot be empty.` (If `[DATE]` is missing)
    - `date input format should be yyyy-mm-dd or date out of range` (If `[DATE]` is invalid)


## Adding events

**Purpose:** 

Allows users to add event task into the list.

---

**Command Format:**

```
event [DESCRIPTION] /from [DATE] /to [DATE]
```

---

**Example Commands:**

```bash
event Assignment 1 /from 2025-02-15 /to 2025-02-21
event AY2024-2025 sem 2 /from 2025-01-13 /to 2025-05-10
```

---

**Parameters:**

1. **`DESCRIPTION`**
    - Brief description of the event
2. **`DATE`**
    - Must follow the format `yyyy-mm-dd`, (e.g.,`2025-02-21`)

---

**Outputs:**

- **Success:**
    - Displays the event added
    - Displays the number of tasks in the list
    - UI updates to show the commands
        
    - Example output:
        
        ```
        Got it. I've added this task:
        [E][ ] Assignment 1  (from: 2 15 2025 to: 2 21 2025)
        Now you have 5 tasks in the list.
        ```
        
- **Error Messages:**
    - `OOPS!!! The description of a event cannot be empty.` (If `[DESCRIPTION]` is missing)
    - `OOPS!!! The from of a event cannot be empty.` (If `[DATE]` after `/from` is missing)
    - `OOPS!!! The to of a event cannot be empty.` (If `[DATE]` after `/to` is missing)
    - `date input format should be yyyy-mm-dd or date out of range` (If `[DATE]` is invalid)


## Adding do-afters

**Purpose:** 

Allows users to add task that needed to done after specific task or date into the list.

---

**Command Format:**

```
doafter [DESCRIPTION] /by date [DATE]
doafter [DESCRIPTION] /by [DESCRIPTION]
```

---

**Example Commands:**

```bash
doafter Assignment 1 /by date 2025-02-15
doafter Assignment 2 /by Assignment 1
```

---

**Parameters:**

1. **`DESCRIPTION`**
    - Brief description of the do (and the after)
2. **`DATE`**
    - Must follow the format `yyyy-mm-dd`, (e.g.,`2025-02-21`)

---

**Outputs:**

- **Success:**
    - Displays the do-after added
    - Displays the number of tasks in the list
    - UI updates to show the commands
        
    - Example output:
        
        ```
        Got it. I've added this task: 
        [A][ ] Assignment 1  (by: 2 15 2025)
        Now you have 7 tasks in the list.
        ```
        ```
        Got it. I've added this task: 
        [A][ ] Assignment 2  (by: [ ] Assignment 1)
        Now you have 8 tasks in the list.
        ```
        
- **Error Messages:**
    - `OOPS!!! The description of a do cannot be empty.` (If first `[DESCRIPTION]` is missing)
    - `OOPS!!! The description of an after cannot be empty.` (If `[DATE]` or `[DESCRIPTION]` after `/by` is missing)
    - `date input format should be yyyy-mm-dd or date out of range` (If `[DATE]` is invalid)

## Show List of tasks

**Purpose:** 

Allows users to view the task list.

---

**Command Format:**

```
list
```

---


**Outputs:**

- **Success:**
    - Displays the entire task list
    - UI updates to show the commands
        
    - Example output:
        
        ```
        Here are the tasks in your list:
        1.[D][X] Assignment1 (by: 2 21 2025)
        2.[T][ ] Assignment1
        3.[E][ ] AY2024-2025sem2 (from: 1 13 2025 to: 5 10 2025)
        4.[A][ ] Assignment2 (by: [ ] Assignment1)
        ```


## Mark/Unmark tasks

**Purpose:** 

Allows users to mark/unmark tasks in list as done/not done.

---

**Command Format:**

```
mark [INDEX]
unmark [INDEX]
```

---

**Example Commands:**

```bash
mark 1
unmark 2
```

---

**Parameters:**

1. **`INDEX`**
    - Index of the task to be marked/unmarked in the list, obtained by `list` method
---

**Outputs:**

- **Success:**
    - Displays the task status after marked/unmarked
    - UI updates to show the commands
        
    - Example output:
        
        ```
        Nice! I've marked this task as done:
        [D][X] Assignment1 (by: 2 21 2025)
        ```
        ```
        Nice! I've marked this task as not done yet:
        [E][ ] AY2024-2025sem2 (from: 1 13 2025 to: 5 10 2025)
        ```
        
- **Error Messages:**
    - `index is missing` by java assertion(If `[INDEX]` is missing)


## Find tasks

**Purpose:** 

Allows users to find tasks by providing substring of the task.

---

**Command Format:**

```
find [DESCRIPTION]
```

---

**Example Commands:**

```bash
find assignment 1
find 2 21
```

---

**Parameters:**

1. **`DESCRIPTION`**
    - Substring of the task to be found
    - Case sensitive
---

**Outputs:**

- **Success:**
    - Displays all the matching tasks in the list
    - UI updates to show the commands
        
    - Example output:
        
        ```
        Here are the matching tasks in your list:
        [D][X] Assignment1 (by: 2 21 2025)
        [T][ ] Assignment1
        [A][ ] Assignment2 (by: [ ] Assignment1)
        ```
        ```
        Here are the matching tasks in your list:
        [D][X] Assignment1 (by: 2 21 2025)
        ```
        
- **Error Messages:**
    - `description is missing` by java assertion(If `[DESCRIPTION]` is missing)
 

## Delete tasks

**Purpose:** 

Allows users to delete tasks by index of the task in the list.

---

**Command Format:**

```
delete [INDEX]
```

---

**Example Commands:**

```bash
delete 1
```

---

**Parameters:**

1. **`INDEX`**
    - Index of the task to be removed from the list
---

**Outputs:**

- **Success:**
    - Displays the removed task
    - Displays remaining number of tasks in the list
    - UI updates to show the commands
        
    - Example output:
        
        ```
        Noted. I've removed this task:
        [E][ ] Assignment1 (from: 2�� 15 2025 to: 2�� 21 2025)
        Now you have 4 tasks in the list.
        ```
        
- **Error Messages:**
    - `index is missing` by java assertion(If `[INDEX]` is missing)


## AI-generated content in project
- User.png by DALL·E
- Ninon.png by DALL·E
- most javadocs comments by ChatGPT
