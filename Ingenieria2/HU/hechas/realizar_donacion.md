## Título:
*Realizar donación*

## Narrativa:
- Como persona
- Quiero realizar una donacion monetaria 
- Para contribuir a la organizacion benefica

## Reglas de Negocio
- Conexion con la api exitosa.
## Criterios de aceptación:
- **Escenario 1: Donacion exitosa**
    + **Dado** una persona anonima que quiere donar 500.000 pesos, con una conexion a la api de la pasarela de pago exitosa, con numero de una tarjeta de credito valida 124, con fecha de expiracion de la tarjeta valida para 11/4/2025 y con codigo de seguridad valido 777 correcto
    + **Cuando** ingresa su valor a donar de 500.000, numero de tarjeta 1234, fecha de expiracion 11/4/2025 y codigo de seguridad 777, interactua con "Pagar" y confirma la operacion
    + **Entonces** el sistema registra el pago, se transfiere el pago a la cuenta de Cáritas **Y** muestra el mensaje de exito "Gracias por su donacion".

- **Escenario 2: Donacion fallida por conexion a api fallida**
    + **Dado** una conexion a la api de la pasarela de pago fallida
    + **Cuando** ingresa su valor a donar de 500.000, numero de tarjeta 1234, fecha de expiracion 11/4/2025 y codigo de seguridad 777, interactua con "Pagar" y confirma la operacion
    + **Entonces** el sistema muestra el mensaje de error "No se pudo realizar la donacion, intentelo mas tarde".

- **Escenario 3: Donacion fallida por numero de tarjeta invalido**
    + **Dado** una conexion a la api de la pasarela de pago exitosa, y el numero 3456 no corresponde a un numero de tarjeta de credito 
    + **Cuando** ingresa su valor a donar de 600.000, numero de tarjeta 3456, fecha de expiracion 11/5/2025 y codigo de seguridad 200, interactua con "Pagar" y confirma la operacion.
    + **Entonces** el sistema muestra el mensaje de error "No se pudo realizar la donacion, datos de tarjeta erroneos".

- **Escenario 4: Donacion fallida por tarjeta vencida**
    + **Dado** una conexion a la api de la pasarela de pago exitosa, y el numero 5555 que corresponde a una tarjeta, con codigo de seguridad 333 correcto, y con expiracion de 11/5/2023
    + **Cuando** ingresa su valor a donar de 600.000, numero de tarjeta 5555, fecha de expiracion 11/5/2023 y codigo de seguridad 333, interactua con "Pagar" y confirma la operacion
    + **Entonces** el sistema muestra el mensaje de error "No se pudo realizar la donacion, datos de tarjeta erroneos".

- **Escenario 5: Donacion fallida por codigo de seguridad erroneo**
    + **Dado** una conexion a la api de la pasarela de pago exitosa, y el numero 2222 que corresponde a una tarjeta, codigo de seguridad 355 incorrecto, y con expiracion de 11/5/2025
    + **Cuando** ingresa su valor a donar de 600.000, numero de tarjeta 2222, fecha de expiracion 11/5/2025 y codigo de seguridad 355, interactua con "Pagar" y confirma la operacion
    + **Entonces** el sistema muestra el mensaje de error "No se pudo realizar la donacion, datos de tarjeta erroneos".

- **Escenario 6: Donacion fallida por cancelación**
    + **Dado** una persona anonima que quiere donar 500.000 pesos, con una conexion a la api de la pasarela de pago exitosa, con numero de una tarjeta de credito valida 124, con fecha de expiracion de la tarjeta valida para 11/4/2025 y con codigo de seguridad valido 777 correcto
    + **Cuando** ingresa su valor a donar de 500.000, numero de tarjeta 1234, fecha de expiracion 11/4/2025 y codigo de seguridad 777, interactua con "Pagar" y se cancela la operacion
    + **Entonces** el sistema muestra la pantalla previa