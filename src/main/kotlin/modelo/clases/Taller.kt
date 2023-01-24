import jakarta.persistence.*
import modelo.clases.Usuario

@Entity
@Table(name = "talleres")
class Taller(

    @Id
    @Column(name = "cif")
    var cif: String,
    @Column(name = "nombre")
    override var nombre: String,
    @Column(name = "password")
    override var password: String,

    // Con la etiqueta OneToOne relacionamos una clave ajena a su tabla, además de proporcionar una regla para el borrado
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_direccion")
    var direccion: Direccion? = null,

    @ManyToMany(mappedBy = "talleres")
    var clientes: Set<Cliente>? = null,
): Usuario (id = cif,nombre = nombre, password = password){

    constructor(): this("", "", "", null,null)

    override fun toString(): String {
        return "CIF: $cif, \"$nombre\""
    }
}