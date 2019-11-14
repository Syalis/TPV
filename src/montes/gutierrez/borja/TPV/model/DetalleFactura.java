package montes.gutierrez.borja.TPV.model;

/**
 * Clase que representa la entidad DetalleFactura.
 *
 * @author Borja Montes Gutiérrez
 */
public class DetalleFactura {

    /**
     * Variables
     */
    private int codeBill;
    private int codeProduct;
    private int total;
    private int subTotal;

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
     * Getter del id del producto.
     *
     * @return int codeProduct
     */
    public int getCodeProduct() {
        return codeProduct;
    }

    /**
     * Setter del id del producto.
     *
     * @param codeProduct int
     */
    public void setCodeProduct(int codeProduct) {
        this.codeProduct = codeProduct;
    }

    /**
     * Getter del total de la factura.
     *
     * @return int total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Setter del total de la factura.
     *
     * @param total int
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Getter del subTotal de la factura.
     *
     * @return int subTotal
     */
    public int getSubTotal() {
        return subTotal;
    }

    /**
     * Setter del subTotal de la factura.
     *
     * @param subTotal int
     */
    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * Constructor parametrizado con todos los datos de la entidad.
     *
     * @param codeBill int identificador de la factura.
     * @param codeProduct int identificador del producto
     * @param total int identificador del total de la factura.
     * @param subTotal int identificador del subtotal de la factura.
     */
    public DetalleFactura(int codeBill, int codeProduct,
            int total, int subTotal) {
        this.codeBill = codeBill;
        this.codeProduct = codeProduct;
        this.total = total;
        this.subTotal = subTotal;
    }

}
