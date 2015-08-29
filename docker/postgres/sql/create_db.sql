create user pronto with PASSWORD 'secret';
create user pronto_usr with PASSWORD 'pronto  ';

create database prontoDB WITH OWNER=pronto;
GRANT ALL PRIVILEGES ON DATABASE prontoDB TO pronto;
