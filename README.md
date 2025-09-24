# ☕ Máquina de Café - Un Proyecto en Kotlin ☕

¡Bienvenido a mi pequeño proyecto de una máquina de café! 🌟 Este es un ejemplo sencillo para demostrar cómo funciona una máquina de estados en Kotlin.

## 🚀 Clases del Proyecto

Aquí tienes un resumen de las clases que hacen la magia posible:
### Clases Necesarias 
Son las clases necesarias para hacer las pruebas
#### `Main.kt` ▶️

El punto de entrada de la aplicación. Aquí es donde creamos un café y le pedimos a nuestra `MaquinaCafe` que se ponga a trabajar.
Aqui hacemos pruebas con los posibles estados

#### `MaquinaCafe.kt` 🤖

Este es el cerebro de la operación. Se trata de un `object` (un Singleton) que representa nuestra máquina de café. Gestiona los diferentes estados por los que pasa la máquina para preparar un delicioso café.

#### `EstadosMaquinas.kt`🚦

Aquí es donde se definen los posibles estados de nuestra máquina. Usando una `sealed class`, nos aseguramos de que solo puedan existir los siguientes estados:

-   `Idle`: La máquina está esperando a que alguien pida un café. 😴
-   `preparandoCafe`: La máquina está en proceso de preparación. 👨‍🍳
-   `sirviendoCafe`: ¡El café está listo y se está sirviendo! ☕
-   `fallo`: Algo ha salido mal. 😱
### Clases extra
Para hacer algo más complejo si se quiere(puse a futuro pero ya lo implemente), si quieres hacer algo más complejo, aqui implemente más cosas, que el estado para hacer pruebas
más complejas porque me apetecio, no son estrictamente necesarias para la tarea pero las implemente junto a las otra, para hacer cosas algo más avanzadas
#### `Cafe.kt` 📝

Una `data class` muy simple que representa un café. Contiene propiedades como:

-   `precio`: ¿Cuánto cuesta? 💰
-   `cantidadAzucar`: ¿Lo quieres dulce? 🍬
-   `tipo`: El tipo de café (definido en `TiposCafe`).

#### `TiposCafe.kt` 📋

Un `enum` que define los tipos de café que nuestra máquina puede preparar. Por ahora, tenemos:

-   `CAPPUCCINO`
-   `DESCAFEINADO`
-   `CHOCOLATE`



---

Y eso es todo, aqui esta explicado el flujo de ejecucion en la maquina de estado, en este caso yo he añadido la clase Cafe, TiposCafe y así como un extra para hacerlo a posteriori,
si deseo hacer algo más complejo pasar como parametro el cafe y lo que vale