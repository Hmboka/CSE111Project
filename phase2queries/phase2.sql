
--------------   1   --------------
--UPDATE hasread date
UPDATE hasread
SET hr_date = '2020-11-12'
WHERE hr_bookID = 77;

--------------   2   --------------
--select book based off of when it is read
SELECT hr_name, hr_date, ad_name
from hasread, authored
where hr_bookID = ad_bookID AND
      hr_date = '2020-11-12';


--------------   3   --------------
--INSERT INTO favBooks based on ID (will be dynamic later)
INSERT INTO favebooks
SELECT b_bookID, b_title, 4.9
FROM books
WHERE b_bookID = 29;

--------------   4   --------------
--remove books from favebooks
DELETE FROM favebooks
WHERE fb_bookID = 10


--------------   5   --------------
--(DO NOT RUN WILL FOREVER CHANGE DATA, ONLY FOR USER:
--update books tables by changing page count)
UPDATE books
set b_numpages = 0
where b_bookID = 1;


--------------   6   --------------
--show user entire selection of books
select b_title
from books;


--------------   7   --------------
--insert into fav author 
INSERT INTO faveauthors (fa_name, fa_myrating)
VALUES ('HabiTesting', 2.4);


--------------   8   --------------
--insert into hasread from books table  (forgot rating input, also best to use bookID instead of title, left title for demonstration purposes)
INSERT INTO hasread (hr_bookID, hr_name, hr_myrating)
SELECT b_bookID, b_title, 4.2 
FROM books
WHERE b_bookID = 22;

    --------------   9   --------------
    --sample to show the next query 
                    INSERT INTO hasread (hr_bookID, hr_name, hr_myrating)
                    SELECT b_bookID, b_title, 4.20
                    FROM books
                    WHERE b_title = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)'
                          and b_bookID = 1;

--------------   10   --------------
--Delete books from hasread 
DELETE FROM hasread
WHERE hr_name = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)'
      and hr_bookID = 1;


--------------   11   --------------
--retroactively add your own rating to books added to hasread 
UPDATE hasread
SET hr_myrating = 4.2
WHERE hr_bookID = 77;


--------------   12   --------------
--(TRY NOT TO RUN QUERY BC ONCE A BOOK IS DELETED WE MAY NOT GET IT BACK) delete books fom the main book able 
DELETE FROM books
WHERE b_bookID = 1;


--------------   13   --------------
--delete books based on the author 
DELETE FROM books
where ad_name = 'testingtesting123'


--------------   14   --------------
--find books and author written by blank author that start with "The" 
SELECT b_title, ad_name
FROM books, authored
WHERE b_title like 'The%' AND
      ad_bookID = b_bookID;


--------------   15   --------------
--find favorite books which are written by a specific author 
SELECT fb_title
FROM favebooks
WHERE fb_bookID IN (SELECT ad_bookID
        FROM authored
        WHERE ad_name like '%J.K. Rowling%');


--------------   16   --------------
--adding another fave author to test
INSERT INTO faveauthors(fa_name, fa_myrating)
VALUES('Random Author', 3.1);


--------------   17   --------------
--delete author from fave authors
DELETE FROM faveauthors
WHERE fa_name = 'Random Author';


--------------   18   --------------
--find books that were published after 2002 and are on your has read list (table)
SELECT hr_name 
FROM hasread
WHERE hr_bookID IN(SELECT p_bookID
        FROM publisher
        WHERE p_date >= '2002-01-01');


--------------   19   --------------
--update favbook rating
UPDATE favebooks
SET fb_myrating = '5.0'
WHERE fb_title = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)';


--------------   20   --------------
--what book has a rating of at least 4.0, written in english, and has at least 600 pages
SELECT b_title, b_numpages, b_langcode
FROM books
WHERE b_numpages >=600.0 AND 
b_langcode = 'eng' AND 
b_bookID IN(SELECT r_bookID
            FROM rating
            WHERE r_avgbookrating >= 4.0);


--------------   21   --------------
--find faveauthor(s) with highest rating
select avg(fa_myrating) as avgRating, fa_name as topAuthor
from faveauthors
where fa_myrating =  (select max(famr) from (select avg(fa_myrating) as famr
                                             from faveauthors
                                             group by fa_name
                                            ) as helper)
group by fa_name;


--------------   22   -------------- (uses 3 tables)
--find books in the has read that was published after 2000 and has a rating of at least 3
SELECT hr_name
FROM hasread
WHERE hr_bookID IN(SELECT p_date
                  FROM publisher
                  WHERE p_date >= '2000-01-01' AND p_bookID IN(SELECT r_bookID 
                                                              FROM rating
                                                              WHERE r_avgbookrating >= 3));


--------------   23   -------------- (uses 3 tables)
--find authors of books that were published by scholastic inc with a rating of at least 3
SELECT ad_name
FROM authored
WHERE ad_bookID IN(SELECT p_bookID
                  FROM publisher
                  WHERE p_name = 'Scholastic' AND p_bookID IN(SELECT r_bookID 
                                                              FROM rating
                                                              WHERE r_avgbookrating >= 3))

GROUP BY ad_name;

--------------   24   -------------- (uses 4 tables)
--find books with rating of at least 4 that where published by William Morrow Paperbacks and the authors had rating of at least 3
SELECT r_title
FROM rating
WHERE r_avgbookrating >= 4 AND  r_bookID IN(SELECT p_bookID
                                         FROM publisher
                                         WHERE p_name = 'William Morrow Paperbacks' AND p_bookID IN(SELECT ad_bookID 
                                                                                                  FROM authored
                                                                                                  WHERE ad_name IN(SELECT a_name
                                                                                                                  FROM authors
                                                                                                                  WHERE a_authrating >=3)));

