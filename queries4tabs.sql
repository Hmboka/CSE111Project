select distinct b1.authors as a_authors, avg(b1.average_rating) as a_authrating
from book b1, book b2
where b1.authors = b2.authors
group by b1.authors
order by b1.authors asc;
--to select authors and rating


select bookID as b_bookID, title as b_title, language_code as b_langcode, num_pages as b_numpages, isbn as b_isbn
from book;
---creates books table


select bookID as r_bookID, title as r_title, average_rating as r_avgbookrating
from book;
--creates rating table


select publisher as p_name, bookID as p_bookID, authors as p_authname, publication_date as p_date
from book;
--publisher table

select authors as ad_name, bookID as ad_bookID
from book;
--authored table

delete
from hasread;
--has read table
delete
from faveauthors;

--fave authors table


--favebooks table

