import jakarta.persistence.*

@Entity
@Table(name = "pedidos")
class Pedido(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "descripcion")
    var descripcion: String,

    @ManyToOne
    @JoinColumn(name = "cif")
    var taller: Taller? = null,

    @ManyToOne
    @JoinColumn(name = "dni")
    var cliente: Cliente? = null,
){
    override fun toString(): String {
        return "Pedido $id: \"$descripcion\""
    }
}