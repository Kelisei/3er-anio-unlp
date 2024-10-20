# 1
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
# 2
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
    int cantPorCola[5] = ([5], 0), caja, idActualAtentido
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
# 3 
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
```

```
