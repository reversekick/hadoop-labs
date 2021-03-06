hdfs-into lab

Lab Objectives:

1. Understand how to view the contents of HDFS
2. Learn how to perform basic file manipulation of your HDFS.
3. Add some files to a directory to prepare for the next exercise.


Quick Note:
'hadoop dfs'  or 'hdfs dfs' ?
for hadoop 1 and earlier versions  use 'hadoop dfs'
for hadoop 2 and later versions  use 'hdfs dfs'


== STEP 1) View Files in HDFS
for hadoop 1
    $ hadoop dfs -ls
for hadoop 2
    $ hdfs dfs -ls

If you do have some data, you will see something like this
    Found 1 items
    drwxr-xr-x   - hduser supergroup          0 2013-02-19 12:00 /user/um

Otherwise if you have no files you will see nothing.


== STEP 2) View files in root filestystem
for hadoop 1
    $ hadoop dfs -ls /
for hadopo 2
    $ hdfs dfs -ls /

Found 4 items
drwxr-xr-x   - hduser supergroup          0 2013-02-19 12:00 /app
drwxr-xr-x   - hduser supergroup          0 2013-02-09 21:37 /hbase
drwxr-xr-x   - hduser supergroup          0 2013-02-19 12:00 /tmp
drwxrwxr-x   - hduser supergroup          0 2013-02-19 11:37 /user


== STEP 3) create your home directory in HDFS
for hadoop 1
    $ hadoop dfs -mkdir <your name>
for hadoop 2
    $ hdfs dfs -mkdir <your name>

e.g $ hdfs dfs -mkdir tim

This directory will be located in /user/<login_name>/<your name>


== STEP 4) Copying files to HDFS
We will copy this README file into HDFS
    $  cd <project root dir>
    $  cd  hadoop-dev
for hadoop 1
    $  hadoop dfs -put hdfs-intro/README.txt  <your_name>/
    $  hadoop dfs -ls  <your_name>
for hadoop 2
    $  hdfs dfs -put hdfs-intro/README.txt  <your_name>/
    $  hdfs dfs -ls  <your_name>

output might look like:
rwxr-xr-x   - hduser supergroup          0 2013-02-19 12:00 README.txt

see the contents of this file from HDFS
for hadoop 1
    $  hadoop dfs -cat <yourname>/README.txt
for hadoop 2
    $  hdfs dfs -cat <yourname>/README.txt
This should print out the file README.txt


== STEP 5)
Now you can browser the HDFS

we can use the command line browser 'w3m'
    $  w3m http://localhost:50070

w3m key shortcuts
    - Shift B takes you back.
    - q   to quit


== STEP 6) Check that python or ruby is running on your system
    $ python --version
    $ ruby --version


== STEP 7) Run the python (or ruby) scripts provided to generate some output.
    $ cd <project root dir>
    $ mkdir logs
    $ cd logs
    $ python ../data/scripts/gen-billing-data.py
OR
    $ ruby ../data/scripts/gen-billing-data.rb

This will generate a bunch of log files in the current dir


== STEP 8) make a directory in hdfs
for hadoop 1
    $ hadoop dfs -mkdir <your name>/billing/in
for hadoop 2
    $ hdfs dfs -mkdir <your name>/billing/in


== STEP 9) Copy the log files generated by the script into hdfs
for hadoop 1
    $ hadoop dfs -put *.log <your name>/billing/in
for hadoop 2
    $ hdfs dfs -put *.log <your name>/billing/in

Also copy the sample data file also
    $  cd  <project root dir>
for hadoop 1
    $  hadoop dfs -put data/billing-data/sample.txt   <your name>/billing/in
verify the files are there
    $  hadoop dfs -ls <your name>/billing/in

for hadoop 2
    $  hdfs dfs -put data/billing-data/sample.txt   <your name>/billing/in
verify the files are there
    $  hdfs dfs -ls <your name>/billing/in



BONUS LAB:

== STEP 10) copy the files back to your local directory
for hadoop 1
    $  hadoop dfs -get <your name>/billing/in ./billing_data2
for hadoop 2
    $  hdfs dfs -get <your name>/billing/in ./billing_data2
