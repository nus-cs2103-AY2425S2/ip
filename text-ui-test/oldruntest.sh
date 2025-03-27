#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java ../src/main/java/exceptions/*.java ../src/main/java/categories/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input2.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin main.java.Yapper < input1.txt > ACTUAL1.TXT

# convert to UNIX format
cp EXPECTED1.TXT EXPECTED-UNIX1.TXT
#dos2unix ACTUAL.TXT EXPECTED2.TXT

# compare the output to the expected output
diff ACTUAL1.TXT EXPECTED-UNIX1.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi