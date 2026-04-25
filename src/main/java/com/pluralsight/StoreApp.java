package com.pluralsight;
import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
//java.util.Map;





public class StoreApp {

    private static final String PRODUCT_FILE = "data/products.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("========== Welcome To ZAM-ZAM Store ===========");

        //Inventory
        ArrayList<Products> inventory = loadInventory();
        ArrayList<Products> cart = new ArrayList<>();


        //Displaying products from inventory
        int userOption;
        do {
            String mainMenuPrompt = """
                    What do you want to do?
                     1- Display Products
                     2- Display Cart
                     3- Exit
                     Enter command: """;


            System.out.println(mainMenuPrompt);
            userOption = scanner.nextInt();
            scanner.nextLine();


            // Store Home Screen

            switch (userOption) {
                case 1:
                    processDisplayAllProducts(inventory, cart, scanner);
                    break;

                case 2:
                    processDisplayCart(cart);
                    break;

                case 3:
                    System.out.println(" Thank you for using ZAM-ZAM store");

                    break;

                default:
                    System.out.println("Invalid option. Please try again");


            }


        } while (userOption != 3);


    }


//Load Inventory

    public static ArrayList<Products> loadInventory() {
        ArrayList<Products> products = new ArrayList<>();

        try {

            //Buffered Reader
            BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE));

            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; //Skipping empty spaces
                Products p = getProducts(line);
                products.add(p);

            }

            br.close();


        } catch (IOException e) {
            System.out.println("There was an error: " + e.getMessage());
        }

        return products;


    }

    public static Products getProducts(String line) {

        String[] parts = line.split("\\|");


        String sku = parts[0];
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        String department = parts[3];

        return new Products(name, sku, price, department);
    }


//Display products


    public static void processDisplayAllProducts(ArrayList<Products> inventory, ArrayList<Products> cart, Scanner scanner) {

        System.out.println("========= Available Products =========");

        for (int i = 0; i < inventory.size(); i++) {

            Products p = inventory.get(i);


            System.out.printf("%s | %s | $%.2f | %s%n",
                    p.getProductSku(),
                    p.getProductName(),
                    p.getProductPrice(),
                    p.getProductDepartment());


        }

        int option;

        do {

            String cartMenuPrompt = """
                    What Do You Want To Do Next?
                    1- Search or filter the list of products
                    2- Add a product to cart
                    3- Go back to home page
                    Enter Command: """;

            System.out.println(cartMenuPrompt);
            option = scanner.nextInt();
            scanner.nextLine();


            switch (option) {

                case 1:
                    processSearchProducts(inventory, scanner);
                    break;

                case 2:
                    processAddProductToCart(inventory, cart, scanner);
                    break;

                case 3:
                    System.out.println("Return to the main menu");
                    break;

                default:
                    System.out.println("Invalid Entry, try again");

            }


        } while (option != 3);


    }


    public static void processSearchProducts(ArrayList<Products> inventory, Scanner scanner) {

        System.out.println("Please, enter product name or department ");
        String userChoice = scanner.nextLine().toLowerCase();

        boolean found = false;

        for ( Products p : inventory) {

            if (p.getProductName().toLowerCase().contains(userChoice) || p.getProductDepartment().toLowerCase().contains(userChoice)){
                System.out.printf("%s | %s | $%.2f | %s%n",
                p.getProductSku(),
                p.getProductName(),
                p.getProductPrice(),
                p.getProductDepartment());

                found = true;

            }
        }

        if (!found){
            System.out.println("Sorry, product unavailable");


        }


    }


    public static void processDisplayCart(ArrayList<Products> cart) {

        if (cart.isEmpty()){
            System.out.println("Your shopping car is empty, please add products to the cart");
            return;
        }

        double total = 0;
        System.out.println("========== Your Cart ===========");
        for (Products p : cart){
            System.out.printf("%s | %s | $%.2f | %s%n",
                    p.getProductSku(),
                    p.getProductName(),
                    p.getProductPrice(),
                    p.getProductDepartment());
            total += p.getProductPrice();

        }

        System.out.printf("Total: $%.2f%n", total);




     }



     public static void processAddProductToCart(ArrayList<Products> inventory, ArrayList<Products> cart, Scanner scanner){
        String sku = scanner.nextLine();

        for (Products p: inventory){
            if (p.getProductSku().equalsIgnoreCase(sku)){
                cart.add(p);
                System.out.println(p.getProductName() + " added to cart.");
                return;
            }else {
                System.out.println("Product not found.");
            }

        }



     }


}























































