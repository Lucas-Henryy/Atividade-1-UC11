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


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
       
        
        try{
         conn = new conectaDAO().connectDB();
         String sql = "INSERT INTO produtos (nome, valor, status) VALUE (?,?,?)";
         prep = conn.prepareStatement(sql);
         prep.setString(1, produto.getNome());
         prep.setInt(2, produto.getValor());
         prep.setString(3, produto.getStatus());
         
         prep.execute();
         
         
         JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
         
         
        } catch (Exception e){
        
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
        }
         
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
            try {
             conn = new conectaDAO().connectDB();
             String sql = "SELECT * FROM produtos";
             prep = conn.prepareStatement(sql);
             resultset = prep.executeQuery();
             
             while (resultset.next()){
             
             ProdutosDTO produto = new ProdutosDTO();
             produto.setId(resultset.getInt("id"));
             produto.setNome(resultset.getString("nome"));
             produto.setStatus(resultset.getString("status"));
             produto.setValor(resultset.getInt("valor"));

             listagem.add(produto);
             }
            }catch (Exception e){
        
            JOptionPane.showMessageDialog(null, "Erro ao Listar!");
        }
        return listagem;
    }
    
    
    public void venderProduto (int id){
    
        try{
        
            conn = new conectaDAO().connectDB();
            String sql = " UPDATE produtos SET status = 'Vendido' where id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1,id);
            prep.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        
        } catch (Exception e){
        
        JOptionPane.showMessageDialog(null, "Erro ao Vender");
        }
    
    }
        
}

