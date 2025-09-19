object MaquinaCafe {
    private var estadoActual: EstadosMaquinas = EstadosMaquinas.Idle
    fun hacerCafe(tipo: Cafe, monedas: Double) {
        println("Estado ${estadoActual}")
        when (estadoActual) {
            is EstadosMaquinas.Idle -> {
                if (tipo.precio >= monedas) {
                    println("Empezando a preparar cafe")
                    estadoActual = EstadosMaquinas.preparandoCafe
                    println("Preparando ${tipo.tipo}")
                    estadoActual = EstadosMaquinas.sirviendoCafe(tipo.tipo.name)
                } else {
                    println("No tienes suficiente dinero")
                }
            }
            is EstadosMaquinas.sirviendoCafe -> {
                println("Sirviendo ${marca}")
                if (tipo.precio > monedas) {
                    println("Abonando la vuelta ${tipo.precio-monedas}")
                }
            }
            is EstadosMaquinas.fallo -> {
                println("")
            }
            else -> {
                println("Que mierda haces")
            }
        }
    }
}