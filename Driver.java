import java.io.File;

public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double[] c1 = { 8, 9 };
        int[] e1 = { 3, 2 };
        Polynomial p1 = new Polynomial(c1, e1);
        double[] c2 = { 1, 2, 3 };
        int[] e2 = { 1, 2, 3 };
        Polynomial p2 = new Polynomial(c2, e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        // Multiply tester
        System.out.print("Coeffi after multiplication: ");
        Polynomial d = p1.multiply(p2);
        for (int i = 0; i < d.expo.length; i++) {
            System.out.print(d.coeffi[i] + " ");
        }

        System.out.println();

        System.out.print("Expo after multiplication: ");
        for (int i = 0; i < d.expo.length; i++) {
            System.out.print(d.expo[i] + " ");
        }

        System.out.println();

        // testing constructor
        try {
            File file = new File("/Users/rivz/b07lab1/lab2.txt");
            Polynomial z = new Polynomial(file);
            System.out.print("Coefficient from file: ");
            for (int i = 0; i < z.coeffi.length; i++) {
                System.out.print(z.coeffi[i] + " ");
            }
            System.out.println();

            System.out.print("Expo from file: ");
            for (int i = 0; i < z.expo.length; i++) {
                System.out.print(z.expo[i] + " ");
            }

            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        try {
            // testing Save to File
            String filepath = "/Users/rivz/b07lab1/lab2save.txt";
            Polynomial t = new Polynomial(c2, e2);
            t.saveToFile(filepath);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}