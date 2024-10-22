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
Process EmpleadoUno{
    while(true){
        muestra = generarMuestra()
        Coordinador!muestra(muestra)
    }
}
Process EmpleadoDos{
    muestra, analisis
    while(true){
        Coordinador!recibir()
        Coordinador?muestras(muestra)
        EmpleadoTres!muestra(armarKit(muestra))
        EmpleadoTres?analisis(analisis)
        archivar(analisis)
    }
}
Process EmpleadoTres{   
    text muestra
    while(true){
        EmpleadoDos?muestra(muestra)
        analisis = analizar(muestra)
        EmpleadoDos!analisis(analisis)
    }
}
Process Coordinador{
    cola muestras
    do
        EmpleadoUno?muestra(muestra) -> muestra.push(muestra)
        [] !muestras.empty; EmpleadoDos?recibir() -> EmpleadoDos!muestra(muestras.pop())
    od
}
```
## 3
En  un  examen  final  hay  N  alumnos  y  P  profesores.  Cada  alumno  resuelve  su  examen,  lo 
entrega  y  espera  a  que  alguno  de  los  profesores  lo  corrija  y  le  indique  la  nota.  Los 
profesores corrigen los exámenes respetando el orden en que los alumnos van entregando.  
a) Considerando que P=1. 
b) Considerando que P>1. 
c) Ídem  b)  pero  considerando  que  los  alumnos  no  comienzan  a  realizar  su  examen  hasta 
que todos hayan llegado al aula. 
Nota: maximizar la concurrencia; no generar demora innecesaria; todos los procesos deben 
terminar su ejecución
a)
```cpp
Process Alumno[id:1..N]{
    //Hace el examen
    Coordinador!examenes(examen,id);
    Profesor?notas(nota);
}
Process Coordinador{
    cola examenes
    do
        Alumno[*]?examenes(examen,idA) -> examenes.push(examen,idA)
        [] !examenes.empty; Profesor?recibir() -> Profesor!pedidos(examenes.pop())
    od
}
Process Profesor{
    while(true){
        Coordinador!recibir();
        Coordinador?pedidos(examen,idA);
        int nota = corregirExamen(examen);
        Alumno[idA]!notas(nota);
    }
}
```
```cpp
Process Alumno[id:1..N]{
    //Hace el examen
    Coordinador!examenes(examen,id);
    Profesor[*]?notas(nota);
}
Process Coordinador{
    cola examenes
    do
        Alumno[*]?examenes(examen,idA) -> examenes.push(examen,idA)
        [] !examenes.empty; Profesor[*]?recibir(idP) -> Profesor[idP]!pedidos(examenes.pop())
    od
}
Process Profesor[id:1..P]{
    while(true){
        Coordinador!recibir(id);
        Coordinador?pedidos(examen,idA);
        int nota = corregirExamen(examen);
        Alumno[idA]!notas(nota);
    }
}
```
```cpp
Process Alumno[id:1..N]{
    //Hace el examen
    Barrera!llegue()
    Barrera?activar()
    Coordinador!examenes(examen,id);
    Profesor[*]?notas(nota);
}
Process Barrera{
    for(i=1, i<= N, i++)
        Alumno[*]?llegue()
    for(i=1, i<= N, i++)
        Alumno[i]!activar()
}
Process Coordinador{
    cola examenes
    do
        Alumno[*]?examenes(examen,idA) -> examenes.push(examen,idA)
        [] !examenes.empty; Profesor[*]?recibir(idP) -> Profesor[idP]!pedidos(examenes.pop())
    od
}
Process Profesor[id:1..P]{
    while(true){
        Coordinador!recibir(id);
        Coordinador?pedidos(examen,idA);
        int nota = corregirExamen(examen);
        Alumno[idA]!notas(nota);
    }
}
```
## 4
En  una  exposición  aeronáutica  hay  un  simulador  de  vuelo  (que  debe  ser  usado  con 
exclusión  mutua)  y  un  empleado  encargado  de  administrar  su  uso.  Hay  P  personas  que 
esperan a que el empleado lo deje acceder al simulador, lo usa por un rato y se retira. 
a) Implemente  una  solución  donde  el  empleado  sólo  se  ocupa  de  garantizar  la  exclusión 
mutua. 
b) Modifique la solución anterior para que el empleado considere el orden de llegada para 
dar acceso al simulador. 
Nota: cada persona usa sólo una vez el simulador.  
a)
```cpp
process Empleado{
    int idP
    while(true){
        Persona[*]?pedir(idP)
        Persona[idP]!recibir()
        Persona[idP]?salir()
    }
}
process Persona[id:0..P-1]{
    while(true){
        Empleado!pedir(id)
        Empleado?recibir()
        //Usar
        Empleado!salir()
    }
}
```
b)
```cpp
```cpp
process Empleado{
    cola pedidos
    bool ocupado = false
    int actual = -1
    do 
        Persona[*]?pedir(idP) -> 
            if (ocupado) 
                pedidos.push(idP) 
            else {
                Persona[idP]!recibir()
                actual = idP
                ocupado = true
            }
        [] Persona[actual]?salir(); -> 
            if (pedidos.empty()) {
                ocupado = false
                actual = -1
            } else {
                actual = pedidos.pop()
                Persona[actual]!recibir()
            }
    od
}

process Persona[id:0..P-1]{
    while(true){
        Empleado!pedir(id)
        Empleado?recibir()
        // Usar el simulador
        Empleado!salir()
    }
}
```
## 5
En un estadio de fútbol hay una máquina expendedora de gaseosas que debe ser usada por 
E  Espectadores  de  acuerdo  con  el  orden  de  llegada.  Cuando  el  espectador  accede  a  la 
máquina  en  su  turno  usa  la  máquina  y  luego  se  retira  para  dejar  al  siguiente.  Nota:  cada 
Espectador una sólo una vez la máquina. 
```cpp
process Coordinador {
    cola pedidos
    bool ocupado = false
    int actual = -1
    do 
        Espectador[*]?pedir(idP) -> 
            if (ocupado) 
                pedidos.push(idP) 
            else {
                Persona[idP]!recibir()
                actual = idP
                ocupado = true
            }
        [] Espectador[actual]?salir(); -> 
            if (pedidos.empty()) {
                ocupado = false
                actual = -1
            } else {
                actual = pedidos.pop()
                Persona[actual]!recibir()
            }
    od
}
process Espectador[id:0..E-1]{
    Coordinador!pedir(id)
    Coordinador?recibir()
    // Usar el simulador
    Coordinador!salir()
}
```