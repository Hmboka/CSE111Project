create table books (
    b_bookID decimal(13,0) not null,
    b_title varchar(250) not null,
    b_languagecode char(10) not null,
    b_numpages decimal(8,0),
    b_isbn decimal(11,0) not null,
);


create table authored (
    ad_name char(40) not null,
    ad_bookID decimal(13,0)
);

create table authors (
    a_authors char(40) not null,
    a_authrating decimal(3,2) not null
);

create table publisher (
    p_name char(40) not null,
    p_bookID varchar(250) not null,
    p_authname char(40) not null,
    p_date date
);

create table rating (
    r_bookID decimal(13,0) not null,
    r_title varchar(250) not null,
    r_avgbookrating decimal(3,2) not null
);

create table faveauthors (
    fa_name char(40) not null,
    fa_myrating decimal(3,2)

);

create table hasread (
    hr_name char(40) not null,
    hr_bookID decimal(13,0),
    hr_myrating decimal(3,2)
);

create table favebooks (
    fb_bookID decimal(13,0) not null,
    fb_title varchar(250) not null,
    fb_myrating decimal(3,2)
);



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
