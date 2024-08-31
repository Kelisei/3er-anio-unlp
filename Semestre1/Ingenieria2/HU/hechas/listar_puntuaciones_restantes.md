## Título:
*Listar puntaciones restantes*

## Narrativa:
- Como usuario registrado
- Quiero ver mis puntuaciones restantes
- Para visualizar aquellas que me faltan hacer

## Reglas de Negocio

## Criterios de aceptación:
- **Escenario 1: Listado exitoso con datos**
    + **Dado** un usuario "juanromanriquelme@gmail.com" con intercambios pendientes por puntuar
    + **Cuando** selecciona la opción con "Ver puntuaciones restantes"
    + **Entonces** el sistema muestra en pantalla un listado de todas las publicaciones pendientes de puntuar, acompañadas de la opción para asignar estrellas.
    
- **Escenario 2: Listado exitoso sin datos**
    + **Dado** un usuario "dimaria@gmail.com" que no tiene intercambios por puntuar 
    + **Cuando**  selecciona la opción con "Ver puntuaciones restantes"
    + **Entonces** el sistema muestra en pantalla un mensaje indicando "No hay puntuaciones pendientes por asignar".