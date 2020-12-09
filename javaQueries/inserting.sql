DROP TRIGGER IF EXISTS books.t1; 
DROP TRIGGER IF EXISTS books.tdelbook; 

CREATE
    TRIGGER insBook AFTER INSERT
    ON books
    FOR EACH ROW
    WHEN (NOT EXISTS (
             select *
             from books
    ))
    BEGIN
        insert books
        VALUES() 
        
    END;


CREATE
    TRIGGER tdelbook AFTER DELETE
    ON books
    FOR EACH ROW
    BEGIN
        delete
        from publisher
        where p_bookID = (select p_bookID
                            from publisher
                            where p_bookID = OLD.b_bookID);
        delete 
        from authored
        where ad_bookID = (select ad_bookID
                            from authored
                            where ad_bookID = OLD.b_bookID);
                            
END;
CREATE
    TRIGGER t2 AFTER INSERT
    ON authored
    FOR EACH ROW
    WHEN (NOT EXISTS (
             select *
             from authored
    ))
    BEGIN
        INSERT INTO author
        VALUES(NEW.b_bookID)
    END;
-- first, find the max book id, then add 1 to make it a new book id
select max(b_bookID)+1
from books;
--then insert new book with max(book_id)+1


INSERT INTO books
VALUES(45642, 'Mboka: The Way of Life in a Congo Village (A Congo Memoir)', 
        'eng', 264, '051750037X');

INSERT INTO publisher(p_name, p_bookID, p_authname)
VALUES('Crown Publishers', 45642, 'Lona B. Kenney');

INSERT INTO authored(ad_name, ad_bookID)
VALUES('Lona B. Kenney', 45642);

select *
from books
where b_title LIKE 'Mboka%';
select *
from authored
where ad_bookID = 45642;
select *
from publisher
where p_bookID = 45642;

/*

Delete from books
where b_title like 'Mboka%';

Delete from authored
where ad_bookID = 45642 or ad_bookID = 45643;

Delete from publisher
where p_bookID = 45642 or p_bookID = 45643;

*/
select distinct b_langcode
from books;

-- INSERT INTO books (b_bookID, b_title, b_langcode, b_numpages, b_isbn)
DROP VIEW IF EXISTS V1;
DROP VIEW IF EXISTS V10;
DROP VIEW IF EXISTS V151;
DROP VIEW IF EXISTS V152;
DROP VIEW IF EXISTS V2;
DROP VIEW IF EXISTS V5;

select *
from books
order by b_bookID desc;