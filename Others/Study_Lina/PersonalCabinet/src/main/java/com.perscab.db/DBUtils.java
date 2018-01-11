package com.perscab.db;

import com.perscab.model.Account;
import com.perscab.model.AccountStatus;

import static com.perscab.db.DBQueries.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    public static Account findAccount(Connection conn, String login) throws SQLException {

        PreparedStatement pstm = conn.prepareStatement(GET_ACCOUNT_BY_USER_LOGIN);
        pstm.setString(1, login);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return getAccountFromResultSet(rs);
        }
        return null;
    }

    private static Account getAccountFromResultSet(ResultSet rs) throws SQLException {

        Account account = Account.newBuilder()
                .setId(rs.getInt("id"))
                .setLogin(rs.getString("login"))
                .setPassword(rs.getString("password"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"))
                .setStatus(AccountStatus.getAccountStatusByValue(rs.getString("status")))
                .setEmail(rs.getString("email"))
                .setAddress(rs.getString("address")).build();

        return account;
    }

}
