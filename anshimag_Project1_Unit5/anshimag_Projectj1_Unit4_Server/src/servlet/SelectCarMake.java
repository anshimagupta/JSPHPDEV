package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.AutoClientSocket;

/**
 * This servlet is used to display all the Models available
 */
public class SelectCarMake extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String HOST = "localhost";
    private int PORT = 1234;
    private String allModelNames;

    public SelectCarMake() {
        super();
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // Set the response message's MIME type
        response.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter printWriter = response.getWriter();


        AutoClientSocket autoClientSocket = new AutoClientSocket(HOST, PORT);
        autoClientSocket.openConnection();

        allModelNames = autoClientSocket.listAllModelNames();
        String[] modelNames = allModelNames.split("\n");

        printWriter.println("<!DOCTYPE HTML>");
        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("<title>Select A Model</title>");
        printWriter.println("</head>");
        printWriter.println("<body>");
        printWriter.println("<h3>Select Car Model to Start with</h3>");
        printWriter.println("<form action='SelectCarModel'>");
        printWriter.println("<table border='2px'>");
        printWriter.println("<tr><td>Available Models: </td>");
        printWriter.println("<td><select name='model'>");
        for(int i = 0; i < modelNames.length; i++)
            printWriter.println("<option value='" + modelNames[i] + "'>" + modelNames[i] + "</option>");
        printWriter.println("</select></td></tr>");
        printWriter.println("<tr><td colspan=2><input type='submit' value='Continue' style='float: right;' />&nbsp;&nbsp;");
        printWriter.println("</form>");
        printWriter.println("</div></body>");;
        printWriter.println("</html>");
        printWriter.close();
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}