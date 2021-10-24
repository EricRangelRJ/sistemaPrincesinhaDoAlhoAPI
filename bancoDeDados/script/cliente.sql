CREATE TABLE Cliente (
  idCliente INT,
  Nome VARCHAR(100),
  DataNascimento DATE,
  Cpf VARCHAR(45) ,
  PRIMARY KEY (`idCliente`)
  );
  

use alho;


create table usuario(
idusuario integer	auto_increment,
nome  	varchar(150)	not null,
email			varchar(100)	not null unique,
senha			varchar(40)	not null,
perfil			varchar(25)	not null,
    primary key(idusuario));
    
    desc usuario;
    
insert into usuario(nome, email, senha, perfil)
values('Eric Rangel', 'ericlsrangel@gmail.com', 'eric', 'eric');

select * from usuario;
