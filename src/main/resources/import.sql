insert into enderecos(id_Endereco,logradouro,numero,complemento,condominio,bairro,municipio,estado,cep)values(1,'Av Robert',50,'Bloco A','Estrela','Bnh','Mesquita','RJ','22222666');
insert into enderecos(id_Endereco,logradouro,numero,complemento,condominio,bairro,municipio,estado,cep)values(2,'Rua União',2,null,null,'Vila Emil','Mesquita','RJ','22666999');
insert into enderecos(id_Endereco,logradouro,numero,complemento,condominio,bairro,municipio,estado,cep)values(3,'Rua Rufino',90,null,null,'Sto Elias','Mesquita','RJ','33666994');
insert into enderecos(id_Endereco,logradouro,numero,complemento,condominio,bairro,municipio,estado,cep)values(4,'Rua Sampa',550,'Apto 105',null,null,'Santos','SP','26599881');
 
insert into clientes(id_Cliente,nome,cpf,data_nascimento,telefone1,telefone2,email,observacao,id_Endereco)values(1,'Bia Silva','11111111197','2000-05-01','999995555',null,'bia@bol.com',null,null);
insert into clientes(id_Cliente,nome,cpf,data_nascimento,telefone1,telefone2,email,observacao,id_Endereco)values(2,'Edy Moura','22222222288','2000-05-01','999995555',null,'edy@bol.com',null,1);
insert into clientes(id_Cliente,nome,cpf,data_nascimento,telefone1,telefone2,email,observacao,id_Endereco)values(3,'Ana Carla','33366699988','2000-05-01','999995555',null,'ana@bol.com','Próx a padaria',2);
insert into clientes(id_Cliente,nome,cpf,data_nascimento,telefone1,telefone2,email,observacao,id_Endereco)values(4,'Tom Carfi','02366688855','2000-05-01','999995555',null,'tom@bol.com','casa azul',1);
 
insert into fornecedores(id_Fornecedor,nome_fornecedor,cpf_Cnpj,telefone1,telefone2,email,id_Endereco)values(1,'Samsung','05.565.279/0001-74','3333-3333',null,'samsung@bol.com',3);
insert into fornecedores(id_Fornecedor,nome_fornecedor,cpf_Cnpj,telefone1,telefone2,email,id_Endereco)values(2,'Sadia','25.123.279/0001-55','22 2222-3333',null,'sadia@bol.com',4);
insert into fornecedores(id_Fornecedor,nome_fornecedor,cpf_Cnpj,telefone1,telefone2,email,id_Endereco)values(3,'Lg','15.456.279/0001-29','21 6666-1000',null,'lg@bol.com',null);
																																																 
insert into produtos(id_Produto,nome_produto,codigo,descricao,data_cadastro,peso,valor_custo,valor_venda,id_Fornecedor)values(1,'Tv','000-1','Smart Tv 55 pol','2022-07-01',null,1500,3000,3);
insert into produtos(id_Produto,nome_produto,codigo,descricao,data_cadastro,peso,valor_custo,valor_venda,id_Fornecedor)values(2,'Celular','000-2','128GB, câmera de 30 MP','2021-02-15',180.0,800,1500,1);
insert into produtos(id_Produto,nome_produto,codigo,descricao,data_cadastro,peso,valor_custo,valor_venda,id_Fornecedor)values(3,'Pizza','000-3','sabor Calabresa','2000-05-10',400.0,7.0,12.5,2);
insert into produtos(id_Produto,nome_produto,codigo,descricao,data_cadastro,peso,valor_custo,valor_venda,id_Fornecedor)values(4,'Pizza','000-4','sabor Portuguesa','2000-05-10',400.0,7.0,12.5,2);
 
 
 
 