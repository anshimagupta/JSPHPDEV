package servlet;

import client.AutoClientSocket;
import model.Automobile;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class SelectCarConfig extends HttpServlet {
    private String HOST = "localhost";
    private int PORT = 1234;
    private HttpSession httpSession;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = res.getWriter();
        AutoClientSocket autoClientSocket = new AutoClientSocket(HOST, PORT);

        httpSession = req.getSession(true);

        if(autoClientSocket.openConnection()) {
            String allModels = autoClientSocket.listAllModelNames();
            String[] modelNames = allModels.split("\n");
        }
        if(httpSession.getAttribute("auto") != null){
            Automobile automobile = (Automobile) httpSession.getAttribute("auto");

            Iterator<String> iterator = automobile.getOptionSets().keySet().iterator();
            while(iterator.hasNext()) {
                String opsetName = iterator.next();
                String selectedOption = req.getParameter(opsetName);
                automobile.setOptionChoice(opsetName, selectedOption);
            }
        }else {
            String model = req.getParameter("model");
            // Allocate a output writer to write the response message into the network socket
            autoClientSocket.listAllModelNames();
            autoClientSocket.sendSelectedModel();
            Automobile automobile = autoClientSocket.receiveSelectedModel();
            req.getSession().setAttribute("auto", automobile);

            printWriter.println("<!DOCTYPE HTML>");
            printWriter.println("<html>");
            printWriter.println("<head>");
            printWriter.println("<title>Basic Car Choice</title>");
            printWriter.println("</head>");
            printWriter.println("<body>");
            printWriter.println("<h3>Basic Car Choice</h3>");
            printWriter.println("<br/>");
            printWriter.println("<form action='SelectBasicCar'>");
            printWriter.println("<table action='AutomobileConfig' border='5px'>");
            printWriter.println("<tr><td>Make</td><td>" + automobile.getMake() + "</td></tr>");
            printWriter.println("<tr><td>Model</td><td>" + automobile.getModelName() + "</td></tr>");

            Iterator<String> iterator = automobile.getOptionSets().keySet().iterator();
            while(iterator.hasNext()) {
                String opsetName = iterator.next();
                printWriter.println("<tr><td>" + opsetName + ": </td>");
                printWriter.println("<td><select name='" + opsetName + "'>");
                Iterator<String> optionIterator = automobile.getOptionSets().keySet().iterator();
                while(optionIterator.hasNext()) {
                    String optionName = optionIterator.next();
                    //Float optPrice = automobile.getOptionSets().get(optionName);
                    printWriter.println("<option value='" + optionName + "'>" + optionName + " - " + "</option>");
                }
                printWriter.println("</select></td>");
                printWriter.println("</tr>");
            }
            printWriter.println("<tr><td></td><td><input type='submit' value='Done'/>&nbsp;&nbsp;");
            printWriter.println("</table>");
            printWriter.println("</form>");
            printWriter.println("</div></body>");
            printWriter.println("</html>");
            printWriter.close();
        }

    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        doGet(req, res);
    }

}
