/**
 * Representacion de una maquina de cafe
 * @property estadoActual El estado actual de la maquina
 * @property filtroLimpio Si el filtro esta limpio o no, se ensucia desde el punto de vista de no poder hacer nada cada diez veces usa un valor booleano
 * @property cafesHechos La cantidad hecha, para revisar el filtro si es 10 o mayor no permite hacer cafes
 */
object MaquinaCafe {
    private var estadoActual: EstadosMaquinas = EstadosMaquinas.Idle
    private var filtroLimpio = true
    private var cafesHechos = 0

    /**
     * Hace, un [cafe] en base al pago reflejado en [monedas] si el pago es suficiente,
     * lo hace, y ya si es más del necesario te da la vuelta y si es menos de lo necesario ni intenta hacerlo
     */
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
                cafe.canitdadIngredientes -= 1
            }
            is EstadosMaquinas.fallo -> {
                println("Fallo: ${(estadoActual as EstadosMaquinas.fallo).error}")
            }
        }
    }

    /**
     * Limpia el filtro
     */
    fun clean() {
        filtroLimpio = true
        estadoActual = EstadosMaquinas.Idle
    }
}