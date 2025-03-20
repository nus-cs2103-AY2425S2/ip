# Ujin project template

This is a chatbot for marking your activities.

## Starting process
Use ./gradlew run command to see the interface for the chatbot. 

## Messages you can write
You can add tasks to your tasklist with the commands:
* todo &lt;your task&gt;
* event &lt;your task&gt; /from &lt;time&gt; /to &lt;time&gt;
* deadline &lt;your task&gt; /by &lt;time&gt;

Notice that &lt;time&gt; has to be in format MM/DD. 

* **list** is for when you want to list your tasks. 
* **mark &lt;index&gt;** is for checking the task as done.
* **unmark &lt;index&gt;** is for unchecking the task as not done.
* **delete &lt;index&gt;** is for deleting the task.
* **find &lt;keyword&gt;** is for finding the tasks that has &lt;keyword&gt; in them. 

Note that &lt;index&gt; is 1-indexed number and has to be less thab or equal to the number of tasks in your tasklist.

Enjoy your app.

