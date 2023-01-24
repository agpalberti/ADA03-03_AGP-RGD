package vista

import Cliente
import Direccion
import Pedido
import modelo.PasswordEncoder
import vista.strings.Strings

class ClienteGUI: GeneralGUI{

    override fun startMenu(): Int? {
        println(Strings.mainMenuCliente)

        val input: String = readln()

        try {
            if (input.toInt() in 1..4){
                return input.toInt()
            } else return null
        }catch (_: Exception){
            return null
        }
    }

    override fun darseAlta(): Pair<Cliente, Direccion>? {

        //Datos cliente
        println(Strings.clientInsertDNI)
        val dni: String = readln()

        println(Strings.insertNombre)
        val nombre: String = readln()

        println(Strings.insertClientEdad)
        val edadString: String = readln()
        var edad: Int = 0
        if (edadString.toIntOrNull() != null) {edad = edadString.toInt()}

        println(Strings.insertPassword)
        val password: String = readln()

        val direccion = getDireccion()

        if (dni.isNotEmpty() && nombre.isNotEmpty() && password.isNotEmpty() && direccion != null){
            return Pair(Cliente(dni,nombre,edad,PasswordEncoder.encodePassword(password)),direccion)
        } else return null

    }

    fun obtenerDNI():String{
        println(Strings.clientInsertDNI)
        val dni: String = readln()
        return dni
    }

    fun altaPedido():Pedido{
        println(Strings.insertPedidoDescripcion)
        var descripcion: String = readln()
        return Pedido(descripcion = descripcion)
    }


}