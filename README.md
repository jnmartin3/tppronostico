# tppronostico

![Badge en Desarollo](https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green)
 
 Trabajo integrador del curso de Java Inicial de Argentina Programa

## Consigna
Se solicita crear un programa de consola que calcule los puntos a otorgar a jugadores que realizan pronósticos de partidos de futbol.
El aplicativo se alimenta de dos archivos *\*.csv* con resultados reales y pronóstico. 
Debe mostrar en orden los jugadores según la cantidad de aciertos que consiguieron.

El trabajo se estructura en cuatro entregas que van guiando en el avance hacie el objetivo final. 

## Primera entrega
(Viene de https://github.com/jnmartin3/integradorUTN)
Desarrollar un programa que lea un archivo de partidos y otro de resultados, el primero correspondiente a una ronda y el otro que contenga
los pronósticos de una persona. Cada ronda debe tener una cantidad fija de partidos, por ejemplo 2. El programa debe:
1)*Estar subido en un repositorio de GIT.* 2)*Tomar como argumento 2 rutas a cada archivo que se necesita.* 3)*Al leer las líneas de los archivos debe instanciar objetos de las clases propuestas.* 4)*Debe imprimir por pantalla el puntaje de la persona.*

Se estructura el aplicativo con la siguientes clases:
 - clase Main: instancia clase de acceso a archivos y luego con sus métodos muestra el pronostico, los resultados y calcula el puntaje,
 - clase AccesoArchivos: es el nucleo del aplicativo, su constructor accede a los archivos csv, y los procesa usando ArrayList para poder acceder a su contenido por medio de índices,
 - clase Pronostico: a través de su constructor se generan tantas instancias como pronosticos vengan en el archivo csv. A través de sus métodos se accede al contenido para operar.
 - clase Partido: de manera similar a la clase Pronostico hace lo mismo con cada resultado de los partidos jugados y cuyos datos aparecen en el archivo,
 - clase Player: es la clase que representa a cada jugador y su puntaje. En esta entrega hay un solo jugador pero en el esquema general se usará para almacenar cuantos jugadores existan y poder puntuarlos.

### Salida de consola
 - Permite visualizar el estado inicial de acceso a los archivos y sino evaluar las excepciones,
 - Muestra el contenido ordenado de ambos archivos para poder comparar en la misma consola el resultado final con los datos de origen,
 - Indica el puntaje del jugador
 
<p align="center">
 <img src="https://user-images.githubusercontent.com/97187862/227731001-90128909-4aeb-4055-9281-d5464316ebdc.png">
</p>
- En este caso el jugador Cacho pronosticó bien para el partido 2 y 3, por lo cual obtuvo 2 puntos.*

## Segunda entrega
(Desarrollado en este repositorio)
En esta entrega se debe poder soportar que los archivos contengan información de muchas rondas y de muchas personas (para eso hay que agregar los datos de ronda y persona en los
archivos correspondientes).
Por otro lado, al leer cada línea del archivo de resultados, se debe verificar que la misma sea correcta: número correcto de campos y que la cantidad de goles sea un número entero. Cada
ronda puede tener cualquier cantidad de partidos.
Al finalizar el programa, se debe imprimir un listado de los puntajes de cada persona que participa.
