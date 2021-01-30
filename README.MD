Task Description: 

There are two .csv files with information from client's CRM: items.csv and customer.csv. 
Each file contains:
-	the header with the column description. 
-	the body/content. 

All the values are split by delimiters (different in each file). 
LastPurchases in customers.csv can contain an array (with " ") or one element (no " ").
PhoneNumber might be empty (no value, just delimiter). DateOfLastPurchase has a format of mm/dd/yyyy. 

Tasks:
1.	Read files and create a collection of Java objects based on that data.
2.	Persist all the data in DB (DB should be created based on entities in csv files).
3.	Create reports from Java:
- what goods are the most popular among women.
- the most popular goods during a particular weekend (passed in as a param).
4.	Alter the DB using plain SQL:
- create new columns: PrimaryItem, CandidateToRemove.
- fetch three the most and least popular goods and mark them correspondingly in the table.
5.	Write all the marked goods (see the bullet point above) in two different files: 
- primaryItems.csv with the most popular goods; 
- candidateToRemove.csv - the least popular good. 
All the data should be in csv format with ; used as a delimiter.
 
