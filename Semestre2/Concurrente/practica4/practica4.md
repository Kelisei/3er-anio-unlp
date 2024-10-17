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
chan Reportes[3]<Texto>
chan Pedido<int>
chan 
```
# 2
Se  desea  modelar  el  funcionamiento  de  un  banco  en  el  cual  existen  5  cajas  para  realizar 
pagos.  Existen  P  clientes que  desean  hacer  un  pago.  Para  esto,  cada  una  selecciona  la  caja donde hay menos personas esperando; una vez seleccionada, espera a ser atendido. En cada 
caja, los clientes son atendidos por orden de llegada por los cajeros. Luego del pago, se les 
entrega un comprobante. Nota: maximizar la concurrencia.
```cpp
chan 
process Cliente[0..P-1]{

}
process Caja[id:0..4]{

}
```