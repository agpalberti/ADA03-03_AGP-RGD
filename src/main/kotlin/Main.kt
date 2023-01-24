import controlador.Controlador
import modelo.GestorBBDD
import modelo.PasswordEncoder

fun main(){

    val gestor = GestorBBDD()

    val direccion1 = Direccion(calle = "Calle Sanchez", codigopostal = 11130, numero = 7)
    val direccion2 = Direccion(calle = "Calle Real", codigopostal = 11100, numero = 23)
    val direccion3 = Direccion(calle = "Calle Molino", codigopostal = 11130, numero = 8)
    val direccion4 = Direccion(calle = "Calle Almería", codigopostal = 11010, numero = 11)

    val taller1 = Taller(cif= "1234", nombre = "ASD1234", PasswordEncoder.encodePassword("guagua") , direccion = direccion1)


    val cliente1 = Cliente(
        dni = "31312644C",
        nombre = "Juan",
        edad = 22,
        direccion = direccion3,
        password = PasswordEncoder.encodePassword("123"),
        talleres = setOf(taller1)
    )
    val cliente2 =
        Cliente(dni = "12345678A", nombre = "Pablo", edad = 32, direccion = direccion4, password = PasswordEncoder.encodePassword("123"), talleres = setOf(taller1))

    val taller2 = Taller(nombre = "Taller Roberto", cif = "CDS4321", direccion = direccion2, password = PasswordEncoder.encodePassword("123"))



    val pedido1 = Pedido(descripcion = "THERMOMIX", cliente = cliente1)
    val pedido2 = Pedido(descripcion = "Amortiguador", taller = taller1)

    gestor.insertDireccion(direccion1)
    gestor.insertDireccion(direccion2)
    gestor.insertDireccion(direccion3)
    gestor.insertDireccion(direccion4)

    gestor.insertTaller(taller1)
    gestor.insertTaller(taller2)

    gestor.insertCliente(cliente1)
    gestor.insertCliente(cliente2)

    gestor.insertPedido(pedido1)
    gestor.insertPedido(pedido2)

    val controlador = Controlador(gestor)

    var salir = false

    do {

        when(controlador.onStart()){
            1 -> {
                if (controlador.loginCliente()){

                    var irAtras = false

                    do {
                        when(controlador.clienteMenu()){
                            1 -> {controlador.clienteDarAltaPedido()}
                            2 -> {controlador.clientePedidosRealizados()}
                            3 -> {controlador.clienteTalleresRelacionados()}
                            4 -> irAtras = true
                        }
                    } while (!irAtras)

                }
            }

            2 -> {
                if(controlador.loginTaller()){
                    var irAtras = false

                    do {
                        when(controlador.tallerMenu()){
                            1 -> {controlador.tallerPedidosSinAsignar()}
                            2 -> {controlador.tallerPedidosAsignados()}
                            3 -> {controlador.tallerRelacionClientes()}
                            4 -> {controlador.tallerAsignarsePedido()}
                            5 -> irAtras = true
                        }
                    } while (!irAtras)
                }
            }

            3 -> {
                controlador.createClienteAccount()
            }

            4 -> {
                controlador.createTallerAccount()
            }

            5 -> {
                controlador.onExit()
                salir = true
            }

            else -> {
                controlador.printIncorrectInput()
            }

        }

    } while (!salir)
}