# PMA
## 1
Suponga  que  N  clientes  llegan  a  la  cola  de  un  banco  y  que  serán  atendidos  por  sus 
empleados. Analice el problema y defina qué procesos, recursos y canales/comunicaciones 
serán necesarios/convenientes para resolverlo. Luego, resuelva considerando las siguientes 
situaciones: 
a. Existe un único empleado, el cual atiende por orden de llegada. 
```cpp
chan Reportes(texto)
process Cliente[id:0..N-1]{
    texto R
    send Reportes(R)
}

process Empleado{
    texto R
    for (int i = 0; i < N; i++){
        R = receive Reportes()
        resolver(R)
    }
}
```
b. Ídem a) pero considerando que hay 2 empleados para atender, ¿qué debe 
modificarse en la solución anterior? 
```cpp
chan Reportes(texto)
process Cliente[id:0..N-1]{
    texto R
    send Reportes(R)
}

process Empleado[0..1]{
    texto R
    while(true){
        R = receive Reportes()
        resolver(R)
    }
}
```
c. Ídem  b)  pero  considerando  que,  si  no  hay  clientes  para  atender,  los  empleados 
realizan  tareas  administrativas  durante  15  minutos.  ¿Se  puede  resolver  sin  usar 
procesos adicionales? ¿Qué consecuencias implicaría?
```cpp
// Sin procesos adicionales
chan Reportes(texto)
process Cliente[id:0..N-1]{
    texto R
    send Reportes(R)
}

process Empleado[0..1]{
    texto R
    while(true){
        if(empty (Reportes)){
            delay(15)
        } else {
            R = receive Reportes()
            resolver(R)
        }
    }
}
// Como no se bloquea por siempre, puede ser que varios vean no empty el coso y vayan a recibir,
// demora innecesaria.
```
```cpp
// Con otro proceso
chan Reportes(texto);
chan Solicitud(int);
chan Siguiente[3](texto);

process Coordinador {
    texto reporte;
    int idEmpleado;
    while (true) {
        // Coordinador se encarga de recibir peticiones de tareas de mpleados, si hay reportes a resolver le envia uno, sino le envia vacio.
        receive Solicitud(idEmpleado);
        if (empty(Reportes)) 
            reporte = "VACÍO";
        else 
            receive Reportes(reporte);
        send Siguiente[idEmpleado](reporte);
    }
}

process Empleado[id: 0..2] {
    texto reporte;
    // Empleados envian que estan libres, si el siguiente que sacan es una tarea vacia, entonces esperan, sino resuelven
    while (true) {
        send Solicitud(id);
        receive Siguiente[id](reporte);
        if (reporte != "VACÍO") 
            resolver(reporte);
        else 
            delay(600); // tareas administrativas durante 10 minutos
    }
}

process Cliente[id: 0..N-1] {
    texto reporte;
    while (true) {
        // Clientes envian reportes
        reporte = generarReporteConProblema();
        send Reportes(reporte);
    }
}
```
## 2
Se  desea  modelar  el  funcionamiento  de  un  banco  en  el  cual  existen  5  cajas  para  realizar 
pagos.  Existen  P  clientes que  desean  hacer  un  pago.  Para  esto,  cada  una  selecciona  la  caja donde hay menos personas esperando; una vez seleccionada, espera a ser atendido. En cada caja, los clientes son atendidos por orden de llegada por los cajeros. Luego del pago, se les 
entrega un comprobante. Nota: maximizar la concurrencia.
```cpp
chan Comprobantes[P](text)
chan Cola[5](int)
chan CajaAsignada[P](int)
chan Terminaciones(int)
chan Peticiones(int)
chan HayPedido(bool) //Este canal unicamente se usa para evitar busy waiting

process Administrador{
    int cantPorCola[5] = ([5], 0), caja, idActualAtendido
    while(true){
        bool pedido
        receive HayPedido(pedido) //Si hay algun pedido (termino o hubo una peticion) avanzamos (receive es bloqueante)
        // Si hay gente que termino reduzco la cantidad en la cola (esta actividad tiene más prioridad)
        if(!empty(Terminaciones)){
            receive Terminaciones(caja)
            cantPorCola[caja]--
            // Sino y hay peticiones, saco al siguiente, consigo la caja con menos gente, le doy la caja la persona, y aumento al cantidad
        } else if (!empty(Peticiones)){
            receive Peticiones(idActualAtendido)
            caja = minCaja(cantPorCola)
            send CajaAsignada[idActualAtentido](caja)
            cantPorCola[idActualAtentido]++
        }
    }
}

process Cliente[0..P-1]{
    int caja; text comprobante
    // Pido una caja
    send Peticiones(id)
    send HayPedido(true)
    receive CajaAsignada[id](caja)
    // Me pongo en una cola y espero terminar, al terminan me avisan y aviso al admin que termine
    send Cola[caja](id)
    receive Comprobantes[id](comprobante)
    send Terminaciones(caja)
    send HayPedido(true)
}
process Caja[id:0..4]{
    while(true){
        // Recibo los siguientes, los atiendo y les pongo el comprobante donde van
        int idSiguiente
        receive Cola(idSiguiente)
        comprobante = pago(idSiguiente)
        send Comprobantes[idSiguiente](comprobante)
    }
}
```
## 3 
3.  Se  debe  modelar  el  funcionamiento  de  una  casa  de  comida  rápida,  en  la  cual  trabajan  2 
cocineros  y  3  vendedores,  y  que  debe  atender  a  C  clientes.  El  modelado  debe  considerar 
que: 
- Cada cliente realiza un pedido y luego espera a que se lo entreguen. 
- Los pedidos que hacen los clientes son tomados por cualquiera de los vendedores y se 
lo pasan a los cocineros para que realicen el plato. Cuando no hay pedidos para atender, 
los vendedores aprovechan para reponer un pack de bebidas de la heladera (tardan entre 
1 y 3 minutos para hacer esto). 
- Repetidamente cada cocinero toma un pedido pendiente dejado por los vendedores, lo 
cocina y se lo entrega directamente al cliente correspondiente. 
Nota: maximizar la concurrencia.
```cpp
chan Pedidos(text, int) // Pedido e id del chabon
chan VendedorLibre(int) // Id de los vendedores libres
chan ColaVendedores[3](text, int) // Pedidos de los vendedores
chan Platos[C](text)
chan ColaCocineros(text, int)

process Administrador{
    while(true){
        text pedido, int idCliente, idVendedor
        receive VendedorLibre(idVendedor)
        if (empty(Pedidos)) {
            send ColaVendedores[idVendedor]('vacio', -1)
        } else {
            receive Pedidos(pedido, idCliente)
            send ColaVendedores[idVendedor]('pedido', idCliente)
        }
    }
}

process Cliente[id:0..C-1]{
    text pedido = ...;
    send Pedidos(pedido, id)
    receive Platos[id](pedido)
}

process Cocinero[0..1]{
    while(true){
        int id, text plato
        receive ColaCocineros(plato, id)
        send Platos[id](generarPedido(plato))
    }
} 

process Vendedor[id:0..2]{
    while(true){
        text pedido, int idCliente
        send VendedorLibre(id)
        receive ColaVendedores[id](pedido, idCliente)
        if(pedido == 'vacio')
            reponerPack() // Tarda entre 1 y 3 minutos
        else 
            send ColaCocineros(pedido, idCliente)
    }
}
```
## 4
Simular  la  atención  en  un  locutorio  con  10  cabinas  telefónicas,  el  cual  tiene  un  empleado 
que se encarga de atender a N clientes. Al llegar, cada cliente espera hasta que el empleado 
le  indique  a  qué  cabina  ir,  la  usa  y  luego  se  dirige  al  empleado  para  pagarle.  El  empleado 
atiende a los clientes en el orden en que hacen los pedidos. A cada cliente se le entrega un 
ticket factura por la operación.  
a) Implemente una solución para el problema descrito. 
b) Modifique  la  solución  implementada  para  que  el  empleado  dé  prioridad  a  los  que 
terminaron de usar la cabina sobre los que están esperando para usarla. 
Nota:  maximizar  la  concurrencia;  suponga  que  hay  una  función  Cobrar()  llamada  por  el 
empleado que simula que el empleado le cobra al cliente.
```cpp
chan PeticionCabina(int)
chan CabinaAsignada[N](int)
chan ListoParaPagar(int, int)
chan Tickets[N](text)

process Empleado{
    bool ocupacionCabina = ([10], false), int idCliente, int cabina
    while(true){
        receive HayPeticion()
        if (hayCabinaLibre(ocupacionCabina) && !empty(PeticionCabina)){ //hayCabina chequea si algun booleano esta libre, siguienteCabina agarra la proximaCabina y pone copada
            receive PeticionCabina(idCliente)
            send CabinaAsignada[idCliente](siguienteCabina(ocupacionCabina))
        elif (!hayCabinaLibre){
            receive ListoParaPagar(idCliente, cabina)
            send Tickets[idCliente] (Cobrar(idCliente))
            ocupacionCabina[cabina] = false
            receive PeticionCabina(idCliente)
            send CabinaAsignada[idCliente](siguienteCabina(ocupacionCabina))
        } else {
            receive ListoParaPagar(idCliente, cabina)
            send Tickets[idCliente] (Cobrar(idCliente))
            ocupacionCabina[cabina] = false
        }
    }
}
process Cliente[id:0..N-1]{
    int cabina
    send PeticionCabina(id)
    send HayPeticion(true)
    receive CabinaAsignada[id](cabina)
    Usar()
    send ListoParaPagar(id, cabina)
    send HayPeticion(true)
    text ticket
    receive Tickets[id](ticket)
}
```
b)
```cpp
chan PeticionCabina(int)
chan CabinaAsignada[N](int)
chan ListoParaPagar(int, int)
chan Tickets[N](text)

process Empleado{
    bool ocupacionCabina = ([10], false), int idCliente, int cabina
    while(true){
        receive HayPeticion()
        if (!empty(ListoParaPagar)) {
            receive ListoParaPagar(idCliente, cabina)
            send Tickets[idCliente] (Cobrar(idCliente))
            ocupacionCabina[cabina] = false
        } elif (hayCabinaLibre(ocupacionCabina) && !empty(PeticionCabina)){ //hayCabina chequea si algun booleano esta libre, siguienteCabina agarra la proximaCabina y pone copada
            receive PeticionCabina(idCliente)
            send CabinaAsignada[idCliente](siguienteCabina(ocupacionCabina))
        else {
            receive ListoParaPagar(idCliente, cabina)
            send Tickets[idCliente] (Cobrar(idCliente))
            ocupacionCabina[cabina] = false
            receive PeticionCabina(idCliente)
            send CabinaAsignada[idCliente](siguienteCabina(ocupacionCabina))
        }
    }
}
process Cliente[id:0..N-1]{
    int cabina
    send PeticionCabina(id)
    send HayPeticion(true)
    receive CabinaAsignada[id](cabina)
    Usar()
    send ListoParaPagar(id, cabina)
    send HayPeticion(true)
    text ticket
    receive Tickets[id](ticket)
}
```
## 5 
Resolver la administración de 3 impresoras de una oficina. Las impresoras son usadas por N 
administrativos, los cuales están continuamente trabajando y cada tanto envían documentos 
a  imprimir.  Cada  impresora,  cuando  está  libre,  toma  un  documento  y  lo  imprime,  de 
acuerdo con el orden de llegada.  
a) Implemente una solución para el problema descrito. 
b) Modifique la solución implementada para que considere la presencia de un director de 
oficina que también usa las impresas, el cual tiene prioridad sobre los administrativos. 
c) Modifique la solución (a) considerando que cada administrativo imprime 10 trabajos y 
que todos los procesos deben terminar su ejecución. 
d) Modifique la solución (b) considerando que tanto el director como cada administrativo 
imprimen 10 trabajos y que todos los procesos deben terminar su ejecución. 
e) Si la solución al ítem d) implica realizar Busy Waiting, modifíquela para evitarlo. 
Nota: ni los administrativos ni el director deben esperar a que se imprima el documento.
a)
```cpp
chan PeticionUso(text)
process Impresora[0..2]{
    text documento
    while (true) {
        receive PeticionUso(documento)
        imprimirDocumento(documento)
    }
}
process Administrativo[0..N-1]{
    text documento
    while(true){
        // Trabajar
        send PeticionUso(documento)
    }
}
```
b)
```cpp
chan PeticionUso(text)
chan PeticionUsoPrioritaria(text)
chan HayPeticion(bool)
chan ImpresoraLibre(int)
chan Impresiones[3](text)

process Impresora[id:0..2]{
    while (true) {
        send ImpresoraLibre(id)
        receive Impresiones[id](documento)
        imprimirDocumento(documento)
    }
}

process Coordinador(){
    text documento, bool peticion, int impresora
    while(true){
        receive HayPeticion(peticion)
        receive ImpresoraLibre(impresora)
        if(!empty(PeticionUsoPrioritaria)){
            receive PeticionUsoPrioritaria(documento)
        } else {
            receive PeticionUso(documento)
        }
        send Impresiones[impresora](documento)
    }
}

process Director{
    text documento;
    while(true) {
        send PeticionUsoPrioritaria(documento)
        send HayPeticion(true)
    }
}

process Administrativo[0..N-1]{
    text documento;
    while(true){
        // Trabajar
        send PeticionUso(documento)
        send HayPeticion(true)
    }
}
```
c)
```cpp
chan PeticionUso(text)
chan PedidosHechos(int)
process Impresora[id:0..2]{
    text documento, int contadorHechos
    if(id == 0){
        send PedidosHechos(0)
    }
    receive PedidosHechos(contadorHechos)
    while (contadorHechos < 10*N) { 
        contadorHechos++
        send PedidosHechos(contadorHechos)
        receive PeticionUso(documento)
        receive PedidosHechos(contadorHechos)
    }
    send PedidosHechos(contadorHechos)
}
process Administrativo[0..N-1]{
    text documento
    for(int i=0; i<10; i++){
        // Trabajar
        send PeticionUso(documento)
    }
}
```
d)
```cpp
chan PeticionUso(text)
chan PeticionUsoPrioritaria(text)
chan HayAccion(bool)
chan ActivarImpresora[3](bool)
chan Documentos[3](text)
chan ImpresoraLibre(int)
chan PedidosHechos(int)
 
process Impresora[id:0..2]{
    text documento, int contadorHechos
    if(id == 0){
        send PedidosHechos(0)
    }
    receive PedidosHechos(contadorHechos)
    while (contadorHechos < 11*N) { 
        contadorHechos++
        send PedidosHechos(contadorHechos)

        send ImpresoraLibre(id)
        receive ActivarImpresora[id]()
        receive Documentos[id](documento)
        imprimirDocumento(documento)

        receive PeticionUso(documento)
        receive PedidosHechos(contadorHechos)
    }
    send PedidosHechos(contadorHechos)
}

process Administrativo[0..N-1]{
    text documento
    for(int i=0; i<10; i++){
        // Trabajar
        send PeticionUso(documento)
        send HayAccion(false) // False significa una accion que representa una no terminacion
    }
    send HayAccion(true)
}

process Director{
    text documento
    for(int i=0; i<10; i++){
        // Trabajar
        send PeticionUsoPrioritaria(documento)
        send HayAccion(false) // False significa una accion que representa una no terminacion
    }
    send HayAccion(true)
}

process Coordinador{
    text documento, int impresora
    bool deboTerminar = false
    for (int i = 0; i<N*11;i++){
        receive HayAccion()
        receive ImpresoraLibre(impresora)
        if (!empty(PeticionUsoPrioritaria)) {
            receive PeticionUsoPrioritaria(documento)
        } else {
            receive PeticionUso(documento)
        }
        send Documentos[impresora](documento)
        send ActivarImpresora[impresora](false)
    }
}
```