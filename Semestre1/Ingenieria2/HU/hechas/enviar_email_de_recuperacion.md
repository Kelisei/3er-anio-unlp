## Título:
*Enviar email de recuperación*

## Narrativa:
- Como administrador
- Quiero enviar un email de recuperacion
- Para poder moderar correctamente mi aplicacion

## Reglas de Negocio
## Criterios de aceptación:
- **Escenario 1: Envio de mail exitoso**
    + **Dado** un usuario "juanromanriquelme@gmail.com", con la cuenta bloqueada temporalmente
    + **Cuando** cuando selecciona el nombre del usuario "juanromanriquelme@gmail.com", presiona "Enviar" y confirma la operacion,
    + **Entonces** el sistema envia un email de recuperacion al usuario, **Y** en pantalla muestra "Email de recuperacion enviado".
    
- **Escenario 2: Envio de email fallido por cancelación**
    + **Dado** un usuario "juanromanriquelme@gmail.com", con la cuenta bloqueada temporalmente 
    + **Cuando** cuando selecciona el nombre del usuario, presiona "Enviar" y cancela la operacion,
    + **Entonces** el sistema muestra la pantalla previa

