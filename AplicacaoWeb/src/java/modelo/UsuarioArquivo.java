/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe utilizada para realizar as ações de manipulação de dados de usuário
 */
public class UsuarioArquivo {
    
    private static final String PATH_ARQUIVO = "C:\\Users\\moesio.meneses\\Desktop\\LoginJavaWeb\\AplicacaoWeb\\src\\java\\modelo\\usuarios.txt";
    
    public boolean efetuarLogin(String login, String senha) {
        Usuario usuario = obterUsuario(login);
        if (usuario != null && senha != null && usuario.getSenha().equals(senha)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean inserir(String nome, String login, String senha) {
        if(!existeUsuario(login)){
            try {
                FileWriter fw;
                fw = new FileWriter(PATH_ARQUIVO, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(nome+";"+login.toLowerCase()+";"+senha);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (Exception ex) {
                System.out.println("erro");
            }
        }
        return true;
    }
    public boolean alterar(String nome, String login, String senha) {
        if(this.existeUsuario(login.toLowerCase())){
            List<Usuario> users = new ArrayList();
            users.addAll(this.obterTodos());
            Usuario user = new Usuario();
            for(Usuario u: users){
                
                if(u.getLogin().equals(login.toLowerCase()) && u.getSenha().equals(senha)){
                  user = u;
                }
            }
            Usuario aux = new Usuario();
            aux.setLogin(user.getLogin());
            aux.setNome(nome);
            aux.setSenha(user.getSenha());
            users.remove(user);
            try {
                FileWriter fw;
                fw = new FileWriter(PATH_ARQUIVO, false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.close();
                fw.close();
                for(Usuario u: users){
                   inserir(u.getNome(), u.getLogin(), u.getSenha());
                }   
                inserir(aux.getNome(), aux.getLogin(), aux.getSenha());
                

            } catch (Exception ex) {
                System.out.println("erro");
                return false;
            }
        }else{
            System.out.println("Login não existe");
            return false;
        }
        return true;
    }
    public boolean remover(String login) {
        if(this.existeUsuario(login)){
            List<Usuario> users = new ArrayList();
            users.addAll(this.obterTodos());
            Usuario user = new Usuario();
            for(Usuario u: users){
                
                if(u.getLogin().equals(login.toLowerCase())){
                  user = u;
                }
            }
            users.remove(user);
            try {
                FileWriter fw;
                fw = new FileWriter(PATH_ARQUIVO, false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.close();
                fw.close();
                for(Usuario u: users){
                   inserir(u.getNome(), u.getLogin(), u.getSenha());
                }   
               
                

            } catch (Exception ex) {
                System.out.println("erro");
                return false;
            }
        }else{
            System.out.println("Login não existe");
            return false;
        }
        return true;
    }
    public Usuario obterUsuario(String login) {
        Usuario user = new Usuario();
        boolean exists = false;
        String usuario = "";
        try {
            FileReader fr = new FileReader(PATH_ARQUIVO);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                usuario = br.readLine();
                String[] tupla = usuario.split(";");
                if(tupla[1].equals(login.toLowerCase())){
                    user.setNome(tupla[0]);
                    user.setLogin(tupla[1]);
                    user.setSenha(tupla[2]);
                    exists = true;
                }
                

            }
            if(!exists){
                System.out.println("Login não encontrado!");
            }
            br.close();
            fr.close();
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return user;
    }
    public List<Usuario> obterTodos() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        String usuario;
        try {
            FileReader fr = new FileReader(PATH_ARQUIVO);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                Usuario user = new Usuario();
                usuario = br.readLine();
                String[] tupla = usuario.split(";");
                user.setNome(tupla[0]);
                user.setLogin(tupla[1]);
                user.setSenha(tupla[2]);
                listaUsuarios.add(user);
                
            }
            br.close();
            fr.close();
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return listaUsuarios;
    }
    
    public boolean existeUsuario(String login){
        String usuario = "";
        try {
            FileReader fr = new FileReader(PATH_ARQUIVO);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
             usuario = br.readLine();
             for(int i = 0; i<usuario.length(); i++){
                 String[] tupla = usuario.split(";");
                 if(tupla[1].equals(login.toLowerCase())){
                     return true;
                 }
             }
             
            }
            br.close();
            fr.close();
        }catch (Exception ex) {
            return false;
        }
        
        return false;
    } 
}

