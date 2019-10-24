CREATE TABLE Articulo (Ar_id int(10) NOT NULL AUTO_INCREMENT, Ar_titulo varchar(255) NOT NULL, Ar_autor varchar(255) NOT NULL, Ar_identificador varchar(255) NOT NULL, Ar_estado bit(1) NOT NULL, fk_tema int(10) NOT NULL, fk_tipo int(10) NOT NULL, PRIMARY KEY (Ar_id)) ENGINE=InnoDB;
CREATE TABLE Cliente (Cl_id int(10) NOT NULL AUTO_INCREMENT, Cl_nombre varchar(255) NOT NULL, Cl_apellido varchar(255) NOT NULL, Cl_telefono int(20) NOT NULL, Cl_direccion varchar(255) NOT NULL, Cl_email varchar(255) NOT NULL, Cl_inicioSuscripcion varchar(30) NOT NULL, Cl_finSuscripcion varchar(30) NOT NULL, PRIMARY KEY (Cl_id)) ENGINE=InnoDB;
CREATE TABLE Tema (Te_id int(10) NOT NULL AUTO_INCREMENT, Te_mensaje varchar(255) NOT NULL, PRIMARY KEY (Te_id)) ENGINE=InnoDB;
CREATE TABLE Tipo (Ti_id int(10) NOT NULL AUTO_INCREMENT, Ti_mensaje varchar(255) NOT NULL, PRIMARY KEY (Ti_id)) ENGINE=InnoDB;
CREATE TABLE ArticuloToCliente (At_id int(10) NOT NULL AUTO_INCREMENT, At_fechaPrestamo varchar(30) NOT NULL, At_fechaPlanificadaDevolucion varchar(30) NOT NULL, At_fechaRealDevolucion varchar(30), fk_cliente int(10) NOT NULL, fk_articulo int(10) NOT NULL, PRIMARY KEY (At_id)) ENGINE=InnoDB;
ALTER TABLE Articulo ADD CONSTRAINT FKArticulo111904 FOREIGN KEY (fk_tipo) REFERENCES Tipo (Ti_id);
ALTER TABLE Articulo ADD CONSTRAINT FKArticulo238970 FOREIGN KEY (fk_tema) REFERENCES Tema (Te_id);
ALTER TABLE ArticuloToCliente ADD CONSTRAINT FKArticuloTo234304 FOREIGN KEY (fk_articulo) REFERENCES Articulo (Ar_id);
ALTER TABLE ArticuloToCliente ADD CONSTRAINT FKArticuloTo960803 FOREIGN KEY (fk_cliente) REFERENCES Cliente (Cl_id);
