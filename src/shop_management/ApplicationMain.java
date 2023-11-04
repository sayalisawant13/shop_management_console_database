package shop_management;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import User_Management.UserManagement;

public class ApplicationMain {
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		Scanner Scanner = new Scanner(System.in);
		boolean canikeeprunningprogram = true;

		System.out.println("**WELCOME TO SHOP MANAGEMENT");
		System.out.println("/n");
		System.out.println("Enter login Name  is");
		String loginName = Scanner.nextLine();
		System.out.println("Enter Password is:");
		String password = Scanner.nextLine();

		if (!UserManagement.validateUserandPassword(loginName, password)) {
			System.out.println("!!!Login failed.closing the application..");
			return;

		}
		while (canikeeprunningprogram == true) {
			System.out.println("##WELCOME TO SHOP MANAGEMENT##");
			System.out.println("/n");
			System.out.println("What would like to do ?");
			System.out.println("1. User Management");
			System.out.println("2.Product Management");
			System.out.println("5.Quit");

			int optionselectedbyuser = Scanner.nextInt();
			if (optionselectedbyuser == 1) {
				UserManagement.Management();
			} else if (optionselectedbyuser == 2) {
				product_management.ProductManagement.ProductManagement();
			} else if (optionselectedbyuser == 5) {
				break;
			}
		}

	}

}
