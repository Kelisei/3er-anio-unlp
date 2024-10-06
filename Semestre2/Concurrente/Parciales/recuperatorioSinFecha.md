# Semaforos
## 1
Resolver con SEMÁFOROS el siguiente problema. En un restorán trabajan C cocineros y M mozos. De forma repetida, los cocineros preparan un plato y lo dejan listo en la bandeja de platos terminados, mientras que los mozos toman los platos de esta bandeja para repartirlos entre los comensales. Tanto los cocineros como los mozos trabajan de a un plato por vez. Modele el funcionamiento del restorán considerando que la bandeja de platos listos puede almacenar hasta P platos. No es necesario modelar a los comensales ni que los procesos terminen. NOTA: es el problema de productores/consumidores con tamaño de buffer limitado visto en la página 14 de la teoría 4.
```cpp
sem espaciosLibres = P, espaciosOcupados = 0
sem mutexMozos =1, mutexCocineros = 1
int ocupado = 0, libre = 0
Plato platos[P]
process Cocinero[1..C]{
    while(true){
        // Producir
        P(espaciosLibres)
        P(mutexCocineros)
        platos[libre] = plato
        libre = (libre + 1) mod P
        V(mutexCocineros)
        V(espaciosOcupados) 
    }
}

process Mozo[1..M]{
    while(true){
        P(espaciosOcupados)
        P(mutexMozos)
        plato = platos[ocupado]
        ocupado = (ocupado + 1) mod P
        V(mutexMozos)
        V(espaciosLibres) 
        // Usar
    }
}
```
# Monitores
## 2
Resolver con MONITORES el siguiente problema. En una planta verificadora de vehículos existen 5 estaciones de verificación. Hay 75 vehículos que van para ser verificados, cada uno conoce el número de estación a la cual debe ir. Cada vehículo se dirige a la estación correspondiente y espera a que lo atiendan. Una vez que le entregan el comprobante de verificación, el vehículo se retira. Considere que en cada estación se atienden a los vehículos de acuerdo con el orden de llegada. Nota: maximizar la concurrencia.
```cpp
process Auto[id:1..75]{
    int numEs = ...;
    Fila[numEs].siguiente()
    Comprobante comprobante 
    Estacion[numEs].ir(comprobante)
    Fila[numEs].irse()
}
process Verificador[id:1..5]{
    while(true){
        Estacion[id].siguiente()
        //Verificar
        Estacion[id].darComprobante(comprobante)
    }
}

monitor Fila[1..5]{
    bool libre = true
    cond sig
    int esperando = 0
    process siguiente(){
        if(libre){
            libre=false
        } else {
            esperando++
            wait(sig)
        }
    }

    procedure irse(){
        if(esperando > 0){
            esperando--
            signal(sig)
        } else {
            libre=true
        }
    }
}

monitor Estacion[1..5]{
    bool esperando = false
    cond despertarVerificador, hayComprobante, meFui
    Comprobante comprobante 

    procedure siguiente(){
        if(!esperando){
            wait(despertarVerificador)
        }
    }

    procedure ir(c out Comprobante){
        esperando = true
        wait(hayComprobante)
        c = comprobante
        esperando = false
        signal(meFui)
    }

    procedure darComprobante(c in Comprobante){
        comprobante = c
        signal(hayComprobante)
        wait(meFui)
    }
}
```