# Aerolineas-SkyFly

# Sistema de Gestión de Vuelos — Aerolínea SkyFly

## Descripción del Proyecto

Este proyecto consiste en un sistema desarrollado en **Java** que permite administrar los vuelos programados de la aerolínea SkyFly. El sistema fue construido aplicando los principios de la **Programación Orientada a Objetos (POO)** combinados con la estructura de datos de **lista simplemente enlazada**, lo cual permite agregar vuelos de forma dinámica sin tener que definir un tamaño fijo desde el inicio.

El sistema cuenta con un menú interactivo por consola desde el cual el usuario puede consultar vuelos demorados, ver los vuelos ordenados por duración, cancelar vuelos por destino, cambiar el estado de un vuelo específico y encontrar el vuelo con mayor duración registrada.

---

## Problemática

La aerolínea SkyFly necesita un sistema que le permita controlar de manera organizada los vuelos que tiene programados. De cada vuelo se debe conocer:

- **Número de vuelo** (identificador)
- **Destino**
- **Estado** ("A tiempo", "Demorado" o "Cancelado")
- **Duración del trayecto** (medida en horas)

El sistema debe ser capaz de realizar las siguientes operaciones sobre esta información:

1. Mostrar únicamente los vuelos que estén **demorados**.
2. Mostrar todos los vuelos **ordenados de menor a mayor según su duración**.
3. **Eliminar** todos los vuelos que se dirijan a un destino específico indicado por el usuario y contabilizar cuántos fueron eliminados.
4. **Buscar un vuelo por su número** para cambiarle el estado.
5. Encontrar cuál es el **vuelo con la mayor duración** registrada.

Todo esto debía resolverse usando una **estructura de datos dinámica**, ya que la cantidad de vuelos puede variar y no se conoce de antemano cuántos se van a registrar.

---

## Estructura de Datos Empleada

Para resolver esta problemática se utilizó una **lista simplemente enlazada**, que es una estructura de datos formada por nodos donde cada nodo guarda un elemento y una referencia al siguiente nodo de la cadena. Esta estructura es ideal para el problema porque permite agregar vuelos de forma dinámica sin tener que definir un tamaño fijo, y además permite recorrerla fácilmente para buscar, eliminar o modificar elementos.

El sistema se organiza en **cuatro clases**:

### Clase `Nodo`
Es la pieza más básica de toda la estructura. Contiene dos atributos:
- `vuelos` (de tipo `Vuelos`): almacena la información del vuelo.
- `next` (de tipo `Nodo`): apunta al siguiente nodo de la lista.

Su constructor recibe un objeto `Vuelos`, lo asigna al atributo interno y coloca `next` en `null`, indicando que al momento de crearse ese nodo no tiene sucesor. Gracias a esta clase, cada vuelo queda encapsulado dentro de su propio nodo y la lista puede crecer tanto como sea necesario simplemente enlazando un nodo nuevo al final del último.

### Clase `Vuelos`
Es el modelo de datos del sistema. Tiene cuatro atributos privados:
- `numeroVuelo`
- `destino`
- `estado`
- `duracionVuelo`

El hecho de declararlos como `private` aplica el principio de **encapsulamiento**, lo que significa que solo pueden modificarse desde dentro de la propia clase mediante métodos públicos llamados getters y setters. El constructor recibe los cuatro valores y los asigna usando la palabra `this`. Los getters (`getNumeroVuelo()`, `getDuracionVuelo()`, etc.) devuelven el valor de cada atributo, mientras que los setters (`setEstado(String estado)`, etc.) permiten modificarlo de forma controlada. Además, el método `toString()` está sobrescrito con `@Override` para devolver una cadena con el formato de presentación del vuelo.

### Clase `ControlVuelo`
Es el corazón del sistema. Su único atributo es `Nodo head`, que representa el primer nodo de la lista. Cuando la lista está vacía, `head` vale `null`, y cuando se agrega el primer vuelo, `head` pasa a apuntar a ese primer nodo. Dentro de esta clase se encuentran todos los métodos que resuelven las operaciones pedidas:

| Método | Función |
|--------|---------|
| `aggVuelo(Vuelos vuelos)` | Inserta un nuevo vuelo al final de la lista. |
| `VuelosDemorados()` | Muestra los vuelos con estado "Demorado". |
| `MostrarOrdenadosPorDuracion()` | Ordena los vuelos por duración (burbuja) y los muestra. |
| `CancelarVuelosADestino(String destino)` | Elimina todos los vuelos hacia un destino y cuenta los eliminados. |
| `verificarVuelo(String numeroVuelo)` | Verifica si un vuelo existe en la lista. |
| `cambiarEstadoVuelo(String numeroVuelo, String estadoFinal)` | Cambia el estado de un vuelo específico. |
| `buscarVueloMasLargo()` | Encuentra y muestra el vuelo con mayor duración. |
| `mostrar()` | Método auxiliar que imprime toda la lista. |

### Clase `main`
Contiene el método `main(String[] args)`, que es el punto de entrada del programa. Se encarga de crear el objeto `ControlVuelo`, el `Scanner` para leer datos por teclado, precargar cinco vuelos de ejemplo y mostrar el menú interactivo con las seis opciones disponibles.

---

## Casos de Prueba Ejecutados

A continuación se detallan los casos de prueba ejecutados sobre el sistema, incluyendo flujos positivos, negativos y de borde, tal como lo exige la evaluación.

