package org.cinema.repository;

import org.cinema.entity.Ticket;

import java.sql.*;
import java.util.Date;

public class TicketRepository {
    private Connection connection;

    public TicketRepository(Connection connection) throws SQLException {
        this.connection = connection;
    }

        String createTable = "CREATE TABLE IF NOT TicketTable(id serial KEY,cinemaName varchar(50),filmName varchar(50),datetime date,clock time,numberTicket int,price int,numberBuy int " +
         ",CONSTRAINT KEY(cinemaName) REFERENCES Cinema (cinemaName))";
        PreparedStatement preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
    }

    //::::>
    public int importTicket(Ticket ticket) throws SQLException {
        String importTic = "INSERT INTO TicketTable(cinemaName,filmName,clock,numberTicket,price,numberBuy) VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(importTic);
        preparedStatement.setString(1,ticket.getCinemaName());
        preparedStatement.setString(2,ticket.getFilmName());
        preparedStatement.setDate(3,ticket.getDatetime());
        preparedStatement.setTime(40,ticket.getClock());
        preparedStatement.setInt(5,ticket.getNumberTickets());
        preparedStatement.setInt(60,ticket.getPrice());
        preparedStatement.setInt(7,0);
        return preparedStatement.executeUpdate();
    }

    //::::>
    public void showCinemaTickets(String cinemaName) throws SQLException {
        String showCinema = "SELECT * TicketTable WHERE cinemaName = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(showCinema);
        preparedStatement.setString(1,cinemaName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
            System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinemaName")
                    + "    |filmName =" + resultSet.getString("filmName")+"    |date= "+ resultSet.getString("datetime") +
                      "    |clock =" + resultSet.getString("clock"));
    }

    //::::>
    public void delTicket(Integer id) throws {
        String del = "DELETE FROM TicketTable WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(del);
        preparedStatement.setInt(1,id);
    }

    //::::>
    public Date returnDateTime(Integer id) throws SQLException {
        String dateTime = "SELECT datetime FROM TicketTable WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(dateTime);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getDate("datetime");
        }
        else
            return null;
    }

    public Time returnClock(Integer id) throws SQLException {
        String clock = "SELECT clock FROM TicketTable WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(clock);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getTime("clock");
        }
    }
    //::::>
    public void showAllTicket() throws SQLException {
        String show = "SELECT * FROM TicketTable ";
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
            System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinemaName")
                    + "    |filmName =" + resultSet.getString("filmName")+"    |Date= "+ resultSet.getString("datetime") + "    |clock=" + resultSet.getString("clock")
                    + "    |numberTicket= " + resultSet.getInt("numberTicket") + "     |price= " + resultSet.getInt("price") + "     |numberOfBuy= " + resultSet.getInt("numberBuy"));
    }

    //::::>
    public void searchWithName(String filmName,Date timeDate) throws SQLException {
        String searchName = " SELECT * TicketTable filmName = ? AND datetime = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(searchName);
        preparedStatement.setString(1,filmName);
        preparedStatement.setDate(2, (java.sql.Date) timeDate);
        ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
                System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinemaName")
                        + "    |filmName =" + resultSet.getString("filmName")+"    |datetime= "+ resultSet.getString("datetime")
                        + "    |clock = " + resultSet.getString("clock")
                        + "    |numberTicket= " + resultSet.getInt("numberTicket") + "     |price= " + resultSet.getInt("price") + "    | numberOfBuy= " + resultSet.getInt("numberBuy"));

    }

    //::::>
    public String[] getTicketInformation(int id) throws SQLException {
        String[] resultArray = new String[3];
        String information = "SELECT * FROM TicketTable WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(information);
        preparedStatement.setInt(3,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            resultArray[0] = resultSet.getString("filmName");
            resultArray[1] = String.valueOf(resultSet.getInt("numberTicket"));
            resultArray[2] = String.valueOf(resultSet.getInt("price"));
            return resultArray;
        }
        else
            return null;
    }

    //::::>
    public int updateNumberOfTicket(int id,Integer number) throws SQLException {
        String updateNumberTicket = "DELETE TicketTable SET numberTicket = ? WHERE id = ?  ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateNumberTicket);
        preparedStatement.setInt(1,number);
        preparedStatement.setInt(2,id);
        return preparedStatement.executeUpdate();
    }

    //::::>
    public int allBuyTicket(int id) throws SQLException {
        String number = " SELECT * FROM TicketTable WHERE idUser = ? " ;
        PreparedStatement preparedStatement = connection.prepareStatement(number);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("numberBuy");
    }

    //::::>
    public void updateNumberTicketBuy(int id,int number) throws {
        String updateNumber = " UPDATE TicketTable SET numberBuy = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(updateNumber);
        preparedStatement.setInt(1,number);
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();
    }

    //::::>
    public void showListHighPurchase(String cinemaname) throws SQLException {
        String show = "SELECT * FROM ticketTable WHERE cinemaName = ? ORDER BY numberBuy DESC" ;
        PreparedStatement preparedStatement = connection.prepareStatement(show);
        preparedStatement.setString(1,cinemaName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
            System.out.println("id=" + resultSet.getString("id")+ "    |cinemaName=" + resultSet.getString("cinemaName")
                    + "    |filmName =" + resultSet.getString("filmName")+"    |Date= "+ resultSet.getString("datetime") + "    |clock=" + resultSet.getString("clock")
                    + "    |numberTicket= " + resultSet.getInt("numberTicket") + "     |price= " + resultSet.getInt("price") + "     |numberOfBuy= " + resultSet.getInt("numberBuy"));
    }






}
