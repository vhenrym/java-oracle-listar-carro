package br.com.fiap.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection abrirConexao(){
        Connection con = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            final String USER = "rm564222";
            final String PASS = "061006";
            con = DriverManager.getConnection(url, USER, PASS);
        }catch(ClassNotFoundException e){
            System.out.println("Erro: A classe de conexão não foi encontrada" + e.getMessage());
        }catch(SQLException e){
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return con;
    }

    public static void fecharConexao(Connection con){
        try{
            con.close();
            System.out.println("Conexão encerrada com sucesso!");
        }catch (SQLException e){
            System.out.println("Erro de SQL: " + e.getMessage());
        }
    }
}
