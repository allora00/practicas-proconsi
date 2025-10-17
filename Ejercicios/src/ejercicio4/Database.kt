import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun iniciarBaseDeDatos() {
    Database.connect("jdbc:sqlite:agenda.db", "org.sqlite.JDBC")

    transaction {
        SchemaUtils.create(Clientes)
    }
    println("Base de datos iniciada y tabla 'Clientes' asegurada.")
}