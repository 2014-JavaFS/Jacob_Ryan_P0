package net.revature.project_0.Account;

import net.revature.project_0.Bank.BankAccount;
import net.revature.project_0.util.ConnectionFactory;
import net.revature.project_0.util.exceptions.DataNotFoundException;
import net.revature.project_0.util.exceptions.InvalidInputException;
import net.revature.project_0.util.interfaces.Serviceable;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AccountRepository implements Serviceable<Account> {


    @Override
    public List<Account> lookup() {

        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Account> userDatabaseData = new ArrayList<>();
            String sql = "select * from user_Account";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                userDatabaseData.add(generateAccountFromResultSet(rs));
            }
            return userDatabaseData;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Account create(Account addedAccount) {

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
    }

    @Override
    public Account findByID(int number) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){

            String sqlEntry = "select * from user_Account where user_number_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                throw new DataNotFoundException("No User with that ID exists");
                }
            return generateAccountFromResultSet(resultSet);

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    //TODO: Implement update feature
    public boolean update(Account account) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){

            String sqlEntry = "UPDATE user_Account SET First_Name = ?, Last_Name = ? where user_number_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
            //preparedStatement.setString();
            //preparedStatement.setString();
            preparedStatement.setInt(3, account.getUserNumID());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
                throw new DataNotFoundException("No User with that ID exists");

            return false;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public Account findByEmailAndPassword(String email, String password){
            try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){

                String sqlEntry = "select * from user_Account where email = ? AND password =?";
                PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

                // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
                preparedStatement.setString(1,email);
                preparedStatement.setString(2,password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(!resultSet.next())
                    throw new DataNotFoundException("No User with that ID exists");

                return generateAccountFromResultSet(resultSet);

            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }

    }

    public Account findByEmail(String email){
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){

            String sqlEntry = "select * from user_Account where email = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
                throw new DataNotFoundException("No User with that ID exists");

            return generateAccountFromResultSet(resultSet);

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }



    private Account generateAccountFromResultSet(ResultSet rs) throws SQLException{
        Account account = new Account();
        account.setUserName(rs.getString("email"));
        account.setUserNumID(rs.getInt("user_number_ID"));
        account.setPassword(rs.getString("password"));
        account.setFirstName(rs.getString("First_Name"));
        account.setLastName(rs.getString("Last_Name"));
        account.setAccountType(rs.getString("account_privilege"));
        return account;
    }


}
