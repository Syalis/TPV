package montes.gutierrez.borja.TPV.model;

/**
 * Clase que representa la entidad Cliente.
 *
 * @author Borja Montes Gutiérrez
 */
public class Cliente {

    /**
     * Variables
     */
    private int idClient;
    private String DNI;
    private String nameClient;
    private String firstSurname;
    private String secondSurname;
    private String address;
    private String phoneNumber;

    /**
     * Getter del id del cliente.
     *
     * @return int idClient
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Setter del id del cliente.
     *
     * @param idClient int
     */
    public void setId(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Getter del DNI.
     *
     * @return String DNI.
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Setter del DNI.
     *
     * @param DNI String
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     * Getter del nombre del cliente.
     *
     * @return String nameClient.
     */
    public String getNameClient() {
        return nameClient;
    }

    /**
     * Setter del nombre del cliente.
     *
     * @param nameClient String
     */
    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    /**
     * Getter del segundo apellido.
     *
     * @return String firstSurname.
     */
    public String getFirstSurname() {
        return firstSurname;
    }

    /**
     * Setter del primer apellido.
     *
     * @param firstSurname String.
     */
    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    /**
     * Getter del segundo apellido.
     *
     * @return String secondSurname.
     */
    public String getSecondSurname() {
        return secondSurname;
    }

    /**
     * Setter del primer apellido.
     *
     * @param secondSurname String.
     */
    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    /**
     * Getter de la direccion.
     *
     * @return String address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter de la direccion.
     *
     * @param address String.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter del número de teléfono.
     *
     * @return String phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter del número de teléfono.
     *
     * @param phoneNumber String.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructor por defecto de los objetos cliente. Inicializa el id a 0.
     */
    public Cliente() {
        this.idClient = 0;
    }

    /**
     * Constructor parametrizado con todos los datos de la entidad.
     *
     * @param idClient int identificador único autogenerado por la BBDD.
     * @param DNI String DNI del cliente.
     * @param nameClient String nombre del cliente.
     * @param firstSurname String primer apellido del cliente.
     * @param secondSurname String segundo apellido del cliente.
     * @param address String dirección del cliente.
     * @param phoneNumber String número de teléfono del cliente.
     */
    public Cliente(int idClient, String DNI, String nameClient,
            String firstSurname, String secondSurname, String address,
            String phoneNumber) {
        this.idClient = idClient;
        this.DNI = DNI;
        this.nameClient = nameClient;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Método para la visualización de un cliente.
     *
     * @return String nameClient, firstSurname, secondSurname.
     */
    @Override
    public String toString() {
        return getNameClient() + " " + getFirstSurname() + " "
                + getSecondSurname();
    }

    /**
     * Método que devuelve los valores de las propiedades del cliente en forma
     * de array de objetos.
     *
     * @return Object[] el orden es idClient, DNI, nameClient, firstSurname,
     * secondSurname, address, phoneNumber.
     */
    public Object[] toObjectArray() {
        return new Object[]{getIdClient(), getDNI(), getNameClient(), getFirstSurname(),
            getSecondSurname(), getAddress(), getPhoneNumber()};
    }

}
