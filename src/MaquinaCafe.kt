object MaquinaCafe {
    private var estadoActual: EstadosMaquinas = EstadosMaquinas.Idle
    private var filtroLimpio = true
    private var cafesHechos = 0
    fun hacerCafe(cafe: Cafe, monedas: Double) {
        when (estadoActual) {
            is EstadosMaquinas.Idle -> {
                if (monedas>cafe.precio) {
                    println("Empezando a preparar cafe")
                    estadoActual = EstadosMaquinas.preparandoCafe
                    hacerCafe(cafe, monedas) /* Esta llamada recursiva es necesaria si quieres
                                            // quieres que actualice solo el estado, al menos en la manera de que */
                }
            }
            is EstadosMaquinas.preparandoCafe -> {
                if (cafe.canitdadIngredientes<1 && !filtroLimpio) {
                    estadoActual = EstadosMaquinas.fallo("No tenemos el material necesario para preparar su producto, y el filtro esta sucio")
                } else if (cafe.canitdadIngredientes<1) {
                    estadoActual = EstadosMaquinas.fallo("No tenemos el material necesario para preparar su producto")
                }
                else if (!filtroLimpio) {
                    estadoActual = EstadosMaquinas.fallo("Filtro sucio")
                } else {
                    println("Preparando cafe")
                    estadoActual = EstadosMaquinas.sirviendoCafe("catppuccino")
                }
                hacerCafe(cafe,monedas)
            }
            is EstadosMaquinas.sirviendoCafe -> {
                println("Sirviendo ${(estadoActual as EstadosMaquinas.sirviendoCafe).marca}")
                println("Cafe servido")
                estadoActual = EstadosMaquinas.Idle
                if (monedas>cafe.precio) {
                    println("Le corresponden ${String.format("%.2f",monedas-cafe.precio)}€ de vuelta")
                }
                if (cafesHechos>10) {
                    filtroLimpio = false
                }
                cafesHechos += 1
            }
            is EstadosMaquinas.fallo -> {
                println("Fallo: ${(estadoActual as EstadosMaquinas.fallo).error}")
            }
        }
    }
    fun clean() {
        filtroLimpio = true
        estadoActual = EstadosMaquinas.Idle
    }
}