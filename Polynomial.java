public class Polynomial {
    double[] coeffi;

    public Polynomial(){
        this.coeffi = new double[0];
    }

    public Polynomial(double [] coeffi){
            this.coeffi = coeffi;
    }

    //Add method
    public Polynomial add(Polynomial poly){
        int length = poly.coeffi.length;

        if(this.coeffi.length > length){
            length = this.coeffi.length;
        }

        double[] newPoly = new double[length];

        for(int i = 0; i<this.coeffi.length; i++)
        {
            newPoly[i] = this.coeffi[i];
        }

        for(int i = 0; i<poly.coeffi.length; i++)
        {
            newPoly[i] += poly.coeffi[i];
        }

        return new Polynomial(newPoly);
    }    

    //Evaluate method
    public double evaluate(double x){
        double returnVal = 0;
        double scalingX = 1;

        for(double item: this.coeffi)
        {
            returnVal += scalingX * item;
            scalingX = scalingX * x;
        }
        return returnVal;
    }

    //hasRoot Method
    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

}

