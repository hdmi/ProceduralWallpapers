package imdh.tfm.proceduralwallpapers;

/**
 * Created by CarlosAB on 10/08/2017.
 */

public class Model {


    String a;
    String b;
    String c;
    String d;
    String e;

    public Model(){}

    @Override
    public String toString() {
        return "[ " + a + ", " + b + ", " + c + ", " + d + ", " + e + " ]";
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}
