# 1 
En una mesa de exámenes hay 3 profesores que les deben tomar un examen oral a 30 alumnos de acuerdo al orden de llegada. Cada examen es tomado por un único profesor. Cuando un alumno llega, espera a que alguno de los profesores (cualquiera) lo llame y se dirige al escritorio correspondiente a ese profesor, donde le tomará el examen; al terminar el profesor le da la nota y el alumno se retira. Cuando un profesor está libre llama al siguiente alumno. Nota: todos los procesos deben terminar su ejecución.
```cpp
process Alumno[id:1..30]{
    int profesor;
    Fila.encolarse(id, profesor)
    Escritorio[profesor].llegar()
    //Rendir
    Escritorio[profesor].conseguirNota()
}

process Profesor[id:1..3]{
    bool deboTerminar;
    Fila.siguiente(deboTerminar, id)
    while(!deboTerminar){
        Escritorio[id].esperar()
        int nota;
        // Corregir
        Escritorio[id].darNota(nota)
        Fila.siguiente(deboTerminar, id)
    }
}

Monitor Escritorio[1..3]{
    cond rendi, rindio, profesor, hayNota, meFui
    int nota;
    llego = false
    procedure esperar(){
        if(!llego){
            wait(profesor)
        }
        signal(rendi)
        wait(rindio)
    }

    procedure llegar(){
        llego=true
        signal(profesor)
        wait(rendi)
    }

    procedure conseguirNota(out int n){
        signal(rindio)
        wait(hayNota)
        n = nota
        signal(meFui)
    }

    procedureDarNota(in int n){
        nota = n
        signal(hayNota)
        wait(meFui)
        llego = true
    }
}

Monitor Fila {
    Cola llegadas<int>, cond profesor, alumno
    int profesores[30], cant = 0
    procedure encolarse(in int id, out int profesor){
        llegadas.push(id)
        signal(profesor)
        wait(alumno)
        profesor = profesores[id]
    }

    procedure siguiente(out bool deboTerminar, in int id){
        if(cant < 30){
            cant++
            if(llegadas.isEmpty()){
                wait(profesor)
            }
            int sig = llegadas.pop()
            profesores[sig] = id
            signal(alumno)
            deboTerminar=false
        } else {
            deboTerminar = true
        }
    }
}
```

# 2
Monitores En una playa hay 5 equipos de 4 personas cada uno (en total son 20 personas donde cada
una conoce previamente a que equipo pertenece). Cuando las personas van llegando
esperan con los de su equipo hasta que el mismo esté completo (hayan llegado los 4
integrantes), a partir de ese momento el equipo comienza a jugar. El juego consiste en que
cada integrante del grupo junta 15 monedas de a una en una playa (las monedas pueden ser
de 1, 2 o 5 pesos) y se suman los montos de las 60 monedas conseguidas en el grupo. Al
finalizar cada persona debe conocer el monto total juntado por su grupo. Nota: maximizar
la concurrencia. Suponga que para simular la búsqueda de una moneda por parte de una
persona existe una función Moneda() que retorna el valor de la moneda encontrada
```cpp
process Persona[id:1..20]{
    int equipo = ..., valor, total
    Equipo[equipo].llegada()
    for i:=1..15 {
        valor += Moneda()
    }
    Equipo[equipo].consultar(valor, total)
    print("Total: "+ total)
}

Monitor Equipo[id:1..5]{
    int llegadas = 0, totalEquipo = 0
    cond barrera
    procedure llegada(){
        llegadas++
        if(llegadas == 4){
            llegadas = 0
            signal_all(barrera)
        } else {
            wait(barrera)
        }
    }

    procedure consultar(in int valor, out int total){
        totalEquipo += valor
        llegadas++
        if(llegadas == 4){
            signal_all(barrera)
        } else {
            wait(barrera)
        }
        total = totalEquipo
    }
}
```

# 3
Resolver con SEMÁFOROS el siguiente problema. Simular un examen técnico para concursos Nodocentes en la Facultad, en el mismo participan 100 personas distribuidas en 4 concursos (25 personas en cada concurso) con un coordinador en cada una de ellos. Cada persona ya conoce en que concurso participa. El coordinador de cada concurso espera hasta que lleguen las 25 personas correspondientes al mismo, les entrega el examen a resolver (el mismo para todos los de ese concurso), y luego corrige los exámenes de esas 25 personas de acuerdo al orden en que van entregando. Cada persona al llegar debe esperar a que su coordinador (el que corresponde a su concurso) le dé el examen, lo resuelve, lo entrega para que su coordinador lo evalúe y espera hasta que le deje la nota para luego retirarse. Nota: maximizar la concurrencia; sólo usar los procesos que representes a las personas y a los coordinadores; todos los procesos deben terminar.
```cpp

```