## Título:
*Puntuar intercambio*

## Narrativa:
- Como usuario
- Quiero puntuar otro usuario 
- Para reconocer la calidad de las contribuciones de otros miembros

## Reglas de Negocio
- Una puntuacion total más baja de 3 estrellas bloqueara la solicitacion de intercambios

## Criterios de aceptación:
- **Escenario 1: Puntuacion exitosa con bloqueo de solicitudes**
    + **Dado** el usuario "dimaria@gmail.com" ha completado una interacción con el usuario "juanromanriquelme@gmail.com", con una calificación promedio de 5 estrellas, y no ha recibido ninguna calificación hasta el momento, y desea calificarlo con 0 estrellas
    + **Cuando**  selecciona la opción de calificar al otro usuario, elige 0 estrellas y confirma la operación
    + **Entonces** el sistema registra la puntuación de "juanromanriquelme@gmail.com", quedando en 2.5 estrellas, y esta actualización se refleja en el perfil del usuario calificado. Además, se bloquea la funcionalidad de solicitar intercambio.

## Criterios de aceptación:
- **Escenario 2: Puntuacion exitosa sin bloqueo de solicitudes**
    + **Dado** el usuario "dimaria@gmail.com" ha completado una interacción con el usuario "juanromanriquelme@gmail.com", con una calificación promedio de 5 estrellas, y no ha recibido ninguna calificación hasta el momento, y desea calificarlo con 5 estrellas
    + **Cuando**  selecciona la opción de calificar al otro usuario, elige 5 estrellas y confirma la operación
    + **Entonces** el sistema registra la puntuación de "juanromanriquelme@gmail.com", quedando en 5 estrellas, y esta actualización se refleja en el perfil del usuario calificado.

## Criterios de aceptación:
- **Escenario 3: Puntuacion fallida por cancelacion**
    + **Dado** el usuario "dimaria@gmail.com" ha completado una interacción con el usuario "juanromanriquelme@gmail.com", con una calificación promedio de 5 estrellas, y no ha recibido ninguna calificación hasta el momento, y desea calificarlo con 4 estrellas
    + **Cuando**  selecciona la opción de calificar al otro usuario, elige 0 estrellas y cancela la operación
    + **Entonces** el sistema muestra la pantalla previa