import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object ClienteRepository {

    private fun toCliente(row: ResultRow): Cliente = Cliente(
        id = row[Clientes.id],
        dni = row[Clientes.dni],
        nombreCompleto = row[Clientes.nombreCompleto],
        tipoCliente = row[Clientes.tipoCliente],
        cuotaMaxima = row[Clientes.cuotaMaxima],
        fechaAlta = row[Clientes.fechaAlta]
    )

    fun anadirCliente(cliente: Cliente) {
        transaction {
            Clientes.insert {
                it[dni] = cliente.dni
                it[nombreCompleto] = cliente.nombreCompleto
                it[tipoCliente] = cliente.tipoCliente
                it[cuotaMaxima] = cliente.cuotaMaxima
                it[fechaAlta] = cliente.fechaAlta
            }
        }
    }

    fun consultarCliente(id: Long): Cliente? {
        return transaction {
            Clientes.select { Clientes.id eq id }
                .map { toCliente(it) }
                .singleOrNull()
        }
    }

    fun borrarCliente(id: Long): Boolean {
        return transaction {
            val rowsDeleted = Clientes.deleteWhere { Clientes.id eq id }
            rowsDeleted > 0
        }
    }

    fun editarCliente(clienteActualizado: Cliente): Boolean {
        return transaction {
            val rowsUpdated = Clientes.update({ Clientes.id eq clienteActualizado.id!! }) {
                it[nombreCompleto] = clienteActualizado.nombreCompleto
                it[tipoCliente] = clienteActualizado.tipoCliente
                it[cuotaMaxima] = clienteActualizado.cuotaMaxima
            }
            rowsUpdated > 0
        }
    }

    fun listarClientes(ordenarPorDNI: Boolean = true): List<Cliente> {
        return transaction {
            val query = if (ordenarPorDNI) {
                Clientes.selectAll().orderBy(Clientes.dni to SortOrder.ASC)
            } else {
                Clientes.selectAll().orderBy(Clientes.fechaAlta to SortOrder.ASC)
            }
            query.map { toCliente(it) }
        }
    }
}