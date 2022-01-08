# DSV-Analyzer

This is a multi-threaded tool for analyzing files with [DSV File Format](https://en.wikipedia.org/wiki/Delimiter-separated_values).
For each found file a format and structure is guessed:
- which delimiter is used (comma, tab and semicolons are possible),
- which digital (dot or comma) and thousand (dot, comma or space) separators are used for numbers,
- which date format is used (DD/MM/YYYY, MM/DD/YYYY, YYYY/MM/DD, DD.MM.YYYY, MM.DD.YYYY, YYYY.MM.DD are possible),
- which are data types for columns (number, date, string could be used).

## Assumptions
1. If a number contains delimiters, then it must contain both digital and a thousand separators
2. That each column should follow the same format 
   (i.e., If a column is date, 
   then all the entries in that column should follow the same format as may be DD/MM/YYYY) 
3. If the column is written in one of the date format ,then it can be detected from the data of that column (i.e if the column is date, then the column must be 
   a day greater than 12 to be able to match with DD/MM/YYYY or MM/DD/YYYY).
## How to Run?
1. Open the Properties.config to configure the settings
2. Open Controller/Controller.java and just Run the main method.
