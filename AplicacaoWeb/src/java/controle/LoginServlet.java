/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.UsuarioArquivo;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que representa a ação de validar um login de usuário
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // entrada
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        // processamento
        UsuarioArquivo arq = new UsuarioArquivo();
        boolean sucessoLogin = arq.efetuarLogin(login, senha);

        // saída
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Página Inicial</title>");
            out.println("</head>");
            out.println("<body>");
            if (sucessoLogin) {
                out.println("<h1>");
                out.println("Olá, " + login + "!");
                out.println("</h1>");
            } else {
                out.println("<h1>");
                out.println("Login ou senha incorreta!");
                out.println("</h1>");
                out.println("<a href=\"index.html\">Ir para página de Login</a>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
