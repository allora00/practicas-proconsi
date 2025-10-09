package ejercicio1

import kotlin.math.round
import kotlin.math.pow

fun main(){
    println("Introduce 2 numeros y los decimales a los que quieres redondear")
    print("Numero 1: ")
    val num1 = readln().toDouble()
    print("Numero 2: ")
    val num2 = readln().toDouble()
    print("Decimales: ")
    val decimales = readln().toInt()

    val suma = suma(num1, num2, decimales)
    println("Suma: $suma")
    val resta  =resta(num1, num2, decimales)
    println("Resta: $resta")
    val multiplicacion = multiplicacion(num1, num2, decimales)
    println("Multiplicacion: $multiplicacion")
    val division = division(num1, num2, decimales)
    println("Division: $division")
    val modulo = modulo(num1, num2, decimales)
    println("Modulo: $modulo")
    val comparar = comparar(num1, num2)
    println("Comparar: $comparar")
}

fun suma(num1: Double, num2: Double, decimales: Int): Double {
    val resultado  = num1 + num2
    val redondeo = 10.0.pow(decimales)
    return round(redondeo * resultado) / redondeo
}

fun resta(num1: Double, num2: Double, decimales: Int): Double {
    val resultado  = num1 - num2
    val redondeo = 10.0.pow(decimales)
    return round(redondeo * resultado) / redondeo
}

fun division(num1: Double, num2: Double, decimales: Int): Double{
    val resultado  = num1 / num2
    val redondeo = 10.0.pow(decimales)
    return round(redondeo * resultado) / redondeo
}

fun multiplicacion(num1: Double, num2: Double, decimales: Int): Double{
    val resultado  = num1 * num2
    val redondeo = 10.0.pow(decimales)
    return round(redondeo * resultado) / redondeo
}

fun modulo(num1: Double, num2: Double, decimales: Int): Double{
    val resultado  = num1 % num2
    val redondeo = 10.0.pow(decimales)
    return round(redondeo * resultado) / redondeo
}

fun comparar(num1: Double, num2: Double){
    if(num1 > num2){
        println("El primer numero es mayor que el segundo")
    }else if(num1 < num2){
        println("El segundo numero es mayor que el primero")
    }else{
        println("Los dos numeros son iguales")
    }
}