package net.revature.project_0.Account;

import net.revature.project_0.util.ConnectionFactory;
import net.revature.project_0.util.interfaces.Serviceable;

import java.util.List;
import java.sql.*;

public class AccountRepository implements Serviceable<Account> {


    @Override
    public Account lookup(List<Account> databaseArray, int idNum) {

        return null;
    }

    @Override
    public Account create(Account newObject) {


        return null;
    }

    @Override
    public Account findByID(int number) {



        return null;
    }

    public List<Account> getDatabaseData(List<Account> userDatabaseData){



        return userDatabaseData;
    }

    public Account addAccount(Account addedAccount){
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){

            String sqlEntry = "insert into user_Account(email, user_number_ID , password) values (?,?,?)";
            // PreparedStatements SANITIZE any user input before execution
            PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
            preparedStatement.setString(1,addedAccount.getUserName() );
            preparedStatement.setInt(2,addedAccount.getUserNumID());
            preparedStatement.setString(3, addedAccount.getPassword());

            int checkInsert=preparedStatement.executeUpdate();
            System.out.println("Inserting New Account");
            if(checkInsert == 0){
                throw new RuntimeException();}

            return addedAccount;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        //return addedAccount;
    }


}
