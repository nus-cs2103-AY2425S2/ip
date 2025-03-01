# Bebop User Guide

<img width="397" alt="Ui" src="https://github.com/user-attachments/assets/e12fa3c3-50a2-4c0b-9e72-7ede6b9a3e54" />

Hi, hi, hi! This is Bebop a text bot application for busy individuals who need to plan activities!

Here's a list of features
1. Add todo task
2. Add deadline task
3. Add event task
4. Delete tasks
5. Mark/Unmark task
6. Save task (**Tasks will only be saved in the tasklist if bye is called!!!**)
7. Find task by name
8. List all tasks

## Adding a todo

Format: ```todo EVENTNAME```

*Example*
```todo birthday```

*Output*
```
You got it buddy, get it done quick :D
[T] [ ] birthday

1 tasks to be done
```

## Adding a deadline

Format - ```deadline EVENTNAME /by EVENTDATE```
**EVENTDATE has to be in YYYY:MM:DD HH:mm format**

*Example*
```deadline bday /by 2000-10-12 10:30```

*Output*
```
Deadlines, shag ah bro ;(.
[D] [ ] bday (by: Oct-12-2000 10:30:00)

2 tasks to be done
```


## Adding a event
Format - ```event EVENTNAME /from STARTDATE /to ENDDATE```
**START/ENDDATE has to be in YYYY:MM:DD HH:mm format**

*Example*
```event vday /from 2025-02-14 10:20 /to 2025-02-16 10:40```

*Output*
```
Yippee, hope it's a fun event :D
[E] [ ] vday (from: Feb-14-2025 10:20:00 to: Feb-16-2025 10:40:00)

3 tasks to be done
```

## delete task

Format - ```delete INDEX```
**INDEX has to be 0 - Num of items in tasklist**

*Example*
```delete 3```

*Output*
```
Alright! Congrats on finishing your task:)
	[E] [ ] vday (from: Feb-14-2025 10:20:00 to: Feb-16-2025 10:40:00)
```

## mark/unmark task

Format - ```mark/unmark INDEX```
**INDEX has to be 0 - Num of items in tasklist**

*Example*
```mark 2```

*Output*
```
9 Good Job! I've marked the task as done!
```

## bye 

Format - ```bye```
**Tasks will only be saved in the tasklist if bye is called!!!**

*Example*
```
bye
```

## find task

Format - ```find TASK```

*Example*
```
find day
```

*Output*
```
[T] [ ] birthday

[D] [X] bday (by: Oct-12-2000 10:30:00)
```

## list task

Format - ```list```

*Example*
```
list
```

*Output*
```
1 [T] [ ] birthday
2 [D] [X] bday (by: Oct-12-2000 10:30:00)
3 [E] [X] vday (from: Feb-14-2025 10:30:00 to: Feb-15-2025 10:40:00)
```