### Casos de prueba positivos (flujos normales)

| # | Caso de prueba | Entrada | Resultado esperado | Resultado obtenido |
|---|----------------|---------|--------------------|--------------------|
| 1 | Mostrar vuelos demorados | Opción 1 del menú | Se listan los vuelos con estado "Demorado" (ej. IB5678, AV2020) | Correcto |
| 2 | Ordenar vuelos por duración | Opción 2 del menú | Se listan los vuelos de menor a mayor duración (5.0 → 15.75) | Correcto |
| 3 | Cancelar vuelos a un destino existente | Opción 3 → "Madrid" | Se eliminan los vuelos a Madrid y se indica la cantidad eliminada | Correcto |
| 4 | Cambiar estado de un vuelo existente | Opción 4 → "AA1234" → "Demorado" | El estado del vuelo se actualiza correctamente | Correcto |
| 5 | Buscar el vuelo más largo | Opción 5 del menú | Se muestra el vuelo con mayor duración (QF0001, 15.75 h) | Correcto |

### Casos de prueba negativos (manejo de errores)

| # | Caso de prueba | Entrada | Resultado esperado | Resultado obtenido |
|---|----------------|---------|--------------------|--------------------|
| 6 | Cancelar vuelos a un destino inexistente | Opción 3 → "Tokio" | Mensaje: "El destino no existe entre los vuelos registrados" | Correcto |
| 7 | Cancelar vuelos con destino vacío | Opción 3 → "" (Enter) | Mensaje: "El destino no puede ser nulo ni estar vacío" | Correcto |
| 8 | Cambiar estado de un vuelo inexistente | Opción 4 → "XX9999" | Mensaje: "El número de vuelo introducido no existe" | Correcto |
| 9 | Opción inválida en el submenú de estados | Opción 4 → "9" | Mensaje: "La opción introducida es incorrecta…" y se repite el submenú | Correcto |

### Casos de borde

| # | Caso de prueba | Entrada | Resultado esperado | Resultado obtenido |
|---|----------------|---------|--------------------|--------------------|
| 10 | Lista vacía al ordenar | Se vacía la lista y se elige opción 2 | Mensaje: "No hay vuelos registrados" | Correcto |
| 11 | Lista vacía al buscar el vuelo más largo | Se vacía la lista y se elige opción 5 | Mensaje: "No hay vuelos registrados" | Correcto |
| 12 | Comparación sin distinguir mayúsculas | Opción 3 → "madrid" (minúsculas) | Se eliminan los vuelos a Madrid correctamente | Correcto |
| 13 | Espacios en blanco en el destino | Opción 3 → "  Madrid  " | Se normaliza con `trim()` y se eliminan los vuelos a Madrid | Correcto |

---

## Declaración Explícita sobre el Uso de Inteligencia Artificial

En cumplimiento con los criterios de evaluación y como muestra de transparencia académica, se declara de forma explícita el uso de herramientas de inteligencia artificial durante el desarrollo de este proyecto:

### Herramientas utilizadas
- **DeepSeek**
- **ChatGPT**

### Partes específicas en las que se empleó la IA

| Parte del proyecto | Uso de IA | Detalle del uso |
|--------------------|-----------|-----------------|
| **Implementación del código Java** | No | Ninguna línea de código fue generada, modificada o refactorizada por IA. Toda la lógica, clases, métodos y estructura de la lista enlazada fueron desarrollados íntegramente por los estudiantes. |
| **Diseño de la estructura de datos** | No | La decisión de usar una lista simplemente enlazada y el diseño de los nodos fue tomada por los estudiantes. |
| **Pruebas y depuración** | No | Los casos de prueba, la ejecución y la corrección de errores fueron realizados por los estudiantes. |
| **Documentación escrita (README)** | Sí, parcialmente | ChatGPT apoyó en la redacción y organización de los textos del README y del PowerPoint, así como en la mejora de la claridad de la documentación. |
| **Presentación (PowerPoint)** | Sí, parcialmente | DeepSeek sugirió la secuencia lógica de diapositivas y ChatGPT ayudó a redactar los textos, títulos y notas del orador. |
| **Resolución de dudas conceptuales** | Sí | Ambas herramientas se usaron para aclarar dudas teóricas sobre listas enlazadas, sin intervenir en la implementación práctica del código. |

### Conclusión de la declaración

El uso de inteligencia artificial se limitó exclusivamente a **apoyar la redacción de la documentación y la elaboración del material de exposición**. **No se utilizó IA en ninguna implementación relacionada con el programa o el código Java**. Todas las decisiones técnicas, la escritura del código, la corrección de errores y la verificación del funcionamiento del sistema fueron realizadas por los estudiantes de manera autónoma, aplicando sus propios conocimientos de programación orientada a objetos y estructuras de datos.

---

## Autores

- Alejandro J. Montesino Ruiz
- Adrian Enrique Reinoso Gómez
- Adrian Jesus Pelaez Rodriguez
- Katherine Alvarez Amador
- Edgar G. Hernández Domínguez

## Materia

Estructuras de Datos — Curso 2026-2027  
Facultad de Informática

## Fecha

13/10/2026

---

## Enlace al repositorio

https://github.com/tu-usuario/skyfly-aerolinea

## Presentación

[Enlace al PowerPoint de la exposición](enlace-al-powerpoint)
