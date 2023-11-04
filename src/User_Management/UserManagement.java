package User_Management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import db_operation.DbUtil;

public class UserManagement {

	public static void Management() throws IOException {
		Scanner sc = new Scanner(System.in);
		boolean canIKeepRunningTheProgram = true;
		while (canIKeepRunningTheProgram == true) {
			System.out.println("*Welcome to user_management*");
			System.out.println("\n");
			System.out.println("what would you like to do?");
			System.out.println("1. Add user");
			System.out.println("2. Edit user");
			System.out.println("3. delete user");
			System.out.println("4. search user");
			System.out.println("5. Quit");

			int optionSelectedByUser = sc.nextInt();

			if (optionSelectedByUser == UserOptions.QUIT_USER) {
				System.out.println("Program closed");
				canIKeepRunningTheProgram = false;
				System.out.println("After while loop");

			} else if (optionSelectedByUser == UserOptions.ADD_USER) {
				addUser();
				System.out.println("\n");

			} else if (optionSelectedByUser == UserOptions.SEARCH_USER) {
				System.out.println("Enter your name to search");
				sc.nextLine();
				String sn = sc.nextLine();
				SearchUser(sn);
				System.out.println("\n");

			} else if (optionSelectedByUser == UserOptions.DELETE_USER) {
				System.out.println("Enter your Name to delete");
				sc.nextLine();
				String deleteuserName = sc.nextLine();
				deleteuserName(deleteuserName);
				System.out.println("\n");

			} else if (optionSelectedByUser == UserOptions.EDIT_USER) {
				System.out.println("Enter your Name to edit");
				sc.nextLine();
				String edituserName = sc.nextLine();
				edituserName(edituserName);
				System.out.println("\n");
			}
		}

	}

	public static void addUser() {

		User User = new User();
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter user name");
		User.userName = sc.nextLine();

		System.out.println("Enter login name");
		User.loginName = sc.nextLine();

		System.out.println("Enter Password");
		User.Password = sc.nextLine();

		System.out.println("Enter confirm Password");
		User.ConfirmPassword = sc.nextLine();

		System.out.println("Enter User Role");
		User.userRole = sc.nextLine();

		System.out.println("userName" + "\t" + User.userName);
		System.out.println("loginName" + "\t" + User.loginName);
		System.out.println("Password" + "\t" + User.Password);
		System.out.println("ConfirmPassword" + "\t" + User.ConfirmPassword);
		System.out.println("userRole" + "\t" + User.userRole);

		String query = "INSERT INTO users(userName,loginName,Password,ConfirmPassword,userRole) VALUES ('"
				+ User.userName + "','" + User.loginName + "','" + User.Password + "','" + User.ConfirmPassword + "','"
				+ User.userRole + "')";
		DbUtil.executeQuery(query);

	}

	public static void SearchUser(String userName) {
		String query = "select* from users where userName='" + userName + "' ";
		ResultSet rs = DbUtil.executeQueryGetResult(query);
		try {
			while (rs.next()) {
				if (rs.getString("userName").equalsIgnoreCase(userName)) {
					System.out.println("user Name: " + rs.getString("userName"));
					System.out.println("login Name: " + rs.getString("loginName"));
					System.out.println("Password: " + rs.getString("Password"));
					System.out.println("ConfirmPassword: " + rs.getString("ConfirmPassword"));
					System.out.println("userRole: " + rs.getString("userRole"));
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("User Not Found");
		}
	}

	public static void deleteuserName(String userName) {
		String query = "delete from users where userName='" + userName + "' ";
		DbUtil.executeQuery(query);
	}

	public static void edituserName(String userName) {

		String Query = "select* from users where userName='" + userName + "' ";
		ResultSet rs = DbUtil.executeQueryGetResult(Query);

		try {
			if (rs.next()) {
				Scanner sc = new Scanner(System.in);

				System.out.println("Enter new user name: ");
				String newuserName = sc.nextLine();

				System.out.println("new loginName name: ");
				String newloginName = sc.nextLine();

				System.out.println("Enter new Password: ");
				String newPassword = sc.nextLine();

				System.out.println("Enter new confirm Password: ");
				String newConfirmPassword = sc.nextLine();

				System.out.println("Enter new user Role: ");
				String newuserRole = sc.nextLine();

				String query = "UPDATE users SET userName = '" + newuserName + "', " + "loginName = '" + newloginName
						+ "', " + "Password = '" + newPassword + "', " + "ConfirmPassword = '" + newConfirmPassword
						+ "'," + "userRole = '" + newuserRole + "'" + "WHERE userName = '" + userName + "'";
				DbUtil.executeQuery(query);

				System.out.println("USER INFORMATION IS UPDATE");

			} else {
				System.out.println("user not found");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean validateUserandPassword(String loginName, String Password) throws SQLException {
		String query = "select*from users where loginName='" + loginName + "' and Password='" + Password + "'";
		ResultSet rs = DbUtil.executeQueryGetResult(query);
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
