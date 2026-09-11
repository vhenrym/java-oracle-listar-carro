package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Carro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarroDAO implements IDAO{
    //atributo

    private Connection con;
    private Carro carro;

    //construtor com parâmetro


    public CarroDAO(Connection con) {
        this.con = con;
    }

    //método get
    public Connection getCon() {
        return con;
    }

    //métodos da classe

    public String inserir(Object object) {
        carro = (Carro) object;
        String sql = "insert into ddd_carro(placa, cor, descricao) values(?,?,?)";
        //try-with-resources
        try(PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setString(1, carro.getPlaca());
            ps.setString(2, carro.getCor());
            ps.setString(3, carro.getDescricao());
            if(ps.executeUpdate() > 0){
                return "Inserido com sucesso";
            }else{
                return "Erro ao inserir";
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return sql;
    }

    public String alterar(Object object) {
        carro = (Carro) object;
        String sql = "update ddd_carro set cor=?, descricao=? where placa=?";
        //try-with-resources
        try(PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setString(3, carro.getPlaca());
            ps.setString(1, carro.getCor());
            ps.setString(2, carro.getDescricao());
            if(ps.executeUpdate() > 0){
                return "Alterado com sucesso";
            }else{
                return "Erro ao alterar";
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return sql;
    }

    public String excluir(Object object) {
        carro = (Carro) object;
        String sql = "delete from ddd_carro where placa=?";
        //try-with-resources
        try(PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setString(1, carro.getPlaca());
            if(ps.executeUpdate() > 0){
                return "Excluído com sucesso";
            }else{
                return "Erro ao excluir";
            }
        }catch (SQLException e){
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return sql;
    }
    public String listarUm(Object object) {
        carro = (Carro) object;
        String sql = "select * from ddd_carro order by placa";
        try (PreparedStatement ps = getCon().prepareStatement(sql))
        {
            ps.setString(1, carro.getPlaca());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "Placa: " + carro.getPlaca() + "\nCor: " + rs.getString
                        ("cor") + "\nDescrição: " + rs.getString("descricao");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }
    }
}
