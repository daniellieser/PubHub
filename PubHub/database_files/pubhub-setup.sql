drop table if exists books;

create table books (
  isbn_13 varchar (13) primary key,
  title varchar (100),
  author varchar (80),
  publish_date date,
  price decimal (6,2),
  content bytea
);
drop table if exists booktags;

create table booktags (
  isbn_13 varchar (13),
  tag varchar (100),
  CONSTRAINT "BOOKTAGS_pkey" PRIMARY KEY (isbn_13, tag)
);

insert into books values (
  '1111111111111',          	-- id
  'The Adventures of Steve',    -- title
  'Russell Barron', 			-- author
  current_date,    				-- publishDate
  123.50,   					-- price
  null							-- blob
);

insert into booktags values (
  '1111111111111',          	-- id
  'Fun to Read'    -- tag
);