@ECHO OFF

REM create bin directory if it doesn't exist
if not exist "..\bin" mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp "C:\NUS COURSES\Y2S2\CS2103 Software Engineering\ip\src\main\java" -Xlint:none -d "..\bin" "C:\NUS COURSES\Y2S2\CS2103 Software Engineering\ip\src\main\java\Partillay.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath "..\bin" Partillay < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
