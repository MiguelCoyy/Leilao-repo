/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        try {
            
            String sql = "INSERT INTO produtos (nome,valor,status) VALUES (?,?,?)";
            
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            prep.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        }catch(SQLException sqle){
            
            System.out.println("Erro ao cadastrar o produto: "+sqle.getMessage());
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        String sql = "SELECT * FROM produtos";
        
        try{
            
            conn = new conectaDAO().connectDB();
            
            prep = conn.prepareStatement(sql);
            
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                
                ProdutosDTO produtos = new ProdutosDTO(); 

                produtos.setId(resultset.getInt("id"));             
                produtos.setNome(resultset.getString("nome"));     
                produtos.setValor(resultset.getInt("valor"));    
                produtos.setStatus(resultset.getString("status")); 

                listagem.add(produtos);
            }
            
        }catch(SQLException sqle){
            
            System.out.println("Erro ao listar produtos :"+ sqle.getMessage());
            
        }
        
        return listagem;
    }
    
                public void venderProduto(int id){
        
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?"; // SQL para atualizar o status

        try {
            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            prep.execute();

            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            //teste de commit aqui

        } catch (SQLException erro) {
            System.err.println("Erro : " + erro.getMessage());
            
    }
    
  }
        
        
}

