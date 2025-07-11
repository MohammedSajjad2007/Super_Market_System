import java.util.*;

// 1. Product class
class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// 2. CartItem class
class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getItemTotal() {
        return product.price * quantity;
    }
}

// 3. Cart class
class Cart {
    List<CartItem> items = new ArrayList<>();

    // 4. Add item to cart
    void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
        System.out.println(product.name + " (Qty: " + quantity + ") added.");
    }

    // 5. Display cart items
    void showCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("\n--- Cart Contents ---");
        for (CartItem item : items) {
            System.out.println(item.product.name + " x " + item.quantity + " = Rs. " + item.getItemTotal());
        }
    }

    // 6. Calculate total
    double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getItemTotal();
        }
        return total;
    }

    // 7. Apply discount if total > 1000
    double applyDiscount(double total) {
        if (total > 1000) {
            return total * 0.10; // 10% discount
        }
        return 0;
    }

    // 8. Show final bill
    void showBill() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        showCart();
        double total = calculateTotal();
        double discount = applyDiscount(total);
        double finalAmount = total - discount;

        System.out.println("\nTotal: Rs. " + total);
        System.out.println("Discount: Rs. " + discount);
        System.out.println("Final Bill: Rs. " + finalAmount);
    }
}

// 9. Main POS class
class SimpleSupermarketPOS {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 10. Product list
        List<Product> products = Arrays.asList(
                new Product("Milk", 250),
                new Product("Bread", 150),
                new Product("Eggs", 300),
                new Product("Rice", 500),
                new Product("Sugar", 200),
                new Product("Tea", 180),
                new Product("Oil", 450),
                new Product("Soap", 90),
                new Product("Shampoo", 300),
                new Product("Salt", 60)
        );

        Cart cart = new Cart();
        int choice;

        do {
            // 1. Show main menu
            System.out.println("\n--- Supermarket POS ---");
            System.out.println("1. Show Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Show Cart");
            System.out.println("4. Show Bill");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Products:");
                    for (int i = 0; i < products.size(); i++) {
                        Product p = products.get(i);
                        System.out.println((i + 1) + ". " + p.name + " - Rs. " + p.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter product number: ");
                    int num = sc.nextInt();
                    if (num >= 1 && num <= products.size()) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        if (qty > 0) {
                            cart.addItem(products.get(num - 1), qty);
                        } else {
                            System.out.println("Invalid quantity.");
                        }
                    } else {
                        System.out.println("Invalid product number.");
                    }
                    break;

                case 3:
                    cart.showCart();
                    break;

                case 4:
                    cart.showBill();
                    break;

                case 5:
                    System.out.println("Thank you for shopping!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}