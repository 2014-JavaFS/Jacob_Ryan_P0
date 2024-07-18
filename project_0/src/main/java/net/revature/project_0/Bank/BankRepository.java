package net.revature.project_0.Bank;

import net.revature.project_0.Account.Account;
import net.revature.project_0.util.ConnectionFactory;
import net.revature.project_0.util.exceptions.DataNotFoundException;
import net.revature.project_0.util.interfaces.Serviceable;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankRepository implements Serviceable<BankAccount> {



    @Override
    public List<BankAccount> lookup() {

        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {

            List<BankAccount> userDatabaseData = new ArrayList<>();
            String sql = "select * from bank_Account";
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
    public BankAccount create(BankAccount newBankAccount) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {

            BankAccount linkedBankAccount = new BankAccount();
            String sqlEntry = "insert into bank_Account(account_number, user_number_ID, account_balance) values (?,?,?)";
            // PreparedStatements SANITIZE any user input before execution
            PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index

            preparedStatement.setInt(1, newBankAccount.getAccountNumber());
            preparedStatement.setInt(2, newBankAccount.getAccountOwner());
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(0.00));

            int checkInsert=preparedStatement.executeUpdate();
            System.out.println("Inserting New Account");
            if(checkInsert == 0){
                throw new RuntimeException();}

            return linkedBankAccount;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BankAccount findByID(int number) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sqlEntry = "select * from bank_Account where account_Number = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
            preparedStatement.setInt(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
                throw new DataNotFoundException("No Account with that number exists");

            return generateAccountFromResultSet(resultSet);
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<BankAccount> findAssociatedAccounts(int idNum){

        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sqlEntry = "select * from bank_Account where user_number_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
            preparedStatement.setInt(1, idNum);
            ResultSet resultSet = preparedStatement.executeQuery();



            List<BankAccount> associatedAccounts = new ArrayList<>();
            while(resultSet.next()){
                associatedAccounts.add(generateAccountFromResultSet(resultSet));
            }

            return associatedAccounts;

        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //TODO:Placeholder
    public BankAccount deposit(BigDecimal depositAmmount, int accountNumber, int accountID){
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {


            String sqlEntry = "update bank_Account set account_balance = account_balance + ? where account_Number = ? AND user_number_ID = ?";
            // PreparedStatements SANITIZE any user input before execution
            PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index

            preparedStatement.setInt(2, accountNumber);
            preparedStatement.setInt(3, accountID);
            preparedStatement.setBigDecimal(1, depositAmmount);

            int checkInsert=preparedStatement.executeUpdate();
            System.out.println("Depositing Funds");
            if(checkInsert == 0){
                throw new RuntimeException();}


            sqlEntry = "select * from bank_Account where user_number_ID = ? AND account_Number = ?";
            preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
            preparedStatement.setInt(2, accountNumber);
            preparedStatement.setInt(1, accountID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
                throw new DataNotFoundException("No Account with that number exists");

            return generateAccountFromResultSet(resultSet);
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //TODO:Placeholder
    public BankAccount withdrawal(BankAccount accountWithFunds, BigDecimal withdrawlAmmount){
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {


            String sqlEntry = "update bank_Account set account_balance = account_balance - ? where account_Number = ? AND user_number_ID = ?";
            // PreparedStatements SANITIZE any user input before execution
            PreparedStatement preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index

            preparedStatement.setInt(2, accountWithFunds.getAccountNumber());
            preparedStatement.setInt(3, accountWithFunds.getAccountOwner());
            preparedStatement.setBigDecimal(1, withdrawlAmmount);

            int checkInsert=preparedStatement.executeUpdate();
            System.out.println("The Funds are being Withdrew");
            if(checkInsert == 0){
                throw new RuntimeException();}


            sqlEntry = "select * from bank_Account where user_number_ID = ? AND account_Number = ?";
            preparedStatement = conn.prepareStatement(sqlEntry);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
            preparedStatement.setInt(2, accountWithFunds.getAccountNumber());
            preparedStatement.setInt(1, accountWithFunds.getAccountOwner());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
                throw new DataNotFoundException("No Account with that number exists");

            return generateAccountFromResultSet(resultSet);
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private BankAccount generateAccountFromResultSet(ResultSet rs) throws SQLException{
        BankAccount account = new BankAccount();
        account.setAccountNumber(rs.getInt("account_number"));
        account.setAccountOwner(rs.getInt("user_number_ID"));
        account.setAccountType(rs.getInt("account_type"));
        account.setBalance(rs.getBigDecimal("account_balance"));
        account.setAccountTypeName(rs.getString("account_type_name"));

        return account;
    }


}
