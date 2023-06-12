import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Polynomial {
    public double[] coeffi;
    public int[] expo;

    public Polynomial() {
        this.coeffi = new double[0];
        this.expo = new int[0];
    }

    public Polynomial(double[] coeffi, int[] expo) {
        this.coeffi = coeffi;
        this.expo = expo;
    }

    // Add method
    public Polynomial add(Polynomial poly) {
        int elength = poly.expo.length;

        int maxLen = this.expo.length + elength;
        int[] temp = new int[maxLen];
        int count = 0;

        for (int i = 0; i < maxLen; i++) {
            temp[i] = -1;
        }

        for (int i = 0; i < this.expo.length; i++) {
            if (!this.dupChecker(this.expo[i], temp)) {
                temp[count] = this.expo[i];
                count += 1;
            }
        }

        for (int i = 0; i < poly.expo.length; i++) {
            if (!this.dupChecker(poly.expo[i], temp)) {
                temp[count] = poly.expo[i];
                count += 1;
            }
        }

        // create exponent array
        int[] totalexpo = new int[count];
        for (int i = 0; i < count; i++) {
            totalexpo[i] = temp[i];
        }

        // sort exponent
        Arrays.sort(totalexpo);

        // Adding Pt 1
        double[] coeffiFinal = new double[count];
        for (int i = 0; i < this.expo.length; i++) {
            int ti = indexChecker(this.expo[i], totalexpo);
            if (ti >= 0) {
                coeffiFinal[ti] = this.coeffi[i];
            }
        }

        // Adding Pt 2
        for (int i = 0; i < poly.expo.length; i++) {
            int ti = indexChecker(poly.expo[i], totalexpo);
            if (ti >= 0) {
                coeffiFinal[ti] = coeffiFinal[ti] + poly.coeffi[i];
            }
        }

        return new Polynomial(coeffiFinal, totalexpo);
    }

    // checks if expoenents are the same
    private boolean dupChecker(int x, int[] expo) {
        for (int i = 0; i < expo.length; i++) {
            if (expo[i] == x) {
                return true;
            }
        }
        return false;
    }

    private int indexChecker(int x, int[] expo) {
        for (int i = 0; i < expo.length; i++) {
            if (expo[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // Evaluate method
    public double evaluate(double x) {
        double returnVal = 0;
        for (int i = 0; i < this.coeffi.length; i++) {
            returnVal = returnVal + this.coeffi[i] * Math.pow(x, this.expo[i]);
        }
        return returnVal;
    }

    // hasRoot Method
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    // Multiply Method
    public Polynomial multiply(Polynomial poly) {
        int maxLen = this.expo.length * poly.expo.length;
        int[] temp = new int[maxLen];

        for (int i = 0; i < maxLen; i++) {
            temp[i] = -1;
        }

        int count = 0;

        // For exponents
        for (int i = 0; i < this.expo.length; i++) {
            for (int j = 0; j < poly.expo.length; j++) {
                if (!dupChecker(this.expo[i] + poly.expo[j], temp)) {
                    temp[count] = this.expo[i] + poly.expo[j];
                    count += 1;
                }
            }
        }

        // Final ans exponents
        int[] fExpo = new int[count];

        for (int i = 0; i < count; i++) {
            fExpo[i] = temp[i];
        }
        Arrays.sort(fExpo);

        // Final Coefficient calculation

        double[] fCoeffi = new double[fExpo.length];

        for (int i = 0; i < this.expo.length; i++) {
            for (int j = 0; j < poly.expo.length; j++) {
                int ti = indexChecker(this.expo[i] + poly.expo[j], fExpo);
                fCoeffi[ti] = fCoeffi[ti] + this.coeffi[i] * poly.expo[j];
            }
        }

        return new Polynomial(fCoeffi, fExpo);
    }

    // Constructor for type File
    public Polynomial(File file) throws Exception {
        Scanner myScanner = new Scanner(file);
        if (!myScanner.hasNextLine()) {
            this.coeffi = null;
            this.expo = null;
        } else {
            String line = myScanner.nextLine();
            line = line.replace("-", "+-");

            String[] poly_arr = line.split("\\+");
            this.expo = new int[poly_arr.length];
            this.coeffi = new double[poly_arr.length];

            for (int i = 0; i < poly_arr.length; i++) {
                String[] subArry = poly_arr[i].split("x");
                coeffi[i] = Double.parseDouble(subArry[0]);
                if (subArry.length > 1) {
                    expo[i] = Integer.parseInt(subArry[1]);
                } else {
                    expo[i] = 0;
                }
            }
        }
        myScanner.close();
    }

    // saveToFile Method
    public void saveToFile(String myFile) throws Exception {
        if(coeffi == null || expo == null || this.coeffi.length != this.expo.length)
        {
        return;
        }

        String writeString = "";

        for (int i = 0; i < this.coeffi.length; i++) {
            writeString += coeffi[i];
            if (expo[i] != 0) {
                writeString += "x" + expo[i];
            }
            writeString += "+";
        }

        if (writeString.endsWith("+")) {
            writeString = writeString.substring(0, writeString.length() - 1);
        }

        FileWriter myWriter = new FileWriter(new File(myFile));
        myWriter.write(writeString);
        myWriter.close();

    }
}
