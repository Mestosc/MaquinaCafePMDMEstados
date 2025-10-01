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
        override fun onEnter(cafe: Cafe) {
            if (!filtroLimpio) {
                MaquinaCafe.setState(cafe, Fallo("Filtro sucio"))
            } else {
                if (Datos.monedas<cafe.precio) {
                    println("No tienes suficiente dinero")
                } else if (Datos.monedas>=cafe.precio) {
                    MaquinaCafe.setState(cafe, PreparandoCafe)
                }

            }
        }
    }
    object PreparandoCafe : EstadosMaquinas() {
        override fun onEnter(cafe: Cafe) {
            println("Empezando a preparar cafe")
            println("Preparando cafe")
            MaquinaCafe.setState(cafe, SirviendoCafe(cafe.tipo.name))
        }
    }
    data class SirviendoCafe(val marca: String) : EstadosMaquinas() {
        override fun onEnter(cafe: Cafe) {
            println("Sirviendo ${(estadoActual as SirviendoCafe).marca}")
            println("Cafe servido")
            if (Datos.monedas>cafe.precio) {
                println("Le corresponden ${String.format("%.2f",Datos.monedas-cafe.precio)}€ de vuelta")
            }
            cafesHechos += 1
            Datos.monedas = 0.0
            if (cafesHechos>=10) {
                filtroLimpio = false
            } else {
                MaquinaCafe.setState(cafe, Idle)
            }
        }
    }
    data class Fallo(val error: String) : EstadosMaquinas() {
        override fun onEnter(cafe: Cafe) {
            println("Fallo: $error")
        }
    }
}