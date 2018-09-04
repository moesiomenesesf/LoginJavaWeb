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

    private static final String PATH_ARQUIVO = "C:\\Users\\leoomoreira\\Documents\\DADOS.txt";
    private static final String PATH_ARQUIVO_TEMP = "C:\\Users\\leoomoreira\\Documents\\dados.tmp";

    /**
     * Método utilizado para efetuar o login de usuário, verificando os dados de
     * login e senha
     *
     * @param login
     * @param senha
     * @return
     */
    public boolean efetuarLogin(String login, String senha) {
        Usuario usuario = obterUsuario(login);
        if (usuario != null && senha != null && usuario.getSenha().equals(senha)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método usado para verificar se um login já está registrado no arquivo
     *
     * @param login
     * @return
     */
    public boolean existeLogin(String login) {
        List<Usuario> usuarios = obterTodos();
        for (int i = 0; usuarios != null && i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método utilizado para recuperar todos os usuários registrados no arquivo
     *
     * @return
     */
    public List<Usuario> obterTodos() {
        List<Usuario> resultado = new ArrayList<Usuario>();
        try {
            FileReader fr = new FileReader(PATH_ARQUIVO);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String[] registro = br.readLine().split(";");
                Usuario usuario = new Usuario();
                usuario.setNome(registro[0]);
                usuario.setLogin(registro[1]);
                usuario.setSenha(registro[2]);
                resultado.add(usuario);
            }
            br.close();
            fr.close();
        } catch (Exception ex) {
            return new ArrayList<Usuario>();
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um usuário pelo login
     *
     * @param login
     * @return
     */
    public Usuario obterUsuario(String login) {
        Usuario usuario = null;
        try {
            FileReader fr = new FileReader(PATH_ARQUIVO);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String[] registro = br.readLine().split(";");
                if (registro[1].equals(login)) {
                    usuario = new Usuario();
                    usuario.setNome(registro[0]);
                    usuario.setLogin(registro[1]);
                    usuario.setSenha(registro[2]);
                    break;
                }
            }
            br.close();
            fr.close();
        } catch (Exception ex) {
            return null;
        }
        return usuario;
    }

    /**
     * Método utilizado para inserir um novo usuário
     *
     * @param nome
     * @param login
     * @param senha
     * @return
     */
    public boolean inserir(String nome, String login, String senha) {
        if (existeLogin(login)) {
            return false;
        }
        boolean resultado = false;
        try {
            FileWriter fw = new FileWriter(PATH_ARQUIVO, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nome + ";" + login + ";" + senha);
            bw.newLine();
            bw.close();
            fw.close();
            resultado = true;
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

}
