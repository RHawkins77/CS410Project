package edu.boisestate.cs410.charity;

import com.budhash.cliche.Command;
import com.budhash.cliche.ShellFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

public class CharityShell {
    private final Connection db;

    public CharityShell(Connection cxn) {
        db = cxn;
    }

    @Command
    public void funds() throws SQLException {
        String query = "SELECT fund_id, fund_name FROM fund";
        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.format("Funds:%n");
            while (rs.next()) {
                System.out.format("%d: %s%n",
                                  rs.getInt("fund_id"),
                                  rs.getString("fund_name"));
            }
        }
    }

    @Command
    public void donor(int id) throws SQLException {
        String query = "SELECT donor_name, donor_address, donor_city, donor_state, donor_zip FROM donor WHERE donor_id = ?";
        try (PreparedStatement stmt = db.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    System.err.format("%d: donor does not exist%n", id);
                    return;
                }
                System.out.format("%s%n", rs.getString("donor_name"));
                System.out.format("%s%n", rs.getString("donor_address"));
                System.out.format("%s, %s %s%n",
                                  rs.getString("donor_city"),
                                  rs.getString("donor_state"),
                                  rs.getString("donor_zip"));
            }
        }
    }

    @Command
    public void renameDonor(int id, String name) throws SQLException {
        String query = "UPDATE donor SET donor_name = ? WHERE donor_id = ?";
        try (PreparedStatement stmt = db.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            System.out.format("Renaming donor %d to %s%n", id, name);
            int nrows = stmt.executeUpdate();
            System.out.format("updated %d donors%n", nrows);
        }
    }
    
    //ADD TASK
    @Command
    public void addTask(String taskLabel) throws SQLException {
        String insertTask = "INSERT INTO task (task_label) VALUES (?)";
        int taskId;
        db.setAutoCommit(false);
        try {
            try (PreparedStatement stmt = db.prepareStatement(insertTask, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, taskLabel);
                stmt.executeUpdate();
                // fetch the generated gift_id!
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (!rs.next()) {
                        throw new RuntimeException("no generated keys???");
                    }
                    taskId = rs.getInt(1);
                    System.out.format("Creating task %d%n", taskId);
                }
            }
            db.commit();
        } catch (SQLException | RuntimeException e) {
            db.rollback();
            throw e;
        } finally {
            db.setAutoCommit(true);
        }
    }

    @Command
    public void echo(String... args) {
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                System.out.print(' ');
            }
            System.out.print(args[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException, SQLException {
        String dbUrl = args[0];
        try (Connection cxn = DriverManager.getConnection("jdbc:" + dbUrl)) {
            CharityShell shell = new CharityShell(cxn);
            ShellFactory.createConsoleShell("charity", "", shell)
                        .commandLoop();
        }
    }
}
