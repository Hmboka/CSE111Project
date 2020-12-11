--could do an analytics option for users average ratijgs like letterboxd

--------------   27   -------------- (uses 5 tables)
--Find authors who have written one of your favorite books, though youve rated one of their other books below 2.5. 
--Display the author, favorite book title , the book's popular rating,  and book's publisher

CREATE VIEW fb_auth_pub_rating(ad_name, b_title, r_avgbookrating, p_name) as
select distinct ad_name, b_title, r_avgbookrating, p_name
from favebooks, authored, rating, publisher, books
where ad_bookID = fb_bookID AND    
      ad_bookID = b_bookID AND
      b_bookID = p_bookID AND
      p_bookID = r_bookID;

select ad_name, b_title, r_avgbookrating, p_name
from fb_auth_pub_rating
where ad_name in (select hr_name
                    from hasread
                    group by hr_name
                    having hr_myrating < 2.5);

-- DROP VIEW fb_auth_pub_rating;
/* Display book info when searched */
CREATE VIEW searchBook(b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn) as
select b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn 
from books, authors, authored, rating
where b_bookID = ad_bookID AND ad_name = a_name AND
      r_bookID = b_bookID;

select * from searchBook where b_title like '%Harry%';