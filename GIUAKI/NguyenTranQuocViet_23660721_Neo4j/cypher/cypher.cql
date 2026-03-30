CREATE CONSTRAINT book_title_year
FOR (book:Book) REQUIRE (book.title, book.publicationYear) IS UNIQUE