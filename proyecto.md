# Emberlords

Simulador de batallas de criaturas personalizables, por turnos, y dec builder.

# Diseño del proyecto
El proyecto esta diseñado con el patron de diseño MVP para separar lo que es el mostrado de las pantallas de la logica del juego en si.

Para mostrar las pantallas se decidió usar lanterna porque es muy simple y corre en cualquier sistema operativo.

## ¿Cómo se usa Lanterna en este proyecto?

En vez de leer Strings del scanner, se leen KeyStroke de Lanterna, y en vez de escribir en la consola con `System.out.println` se dibuja a la `screen` directamente.

El proyecto esta fragmentado en diferentes "pantallas" con el patron State, esto permite intercambiar los menu y no perder la informacion o el estado.

Las pantallas se intercambian en tiempo de ejecucion para brindar una experiencia rica y dinamica.
