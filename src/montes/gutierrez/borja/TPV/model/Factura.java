package montes.gutierrez.borja.TPV.model;

/**
 * Clase que representa la entidad Factura.
 *
 * @author Borja Montes Gutiérrez
 */
public class Factura {

    /**
     * Variables
     */
    private int codeBill;
    private String dateBill;
    private int idClient;
    private int totalBill;
    private float discount;
    private double iva;
    private int totalAPay;

    /**
     * Getter del id de la factura.
     *
     * @return int codeBill
     */
    public int getCodeBill() {
        return codeBill;
    }

    /**
     * Setter del id de la factura.
     *
     * @param codeBill int
     */
    public void setCodeBill(int codeBill) {
        this.codeBill = codeBill;
    }

    /**
     * Getter de la fecha de la factura.
     *
     * @return String dateBill
     */
    public String getDateBill() {
        return dateBill;
    }

    /**
     * Setter del id de la factura.
     *
     * @param dateBill String
     */
    public void setDateBill(String dateBill) {
        this.dateBill = dateBill;
    }

    /**
     * Getter del idClient del cliente.
     *
     * @return int codeBill
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Setter del idClient del cliente
     *
     * @param idClient int
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Getter del total de la factura.
     *
     * @return int totalBill
     */
    public int getTotalBill() {
        return totalBill;
    }

    /**
     * Setter del total de la factura.
     *
     * @param totalBill int
     */
    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    /**
     * Getter del descuento de la factura.
     *
     * @return float discount.
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * Setter del descuento de la factura.
     *
     * @param discount float
     */
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    /**
     * Getter del IVA de la factura.
     *
     * @return int iva.
     */
    public double getIva() {
        return iva;
    }

    /**
     * Setter del IVA de la factura.
     *
     * @param iva int
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * Getter del total a pagar.
     *
     * @return int totalAPay
     */
    public int getTotalAPay() {
        return totalAPay;
    }

    /**
     * Setter del total apagar.
     *
     * @param totalAPay int
     */
    public void setTotalAPay(int totalAPay) {
        this.totalAPay = totalAPay;
    }

    /**
     * Constructor por defecto de los objetos factura.
     */
    public Factura() {

    }

    /**
     * Constructor parametrizado con todos los datos de la entidad.
     *
     * @param codeBill int identificador de la factura.
     * @param dateBill String de la fecha
     * @param idClient int identificador del cliente.
     * @param totalBill int total de la factura.
     * @param discount int descuento de la factura.
     * @param iva int IVA de la factura.
     * @param totalAPay int total a pagar de la factura.
     */
    public Factura(int codeBill, String dateBill, int idClient,
            int totalBill, float discount, double iva, int totalAPay) {
        this.codeBill = codeBill;
        this.dateBill = dateBill;
        this.idClient = idClient;
        this.totalBill = totalBill;
        this.discount = discount;
        this.iva = iva;
        this.totalAPay = totalAPay;
    }

    public Object[] toObjectArrayTPVFactura() {
        return new Object[]{getDiscount()};
    }
}
