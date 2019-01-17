CREATE DATABASE LMS;

CREATE TABLE Books(
  bookid VARCHAR(10) NOT NULL PRIMARY KEY ,
  bookname VARCHAR(50) NOT NULL ,
  bookcategory VARCHAR(20) NOT NULL
);

CREATE TABLE Member(
  memberid VARCHAR(10) NOT NULL PRIMARY KEY ,
  membername VARCHAR(20) NOT NULL ,
  email VARCHAR(30)
);

CREATE TABLE Borrow(
  memberid VARCHAR(10) NOT NULL ,
  bookid VARCHAR(10) NOT NULL ,
  borrowdate DATE NOT NULL ,
  returndate DATE NOT NULL ,
  CONSTRAINT PRIMARY KEY (memberid,bookid),
  CONSTRAINT FOREIGN KEY (memberid) REFERENCES Member(memberid),
  CONSTRAINT FOREIGN KEY (bookid) REFERENCES Books(bookid)
);

CREATE TABLE Author(
  authorid VARCHAR(10) NOT NULL PRIMARY KEY ,
  authorname VARCHAR(20) NOT NULL ,
  authoremail VARCHAR(20) NOT NULL
);

CREATE TABLE Author_Book(
  authorid VARCHAR(10) NOT NULL ,
  bookid VARCHAR(10) NOT NULL ,
  CONSTRAINT PRIMARY KEY (authorid,bookid),
  CONSTRAINT FOREIGN KEY (authorid) REFERENCES Author(authorid),
  CONSTRAINT FOREIGN KEY (bookid) REFERENCES Books(bookid)
);

CREATE TABLE Publisher(
  publisherid VARCHAR(10) NOT NULL PRIMARY KEY ,
  publishername VARCHAR(20) NOT NULL ,
  publisheremail VARCHAR(20) NOT NULL
);

CREATE TABLE Publisher_Book(
  publisherid VARCHAR(10) NOT NULL ,
  bookid VARCHAR(10) NOT NULL ,
  CONSTRAINT PRIMARY KEY (publisherid,bookid),
  CONSTRAINT FOREIGN KEY (publisherid) REFERENCES Publisher(publisherid),
  CONSTRAINT FOREIGN KEY (bookid) REFERENCES Books(bookid)
);

CREATE TABLE Returns(
  memberid VARCHAR(10) NOT NULL ,
  bookid VARCHAR(10) NOT NULL ,
  duedate DATE NOT NULL ,
  returndate DATE NOT NULL ,
  fine INT ,
  CONSTRAINT PRIMARY KEY (memberid,bookid),
  CONSTRAINT FOREIGN KEY (bookid) REFERENCES Books (bookid),
  CONSTRAINT FOREIGN KEY (memberid) REFERENCES Member(memberid)
);

