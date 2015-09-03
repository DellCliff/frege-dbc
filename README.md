# frege-dbc

This library aims to supply useful functions to deal with JDBC in Frege.
The only function right now is to retrieve results from ResultSet.
fetchRow does not throw exceptions, almost all native JDBC might though, so use with care.

```
module Main where

import frege_dbc.JDBC
import frege_dbc.FregeDBC

drop_table_sql = "DROP TABLE IF EXISTS  people"

create_table_sql = "CREATE TABLE people (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, dob TEXT NOT NULL);"

insert_sql = "INSERT INTO people (name, dob) VALUES (\"user 1\", \"2015-08-22T05:31:54\"), (\"user 2\", \"1955-01-10T05:31:54\"), (\"user 3\", \"1985-03-7T05:31:54\");"

query_people_sql = "SELECT * FROM people;"

instance Show SqlValue where
    show (SqlString s) = "String: " ++ s
    show (SqlInt s) = "Int: " ++ show s
    show (SqlLong s) = "Long: " ++ show s
    show t = "Some value"

main = do
    println "START"
    conn <- DriverManager.getConnection "jdbc:sqlite:sample.db"
    stmt <- Connection.createStatement conn

    drop_res <- Statement.execute stmt drop_table_sql
    create_res <- Statement.execute stmt create_table_sql
    insert_res <- Statement.execute stmt insert_sql
    -- true if the first result is a ResultSet object;
    -- false if the first result is an update count or there is no result
    print "drop_res: "; println drop_res
    print "create_res: "; println create_res
    print "insert_res: "; println insert_res

    people_res <- Statement.executeQuery stmt query_people_sql
    parsed <- sequence $ [fetchRow people_res | x <- [0..3]] -- more than exist

    println parsed

    println "END"
```
