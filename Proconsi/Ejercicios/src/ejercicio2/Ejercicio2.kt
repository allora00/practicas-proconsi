package ejercicio2

import kotlin.time.Duration
import kotlin.time.measureTime

fun main(){

    val texto: String = "Proconsi es una empresa de Tecnologías de la Información y la Comunicación especializada en el desarrollo e integración de soluciones informáticas para todo tipo de empresas. Más de tres décadas de experiencia avalan a una compañía tan flexible como responsable. Cuenta con un equipo multidisciplinar de más de 120 profesionales cualificados, expertos y comprometidos con un único objetivo: hallar la solución tecnológica exacta para cada cliente. Proconsi es especialista en la creación y el desarrollo de software de gestión, consultoría tecnológica, dirección y gestión de proyectos I+D+i basados en TIC, soporte técnico, aplicaciones móviles y fomento de tendencias en nuevas tecnologías, como el cloud computing."

    val caracteres = numCaracteres(texto)
    println("El numero de caracteres es: $caracteres")

    val mayuscula = mayuscula(texto)
    println(mayuscula)

    val minuscula = minuscula(texto)
    println(minuscula)

    palabrasRep(texto)

    val sustituir = sustituir(texto)
    println(sustituir)

    val concatenar = concatenar(texto)
    println("Se ha terdado: $concatenar en concatenar la cadena")
}

fun numCaracteres(texto: String): Int{
    var caracteres = 0
    for(aux in texto){
        if(aux.toString() != " " && aux.toString() != "\n"){
            caracteres+= 1
        }
    }
    return caracteres
}

fun mayuscula(texto: String){
    for(aux in texto){
        print(aux.uppercase())
    }

}

fun minuscula(texto: String){
    for(aux in texto){
        print(aux.lowercase())
    }
}

fun palabrasRep(texto: String){
    val palabras = texto.split(" ")
    val conteo = palabras.groupingBy { it }.eachCount()
    val repetidas = conteo.filter { it.value > 1 }.keys
    if (repetidas.isEmpty()) {
        println("No hay palabras repetidas.")
    } else {
        println("Palabras repetidas:")
        for (palabra in repetidas) {
            print("$palabra  " )
        }
        println("")
    }
}

fun sustituir(texto: String){
    print(texto.replace("Proconsi", "Isnocorp"))
}

fun concatenar(texto: String): Duration{
    var resultado = ""
    var tiempo = measureTime {
        for(texto in 1..1000 step 1){
            resultado += texto
        }
    }
    return tiempo
}