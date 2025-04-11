package br.senai.sc.drones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private String URL = "jdbc:mysql://mi75.mysql.database.azure.com/db_filipef";
    private final String USER = "mi75";
    private final String PASSWORD = "Temporario18!";


    public Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
