#!/usr/bin/env bash

# Function to run the test
run_test() {
    local input_file=$1
    local expected_file=$2
    local actual_file=$3

    # Delete output from previous run
    if [ -e "$actual_file" ]
    then
        rm "$actual_file"
    fi

    # Run the program, feed commands from input file and redirect the output to the ACTUAL.TXT
    java -classpath ../bin yapper.Yapper < "$input_file" > "$actual_file"

    # Convert to UNIX format
    cp "$expected_file" EXPECTED-UNIX.TXT

    # Compare the output to the expected output
    diff "$actual_file" EXPECTED-UNIX.TXT
    if [ $? -eq 0 ]
    then
        echo "Test result: PASSED ✅ "
    else
        echo "Test result: FAILED ❌ "
        exit 1
    fi
}

# Create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# Compile the code into the bin folder, terminates if error occurred
echo "......................................................NEW TEST......................................................"
if ! javac -cp ../src/main/java/yapper -Xlint:none -d ../bin $(find ../src/main/java/yapper -name "*.java")
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Reset data before the runs
echo "Resetting data files........................"
if [ -e "../data/YapperTasks.txt" ]
then
    rm "../data/YapperTasks.txt"
fi

# First run
echo "Running first test.........................."
run_test "input1.txt" "EXPECTED1.TXT" "ACTUAL1.TXT"

# Second run
echo "Running second test.........................."
run_test "input2.txt" "EXPECTED2.TXT" "ACTUAL2.TXT"

# Compare YapperTasks.txt with expected_datafile.txt
echo "Comparing YapperTasks.txt with expected_datafile.txt..."
if [ -e "../data/YapperTasks.txt" ] && [ -e "EXPECTED-DATAFILE.txt" ]
then
    diff "../data/YapperTasks.txt" "EXPECTED-DATAFILE.txt"
    if [ $? -eq 0 ]
    then
        echo "YapperTasks.txt matches expected_datafile.txt. ✅ "
    else
        echo "YapperTasks.txt does not match expected_datafile.txt. ❌ "
        exit 1
    fi
else
    echo "YapperTasks.txt or expected_datafile.txt not found. ❌ "
    exit 1
fi

echo "All tests completed."