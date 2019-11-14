package montes.gutierrez.borja.TPV.model;

/**
 * Clase que representa la entidad de negocio principal que vamos a manejar.
 *
 * @author Borja Montes Gutiérrez
 */
public class Vendedor {

    /**
     * Variables
     */
    private int idSeller;
    private String nameSeller;
    private String firstSurname;
    private String secondSurname;

    /**
     * Getter del idSeller.
     *
     * @return int idSeller
     */
    public int getIdSeller() {
        return idSeller;
    }

    /**
     * Setter del idSeller.
     *
     * @param idSeller int
     */
    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    /**
     * Getter del nameSeller.
     *
     * @return string nameSeller
     */
    public String getNameSeller() {
        return nameSeller;
    }

    /**
     * Setter del nameSeller.
     *
     * @param nameSeller string
     */
    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    /**
     * Getter del firstSurname.
     *
     * @return string firstSurname
     */
    public String getFirstSurname() {
        return firstSurname;
    }

    /**
     * Setter del firstSurname.
     *
     * @param firstSurname string.
     */
    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    /**
     * Getter del secondSurname.
     *
     * @return string secondSurname
     */
    public String getSecondSurname() {
        return secondSurname;
    }

    /**
     * Setter del secondSurname.
     *
     * @param secondSurname string
     */
    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    /**
     * Constructor por defecto de los objetos vendedor. Inicializa el id a 0.
     */
    public Vendedor() {
        this.idSeller = 0;
    }

    /**
     * Constructor parametrizado con todos los datos de la entidad.
     *
     * @param idSeller int identificador único autogenerado por la BBDD.
     * @param nameSeller String nombre del vendedor.
     * @param firstSurname String primer apellido del vendedor.
     * @param secondSurname String segundo apellido del vendedor.
     *
     */
    public Vendedor(int idSeller, String nameSeller, String firstSurname, String secondSurname) {
        this.idSeller = idSeller;
        this.nameSeller = nameSeller;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
    }

    /**
     * Método para la visualización de un vendedor.
     *
     * @return String nameSeller, firstSurname, secondSurname.
     */
    @Override
    public String toString() {
        return getNameSeller() + " " + getFirstSurname() + " "
                + getSecondSurname();
    }

    /**
     * Método que devuelve los valores de las propiedades del vendedor en forma
     * de array de objetos.
     *
     * @return Object[] el orden es idSeller, nameSeller, firstSurname,
     * secondSurname
     */
    public Object[] toObjectArray() {
        return new Object[]{getIdSeller(), getNameSeller(), getFirstSurname(),
            getSecondSurname()};
    }

}
