object MaquinaCafe {
    private var estadoActual: EstadosMaquinas = EstadosMaquinas.Idle
    fun hacerCafe() {
        println("Estado $estadoActual")
        when (estadoActual) {
            is EstadosMaquinas.Idle -> {
                println("Empezando a preparar cafe")
                estadoActual = EstadosMaquinas.preparandoCafe
                    //hacerCafe(tipo,monedas) /* Esta llamada recursiva es necesaria si quieres
                                            // quieres que actualice solo el estado */
            }
            is EstadosMaquinas.preparandoCafe -> {
                println("Preparando cafe")
                estadoActual = EstadosMaquinas.sirviendoCafe("catppuccino")
                //hacerCafe(tipo,monedas)
            }
            is EstadosMaquinas.sirviendoCafe -> {
                println("Sirviendo ${(estadoActual as EstadosMaquinas.sirviendoCafe).marca}")
                println("Cafe servido")
                estadoActual = EstadosMaquinas.Idle
            }
            is EstadosMaquinas.fallo -> {
                println("Fallo: ${(estadoActual as EstadosMaquinas.fallo).error}")
            }
        }
    }
}