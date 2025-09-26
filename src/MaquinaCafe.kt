/**
 * Representacion de una maquina de cafe
 * @property estadoActual El estado actual de la maquina
 * @property filtroLimpio Si el filtro esta limpio o no, se ensucia desde el punto de vista de no poder hacer nada cada diez veces usa un valor booleano
 * @property cafesHechos La cantidad hecha, para revisar el filtro si es 10 o mayor no permite hacer cafes
 */
object MaquinaCafe {
    var estadoActual: EstadosMaquinas = EstadosMaquinas.Idle // La maquina empieza en Idle
    var filtroLimpio = true // El filtro empieza limpio
    var cafesHechos = 0 // Contador para rastrear la cantidad de cafés hechos, para decidir si el filtro ya esta muy sucio
    fun setState(cafe:Cafe,monedas: Double,nuevoEstado: EstadosMaquinas) {
        if (transicionValida(monedas,cafe,nuevoEstado)) { // El dinero y el cafe no importan en este caso
            estadoActual = nuevoEstado
            actualizarEstado(cafe,monedas)
        } else {
            println("Transición inválida de $estadoActual a $nuevoEstado")
        }
    }
    fun actualizarEstado(cafe: Cafe, monedas: Double) {
        estadoActual.onEnter(cafe,monedas)
    }
    fun transicionValida(monedas: Double, cafe: Cafe, nuevoEstado: EstadosMaquinas): Boolean {
        return when (estadoActual) {
            is EstadosMaquinas.Idle -> {
                when (nuevoEstado) {
                    is EstadosMaquinas.PreparandoCafe -> monedas>=cafe.precio && filtroLimpio
                    is EstadosMaquinas.Fallo -> !filtroLimpio
                    else -> false
                }
            }
            is EstadosMaquinas.PreparandoCafe -> {
                when (nuevoEstado) {
                    is EstadosMaquinas.SirviendoCafe -> filtroLimpio
                    else -> false
                }
            }
            is EstadosMaquinas.SirviendoCafe -> {
                when (nuevoEstado) {
                    is EstadosMaquinas.Idle -> true
                    else -> false
                }}
            else -> false
        }
    }
    /**
     * Hace, un [cafe] en base al pago reflejado en [monedas] si el pago es suficiente,
     * lo hace, y ya si es más del necesario te da la vuelta y si es menos de lo necesario ni intenta hacerlo
     */
    fun hacerCafe(cafe: Cafe, monedas: Double) {
        estadoActual.onEnter(cafe,monedas)
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