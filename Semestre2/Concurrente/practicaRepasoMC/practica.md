# Semaforos
## 1
a) En una estación de trenes, asisten P personas que deben realizar una carga de su tarjeta SUBE
en la terminal disponible. La terminal es utilizada en forma exclusiva por cada persona de acuerdo
con el orden de llegada. Implemente una solución utilizando únicamente procesos Persona. Nota:
la función UsarTerminal() le permite cargar la SUBE en la terminal disponible.
b) Resuelva el mismo problema anterior pero ahora considerando que hay T terminales disponibles.
Las personas realizan una única fila y la carga la realizan en la primera terminal que se libera.
Recuerde que sólo debe emplear procesos Persona. Nota: la función UsarTerminal(t) le permite
cargar la SUBE en la terminal t.

```cpp
bool ocupado = false
cola llegadas<int>
sem usarMaquina[P] = ([P], 0), mutexAcceso = 1;
process Persona[1..P]{
    P(mutexAcceso)
    if(ocupado){
        llegadas.push(id)
        V(mutexAcceso)
        P(usarMaquina[id])
    } else{
        ocupado = true
        V(mutexAcceso)
    }

    UsarTerminal()

    P(mutexAcceso);
    if(llegadas.isEmpty()){
        ocupado = false
    } else {
        int sig = llegadas.pop()
        V(usarMaquina[sig])
    }
    V(mutexAcceso)
}
```
```cpp
cola llegadas<int>
int terminales[T] = {...}; int terminalesLibres = T;
sem usarMaquina[P] = ([P], 0), mutexAcceso = 1;
int terminalAsignada[N];

process Persona[1..P]{
    P(mutexAcceso)
    if(terminalesLibres > 0){
        terminalAsignada[id] = terminales.pop()
        terminalesLibres--
        V(mutexAcceso)
    } else{
        llegadas.push(id)
        V(mutexAcceso)
        P(usarMaquina[id])
    }

    UsarTerminal(terminalAsignada[id])

    P(mutexAcceso);
    if(llegadas.isEmpty()){
        terminales.push(terminalAsignada[id]);
        terminalesLibres++;
    } else {
        int sig = llegadas.pop()
        terminalAsignada[sig] = terminalAsignada[id] 
        V(usarMaquina[sig])
    }
    V(mutexAcceso)
}
```
## 2
Implemente una solución para el siguiente problema. Un sistema debe validar un conjunto de 10000
transacciones que se encuentran disponibles en una estructura de datos. Para ello, el sistema dispone
de 7 workers, los cuales trabajan colaborativamente validando de a 1 transacción por vez cada uno.
Cada validación puede tomar un tiempo diferente y para realizarla los workers disponen de la
función Validar(t), la cual retorna como resultado un número entero entre 0 al 9. Al finalizar el
procesamiento, el último worker en terminar debe informar la cantidad de transacciones por cada
resultado de la función de validación. Nota: maximizar la concurrencia. 

```cpp
sem mutexTransacciones = 1; Transaccion transacciones[10_000] = {...};
sem mutexResultado[10]; int resultados[10];
sem mutexFin = 1; int cantTerminaron = 7;
process Worker[id: 1..7]{
    P(mutexTransacciones)
    while(!transacciones.isEmpty()){
        Transaccion siguiente = transacciones.pop()
        V(mutexTransacciones)
        
        int resultado = Validar(siguiente);
        P(mutexResultado[resultado])
        resultados[resultado]++;
        V(mutexResultado[resultado])

        P(mutexTransacciones)
    }
    V(mutexTransacciones)

    P(mutexFin)
    cantTerminaron--
    if(cantTerminaron == 0)
        for i:= 0..9
            print(resultados[i])
    V(mutexFin)
}
```
## 3
Implemente una solución para el siguiente problema. Se debe simular el uso de una máquina
expendedora de gaseosas con capacidad para 100 latas por parte de U usuarios. Además, existe un
repositor encargado de reponer las latas de la máquina. Los usuarios usan la máquina según el orden
de llegada. Cuando les toca usarla, sacan una lata y luego se retiran. En el caso de que la máquina se
quede sin latas, entonces le debe avisar al repositor para que cargue nuevamente la máquina en forma
completa. Luego de la recarga, saca una botella y se retira. Nota: maximizar la concurrencia; mientras
se reponen las latas se debe permitir que otros usuarios puedan agregarse a la fila.
```cpp

int latas = 100; sem repusieronLatas = 0; sem despertarRepositor = 0; sem despertarUsuario[U] = ([U], 0)
cola llegadas<int>; bool libre = true

process Usuario[id:1..U]{
    P(mutexAcceso);
    if(libre){
        libre = false
        V(mutexAcceso)
    } else{
        llegadas.push(id)
        V(mutexAcceso)
        P(despertarUsuario[id])
    }

    if(latas == 0){
        V(despertarRepositor)
        P(repusieronLatas)
    }
    latas--;

    P(mutexAcceso);
    if(llegadas.isEmpty()){
        libre = true;
    } else {
        int sig = llegadas.pop()
        V(despertarUsuario[sig])
    }
    V(mutexAcceso)
}

process Repositor{
    while(true){
        P(despertarRepositor)
        latas = 100
        V(esperarLatas)
    }
}
```

