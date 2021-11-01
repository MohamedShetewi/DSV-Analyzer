#Assumptions
1. If a number contains delimiters, then it must contain both digital and a thousand separators
2. That each column should follow the same format 
   (i.e., If a column is date, 
   then all the entries in that column should follow the same format as may be DD/MM/YYYY) 
3. If the column is written in one of the date format ,then it can be detected from the data of that column (i.e if the column is date, then the column must be 
   a day greater than 12 to be able to match with DD/MM/YYYY or MM/DD/YYYY).
#How to Run?
1. Open the Properties.config to configure the settings
2. Open Controller/Controller.java and just Run the main method.
