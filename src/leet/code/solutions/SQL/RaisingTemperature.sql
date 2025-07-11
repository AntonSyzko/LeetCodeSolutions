/*
Given a Weather table, write a SQL query to find all dates' Ids with higher
temperature compared to its previous (yesterday's) dates.
+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|
|
|
1 |
2 |
3 |
2015-01-01 |
2015-01-02 |
2015-01-03 |
10 |
25 |
20 |
|
4 |
2015-01-04 |
30 |
+---------+------------------+------------------+
For example, return the following Ids for the above Weather table:
+----+
| Id |
+----+
| 2 |
| 4 |
+----+

*/



select w1.Id
    from Weather as w1, Weather as w2
    where datediff(w1.RecordDate, w2.RecordDate) = 1
      and w1.Temperature > w2.Temperature;