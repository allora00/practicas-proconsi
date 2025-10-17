import org.jetbrains.exposed.sql.Table
object Clientes : Table() {
    val dni = varchar("dni", 9)
    val nombreCompleto = varchar("nombre_completo", 255)
    val tipoCliente = varchar("tipo_cliente", 20)
    val cuotaMaxima = double("cuota_maxima").nullable()
    val fechaAlta = varchar("fecha_alta", 25)

    override val primaryKey = PrimaryKey(dni)
}

data class Cliente(
    val dni: String,
    val nombreCompleto: String,
    val tipoCliente: String,
    val cuotaMaxima: Double?,
    val fechaAlta: String
)