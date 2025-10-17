// Archivo: ClienteDAO.kt

/*import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.StringEntity
import org.jetbrains.exposed.dao.id.StringEntityClass

class ClienteDAO(id: EntityID<String>) : StringEntity(id) {
    // ESTA LÍNEA ES LA QUE ARREGLA EL ERROR '.new()'
    // Le da a la "fábrica" el poder de crear, buscar y gestionar objetos ClienteDAO.
    companion object : StringEntityClass<ClienteDAO>(Clientes)

    // El resto de tu código ya estaba bien.
    var nombreCompleto by Clientes.nombreCompleto
    var tipoCliente by Clientes.tipoCliente
    var cuotaMaxima by Clientes.cuotaMaxima
    var fechaAlta by Clientes.fechaAlta
}

fun ClienteDAO.toCliente(): Cliente {
    return Cliente(
        dni = this.id.value,
        nombreCompleto = this.nombreCompleto,
        tipoCliente = this.tipoCliente,
        cuotaMaxima = this.cuotaMaxima,
        fechaAlta = this.fechaAlta
    )
}

 */