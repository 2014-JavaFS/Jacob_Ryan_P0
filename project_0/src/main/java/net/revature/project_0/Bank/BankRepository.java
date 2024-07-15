package net.revature.project_0.Bank;

import net.revature.project_0.util.ConnectionFactory;
import net.revature.project_0.util.interfaces.Serviceable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BankRepository implements Serviceable<BankAccount> {



    @Override
    public List<BankAccount> lookup() {
        //TODO: Take Info from the database and where the UserID matches Fill a list with Bank Account Objects
        return null;
    }

    @Override
    public BankAccount create(BankAccount newBankAccount) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            //TODO: Put in the SQL Statement to make this work
            return null;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BankAccount findByID(int number) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {

            return null;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }



}
