package com.pluralsight;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;







public class StoreApp {

    private static final String PRODUCT_FILE = "data/products.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("========== Welcome To ZAM-ZAM Store ===========");

        //Loading Inventory
        ArrayList<Products> inventory = loadInventory();
        ArrayList<Products> cart = new ArrayList<>();


        //Home Menu Screen
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


            // Display products and cart methods. I will call each of the methods based on the user option

            switch (userOption) {
                case 1:
                    displayAllProducts(inventory, cart, scanner);
                    break;

                case 2:
                    displayCart(cart);
                    break;

                case 3:
                    System.out.println(" Thank you for using ZAM-ZAM store"); //Exitting the application

                    break;

                default:
                    System.out.println("Invalid option. Please try again");


            }


        } while (userOption != 3);//Application stops when user chooses option 3


    }


// Load Inventory method implementing exception handlers

    public static ArrayList<Products> loadInventory() {
        ArrayList<Products> products = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE));

            br.readLine();
            String line;

            //Reading and converting products objects
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; //Skipping empty lines
                Products p = getProducts(line);
                products.add(p);

            }

            br.close();


        } catch (IOException e) {
            System.out.println("There was an error: " + e.getMessage());
        }

        return products;


    }


        //
    public static Products getProducts(String line) {

        String[] parts = line.split("\\|");

        String sku = parts[0];
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        String department = parts[3];

        return new Products(name, sku, price, department);
    }


//Displaying all products available in the inventory

    public static void displayAllProducts(ArrayList<Products> inventory, ArrayList<Products> cart, Scanner scanner) {

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
                        searchProducts(inventory, scanner);
                    break;

                case 2:
                        addProductToCart(inventory, cart, scanner);
                    break;

                case 3:
                    System.out.println("Return to the main menu");
                    break;

                default:
                    System.out.println("Invalid Entry, try again");

            }


        } while (option != 3);


    }

    //This method allows custumers to search for any peoduct of their choice
    public static void searchProducts(ArrayList<Products> inventory, Scanner scanner) {

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


    //THis method allows users to add products to their shopping cart
    public static void addProductToCart(ArrayList<Products> inventory, ArrayList<Products> cart, Scanner scanner){

        System.out.println("Please enter the product sku");
        String sku = scanner.nextLine();

        boolean found = false;

        for (Products p: inventory){
            if (p.getProductSku().equalsIgnoreCase(sku)){
                cart.add(p);

                System.out.println(p.getProductName() + " successfully added to cart.");

                found =true;
                return;
            }

        }

         if (!found) {
             System.out.println("Product not found.");
         }

    }

   //Displaying products added in the cart
    public static void displayCart(ArrayList<Products> cart) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("""
                   
                   What do you want to do?
                   1-checkout
                   2-Remove Product
                   3-Go back
                   """);

        int option = scanner.nextInt();
        scanner.nextLine();

           switch (option) {
               case 1:
                   checkout(cart, scanner);
                   break;
               case 2:
                   removeProductFromCart(cart, scanner);
                   break;
               case 3:
                   System.out.println("Return to the main menu");
                   break;

           }

           if (cart.isEmpty()){
            System.out.println("Your shopping car is empty, please add products to the cart");
            return;
           }


        //Displaying the total price of products in the cart
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


    public static void removeProductFromCart(ArrayList<Products> cart, Scanner scanner){

//        System.out.println("Please enter product sku");

//        cart.remove();

        if (cart.isEmpty()){
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Please enter product sku");
        String sku = scanner.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < cart.size(); i++) {
            Products p = cart.get(i);

            if (p.getProductSku().equalsIgnoreCase(sku)){
                cart.remove(i);


                System.out.println("Do you want to remove items? Yes/no");
                String option = scanner.nextLine();

                if (option.equalsIgnoreCase("yes")){

                    System.out.println(p.getProductName() + " removed from cart");
                    found = true;
                    break;

                } else if (!found){
                    System.out.println("product not found");

                }




//                System.out.println(p.getProductName() + " removed from cart");
//                found = true;
//                break;
            }
        }

//        if(!found){
//            System.out.println("product not found");
//        }


    }





  //Checking out
    public static void checkout(ArrayList<Products> cart, Scanner scanner){

        if (cart.isEmpty()){
            System.out.println("Your cart is empty");
            return;
        }

        double total = 0;

        System.out.println("========== Checkout ===========");
        for(Products p : cart) {
            System.out.printf("%s | %s | $%.2f | %s%n" ,
                    p.getProductSku(),
                    p.getProductName(),
                    p.getProductPrice(),
                    p.getProductDepartment());

            total += p.getProductPrice();
        }

        System.out.printf("Total: $%.2f%n", total);
        System.out.println("Do you want to proceed to checkout? yes/no: ");
        String process = scanner.nextLine();

        if (process.equalsIgnoreCase("yes")){
            cart.clear();
            System.out.println("Checkout successfully complete. Thank you!");

        }else {
            System.out.println("Checkout cancelled");
        }





    }








}























































