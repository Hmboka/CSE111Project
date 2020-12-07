create table books (
    b_bookID decimal(13,0) PRIMARY KEY,
    b_title varchar(250) not null,
    b_langcode char(10) not null,
    b_numpages decimal(8,0),
    b_isbn decimal(11,0)
);


create table authored (
    ad_name char(40) REFERENCES authors(a_name) ON UPDATE CASCADE ON DELETE CASCADE,
    ad_bookID decimal(13,0) REFERENCES books(b_bookID) ON UPDATE CASCADE ON DELETE CASCADE
);


create table authors (
    a_name char(40) PRIMARY KEY,
    a_authrating decimal(3,2) not null
);

create table publisher (
    p_name char(40) not null,
    p_bookID varchar(250) REFERENCES books(b_bookID) ON UPDATE CASCADE ON DELETE CASCADE,
    p_authname char(40)  REFERENCES authors(a_name) ON UPDATE CASCADE ON DELETE CASCADE,
    p_date date
);

create table rating (
    r_bookID decimal(13,0) REFERENCES books(b_bookID) ON UPDATE CASCADE ON DELETE CASCADE,
    r_title varchar(250) not null,
    r_avgbookrating decimal(3,2) not null
);

create table faveauthors (
    fa_name char(40) PRIMARY KEY,
    fa_myrating decimal(3,2) REFERENCES authors(a_name) ON UPDATE CASCADE ON DELETE CASCADE

);

create table hasread (
    hr_name char(40) REFERENCES authors(a_name) ON UPDATE CASCADE ON DELETE CASCADE,
    hr_bookID decimal(13,0) REFERENCES books(b_bookID) ON UPDATE CASCADE ON DELETE CASCADE,
    hr_myrating decimal(3,2),
    hr_date date
);

create table favebooks (
    fb_bookID decimal(13,0) not null REFERENCES books(b_bookID) ON UPDATE CASCADE ON DELETE CASCADE,
    fb_title varchar(250) not null,
    fb_myrating decimal(3,2)
);

/*
DROP TABLE authored;
DROP TABLE authors;
DROP TABLE books;
DROP TABLE faveauthors;
DROP TABLE favebooks;
DROP TABLE hasread;
DROP TABLE publisher;
DROP TABLE rating;

*/

/*create table books (
    b_bookID decimal(13,0) not null,
    b_title varchar(250) not null,
    b_authors char(10,0) not null,
    b_averagerating decimal(3,2) not null,
    b_isbn decimal(11,0) not null,
    b_isbn13 decimal(13,0) not null,
    b_languagecode char(10,0) not null,
    b_numpages decimal(8,0),
    b_ratingscount decimal(13),
    b_textreviewscount decimal(13),
    b_publicationdate date,
    b_publisher char(50) not null

);
*/
