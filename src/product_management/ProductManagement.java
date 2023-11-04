package product_management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import db_operation.DbUtil;



public class ProductManagement {
	

	// ******* Main Function*******
	public static void ProductManagement() throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		boolean CanIKeepRunningTheProgram = true;

		while (CanIKeepRunningTheProgram == true) {

			System.out.println("**** Welcome to Product Management****");
			System.out.println("\n");

			System.out.println("What would you like to do?");
			System.out.println("1. Add Product");
			System.out.println("2. Edit Product");
			System.out.println("3. Delete Product");
			System.out.println("4. Search Product");
			System.out.println("5. Quit");

			int optionSelectedByUser = scanner.nextInt();
			if (optionSelectedByUser == ProductOptions.QUIT_PRODUCT) {

				System.out.println("!!! Program Closed");
				CanIKeepRunningTheProgram = false;
			} else if (optionSelectedByUser == ProductOptions.ADD_PRODUCT) {
				addProduct();
				System.out.println("\n");
			} else if (optionSelectedByUser == ProductOptions.SEARCH_PRODUCT) {
				System.out.println("Enter Product Name to Search:");
				scanner.nextLine();
				String sn = scanner.nextLine();
				searchProduct(sn);
				System.out.println("\n");

			} else if (optionSelectedByUser == ProductOptions.DELETE_PRODUCT) {
				System.out.println("Enter Product Name to delete:");
				scanner.nextLine();
				String deleteProductName = scanner.nextLine();
				deleteProduct(deleteProductName);
				System.out.println("\n");

			} else if (optionSelectedByUser == ProductOptions.EDIT_PRODUCT) {
				System.out.println("Enter Product Name to edit:");
				scanner.nextLine();
				String editProductName = scanner.nextLine();
				editProduct(editProductName);
				System.out.println("\n");
			}
		}
	}

	// ***** Add Product Function*****
	public static void addProduct() throws IOException {
		Scanner scanner = new Scanner(System.in);
		Product userObject = new Product();

		System.out.print("Product Name: ");
		userObject.ProductName = scanner.nextLine();

		System.out.print("Product ID: ");
		userObject.ProductID = scanner.nextLine();

		System.out.print("Price:");
		userObject.Price = scanner.nextLine();

		System.out.print("Quantity: ");
		userObject.Quantity = scanner.nextLine();

		System.out.print("Categoy: ");
		userObject.Category = scanner.nextLine();

		System.out.println(" Product Name: " + userObject.ProductName);
		System.out.println(" ProductID: " + userObject.ProductID);
		System.out.println(" Price: " + userObject.Price);
		System.out.println(" Quantity: " + userObject.Quantity);
		System.out.println(" Category: " + userObject.Category);

		String query="INSERT INTO products(Product_Name,Product_ID,price,quantity,category) VALUES ('"+userObject.ProductName+"','"+userObject.ProductID+"','"+ userObject.Price+"','"+userObject.Quantity+"','"+userObject.Category+"')";
		DbUtil.executeQuery(query);

	}

	public static void searchProduct(String ProductName) {
		String query="select* from products where Product_Name='"+ ProductName+"' ";
		ResultSet rs= DbUtil.executeQueryGetResult(query);
		try {
			while(rs.next()) {
			if (rs.getString("Product_Name").equalsIgnoreCase(ProductName)) {
				System.out.println("Product Name: " + rs.getString("Product_Name"));
				System.out.println("Product ID: " + rs.getString("Product_ID"));
				System.out.println("Price: " + rs.getString("price"));
				System.out.println("Quantity: " + rs.getString("quantity"));
				System.out.println("Category: "  + rs.getString("category"));
				return;
			}
			}
		}
		catch(Exception e) {
		System.out.println("Product Not Found");
	}
		}
	

	public static void deleteProduct(String ProductName) {
		String query="delete from products where Product_Name='"+ ProductName+"' ";
		DbUtil.executeQuery(query);
	}


	public static void editProduct(String ProductName) {
		String query="select* from products where Product_Name='"+ ProductName+"' ";
		ResultSet rs= DbUtil.executeQueryGetResult(query);
		
		try {
			if(rs.next()) {
				Scanner scanner = new Scanner(System.in);
				
				System.out.println(" New Product Name: ");
				String newProductName = scanner.nextLine();

				System.out.println(" New Product ID: ");
				String newProductID = scanner.nextLine();

				System.out.println(" New Price:");
				String newPrice = scanner.nextLine();

				System.out.println(" New Quantity: ");
				String newQuantity = scanner.nextLine();

				System.out.println(" New Category: ");
				String newCategory = scanner.nextLine();

				
				String updateQuery=  "UPDATE products SET Product_Name = '"+newProductName+"' ,"
						+ "Product_ID = '"+newProductID+"',"
						+ "price = '"+newPrice+"',"
						+ "quantity ='"+newQuantity+"',"
						+ "category ='"+newCategory+"'"
						+ "WHERE Product_Name = '"+ProductName+"'";
				DbUtil.executeQuery(updateQuery);	
						
						
						System.out.println("PRODUCT INFORMATION IS UPDATE");

				}
			else {
				System.out.println("Product not found");
			}
		}catch(Exception e) {
			e.printStackTrace();
			}
		}
	  }
			