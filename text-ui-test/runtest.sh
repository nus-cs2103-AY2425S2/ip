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
if ! javac -cp /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/src/main/java -Xlint:none -d ../bin /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/src/main/java/*.java

then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/src/main/bin Nana < /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/text-ui-test/input.txt > /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/text-ui-test/ACTUAL.TXT

# convert to UNIX format
cp /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/text-ui-test/EXPECTED.TXT /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/text-ui-test/EXPECTED-UNIX.TXT
dos2unix /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/text-ui-test/ACTUAL.TXT /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/text-ui-test/EXPECTED-UNIX.TXT

# compare the output to the expected output
diff /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/text-ui-test/ACTUAL.TXT /Users/gonghaozhen/Documents/Y2S2/CS2103T/ip/text-ui-test/EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi