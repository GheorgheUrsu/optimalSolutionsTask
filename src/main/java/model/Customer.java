package model;

public class Customer {
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;

    public Customer() {
    }

    public Customer(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j) {
        super();
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
        F = f;
        G = g;
        H = h;
        I = i;
        J = j;
    }

    public String getA() {
        return A;
    }
    public void setA(String a) {
        A = a;
    }
    public String getB() {
        return B;
    }
    public void setB(String b) {
        B = b;
    }
    public String getC() {
        return C;
    }
    public void setC(String c) {
        C = c;
    }
    public String getD() {
        return D;
    }
    public void setD(String d) {
        D = d;
    }
    public String getE() {
        return E;
    }
    public void setE(String e) {
        E = e;
    }
    public String getF() {
        return F;
    }
    public void setF(String f) {
        F = f;
    }
    public String getG() {
        return G;
    }
    public void setG(String g) { G = g; }
    public String getH() {
        return H;
    }
    public void setH(String h) {
        H = h;
    }
    public String getI() {
        return I;
    }
    public void setI(String i) {
        I = i;
    }
    public String getJ() {
        return J;
    }
    public void setJ(String j) {
        J = j;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                ", D='" + D + '\'' +
                ", E='" + E + '\'' +
                ", F='" + F + '\'' +
                ", G=" + G +
                ", H='" + H + '\'' +
                ", I='" + I + '\'' +
                ", J='" + J + '\'' +
                '}';
    }
}

