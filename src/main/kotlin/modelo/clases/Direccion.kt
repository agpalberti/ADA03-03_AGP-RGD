import jakarta.persistence.*

/*Especificamos que cada clase es una entidad, y la relacionamos con una tabla de nuestra BD mediante @Table, además de relacionar
los atributos estas con sus tablas necesarias*/
@Entity
@Table(name = "direcciones")
class Direccion(
    // Especificamos el ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "calle")
    var calle: String,
    @Column(name = "numero")
    var numero: Int,
    @Column(name = "cp")
    var codigopostal: Int,

    @OneToOne(mappedBy = "direccion")
    var cliente: Cliente? = null,

    @OneToOne(mappedBy = "direccion")
    var taller: Taller? = null,

    ){
    override fun toString(): String {
        return "$calle, Nº$numero, código postal $codigopostal"
    }
}