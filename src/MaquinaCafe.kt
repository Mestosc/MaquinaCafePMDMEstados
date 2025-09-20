object MaquinaCafe {
    private var estadoActual: EstadosMaquinas = EstadosMaquinas.Idle
    fun hacerCafe(tipo: Cafe, monedas: Double) {
        println("Estado $estadoActual")
        when (estadoActual) {
            is EstadosMaquinas.Idle -> {
                if (tipo.precio <= monedas) {
                    println("Empezando a preparar cafe")
                    estadoActual = EstadosMaquinas.preparandoCafe
                    hacerCafe(tipo,monedas)
                } else {
                    println("No tienes suficiente dinero")
                }
            }
            is EstadosMaquinas.preparandoCafe -> {
                println("Preparando ${tipo.tipo}")
                estadoActual = EstadosMaquinas.sirviendoCafe(tipo.tipo.name)
                hacerCafe(tipo,monedas)
            }
            is EstadosMaquinas.sirviendoCafe -> {
                println("Sirviendo ${(estadoActual as EstadosMaquinas.sirviendoCafe).marca}")
                if (tipo.precio > monedas) {
                    println("Abonando la vuelta ${tipo.precio-monedas}")
                }
                println("Cafe servido")
                estadoActual = EstadosMaquinas.Idle
            }
            is EstadosMaquinas.fallo -> {
                println("Fallo: ${(estadoActual as EstadosMaquinas.fallo).error}")
            }
        }
    }
}