package controlador

import Cliente
import Pedido
import Taller
import modelo.GestorBBDD
import modelo.PasswordEncoder
import modelo.clases.Usuario
import org.mindrot.jbcrypt.BCrypt
import vista.ClienteGUI
import vista.LoginGUI
import vista.TallerGUI

class Controlador(val gestorBBDD: GestorBBDD) {

    private val defaultGUICliente = ClienteGUI()
    private val tallerGUI = TallerGUI()
    private val login: LoginGUI = LoginGUI()

    private lateinit var usuario: Usuario


    fun onStart(): Int {
        val intMenu = login.identifyType()
        if (intMenu != null) return intMenu
        else defaultGUICliente.printIncorrectInput()
        return -1
    }

    fun loginCliente(): Boolean {
        val datos = login.clientLogin()
        val cliente = gestorBBDD.selectClienteByDNI(datos.first)
        if (cliente != null) {
            if (PasswordEncoder.decodePassword(cliente.password) == datos.second) {
                usuario = cliente
                return true
            }
        }
        return false
    }

    fun loginTaller(): Boolean {
        val datos = login.tallerLogin()
        val taller = gestorBBDD.selectTallerByCIF(datos.first)
        if (taller != null) {
            if (PasswordEncoder.decodePassword(taller.password) == datos.second) {
                usuario = taller
                return true
            }
        }
        return false
    }

    fun createClienteAccount(): Boolean {
        val datos = defaultGUICliente.darseAlta()

        if (datos != null) {
            gestorBBDD.insertDireccion(datos.second)
            val cliente = datos.first
            cliente.direccion = datos.second
            gestorBBDD.insertCliente(cliente)
            defaultGUICliente.printAltaExito()
            return true
        } else {
            defaultGUICliente.printAltaError()
            return false
        }
    }

    fun createTallerAccount(): Boolean {
        val datos = tallerGUI.darseAlta()

        if (datos != null) {
            gestorBBDD.insertDireccion(datos.second)
            val taller = datos.first
            taller.direccion = datos.second
            gestorBBDD.insertTaller(taller)
            tallerGUI.printAltaExito()
            return true
        } else {
            tallerGUI.printAltaError()
            return false
        }
    }

    fun clienteMenu(): Int {
        val intMenu = defaultGUICliente.startMenu()
        if (intMenu != null) return intMenu
        else defaultGUICliente.printIncorrectInput()
        return -1
    }

    fun tallerMenu(): Int {
        val intMenu = tallerGUI.startMenu()
        if (intMenu != null) return intMenu
        else tallerGUI.printIncorrectInput()
        return -1
    }

    fun clienteDarAltaPedido(): Boolean {
        val pedido = defaultGUICliente.altaPedido()
        pedido.cliente = usuario as Cliente
        val boolean = gestorBBDD.insertPedido(pedido)
        if (boolean) {
            defaultGUICliente.printAltaExito()
            return true
        } else {
            defaultGUICliente.printAltaError()
            return false
        }
    }

    fun clientePedidosRealizados() {
        if (usuario is Cliente){
            val cliente = usuario as Cliente
            defaultGUICliente.printSet(gestorBBDD.selectPedidosByCliente(cliente))
        }
    }

    fun clienteTalleresRelacionados() {

        if (usuario is Cliente) {
            val cliente = usuario as Cliente
            defaultGUICliente.printSet(gestorBBDD.selectTalleresByCliente(cliente))
        }
    }

    fun tallerPedidosAsignados() {
        if(usuario is Taller){
            val taller = usuario as Taller
            defaultGUICliente.printSet(gestorBBDD.selectPedidosByTaller(taller))
        }

        }

    fun tallerPedidosSinAsignar() {
        defaultGUICliente.printSet(gestorBBDD.selectPedidosSinAsignar())
    }

    fun tallerAsignarsePedido(): Boolean {
        if (usuario is Taller) {
            val idPedido = tallerGUI.asignarPedido()
            val pedido = gestorBBDD.selectPedidoById(idPedido)
            if (pedido != null) {
                pedido.taller = usuario as Taller
                gestorBBDD.updatePedido(pedido)
                return true
            }
        }
        return false
    }

    fun tallerRelacionClientes() {
        if (usuario is Taller){
            val taller:Taller = usuario as Taller
            defaultGUICliente.printSet(gestorBBDD.selectClientesByTaller(taller))
        }


    }

    fun printIncorrectInput() {
        defaultGUICliente.printIncorrectInput()
    }

    fun onExit() {
        gestorBBDD.close()
        defaultGUICliente.exit()
    }


}