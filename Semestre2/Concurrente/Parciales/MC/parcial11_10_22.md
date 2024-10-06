# Semaforos
## 1
Resolver con SEMÁFOROS el siguiente problema. 
En una planta verificadora de vehículos, existen 7 estaciones donde se dirigen 150 vehículos para ser verificados. 
Cuando un vehículo llega a la planta, el coordinador de la planta le indica a qué estación debe dirigirse. 
El coordinador selecciona la estación que tenga menos vehículos asignados en ese momento. 
Una vez que el vehículo sabe qué estación le fue asignada, se dirige a la misma y espera a que lo llamen para verificar. Luego de la revisión, la estación le entrega un comprobante que indica si pasó la revisión o no. Más allá del resultado, el vehículo se retira de la planta. Nota: maximizar la concurrencia.
```cpp
sem accesoVector = 1, accesoCola[7] = ([7],1), despertarEstacion[7] = ([7],0), despertarVehiculo[150] = ([150],0) despertarCoodinador = 0, mutexColaCompartida = 1
int vec[7]
cola llegadas[7], compartida
Comprobante comprobantes[150]
process estacion[id:1..7]{
    while(true){
        P(despertarEstacion[id])
        P(accesoCola[id])
        int sig = llegadas.pop()
        V(accesoCola[id])
        Comprobante comprobante
        //Verificar
        comprobantes[sig] = comprobante
        V(despertarVehiculo[sig])
        P(accesoVector)
        vec[id]--
        V(accesoVector)
    }
}

process coordinador{
    for i:=1..150{
        P(despertarCoodinador) 

        P(mutexColaCompartida)
        int sig = compartida.pop()
        V(mutexColaCompartida)

        P(accesoVector)
        int estacion = min(vec)
        vec[estacion]++
        V(accesoVector)

        estaciones[sig] = estacion
        V(despertarVehiculo[sig])
    }
}

process vehiculo[id:1..7]{
    P(mutexColaCompartida)
    compartida.push(id)
    V(mutexColaCompartida)
    V(despertarCoodinador)

    P(despertarVehiculo[id])
    P(accesoCola[estaciones[id]])
    llegadas[estaciones[id]].push(id)
    V(accesoCola[estaciones[id]])
    V(despertarEstacion[estaciones[id]])

    P(despertarVehiculo[id])
    Comprobante comprobante = comprobantes[id]
}
```
# Monitores
## 2
2. Resolver con MONITORES el siguiente problema. En un sistema operativo se ejecutan 20 procesos que periódicamente realizan cierto cómputo mediante la función Procesar(). Los resultados de dicha función son persistidos en un archivo, para lo que se requiere de acceso al subsistema de E/S. Sólo un proceso a la vez puede hacer uso del subsistema de E/S, y el acceso al mismo se define por la prioridad del proceso (menor valor indica mayor prioridad).
```cpp
process proceso[id:1..20]{
    int prioridad = ...
    while(true){
        Resultado res = Procesar()
        Subsistema.pasar(id, prioridad)
        //Escribir
        Subsistema.irse()
    }
}

monitor Subsistema{
    bool libre = true
    cond despertarse[20]
    Cola cola<int, int>
    procedure pasar(in int id, prioridad){
        if(libre){
            libre = false
        } else {
            cola.push(id, prioridad)
            wait(despertarse[id])
        }
    }

    procedure irse(){
        if(cola.isEmpty()){
            libre = true
        } else {
            id, _ = cola.pop()
            signal(despertarse[id])
        }
    }
}
```