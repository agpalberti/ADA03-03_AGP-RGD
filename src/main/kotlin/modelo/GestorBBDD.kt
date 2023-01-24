package modelo

import Cliente
import Direccion
import Pedido
import Taller
import jakarta.persistence.EntityExistsException
import jakarta.persistence.NoResultException
import jakarta.persistence.Persistence

class GestorBBDD {

    private val entityManagerFactory = Persistence.createEntityManagerFactory("ADA03")
    private val entityManager = entityManagerFactory.createEntityManager()
    private val transaction = entityManager.transaction


    // Métodos SelectAll

    fun selectAllClientes(): Set<Cliente> {

        val clienteQuery = entityManager.createQuery("from Cliente", Cliente::class.java)
        return clienteQuery.resultList.toSet()

    }

    fun selectAllDirecciones(): Set<Direccion> {
        val direccionQuery = entityManager.createQuery("from Direccion", Direccion::class.java)
        return direccionQuery.resultList.toSet()
    }

    fun selectAllPedidos(): Set<Pedido> {
        val pedidoQuery = entityManager.createQuery("from Pedido", Pedido::class.java)
        return pedidoQuery.resultList.toSet()
    }

    fun selectAllTalleres(): Set<Taller> {

        val tallerQuery = entityManager.createQuery("from Taller", Taller::class.java)
        return tallerQuery.resultList.toSet()
    }

    // Métodos SelectByID

    fun selectClienteByDNI(dni: String): Cliente? {

        val clienteQuery = entityManager.createQuery("from Cliente where dni = :id", Cliente::class.java)
        clienteQuery.setParameter("id", dni)
        return try {
            clienteQuery.singleResult
        } catch (e: NoResultException) {
            null
        }

    }

    fun selectDireccionByID(id: Long): Direccion? {

        val direccionQuery = entityManager.createQuery("from Direccion where id = :id", Direccion::class.java)
        direccionQuery.setParameter("id", id)
        return try {
            direccionQuery.singleResult
        } catch (e: NoResultException) {
            null
        }

    }

    fun selectPedidoById(id: Long): Pedido? {
        val pedidoQuery = entityManager.createQuery("from Pedido where id = :id", Pedido::class.java)
        pedidoQuery.setParameter("id", id)
        return try {
            pedidoQuery.singleResult
        } catch (e: NoResultException) {
            null
        }
    }

    fun selectTallerByCIF(cif: String): Taller? {
        val tallerQuery = entityManager.createQuery("from Taller where id = :id", Taller::class.java)
        tallerQuery.setParameter("id", cif)
        return try {
            tallerQuery.singleResult
        } catch (e: NoResultException) {
            null
        }
    }

    // Métodos select por sus relaciones

    fun selectPedidosSinAsignar(): Set<Pedido>{
        val pedidoQuery = entityManager.createQuery("from Pedido where taller is null", Pedido::class.java)
        return pedidoQuery.resultList.toSet()
    }

    fun selectPedidosByTaller(taller: Taller): Set<Pedido> {
        val pedidoQuery = entityManager.createQuery("from Pedido where taller = :taller", Pedido::class.java)
        pedidoQuery.setParameter("taller", taller)
        return pedidoQuery.resultList.toSet()
    }

    fun selectPedidosByCliente(cliente: Cliente): Set<Pedido>{
        val pedidoQuery = entityManager.createQuery("from Pedido where cliente = :cliente", Pedido::class.java)
        pedidoQuery.setParameter("cliente", cliente)
        return pedidoQuery.resultList.toSet()
    }

    fun selectClientesByTaller(taller: Taller): Set<Cliente>{
        val clienteQuery = entityManager.createQuery("select c from Cliente c join c.talleres t where t = :taller", Cliente::class.java)
        clienteQuery.setParameter("taller", taller)
        return clienteQuery.resultList.toSet()
    }

