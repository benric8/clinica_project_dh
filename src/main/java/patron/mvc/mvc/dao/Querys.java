package patron.mvc.mvc.dao;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Querys {
    SQL_DROP_CREATE_PACIENTES(" DROP TABLE IF EXISTS PACIENTES; " +
            "CREATE TABLE PACIENTES(" +
            "ID INT AUTO_INCREMENT PRIMARY KEY, " +
            "NOMBRE VARCHAR(30) NOT NULL, " +
            "APELLIDO VARCHAR(40) NOT NULL, " +
            "CEDULA VARCHAR(30) NOT NULL, " +
            "FECHA_INGRESO DATE NOT NULL, " +
            "DOMICILIO_ID INT NOT NULL," +
            "EMAIL VARCHAR(50) NOT NULL)"),
    SQL_DROP_CREATE_DOMICILIOS("DROP TABLE IF EXISTS DOMICILIOS; " +
            "CREATE TABLE DOMICILIOS(" +
            "ID INT AUTO_INCREMENT PRIMARY KEY, " +
            "CALLE VARCHAR(100) NOT NULL, " +
            "NUMERO INT NOT NULL, " +
            "LOCALIDAD VARCHAR(100) NOT NULL, " +
            "PROVINCIA VARCHAR(100) NOT NULL)"),
    SQL_DROP_CREATE_ODONTOLOGOS("DROP TABLE IF EXISTS ODONTOLOGOS; " +
            "CREATE TABLE ODONTOLOGOS("+
            "ID INT AUTO_INCREMENT PRIMARY KEY,"+
            "NOMBRE VARCHAR(30) NOT NULL, " +
            "APELLIDO VARCHAR(40) NOT NULL, " +
            "MATRICULA VARCHAR(40) NOT NULL, )"),
    SQL_DROP_CREATE_TURNOS("DROP TABLE IF EXISTS TURNOS; " +
            "CREATE TABLE TURNOS("+
            "ID INT AUTO_INCREMENT PRIMARY KEY,"+
            "PACIENTE_ID INT NOT NULL,"+
            "ODONTOLOGO_ID INT NOT NULL,"+
            "FECHA DATETIME NOT NULL"+
            "CONSTRAINT FK_TURNOS_PACIENTES FOREIGN KEY (PACIENTE_ID) REFERENCES PACIENTES(ID)," +
            "CONSTRAINT FK_TURNOS_ODONTOLOGOS FOREIGN KEY (ODONTOLOGO_ID) REFERENCES ODONTOLOGOS(ID)"),
    SQL_DROP_PRUEBA("INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES('Siempre Viva','748','Sprinfield','EUA'),('Calle Falsa','123','Sprinfield','EUA'); " +
            "INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID,EMAIL) VALUES('Julian','Espinoza','234455667','2024-08-08',1,'lsl@slsl.es'),('Helen','Vasquez','3445666','2024-07-20',2,'d@d.com')");

    String sql;
}
