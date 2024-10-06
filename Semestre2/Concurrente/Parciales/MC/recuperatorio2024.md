# Semaforos
## 1
En una planta verificadora de vehículos existe un puesto de atención para atender a 30 vehiculos que deben ser verificados. En el puesto de atencion hay un empleado que atiende a los vehículos de acuerdo al orden de llegada, realiza la verificación y le entrega el comprobante, luego espera que el vehículos abandone el puesto para poder atender al siguiente. Todos los procesos deben terminar

```cpp
sem mutex =1, despertarEmpleado = 0, despertarVehiculo[30] = ([30], 0), fin = 0
Comprobante comprobanteCompartido
Cola llegadas<int>
process vehiculo[id:1..30]{
    P(mutex)
    llegadas.push(id)
    V(mutex)
    V(despertarEmpleado)
    P(despertarVehiculo[id])
    Comprobante comprobante = comprobanteCompartido
    V(fin)
}

process Verificador{
    for :=1..30{
        P(despertarEmpleado)
        P(mutex)
        int idSig = llegadas.pop()
        V(mutex)
        //Verificar
        comprobanteCompartido = comprobante
        V(despertarVehiculo[idSig])
        P(fin)
    }
}
```

# Monitores
## 2
En una oficina hay un empleado para atender solicitudes de N personas que vienen a realizar un trámite de acuerdo al orden de llegada. Cada persona espera a que el empleado atienda su trámite y le de el resultado. Cuando el empleado está libre atiende el sig pedido pendiente y si no hay ninguno lee un libro por 10min. Todos los procesos deben terminar.
```cpp
process empleado{
    int atendidos = 0
    while(atendidos < N){
        bool hayUno
        Fila.siguiente(hayUno)
        if(hayUno){
            atendidos++
            Tramite tramite
            Oficina.atender(tramite)
            Oficina.darResultado(tramite.resolver())
        } else {
            delay(10)
        }
    }
}

process persona[1..N]{
    Fila.encolarse()
    Tramite tramite
    Oficina.atenderse(tramite)
}
monitor Fila{
    int esperando = 0
    cond despertarse
    procedure encolarse(){
        esperando++
        wait(despertarse)
    }

    procedure siguiente(bool hayUno out){
        if(esperando == 0){
            hayUno = false
        } else {
            esperando--
            signal(despertarse)
            hayUno = true
        }
    }
}
monitor Oficina{
    Tramite resultado, tramite
    cond empleado, hayResultado, fin 
    bool hayTramite

    procedure atenderse(in t Tramite, out r){
        tramite = t
        hayTramite = true
        signal(empleado)
        wait(hayResultado)
        r = resultado
        signal(fin)
    }
    procedure atender(out t Tramite){
        if(!hayTramite){
            wait(empleado)
        }
        t = tramite
    }

    procedure darResultado(in r Tramite){
        resultado = r
        signal(hayResultado)
        wait(fin)
        hayTramite=false
    }
}
```