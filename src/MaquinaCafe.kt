/**
 * Representacion de una maquina de cafe
 * @property estadoActual El estado actual de la maquina
 * @property filtroLimpio Si el filtro esta limpio o no, se ensucia desde el punto de vista de no poder hacer nada cada diez veces usa un valor booleano
 * @property cafesHechos La cantidad hecha, para revisar el filtro si es 10 o mayor no permite hacer cafes
 */
object MaquinaCafe {
    private var estadoActual: EstadosMaquinas = EstadosMaquinas.Idle // La maquina empieza en Idle
    var filtroLimpio = true // El filtro empieza limpio
    private var cafesHechos = 0 // Contador para rastrear la cantidad de cafés hechos, para decidir si el filtro ya esta muy sucio

    /**
     * Hace, un [cafe] en base al pago reflejado en [monedas] si el pago es suficiente,
     * lo hace, y ya si es más del necesario te da la vuelta y si es menos de lo necesario ni intenta hacerlo
     */
    fun hacerCafe(cafe: Cafe, monedas: Double) {
        when (estadoActual) {
            is EstadosMaquinas.Idle -> {
                empezarPreparacionCafe(monedas,cafe)
            }
            is EstadosMaquinas.PreparandoCafe -> {
                if (!filtroLimpio) {
                    estadoActual = EstadosMaquinas.Fallo("Filtro sucio") // Si el filtro no esta limpio, falla
                    hacerCafe(cafe,monedas)
                } else {
                    println("Preparando cafe")
                    //Thread.sleep(2000)
                    estadoActual = EstadosMaquinas.SirviendoCafe("catppuccino")
                    hacerCafe(cafe,monedas)
                }
            }
            is EstadosMaquinas.SirviendoCafe -> {
                println("Sirviendo ${(estadoActual as EstadosMaquinas.SirviendoCafe).marca}")
                println("Cafe servido")
                estadoActual = EstadosMaquinas.Idle
                if (monedas>cafe.precio) {
                    println("Le corresponden ${String.format("%.2f",monedas-cafe.precio)}€ de vuelta")
                }
                cafesHechos += 1
                if (cafesHechos>=10) {
                    filtroLimpio = false
                }
            }
            is EstadosMaquinas.Fallo -> {
                println("Fallo: ${(estadoActual as EstadosMaquinas.Fallo).error}")
            }
        }
    }

    /**
     * Esta funcion empieza la preparacion del cafe, pasando como parametro las [monedas] que son el dinero que gastas
     * y el [cafe] que es lo que quieres preparar
     */
    private fun empezarPreparacionCafe(monedas: Double, cafe:Cafe) {
        if (monedas >= cafe.precio) {
            println("Empezando a preparar cafe")
            estadoActual = EstadosMaquinas.PreparandoCafe
            hacerCafe(cafe, monedas) /* Esta llamada recursiva es necesaria si quieres
                                                            // quieres que actualice solo el estado, al menos en la manera de que */
        } else {
            println("No tienes suficiente dinero")
        }
    }
    /**
     * Limpia el filtro, si esta sucio
     */
    fun clean() {
        filtroLimpio = true
        cafesHechos = 0
        estadoActual = EstadosMaquinas.Idle
    }
}