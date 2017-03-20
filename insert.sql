use library;

insert into inventory values(null, '1234-5678-9876', 'Game of Thrones', 'George R. R. Martin', 'book', 'english', 'publisher1', '2009-12-30', curdate());
insert into inventory values(null, '4125-6234-6132', 'Harry Potter and the Prizoner of Azkaban', 'J. K. Rowling', 'book', 'english', 'publisher2', '2003-05-25', curdate());
insert into inventory values(null, '8142-6123-4123', 'På Myrens Fodsti', 'Johannes Møllehave', 'book', 'dansk', 'Gyldendal', '1990-4-30', curdate());

insert into users values (null, 'root', 'root', 'test street 123', '60009853', 'spam@me.dk', '1212121212', default, default);
insert into users values (null, 'kasper', 'kasper', 'Lyngbyvej 38', '28975015', 'kasper@breindal.me', '0610960000', default, default);

insert into employees values (default, 'librarian', 'librarian');