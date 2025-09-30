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
    /**
     * Cambia el estado de la maquina a [nuevoEstado] si la transicion es valida, pasando el [cafe] al nuevo estado
     */
    fun setState(cafe:Cafe,nuevoEstado: EstadosMaquinas) {
        if (transicionValida(cafe,nuevoEstado)) { // El dinero y el cafe no importan en este caso
            estadoActual = nuevoEstado
            actualizarEstado(cafe)
        } else {
            println("Transición inválida de $estadoActual a $nuevoEstado")
        }
    }
    /**
     * Actualiza el estado de la maquina, pasando el [cafe]
     */
    fun actualizarEstado(cafe: Cafe) {
        estadoActual.onEnter(cafe)
    }

    /**
     * Verifica si la transicion de estados es valida, en base al estado actual, el [nuevoEstado] ya que dependiendo de cuál sea, y él [cafe]
     */
    fun transicionValida(cafe: Cafe, nuevoEstado: EstadosMaquinas): Boolean {
        return when (estadoActual) {
            is EstadosMaquinas.Idle -> {
                when (nuevoEstado) {
                    is EstadosMaquinas.PreparandoCafe -> Datos.monedas>=cafe.precio && filtroLimpio
                    is EstadosMaquinas.Fallo -> !filtroLimpio /* Me he dado cuenta de que
                    si empezara desde Idle y fuera a preparandoCafe a fallo si el filtro no esta limpio seria conceptualmente más dificil
                    porque empezaria a hacer el cafe veria que no esta limpio y no lo serviria, y seria extraño así es mejor*/
                    else -> false
                }
            }
            is EstadosMaquinas.PreparandoCafe -> {
                when (nuevoEstado) {
                    is EstadosMaquinas.SirviendoCafe -> true
                    else -> false
                }
            }
            is EstadosMaquinas.SirviendoCafe -> {
                when (nuevoEstado) {
                    is EstadosMaquinas.Idle -> true
                    else -> false
                }}
            is EstadosMaquinas.Fallo -> {
                false // Desde fallo no se puede ir a ningun otro estado
            }
        }
    }
    /**
     * Hace, un [cafe] en base al pago que haya en [Datos.monedas], si no hay suficiente dinero no
     * lo hace, y ya si es más del necesario te da la vuelta y si es menos de lo necesario ni intenta hacerlo
     */
    fun hacerCafe(cafe: Cafe) {
        estadoActual = EstadosMaquinas.Idle
        estadoActual.onEnter(cafe)
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