import jakarta.persistence.*
import modelo.clases.Usuario

@Entity
@Table(name = "clientes")
class Cliente(

    @Id
    @Column(name = "dni")
    var dni: String,
    @Column(name = "nombre")
    override var nombre: String,
    @Column(name = "edad")
    var edad: Int,
    @Column(name = "password")
    override var password: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_direccion")
    var direccion: Direccion? = null,

    @ManyToMany
    @JoinTable(
        name = "cliente_taller",
        joinColumns = [JoinColumn(name = "dni")],
        inverseJoinColumns = [JoinColumn(name = "cif")]
    )
    var talleres: Set<Taller>? = null
):Usuario(id = dni,nombre = nombre, password = password){

    constructor(): this("", "", 0, "", null, null)

    override fun toString(): String {
        return "DNI: $dni, nombre: $nombre, edad: $edad"
    }
}