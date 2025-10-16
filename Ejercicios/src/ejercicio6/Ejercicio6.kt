package ejercicio6

import kotlin.random.Random
import kotlin.math.PI


sealed class Figura(
    open val color: String,
    open val coordenadaX: Int,
    open val coordenadaY: Int,
    open val area: Double
)

data class Circulo(
    val radio: Double,
    override val color: String,
    override val coordenadaX: Int,
    override val coordenadaY: Int,
    override val area: Double
): Figura(color, coordenadaX, coordenadaY, area)

data class Triangulo(
    val altura: Int,
    val base: Int,
    override val color: String,
    override val coordenadaX: Int,
    override val coordenadaY: Int,
    override val area: Double
): Figura(color, coordenadaX, coordenadaY, area)

data class Cuadrado(
    val lado: Int,
    override val color: String,
    override val coordenadaX: Int,
    override val coordenadaY: Int,
    override val area: Double
): Figura(color, coordenadaX, coordenadaY, area)




fun main(){

    println("Introduzca el numero de circulos que desea generar: ")
    val nCirculos = readln().toIntOrNull() ?: 0
    println("Introduzca el numero de triangulos que desea generar: ")
    val nTriangulos = readln().toIntOrNull() ?: 0
    println("Introduzca el numero de cuadrados que desea generar: ")
    val nCuadrados = readln().toIntOrNull() ?: 0

    val figuras = mutableListOf<Figura>()

    repeat(nCirculos){
        val nuevoCirculo = crearCirculo()
        figuras.add(nuevoCirculo)
    }

    repeat(nTriangulos){
        val nuevoTriangulo = crearTriangulo()
        figuras.add(nuevoTriangulo)
    }

    repeat(nCuadrados){
        val nuevoCuadrado = crearCuadrado()
        figuras.add(nuevoCuadrado)
    }

    mostrarFiguras(figuras)
    mostrarAgrupadas(figuras)
}


fun crearCirculo(): Circulo {
    val radio = (1..20).random().toDouble()
    val color = colorAleatorio()
    val coordenadaX = (0..100).random()
    val coordenadaY = (0..100).random()
    val area = PI*radio*radio
    return Circulo(radio, color, coordenadaX, coordenadaY, area)
}

fun crearTriangulo(): Triangulo {
    val altura = (1..20).random()
    val base = (1..100).random()
    val color = colorAleatorio()
    val coordenadaX:Int = (0..100).random()
    val coordenadaY:Int = (0..100).random()
    val area = (base*altura)/2.0
    return Triangulo(altura, base, color, coordenadaX, coordenadaY, area)
}

fun crearCuadrado(): Cuadrado{
    val lado = (0..20).random()
    val color = colorAleatorio()
    val coordenadaX:Int = (0..100).random()
    val coordenadaY:Int = (0..100).random()
    val area = lado*lado.toDouble()
    return Cuadrado(lado, color, coordenadaX, coordenadaY, area)
}

fun colorAleatorio(): String{
    val colores = "rojo, verde, azul, amarillo, morado, gris, blanco, negro, marrón, naranja, rosa"
    val aux = colores.split(", ")
    return aux.random()
}

fun mostrarFiguras(lista: List<Figura>) {
    println("Figuras en orden de creación: " )
    if(lista.isEmpty()){
        println("La lista esta vacia")
        return
    }
    lista.forEachIndexed { index, figura -> println("${index+1}. $figura")}
}

fun mostrarAgrupadas(lista: List<Figura>) {
    println("\nFiguras por tipo: ")
    if(lista.isEmpty()){
        println("La lista esta vacia")
        return
    }
    println("\nCirculos: ")
    lista.filterIsInstance<Circulo>().forEach(){println(it)}

    println("\nTriangulos: ")
    lista.filterIsInstance<Triangulo>().forEach(){println(it)}

    println("\nCuadrados: ")
    lista.filterIsInstance<Cuadrado>().forEach(){println(it)}
}

