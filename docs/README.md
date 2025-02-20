# Prophet User Guide

---

![User Interface Demo](https://tim0tay.github.io/ip/Ui.png)

Prophet frees your mind of having to remember things you need to do. It's:
- text-based
- easy to understand
- SUPER FAST to use

All you need to do is:
1. download Java 17; either the [Oracle version](https://www.oracle.com/java/technologies/downloads/#java17) or 
another alternative
   2. for Mac users, please consider using the Azul JDK 17 version;
   the tutorial is [here](https://se-education.org/guides/tutorials/javaInstallationMac.html)
2. download the Prophet JAR file from [here](https://github.com/tim0tay/ip/releases/tag/A-Release)
3. either double-click it or run `java -jar prophet.jar` in your terminal
4. add your tasks.
5. let it manage your tasks for you 😉

And it is **free**!

---
## Adding Todo Tasks

To add todo tasks, format your input as such:

`todo description`

>Example: `todo finish homework`

Prophet will then add your todo task to your list of tasks:
```
Task added to list: T | [ ] finish homework
```

---
## Adding Deadlines

To add deadlines, format your input as such:

`deadline description /by when`
> 
> *take note of the format for when: YYYY-MM-DDTHH:MM
> 
> Example: `deadline run /by 2025-02-19T23:59`

Prophet will then add your deadline to your list of tasks:

```
Task added to list: D | [ ] run by: Feb 19 2025 2359
```
---
## Adding Events

To add events, format your input as such:

`event description /from when /to when`
>
> *take note of the format for when: YYYY-MM-DDTHH:MM
>
> Example: `event sports day /from 2025-01-10T00:00 /to 2025-01-11T23:59`

Prophet will then add your event to your list of tasks:

```
Task added to list: E | [ ] sports day from: Jan 10 2025 0000 to: Jan 11 2025 2359
```
---
## Listing All Tasks

To list all current tasks, format your input as such:

`list`

Prophet will then list all your tasks:

```
Let's see what you have on your plate:
1. T | [ ] finish homework
2. D | [ ] run by: Feb 19 2025 2359
3. E | [ ] sports day from: Jan 10 2025 0000 to: Jan 11 2025 2359
```
---
## Marking/Unmarking Tasks as Done

To mark tasks as done, format your input as such:

`mark task number`

> Example: `mark 1`

Prophet will then mark the first task as done:
```
Marked done! Good job.
T | [X] finish homework
```

Similarly, to mark tasks as not done, format your input as such:

`unmark task number`

> Example: `unmark 1`
 
Prophet will then mark the first task as not done:
```
Marked not done! Jiayous...
T | [ ] finish homework
```
---
## Deleting Tasks

To delete a task, format your input as such:

`delete task number`

> Example: `delete 1`

Prophet will then delete the first task:
```
The following task was deleted:
T | [ ] finish homework
```
---
## Finding Tasks By Name (or part thereof)

To find a task by name, format your input as such:

`find task name`

> Example: `find run`

Prophet will then find all tasks that contain the task name you've keyed in:
```
Here are the tasks that match: run
D | [ ] run by: Feb 19 2025 2359
```
---
## Viewing Your Schedule on a Particular Day

To view your tasks for any particular day, format your input as such:

`schedule date`

> *take note of the format for date: YYYY-MM-DD
> Example: `schedule 2025-02-19`

Prophet will then find all tasks that fall on the date you've keyed in:
```
Here are the tasks that are on : 2025-02-19
D | [ ] run by: Feb 19 2025 2359
```