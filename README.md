# optimalSolutionsTask
Task:
Customer X just informed us that we need to churn out a code enhancement ASAP for a new project.  Here is what they need:

 
1. We need a Java application that will consume a CSV file, parse the data and insert to a SQLite In-Memory database.  

a. Table X has 10 columns A, B, C, D, E, F, G, H, I, J which correspond with the CSV file column header names.

b. Include all DDL in submitted repository

c. Create your own SQLite DB

 
2. The data sets can be extremely large so be sure the processing is optimized with efficiency in mind.  

 
3. Each record needs to be verified to contain the right number of data elements to match the columns.  

a. Records that do not match the column count must be written to the bad-data-<timestamp>.csv file

b. Elements with commas will be double quoted

 
4. At the end of the process write statistics to a log file

a. # of records received

b. # of records successful

c. # of records failed

Tools I used:
-> Maven build for dependencies and build
-> openCsv from Apache 2.0
 
My approaches to solve this task:
1. establish a connection to SQLite database
  -> create a table 
2. implement filters that read CSV file (to find matching/non-matching records)
  -> Filters.class
3. load matching records to db 
  -> I used for this JDBC  batch statement that allows a better performance for bigger data insertions
4. find element in records that contain commas and double quot them  
    4.a -> write the to  bad-data-<timestamp>.csv file
    4.b -> if we don't have any bad record this file won't be created 
5. Log  the required info with logging.Logger from java.util package