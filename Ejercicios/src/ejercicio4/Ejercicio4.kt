import ClienteRepository.anadirCliente
import ClienteRepository.borrarCliente
import Clientes.id
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    iniciarBaseDeDatos()

    while (true) {
        println("\nAGENDA DE CLIENTES")
        println("1. Añadir cliente")
        println("2. Consultar cliente")
        println("3. Borrar cliente")
        println("4. Editar cliente")
        println("5. Listar clientes")
        println("6. Salir")
        print("Elige una opción: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> menuAnadirCliente()
            2 -> menuConsultarCliente()
            3 -> menuBorrarCliente()
            4 -> menuEditarCliente()
            5 -> menuListarClientes()
            6 -> {
                println("Saliste del programa")
                return
            }
            else -> println("Opción no válida.")
        }
    }
}

fun menuAnadirCliente() {
    print("Introduce DNI: ")
    val dni = readln()

    print("Introduce nombre y apellidos: ")
    val nombre = readln()

    var tipo: String
    while (true) {
        print("Tipo de cliente (REGISTRADO / SOCIO): ")
        tipo = readln().uppercase()
        if (tipo == "REGISTRADO" || tipo == "SOCIO") break
        println("Tipo no válido.")
    }

    val cuota = if (tipo == "REGISTRADO") {
        print("Cuota máxima de pago: ")
        readln().toDoubleOrNull()
    } else null

    val nuevoCliente = Cliente(
        dni = dni,
        nombreCompleto = nombre,
        tipoCliente = tipo,
        cuotaMaxima = cuota,
        fechaAlta = obtenerFechaActualFormateada()
    )

    anadirCliente(nuevoCliente)
    println("Cliente añadido con éxito.")
}

fun menuConsultarCliente() {
    print("Introduce el ID del cliente a consultar: ")
    val id = readln().toLong()
    val cliente = ClienteRepository.consultarCliente(id)

    if (cliente != null) {
        println("Datos del cliente:")
        imprimirCliente(cliente)
    } else {
        println("No se encontró ningún cliente con ese ID.")
    }
}

fun menuBorrarCliente() {
    print("Introduce el ID del cliente a borrar: ")
    val id = readln().toLong()
    if (borrarCliente(id)) {
        println("Cliente borrado con éxito.")
    } else {
        println("No se encontró ningún cliente con ese ID para borrar.")
    }
}

fun menuEditarCliente() {
    println("\nEditar Cliente")
    print("Introduce el ID del cliente a editar: ")
    val id = readln().toLong()
    val clienteExistente = ClienteRepository.consultarCliente(id)

    if (clienteExistente == null) {
        println("No se encontró ningún cliente con ese ID.")
        return
    }

    println("Editando cliente. Deje el campo en blanco para no modificar.")

    print("Nuevo nombre (${clienteExistente.nombreCompleto}): ")
    val nombre = readlnOrNull().takeIf { !it.isNullOrBlank() } ?: clienteExistente.nombreCompleto

    var tipo: String
    while (true) {
        print("Nuevo tipo (${clienteExistente.tipoCliente}) (REGISTRADO / SOCIO): ")
        val tipoInput = readlnOrNull()?.uppercase()?.trim()
        if (tipoInput.isNullOrBlank()) {
            tipo = clienteExistente.tipoCliente
            break
        }
        if (tipoInput == "REGISTRADO" || tipoInput == "SOCIO") {
            tipo = tipoInput
            break
        }
        println("Tipo no válido.")
    }

    var cuota: Double? = clienteExistente.cuotaMaxima
    if (tipo == "REGISTRADO") {
        print("Nueva cuota (${cuota ?: "N/A"}): ")
        val cuotaInput = readlnOrNull()
        if (!cuotaInput.isNullOrBlank()) {
            cuota = cuotaInput.toDoubleOrNull()
        }
    } else {
        cuota = null
    }

    val clienteActualizado = clienteExistente.copy(
        nombreCompleto = nombre,
        tipoCliente = tipo,
        cuotaMaxima = cuota
    )

    if (ClienteRepository.editarCliente(clienteActualizado)) {
        println("Cliente actualizado con éxito.")
    } else {
        println("Error al actualizar el cliente.")
    }
}

fun menuListarClientes() {
    println("\nListado de Clientes")
    print("¿Ordenar por (DNI / FECHA)?: ")
    val orden = readlnOrNull()?.lowercase()?.trim() ?: "dni"
    val ordenarPorDni = !orden.contains("fecha")

    val clientes = ClienteRepository.listarClientes(ordenarPorDni)

    if (clientes.isEmpty()) {
        println("No hay clientes en la agenda.")
    } else {
        println("\n LISTA DE CLIENTES (Total: ${clientes.size}) ---")
        clientes.forEach { cliente ->
            imprimirCliente(cliente)
        }
    }
}

fun obtenerFechaActualFormateada(): String {
    val formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
    return LocalDateTime.now().format(formato)
}

fun imprimirCliente(cliente: Cliente) {
    println("")
    print("ID: ${cliente.id}    ")
    print("DNI: ${cliente.dni}    ")
    print("Nombre: ${cliente.nombreCompleto}    ")
    print("Tipo: ${cliente.tipoCliente}    ")
    if (cliente.tipoCliente == "REGISTRADO") {
        print("Cuota Max: ${cliente.cuotaMaxima ?: "No especificada"}    ")
    }
    print("Fecha Alta: ${cliente.fechaAlta}    ")
    println("")
}