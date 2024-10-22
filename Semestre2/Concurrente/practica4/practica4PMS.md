# PMS
## 1
Suponga  que  existe  un  antivirus  distribuido que  se  compone  de  R  procesos  robots 
Examinadores y 1 proceso Analizador. Los procesos Examinadores están buscando 
continuamente  posibles  sitios  web  infectados;  cada  vez  que  encuentran  uno  avisan  la 
dirección y luego continúan buscando. El proceso Analizador se encarga de hacer todas las 
pruebas necesarias con cada uno de los sitios encontrados por los robots para determinar si 
están o no infectados.  
a) Analice  el  problema  y  defina  qué  procesos,  recursos  y  comunicaciones  serán 
necesarios/convenientes para resolverlo. 
b) Implemente una solución con PMS sin tener en cuenta el orden de los pedidos. 
c) Modifique  el  inciso  (b)  para  que  el  Analizador  resuelva  los  pedidos  en  el  orden 
en que se hicieron. 
b)
```cpp
process Robot[0..R-1]{
    text sitio
    while(true){
        // buscar sitio
        Admin!aviso(sitio)
    }
}
process Analizador{
    text sitio
    while(true){
        Admin!pedido()
        Admin!aviso(sitio)
        analizar(sitio)
    }
}
// Si me llego un sitio, lo guardo en la cola,
// Si tengo pedidos en la cola espero a un pedido del analizar (esta libre) y se lo doy
process Admin{
    text sitioRecibido
    cola sitios<text>
    do {
        Robot[*]?aviso(sitioRecibido) -> sitios.push(sitioRecibido)
        [] !sitios.Empty(); Analizador?pedido()-> Analizador!aviso(sitios.pop())
    } od
}
```
## 2
En un laboratorio de genética veterinaria hay 3 empleados. El primero de ellos 
continuamente prepara las muestras de ADN; cada vez que termina, se la envía al segundo 
empleado  y  vuelve  a  su  trabajo.  El  segundo  empleado  toma  cada  muestra  de  ADN 
preparada,  arma  el  set  de  análisis  que  se  deben  realizar  con  ella  y  espera  el  resultado  para 
archivarlo.  Por  último,  el  tercer  empleado  se  encarga  de  realizar  el  análisis  y  devolverle  el 
resultado al segundo empleado.  
```cpp
process EmpleadoUno{
    while(true){
        muestra = prepararMuestra()
        EmpleadoDos!muestra(muestra)
    }
}
process EmpleadoDos{
    cola muestras, cola analisis
    do {
        EmpleadoUno?muestra(muestra) -> muestras.push(armarSet(muestra))
        [] !muestras.empty(); EmpleadoTres?pedido() -> EmpleadoTres!set(muestras.pop())
        [] !EmpleadoTres?analisis(analisis) -> analisis.push(analisi) 
    } od
}
process EmpleadoTres{
}
```