package com.pluralsight;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;




public class StoreApp {

    private static final String PRODUCT_FILE = "data/products.csv";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========Welcome To ZAM-ZAM Store===========");

        //Getting Inventory
        ArrayList<Products> inventory = loadInventory();


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
                    processDisplayAllProducts(inventory);
                    break;

                case 2:
                    processDisplayCart();
                    break;

                case 3:
                    System.out.println(" Thank you for using ZAM-ZAM store");

                    break;

                default:
                    System.out.println("Invalid option. Please try again");


            }


        } while (userOption != 3);



    }


//Load products from CSV

    public static ArrayList<Products> loadInventory(){
        ArrayList<Products> products = new ArrayList<>();

        try {

            //File And Buffered Readers
//            FileReader fr = new FileReader("data/inventory.csv");
            BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE));

            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {
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



//DIsplay products
    public static void processDisplayAllProducts(ArrayList<Products> inventory) {

        for (int i = 0; i < inventory.size(); i++) {

            Products p = inventory.get(i);

            System.out.printf("%s | %s | $%.2f | %s%n",
                    p.getProductSku(),
                    p.getProductName(),
                    p.getProductPrice(),
                    p.getProductDepartment());
        }
    }

    public static void processDisplayCart(){




    }






}









































