create database ahorcado;
use ahorcado;

create table words(
id_word int unsigned not null auto_increment,
word varchar(50),
constraint pk_words primary key (id_word),
constraint unq_words unique (word)
);

insert into palabras (palabra) values ('castillo'), ('universidad'), ('botella'), ('vertebra'), ('epilepsia'), ('santidad'), ('mineral'), ('mediterraneo'),
            ('juego'), ('vestimenta'), ('recurso'), ('placard'), ('sillon'), ('anteojos'), ('cartuchera'), ('espantapajaros'), ('computadora'),
            ('fibron'), ('campera'), ('parlante');

create table winners(
id_winner int unsigned not null auto_increment,
name varchar(50),
date date,
id_word int unsigned not null,
constraint pk_winners primary key (id_winner),
constraint fk_winners_words foreign key (id_word) references words (id_word)
);

DELIMITER $$
CREATE PROCEDURE insertWinner (pName varchar(50), pWord varchar(50))
BEGIN
	declare vId_word int default 0 ;
	
	select id_word into vId_word from words where word = pWord;
	
	insert into winners (name, date, id_word) values (pName, now(), vId_word);

END $$

select w.name, w.date, wo.word from winners w inner join words wo on w.id_word = wo.id_word;