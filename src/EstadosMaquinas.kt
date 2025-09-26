import MaquinaCafe.estadoActual
import MaquinaCafe.filtroLimpio
import MaquinaCafe.cafesHechos
/**
 * Los estados de la maquina de cafe
 * @property Idle Estado de espera, la maquina esta esperando
 * @property PreparandoCafe Estado de preparacion el cafe esta preparandose
 * @property SirviendoCafe Estado de que el cafe esta siendo servido
 * @property Fallo Ha habido alguna clase de fallo
 */
sealed class EstadosMaquinas: EntrarCafe {
    object Idle : EstadosMaquinas() {
        override fun onEnter(cafe: Cafe, monedas: Double) {
            if (!filtroLimpio) {
                MaquinaCafe.setState(cafe,monedas, Fallo("Filtro sucio"))
            } else {
                if (monedas<cafe.precio) {
                    println("No tienes suficiente dinero")
                }
                MaquinaCafe.setState(cafe,monedas, PreparandoCafe)
            }
        }
    }
    object PreparandoCafe : EstadosMaquinas() {
        override fun onEnter(cafe: Cafe, monedas: Double) {
            println("Empezando a preparar cafe")
            println("Preparando cafe")
            MaquinaCafe.setState(cafe,monedas, SirviendoCafe(cafe.tipo.name))
        }
    }
    data class SirviendoCafe(val marca: String) : EstadosMaquinas() {
        override fun onEnter(cafe: Cafe, monedas: Double) {
            println("Sirviendo ${(estadoActual as SirviendoCafe).marca}")
            println("Cafe servido")
            if (monedas>cafe.precio) {
                println("Le corresponden ${String.format("%.2f",monedas-cafe.precio)}€ de vuelta")
            }
            cafesHechos += 1
            if (cafesHechos>=10) {
                filtroLimpio = false
            }
            MaquinaCafe.setState(cafe,monedas, Idle)
        }
    }
    data class Fallo(val error: String) : EstadosMaquinas() {
        override fun onEnter(cafe: Cafe, monedas: Double) {
            println("Fallo: $error")
        }
    }
}