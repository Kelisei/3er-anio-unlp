## Título:
*Generar reporte*

## Narrativa:
- Como administrador
- Quiero generar un reporte
- Para para analizar los datos

## Reglas de Negocio
## Criterios de aceptación:
- **Escenario 1: Generacion de reportes exitosa sobre categoria más intercambiada**
    + **Dado** el administrador con intencion de generar un reporte sobre "Categoria más intercambiada", **Y** hay datos sobre esta categoria
    + **Cuando** se interactua con "Generar reporte"
    + **Entonces** el sistema muestra en pantalla un grafico de la "Categoria más intercambiada".

- **Escenario 2: Generacion de reportes exitosa sobre categoria más intercambiada sin datos**
    + **Dado**  el administrador con intencion de generar un reporte sobre "Categoria más intercambiada", **Y** no hay datos sobre esta categoria
    + **Cuando** se interactua con "Generar reporte"
    + **Entonces** se muestra en pantalla "No hay datos sobre esta categoria"

- **Escenario 3: Generacion de reportes exitosa sobre cantidad de transaccion hechas**
    + **Dado** el administrador con intencion de generar un reporte sobre "Cantidad de transacciones hechas", **Y** hay datos sobre esta categoria
    + **Cuando** se interactua con "Generar reporte"
    + **Entonces** el sistema muestra en pantalla un grafico de la "Estadisticas ya disponibles en su computadora".

- **Escenario 4: Generacion de reportes exitosa sobre cantidad de transaccion hechas sin datos**
    + **Dado** el administrador con intencion de generar un reporte sobre "Cantidad de transacciones hechas", **Y** no hay datos sobre esta categoria
    + **Cuando** se interactua con "Generar reporte" de la categoria más intercambiada
    + **Entonces** el sistema muestra en pantalla "No hay datos sobre esta categoria"

- **Escenario 5: Generacion de reportes sobre cantidad de transaccion fallidas**
    + **Dado** el administrador con intencion de generar un reporte sobre "Cantidad de transacciones fallidas", **Y** hay datos sobre esta categoria
    + **Cuando** se interactua con "Generar reporte"
    + **Entonces** el sistema muestra en pantalla un grafico de la "Estadisticas ya disponibles en su computadora".
    
- **Escenario 6: Generacion de reportes sobre categoria  sobre cantidad de transaccion fallidas exitoso sin datos**
    + **Dado** el administrador con intencion de generar un reporte sobre "Cantidad de transacciones fallidas", **Y** no hay datos sobre esta categoria
    + **Cuando** se interactua con "Generar reporte" de la categoria más intercambiada
    + **Entonces** el sistema muestra en pantalla "No hay datos sobre esta categoria"

- **Escenario 7: Generacion de reportes sobre los usuarios bloqueados permanentemente**
    + **Dado** el administrador con intencion de generar un reporte sobre "Lista negra de usuarios", **Y** hay datos sobre esta categoria
    + **Cuando** se interactua con "Mostrar lista negra"
    + **Entonces** el sistema muestra en pantalla un listado de la con todos los usuarios en la lista, sus DNIs, con un boton desbloquear.
    
- **Escenario 8: Generacion de reportes sobre los usuarios bloqueados permanentemente**
    + **Dado** el administrador con intencion de generar un reporte sobre "Lista negra de usuarios", **Y** no hay datos sobre esta categoria
    + **Cuando** se interactua con "Mostrar lista negra" de la categoria más intercambiada
    + **Entonces** el sistema muestra en pantalla "No hay datos sobre esta categoria"