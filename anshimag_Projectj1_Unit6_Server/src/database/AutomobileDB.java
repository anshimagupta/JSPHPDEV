package database;

import com.mysql.jdbc.Statement;
import model.Automobile;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

public class AutomobileDB extends MakeConnection {
    private static AutomobileDB instance = new AutomobileDB();
    private static int autoID;

    private AutomobileDB() {
    }

    public static AutomobileDB getInstance() {
        return instance;
    }

    Statement statement = null;

    public void addAuto(Automobile automobile) {
        String query;
        if (isConnected()) {
            String model = automobile.getModelName();
            String make = automobile.getMake();
            Float basePrice = automobile.getBasePrice();
            autoID++;
            try {
                statement = (Statement) connection.createStatement();

                query = " insert into automobile (modelname, make, basePrice)"
                        + " values(?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, model);
                preparedStatement.setString(2, make);
                preparedStatement.setFloat(3, basePrice);

                // execute the preparedstatement
                preparedStatement.execute();

                Iterator<String> iterator = automobile.getOptionSets().keySet().iterator();
                while(iterator.hasNext()) {

                    String opSetName = iterator.next();

                    /**
                     * Insert opset name into 'opset' table
                     */
                    query = "INSERT INTO optionset (name, autoid) VALUES (?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, opSetName);
                    preparedStatement.setInt(2, autoID);
                    statement.executeUpdate(query);

                    Iterator<String> optIter = automobile.getOptions(opsetName).keySet().iterator();
                    while(optIter.hasNext()) {
                        int opt_id = 0;
                        String optName = optIter.next();
                        Float optPrice = auto.getOptions(opsetName).get(optName);
                        /**
                         * Insert option name and option price into 'opt' table
                         */
                        values = "'" + optName + "'," + optPrice;
                        query = "INSERT INTO opt (opt_name, opt_price) VALUES (" + values + ")";
                        statement.executeUpdate(query);
                        /**
                         * Insert opset_id and option_id into 'opset_option' table
                         */
                        query = "SELECT opt_id FROM opt WHERE opt_name = '" + optName + "' AND opt_price = '" + optPrice + "' ORDER BY opt_id DESC LIMIT 1";
                        rs = statement.executeQuery(query);
                        if(rs.next()){
                            opt_id = Integer.parseInt(rs.getString("opt_id"));
                        }
                        rs.close();
                        values = opset_id + "," + opt_id;
                        query = "INSERT INTO opset_opt (opset_id, opt_id) VALUES (" + values + ")";
                        statement.executeUpdate(query);
                }


                    connection.close();

            } }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void deleteAuto(Automobile automobile) {

    }


}




