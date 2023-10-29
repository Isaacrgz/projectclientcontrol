package web;

import java.io.IOException;
import java.util.List;

import data.ClientDaoJDBC;
import domain.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ServletController")
public class ServletController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "edit":
                    this.editClient(request, response);
                    break;
                case "delete":
                    this.deleteClient(request, response);
                    break;
                default:
                    this.defaultAction(request, response);
                    break;
            }
        } else {
            this.defaultAction(request, response);
        }
    }

    private double totalBalanceCal(List<Client> clients) {
        double totalBalance = 0;
        for (Client client : clients) {
            totalBalance += client.getBalance();
        }
        return totalBalance;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "insert":
                    this.insertClient(request, response);
                    break;
                case "modify":
                    this.modifyClient(request, response);
                    break;
                default:
                    this.defaultAction(request, response);
                    break;
            }
        } else {
            this.defaultAction(request, response);
        }
    }

    private void defaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = new ClientDaoJDBC().list();
        HttpSession session = request.getSession();
        session.setAttribute("clients", clients);
        session.setAttribute("totalClients", clients.size());
        session.setAttribute("totalBalance", this.totalBalanceCal(clients));
        //request.getRequestDispatcher("clients.jsp").forward(request,response); // este netodo no notifica al navegador y puede ocacionar duplicacion de eventos
        response.sendRedirect("clients.jsp");
    }

    private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        double balance = 0;
        String stgBalance = request.getParameter("balance");

        if (stgBalance != null && !"".equals(stgBalance)) {
            balance = Double.parseDouble(stgBalance);
        }

        Client client = new Client(name, lastname, email, phone, balance);

        int modifiedRegisters = new ClientDaoJDBC().insert(client);
        System.out.println("Modified registers: " + modifiedRegisters);

        this.defaultAction(request, response);
    }

    private void modifyClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idClient = Integer.parseInt(request.getParameter("idClient"));
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        double balance = 0;
        String stgBalance = request.getParameter("balance");

        if (stgBalance != null && !"".equals(stgBalance)) {
            balance = Double.parseDouble(stgBalance);
        }

        Client client = new Client(idClient, name, lastname, email, phone, balance);

        int modifiedRegisters = new ClientDaoJDBC().update(client);
        System.out.println("Modified registers: " + modifiedRegisters);

        this.defaultAction(request, response);
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idClient = Integer.parseInt(request.getParameter("idClient"));

        Client client = new Client(idClient);

        int modifiedRegisters = new ClientDaoJDBC().delete(client);
        System.out.println("Modified registers: " + modifiedRegisters);

        this.defaultAction(request, response);
    }

    private void editClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idClient = Integer.parseInt(request.getParameter("idClient"));
        Client client = new ClientDaoJDBC().find(new Client(idClient));
        request.setAttribute("client", client);
        String stgEditClient = "/WEB-INF/pages/client/editClient.jsp";
        request.getRequestDispatcher(stgEditClient).forward(request, response);
    }
}
