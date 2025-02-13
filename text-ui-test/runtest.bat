@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist data\zoe.txt del data\zoe.txt

REM compile the code into the bin folder
javac -cp "..\lib\gson-2.11.0.jar;..\lib\lombok-1.18.36.jar;..\src\main\java" -Xlint:unchecked -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath "..\lib\gson-2.11.0.jar;..\lib\lombok-1.18.36.jar;..\bin" Zoe < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