# Monitores
## 1
Resolver el siguiente problema. En una elección estudiantil, se utiliza una máquina para voto
electrónico. Existen N Personas que votan y una Autoridad de Mesa que les da acceso a la máquina
de acuerdo con el orden de llegada, aunque ancianos y embarazadas tienen prioridad sobre el resto.
La máquina de voto sólo puede ser usada por una persona a la vez. Nota: la función Votar() permite
usar la máquina.
```cpp
process Persona[1..N]{
    prioridad = determinarPrioridad()
    Mesa.llegar(prioridad)
    //votar
    Mesa.irse()
}

process Autoridad{
    Mesa.manejar()
}

Monitor Mesa{
    cond condPrioridad, autoridad, condNormales, finVoto
    int prioritarios = 0, normales = 0
    procedure llegar(in int prioridad){
        if(prioridad){
            prioritarios++
            signal(autoridad)
            wait(condPrioridad)
        } else {
            normales++
            signal(autoridad)
            wait(condNormales)
        }
    }

    procedure irse(){
        signal(finVoto)
    }

    procedure manejar(){
        for i:=1..N {
            if(prioritarios == 0 && normales == 0){
                wait(autoridad)
            }
            if(prioritarios > 0){
                prioritarios--;
                signal(condPrioridad)
            } else if (normales > 0) {
                normales--;
                signal(condNormales)
            }
            wait(finVoto)
        }
    }
}
```

## 2
Resolver el siguiente problema. En una empresa trabajan 20 vendedores ambulantes que forman 5
equipos de 4 personas cada uno (cada vendedor conoce previamente a qué equipo pertenece). Cada
equipo se encarga de vender un producto diferente. Las personas de un equipo se deben juntar antes
de comenzar a trabajar. Luego cada integrante del equipo trabaja independientemente del resto
vendiendo ejemplares del producto correspondiente. Al terminar cada integrante del grupo debe
conocer la cantidad de ejemplares vendidos por el grupo. Nota: maximizar la concurrencia
```cpp
process Vendedor[id:1..20]{
    int equipo = recordarEquipo()   
    Equipo[equipo].llegar() 
    int ventas = 0
    // Vender
    int ventasTotales
    Equipo[equipo].normalizar(ventas, ventasTotales)
}

Montir Equipo[1..5]{
    int llegadas = 0
    int ventasEquipo = 0;
    cond barrera;
    procedure llegar(){
        llegadas++
        if(llegadas == 4){
            llegadas = 0
            signal_all(barrera)
        } else {
            wait(barrera)
        }
    }

    procedure normalizar(in int ventas, out int ventasTotales){
        ventasEquipo += ventas
        llegadas++
        if(llegadas == 4){
            signal_all(barrera)
        } else {
            wait(barrera)
        }
        ventasTotales = ventasEquipo
    }
}
```
### 3
Resolver el siguiente problema. En una montaña hay 30 escaladores que en una parte de la subida
deben utilizar un único paso de a uno a la vez y de acuerdo con el orden de llegada al mismo. Nota:
sólo se pueden utilizar procesos que representen a los escaladores; cada escalador usa sólo una vez
el paso.
```cpp
process Escalador[id:1..30]{
    Paso.acceder()
    //Cruzar
    Paso.irse()
}
 
Monitor Paso{
    bool libre = true; cond pasar; int esperando = 0;
    procedure acceder(){
        if(libre){
            libre=false
        } else {
            esperando++
            wait(pasar)
        }
    }

    procedure irse(){
        if(esperando > 0){
            esperando--
            signal(pasar)
        } else {
            libre=true
        }
    }
}
```