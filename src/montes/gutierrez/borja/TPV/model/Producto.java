package montes.gutierrez.borja.TPV.model;

/**
 * Clase que representa la entidad Producto.
 *
 * @author Borja Montes Gutiérrez
 */
public class Producto {

    /**
     * Variables
     */
    private int codeProduct;
    private String nameProduct;
    private String descriptionProduct;
    private String priceProduct;
    private String stockProduct;

    /**
     * Getter del id del producto.
     *
     * @return int codeProduct
     */
    public int getCodeProduct() {
        return codeProduct;
    }

    /**
     * Setter del id del producto
     *
     * @param codeProduct int
     */
    public void setCodeProduct(int codeProduct) {
        this.codeProduct = codeProduct;
    }

    /**
     * Getter del nombre del producto.
     *
     * @return String codeProduct
     */
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     * Setter del nombre del producto
     *
     * @param nameProduct String.
     */
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    /**
     * Getter de la descripción del producto.
     *
     * @return String descriptionProduct
     */
    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    /**
     * Setter de la descripción del producto.
     *
     * @param descriptionProduct String.
     */
    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    /**
     * Getter del precio del producto.
     *
     * @return String priceProduct
     */
    public String getPriceProduct() {
        return priceProduct;
    }

    /**
     * Setter del precio del producto.
     *
     * @param priceProduct String.
     */
    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    /**
     * Getter del stock del producto.
     *
     * @return String priceProduct
     */
    public String getStockProduct() {
        return stockProduct;
    }

    /**
     * Setter del stock del producto.
     *
     * @param stockProduct String.
     */
    public void setStockProduct(String stockProduct) {
        this.stockProduct = stockProduct;
    }

    /**
     * Constructor por defecto de los objetos producto.
     */
    public Producto() {

    }

    /**
     * Constructor parametrizado con todos los datos de la entidad.
     *
     * @param codeProduct int identificador único autogenerado por la BBDD.
     * @param nameProduct String nombre del producto.
     * @param descriptionProduct String descripción del producto.
     * @param priceProduct String precio del producto.
     * @param stockProduct String stock del producto.
     */
    public Producto(int codeProduct, String nameProduct,
            String descriptionProduct, String priceProduct, String stockProduct) {
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
        this.stockProduct = stockProduct;
    }

    /**
     * Método para la visualización de un producto.
     *
     * @return String nameProduct.
     */
    @Override
    public String toString() {
        return getNameProduct();
    }

    /**
     * Método que devuelve los valores de las propiedades del producto en forma
     * de array de objetos.
     *
     * @return Object[] el orden es codeProduct, nameProduct,
     * descriptionProduct, priceProduct, stockProduct.
     */
    public Object[] toObjectArray() {
        return new Object[]{getCodeProduct(), getNameProduct(),
            getDescriptionProduct(), getPriceProduct(), getStockProduct()};
    }

    /**
     * Método que devuelve los valores de las propiedades del producto en forma
     * de array de objetos.
     *
     * @return Object[] nameProduct.
     */
    public Object[] toObjectArrayTPVNameProduct() {
        return new Object[]{getNameProduct()};
    }

    /**
     * Método que devuelve los valores de las propiedades del producto en forma
     * de array de objetos.
     *
     * @return Object[] priceProduct.
     */
    public Object[] toObjectArrayTPVPriceProduct() {
        return new Object[]{getPriceProduct()};
    }
}
