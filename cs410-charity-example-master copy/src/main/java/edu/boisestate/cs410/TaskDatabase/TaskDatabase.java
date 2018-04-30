package edu.boisestate.cs410.TaskDatabase;

import com.budhash.cliche.Command;
import com.budhash.cliche.ShellFactory;

import java.io.IOException;
import java.sql.*;

public class TaskDatabase {
	private final Connection db;

	public TaskDatabase(Connection cxn) {
		db = cxn;
	}

	// 1. View currently-active tasks
	@Command
	public void active() throws SQLException {
		String active = "SELECT task_id, task_label, date_created, due_date" + "FROM task WHERE status = 'active'";

		try (Statement stmt = db.createStatement(); ResultSet rs = stmt.executeQuery(active)) {
			System.out.format("Tasks: %n");

			while (rs.next()) {
				System.out.format("%d: %s%t%t%n", rs.getInt("task_id"), rs.getString("task_label"),
						rs.getString("date_created"), rs.getString("due_date"));
			}
		}
	}

	// 2. ADD TASK
	@Command
	public void addTask(int taskId, String taskLabel) throws SQLException {
		String addTask = "INSERT INTO task (task_id, task_label) VALUES (?, ?)";
		db.setAutoCommit(false);
		try {
			try (PreparedStatement stmt = db.prepareStatement(addTask, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setInt(1, taskId);
				stmt.setString(2, taskLabel);
				stmt.executeUpdate();
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

	// 3. Associate due dates with tasks - to make task 7 due on April 1
	@Command
	public void due(int taskId, String date) throws SQLException {
		String task7 = "UPDATE task SET due_date = ? WHERE task_id = ?;";
		db.setAutoCommit(false);

		try {
			try (PreparedStatement stmt = db.prepareStatement(task7, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setInt(1, taskId);
				stmt.setString(2, date);
				stmt.executeUpdate();
				System.out.format("Due date is changed.");
			}
			db.commit();
		} catch (SQLException | RuntimeException e) {
			db.rollback();
			throw e;
		} finally {
			db.setAutoCommit(true);
		}

	}

	// 4. Associate tags with tasks - to tag task 7 with 'school' and 'homework'
	@Command
	public void tag(int taskId, String association) throws SQLException {
		String updateKeyword = "UPDATE keywords SET keyword_details = ? WHERE task_id = ?;";
		db.setAutoCommit(false);

		try {
			try (PreparedStatement stmt = db.prepareStatement(updateKeyword, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setInt(1, taskId);
				stmt.setString(2, association);
				stmt.executeUpdate();
				System.out.format("Update the tags with task %d\n", taskId);
			}
			db.commit();
		} catch (SQLException | RuntimeException e) {
			db.rollback();
			throw e;
		} finally {
			db.setAutoCommit(true);
		}
	}

	// 5. Mark tasks as completed
	@Command
	public void finish(int taskId) throws SQLException {
		String completeTask = "UPDATE task SET status = 'completed' WHERE task_id = ?";
		db.setAutoCommit(false);

		try {
			try (PreparedStatement stmt = db.prepareStatement(completeTask, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setInt(1, taskId);
				stmt.executeUpdate();
				System.out.format("Tasks marked as completed\n");
			}
			db.commit();
		} catch (SQLException | RuntimeException e) {
			db.rollback();
			throw e;
		} finally {
			db.setAutoCommit(true);
		}
	}

	// 6. Mark tasks as canceled
	@Command
	public void cancel(int taskId) throws SQLException {
		String canceledTask = "UPDATE task SET status = 'canceled' WHERE task_id = ?";
		db.setAutoCommit(false);

		try {
			try (PreparedStatement stmt = db.prepareStatement(canceledTask, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setInt(1, taskId);
				stmt.executeUpdate();
				System.out.format("Tasks marked as cancelled\n");
			}
			db.commit();
		} catch (SQLException | RuntimeException e) {
			db.rollback();
			throw e;
		} finally {
			db.setAutoCommit(true);
		}
	}

	// 7. Show details for a task
	@Command
	public void show(int taskId) throws SQLException {
		String details = "SELECT task_id, task_label, description FROM task WHERE task_id = ?";

		try (PreparedStatement stmt = db.prepareStatement(details)) {
			stmt.setInt(1, taskId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					System.out.format("details of %d", taskId);
					return;
				}
				System.out.format("Task ID: %d%n", rs.getInt("task_id"));
				System.out.format("Task Label: %s%n", rs.getString("task_label"));
				System.out.format("Description of task: s%n", rs.getString("description"));
			}
		}
	}

	// 8. Show active tasks for a tag
	@Command
	public void activeTask(String tag) throws SQLException {
		String activeTask = "SELECT task_id, task_label, description, status, keyword_details"
				+ "FROM task JOIN keywords USING(task_id)\n" + "WHERE status = 'active'\n" + "AND keyword_details = ?;";

		try (PreparedStatement stmt = db.prepareStatement(activeTask)) {
			stmt.setString(1, tag);
			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					System.err.format("%s does not exist%n", tag);
					return;
				}
				System.out.format("%d%n", rs.getInt("task_id"));
				System.out.format("%s%n", rs.getString("task_label"));
				System.out.format("%s%n", rs.getString("description"));
				System.out.format("%s%n", rs.getString("status"));
				System.out.format("%s%n", rs.getString("keyword_details"));
			}
		}
	}

	// 9. Show completed tasks for a tag
	@Command
	public void completed(String tag) throws SQLException {
		String activeTask = "SELECT task_id, task_label, description, status, keyword_details"
				+ "FROM task JOIN keywords USING (task_id)" + "WHERE status = 'completed'" + "AND keyword_details = ?;";

		try (PreparedStatement stmt = db.prepareStatement(activeTask)) {
			stmt.setString(1, tag);
			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					System.err.format("%s does not exist%n", tag);
					return;
				}
				System.out.format("%d%n", rs.getInt("task_id"));
				System.out.format("%s%n", rs.getString("task_label"));
				System.out.format("%s%n", rs.getString("description"));
				System.out.format("%s%n", rs.getString("status"));
				System.out.format("%s%n", rs.getString("keyword_details"));
			}
		}
	}

	// 10. Show tasks overdue (due date in the past but not completed)
	@Command
	public void overdue() throws SQLException {
		String overdue = "SELECT task_id, task_label, description, status" + "FROM task" + "WHERE status = 'overdue'";
		try (Statement stmt = db.createStatement(); ResultSet rs = stmt.executeQuery(overdue)) {
			System.out.format("Tasks: %n");

			while (rs.next()) {
				System.out.format("%d: %s%s%s%n", rs.getInt("task_id"), rs.getString("task_label"),
						rs.getString("description"), rs.getString("status"));
			}
		}
	}

	// TODO
	// 11. Tasks due today, or due in the next 3 days
	@Command
	public void due() throws SQLException {

	}

	// 12. Change the label of a task
	@Command
	public void rename(int taskId, String task_label) throws SQLException {
		String rename = "UPDATE task SET task_label = ? WHERE task_id = ?";
		db.setAutoCommit(false);

		try {
			try (PreparedStatement stmt = db.prepareStatement(rename, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setString(1, task_label);
				stmt.setInt(2, taskId);
				stmt.executeUpdate();
				System.out.format("Renaming the label of task %d%n to %s%n", taskId, task_label);
			}
			db.commit();
		} catch (SQLException | RuntimeException e) {
			db.rollback();
			throw e;
		} finally {
			db.setAutoCommit(true);
		}
	}

	// 13. Search for tasks by keyword
	@Command
	public void search(String tag) throws SQLException {
		String searchTask = "SELECT task_id, task_label, description, keyword_details"
				+ "FROM task JOIN keywords USING (task_id)"

				+ "WHERE keyword_details = ?;";
		try (PreparedStatement stmt = db.prepareStatement(searchTask)) {
			stmt.setString(1, tag);

			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					System.out.format("search task for %s keyword", tag);
					return;
				}
				System.out.format("%d%n", rs.getInt("task_id"));
				System.out.format("%s%n", rs.getString("task_label"));
				System.out.format("%s%n", rs.getString("description"));
				System.out.format("%s%n", rs.getString("keyword_details"));
			}
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
			TaskDatabase shell = new TaskDatabase(cxn);
			ShellFactory.createConsoleShell("\nTask Database", "", shell).commandLoop();
		}
	}
}
