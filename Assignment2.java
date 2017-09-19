import java.util.*;

public class Assignment2 {
    // Q1
    public double employeeSalary(double hours) {
        double salary = 0;
        if (hours <= 36) {
            salary = hours * 15;
        } else if (hours > 36 && hours <= 41) {
            salary = 36 * 15 + (hours - 36) * 15 * 1.5;
        } else if (hours > 41 && hours <= 48) {
            salary = 36 * 15 + 5 * 15 * 1.5 + (hours - 41) * 2;
        } else if (hours > 48) {
            salary = 36 * 15 + 5 * 15 * 1.5 + 7 * 2;
        }
        return salary;
    }

    // Q2
    public int addDigits(int input) {
        int res = 0;
        while (input > 0) {
            res += input % 10;
            input /= 10;
        }
        return res;
    }

    // Q3
    public void printPerfectNumbers(int n) {
        for (int i = 6; i <= n; i++) {
            if (isPerfectNumber(i)) {
                System.out.println(i);
            }
        }
    }

    private boolean isPerfectNumber(int n) {
        if (n == 1)
            return false;
        int sum = 1;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i != 0)
                continue;
            sum += i;
            if (i != n / i)
                sum += n / i;
        }
        return sum == n;
    }

    // Q4
    static class Pizza {
        public List<String> pizzaType = new ArrayList<>(Arrays.asList("cheese", "pepperoni", "meat lover"));
        public List<Double> prices = new ArrayList<>(Arrays.asList(5.0, 6.5, 7.0));
        public List<Integer> loyaltyPoints = new ArrayList<>(Arrays.asList(5, 6, 7));
    
        // when I instantiate class Pizza, I actually instantiate a menu of pizzas. Pizza type - price - loyalty point, they all have the same index number in their own list.
        public Pizza() {
    
        }
    
        // add more choice depending on different restaurant
        public Pizza(String type, double price, int point) {
            pizzaType.add(type);
            prices.add(price);
            loyaltyPoints.add(point);
        }
    
        
    
        public void showMenu() {
            for (int i = 0; i < pizzaType.size(); i++) {
                System.out.print("Type: " + pizzaType.get(i) + " ");
                System.out.print("Price: $" + prices.get(i) + " ");
                System.out.println("Loyalty points: " + loyaltyPoints.get(i));
            }
        }
    }

    static class Customer {
        String name;
        Map<String, Integer> pizzaOrdered = new HashMap<>(); // key is pizza type, value is the number customer would like to order
        int pointsGained;
        double cash;

        public Customer(String name, int pointsGained, double cash) {
            this.name = name;
            this.pointsGained = pointsGained;
            this.cash = cash;
        }

        public void orderPizza(String p, int n) {
            if (pizzaOrdered.get(p) == null) {
                pizzaOrdered.put(p, n);
            } else {
                int num = pizzaOrdered.get(p);
                pizzaOrdered.put(p, n + num);
            }
        }

        // In this checkOut method, it will output how much money the customer spent. Please check the example in main method. 
        public void checkOut() {
            double bill = 0;
            int loyaltyPoints = 0;
            Pizza menu = new Pizza();
            Set<String> keys = pizzaOrdered.keySet();
            for (String key : keys) {
                int idx = menu.pizzaType.indexOf(key);
                bill += menu.prices.get(idx) * pizzaOrdered.get(key);
                loyaltyPoints += menu.loyaltyPoints.get(idx) * pizzaOrdered.get(key);

                int num = pizzaOrdered.get(key);
                System.out.println(name + " ordered " + num + " " + key + " pizza;");
            }
            pointsGained += loyaltyPoints;
            if (bill > cash) {
                System.out.println("But You don't have enough cash to pay.");
            } else {
                cash -= bill;
                System.out.println("The bill is $" + bill + ".");
                System.out.println(name + " have $" + cash + " left in wallet.");
                System.out.println(name + " gained " + loyaltyPoints + " points this time and have " + pointsGained
                        + " points in total now.");
            }
        }

    }

    public static void main(String[] args) {
        // Pizza test = new Pizza("Supreme", 8.0, 8);
        // test.showMenu();
        System.out.println("Q3:");
        Assignment2 question3 = new Assignment2();
        question3.printPerfectNumbers(10000);

        System.out.println("Q4 and Q5:");
        Customer cus1 = new Customer("Ashley", 5, 100.0);
        cus1.orderPizza("cheese", 5);
        cus1.orderPizza("cheese", 3);
        cus1.orderPizza("pepperoni", 1);
        cus1.orderPizza("meat lover", 1);
        cus1.checkOut();

    }
}

