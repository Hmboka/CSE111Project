select b_title
from books

--insert into fav auhtor....
INSERT INTO faveauthors (fa_name, fa_myrating)
SELECT a_authors, a_authrating FROM authors;

--insert into hasread from books table
INSERT INTO hasread (hr_bookID, hr_name)
SELECT b_bookID, b_title FROM books
WHERE b_title = 'The Control of Nature';

    --sample to show the next queire 
                    INSERT INTO hasread (hr_bookID, hr_name)
                    SELECT b_bookID, b_title FROM books
                    WHERE b_title = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)';

--Delete books from hasread
DELETE FROM hasread
WHERE hr_name = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)';

--add your own rating to books added to hasread......
INSERT INTO hasread (hr_myrating)
VALUES (9.2)
WHERE hr_name = 'The Control of Nature';


--delete books fom the main book able......
DELETE FROM books
WHERE b_title = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)';

--find books written by blank author that start with The
SELECT b_title
FROM books
WHERE b_bookID IN (SELECT ad_bookID
        FROM authored
        WHERE ad_name = 'J.K. Rowling');


--find books that were published after 2002 and are on your has read list (table)...

SELECT hr_name 
FROM hasread
WHERE hr_bookID IN(SELECT p_bookID
        FROM publisher
        WHERE p_date >= '2002-01-01');

--update favbook rating
UPDATE favebooks
SET fa_myrating = '5.0'
WHERE fa_name = 'Harry Potter and the Half-Blood Prince (Harry Potter  #6)';

--what book has a rating of at least 4.0, written in english, and has at least 600 pages
SELECT b_title, b_numpages, b_langcode, r_avgbookrating
FROM books, rating
WHERE b_numpages >=600.0 AND 
b_langcode = 'eng' AND 
b_bookID IN(SELECT r_bookID
            FROM rating
            WHERE r_avgbookrating >= 4.0);

            