    fun selectTalleresByCliente(cliente: Cliente): Set<Taller>{
        val clienteQuery = entityManager.createQuery("select t from Taller t join t.clientes c where c = :cliente", Taller::class.java)
        clienteQuery.setParameter("cliente", cliente)
        return clienteQuery.resultList.toSet()
    }

    // Métodos update

    fun updatePedido(pedido: Pedido) {
        transaction.begin()
        val pedidoToUpdate = entityManager.find(Pedido::class.java, pedido.id)
        if (pedidoToUpdate != null) {
            entityManager.merge(pedido)
            transaction.commit()
        } else {
            transaction.rollback()
        }
    }

    fun updateCliente(cliente: Cliente):Boolean {
        transaction.begin()
        val clienteToUpdate = entityManager.find(Cliente::class.java, cliente.dni)
        if (clienteToUpdate != null) {
            entityManager.merge(cliente)
            transaction.commit()
            return true
        } else {
            transaction.rollback()
            return false
        }
    }

    fun updateDireccion(direccion: Direccion):Boolean {
        transaction.begin()
        val direccionToUpdate = entityManager.find(Direccion::class.java, direccion.id)
        if (direccionToUpdate != null) {
            entityManager.merge(direccion)
            transaction.commit()
            return true
        } else {
            transaction.rollback()
            return false
        }
    }

    fun updateTaller(taller: Taller):Boolean {
        transaction.begin()
        val tallerToUpdate = entityManager.find(Taller::class.java, taller.cif)
        if (tallerToUpdate != null) {
            entityManager.merge(taller)
            transaction.commit()
            return true
        } else {
            transaction.rollback()
            return false
        }
    }
    

    // Métodos Insert

    fun insertCliente(cliente: Cliente):Boolean{
        transaction.begin()
        try {
            entityManager.persist(cliente)
            transaction.commit()
            return true
        }catch (e: EntityExistsException){
            transaction.rollback()
            return false
        }
    }

    fun insertDireccion(direccion: Direccion) {
        transaction.begin()
        try {
            entityManager.persist(direccion)
            transaction.commit()
        }catch (e: EntityExistsException){
            transaction.rollback()
        }
    }

    fun insertPedido(pedido: Pedido):Boolean {
        transaction.begin()
        try {
            entityManager.persist(pedido)
            transaction.commit()
            return true
        }catch (e: EntityExistsException){
            transaction.rollback()
            return false
        }
    }

    fun insertTaller(taller: Taller):Boolean {
        transaction.begin()
        try {
            entityManager.persist(taller)
            transaction.commit()
            return true
        }catch (e: EntityExistsException){
            transaction.rollback()
            return false
        }
    }

    // Métodos Delete

    fun deleteCliente(dni: String):Boolean {
        entityManager.transaction.begin()
        val clienteARemover = entityManager.find(Cliente::class.java, dni)
        if (clienteARemover != null){
            entityManager.remove(clienteARemover)
            transaction.commit()
            return true
        }
        else {
            transaction.rollback()
            return false
        }
    }

    fun deleteDireccion(id: Long):Boolean {
        entityManager.transaction.begin()
        val direccionARemover = entityManager.find(Direccion::class.java, id)
        if (direccionARemover != null){
            entityManager.remove(direccionARemover)
            transaction.commit()
            return true
        }
        else {
            transaction.rollback()
            return false
        }
    }

    fun deletePedido(id: Long){
        entityManager.transaction.begin()
        val pedidoARemover = entityManager.find(Pedido::class.java, id)
        if (pedidoARemover != null){
            entityManager.remove(pedidoARemover)
            transaction.commit()
        }
        else transaction.rollback()
    }

    fun deleteTaller(cif: String){
        entityManager.transaction.begin()
        val tallerARemover = entityManager.find(Taller::class.java, cif)
        if (tallerARemover != null){
            entityManager.remove(tallerARemover)
            transaction.commit()
        }
        else transaction.rollback()
    }


    // Método para cerrar las instancias
    fun close() {
        entityManagerFactory.close()
        entityManager.close()
    }


}