
--------------   1   --------------
--INSERT INTO favBooks based on ID (will be dynamic later)
INSERT INTO favebooks
SELECT b_bookID, b_title, 4.9
FROM books
WHERE b_bookID = 29;

--------------   2   --------------
--remove books from favebooks
DELETE FROM favebooks
WHERE fb_bookID = 10


--------------   3   --------------
--(DO NOT RUN WILL FOREVER CHANGE DATA, ONLY FOR USER:
--update books tables by changing page count)
UPDATE books
set b_numpages = 0
where b_bookID = 1;


--------------   3   --------------
--show user entire selection of books
select b_title
from books;


--------------   4   --------------
--insert into fav author 
INSERT INTO faveauthors (fa_name, fa_myrating)
VALUES ('HabiTesting', 2.4);


--------------   5   --------------
--insert into hasread from books table  (forgot rating input, also best to use bookID instead of title, left title for demonstration purposes)
INSERT INTO hasread (hr_bookID, hr_name, hr_myrating)
SELECT b_bookID, b_title, 4.2 
FROM books
WHERE b_bookID = 22;

    --------------   6   --------------
    --sample to show the next query 
                    INSERT INTO hasread (hr_bookID, hr_name, hr_myrating)
                    SELECT b_bookID, b_title, 4.20
                    FROM books
                    WHERE b_title = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)'
                          and b_bookID = 1;

--------------   7   --------------
--Delete books from hasread 
DELETE FROM hasread
WHERE hr_name = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)'
      and hr_bookID = 1;


--------------   8   --------------
--retroactively add your own rating to books added to hasread 
UPDATE hasread
SET hr_myrating = 4.2
WHERE hr_bookID = 77;


--------------   9   --------------
--(TRY NOT TO RUN QUERY BC ONCE A BOOK IS DELETED WE MAY NOT GET IT BACK) delete books fom the main book able 
DELETE FROM books
WHERE b_bookID = 1;


--------------   10   --------------
--delete books based on the author 
DELETE FROM books
where ad_name = 'testingtesting123'


--------------   11   --------------
--find books and author written by blank author that start with "The" 
SELECT b_title, ad_name
FROM books, authored
WHERE b_title like 'The%' AND
      ad_bookID = b_bookID;


--------------   12   --------------
--find favorite books which are written by a specific author 
SELECT fb_title
FROM favebooks
WHERE fb_bookID IN (SELECT ad_bookID
        FROM authored
        WHERE ad_name like '%J.K. Rowling%');


--------------   13   --------------
--adding another fave author to test
INSERT INTO faveauthors(fa_name, fa_myrating)
VALUES('Random Author', 3.1);


--------------   14   --------------
--delete author from fave authors
DELETE FROM faveauthors
WHERE fa_name = 'Random Author';


--------------   15   --------------
--find books that were published after 2002 and are on your has read list (table)
SELECT hr_name 
FROM hasread
WHERE hr_bookID IN(SELECT p_bookID
        FROM publisher
        WHERE p_date >= '2002-01-01');


--------------   16   --------------
--update favbook rating
UPDATE favebooks
SET fa_myrating = '5.0'
WHERE fa_name = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)';


--------------   17   --------------
--what book has a rating of at least 4.0, written in english, and has at least 600 pages
SELECT b_title, b_numpages, b_langcode
FROM books
WHERE b_numpages >=600.0 AND 
b_langcode = 'eng' AND 
b_bookID IN(SELECT r_bookID
            FROM rating
            WHERE r_avgbookrating >= 4.0);


--------------   18   --------------
--find faveauthor(s) with highest rating
select avg(fa_myrating) as avgRating, fa_name as topAuthor
from faveauthors
where fa_myrating =  (select max(famr) from (select avg(fa_myrating) as famr
                                             from faveauthors
                                             group by fa_name
                                            ) as helper)
group by fa_name;

