1)
    /*
    1. Existen N personas que deben ser chequeadas por un detector de metales antes de poder
    ingresar al avión.
        a. Analice el problema y defina qué procesos, recursos y semáforos/sincronizaciones
        serán necesarios/convenientes para resolverlo.
        b. Implemente una solución que modele el acceso de las personas a un detector (es decir,
        si el detector está libre la persona lo puede utilizar; en caso contrario, debe esperar).
        c. Modifique su solución para el caso que haya tres detectores.
        d. Modifique la solución anterior para el caso en que cada persona pueda pasar más de
        una vez, siendo aleatoria esa cantidad de veces. 
    */
    a & b)
    sem metalex = 1 
    process Persona[i=1..N-1]{
        P(metalex)
        //Pasar
        V(metalex)
    }
    c) 
    sem metalex = 3 
    process Persona[i=1..N-1]{
        P(metalex)
        //Pasar
        V(metalex)
    }
    d) 
    sem metalex = 3 
    process Persona[i=1..N-1]{
        While(true){
            P(metalex)
            //Pasar
            V(metalex)
        }
    }
2)
    /*
    2. Un sistema de control cuenta con 4 procesos que realizan chequeos en forma
    colaborativa. Para ello, reciben el historial de fallos del día anterior (por simplicidad, de
    tamaño N). De cada fallo, se conoce su número de identificación (ID) y su nivel de
    gravedad (0=bajo, 1=intermedio, 2=alto, 3=crítico). Resuelva considerando las siguientes
    situaciones:
    a) Se debe imprimir en pantalla los ID de todos los errores críticos (no importa el
    orden).
    b) Se debe calcular la cantidad de fallos por nivel de gravedad, debiendo quedar los
    resultados en un vector global.
    c) Ídem b) pero cada proceso debe ocuparse de contar los fallos de un nivel de
    gravedad determinado.
    */
   a & b)
   int fallos[4] = (0, 4);
   sem accesoArray = 1;
   process chequeador[i=0..3]{ 
    for(int x = i; x < N-1; x+4){
        nro = historial[x].nro;
        if nro == 3{ print(historial[x].id) }
        P(accesoArray)
        fallos[nro]++
        V(accesoArray)
    }
   }
   // La solución anterior creo q funciona bien, pero siguiendo la idea de la teoria quiero presentar otra más linda:
   sem accesoHistorial = 1; sem accesoArray = 1;
   int pos = 0; 
   int fallos[4] = (0,4)
   process chequeador[i=0..3]{
    P(accesoHistorial)
    while(pos < n){
        historia = historial[pos];
        pos++;
        V(accesoHistorial)

        if historia.nro == 3 { print(historia.id) }
        P(accesoArray)
        fallos[historia.nro]++
        V(accesoArray)
    }
    V(accesoHistorial)
   }
   c) 
   sem accesoHistorial = 1; sem accesoArray = 1;
   int pos = 0; 
   int fallos[4] = (0,4)
   process chequeador[i=0..3]{
    P(accesoHistorial)
    while(pos < n){
        historia = historial[pos];
        pos++;
        V(accesoHistorial)

        if historia.nro == 3 { print(historia.id) }
        if historia.nro == i {
            P(accesoArray)
            fallos[historia.nro]++
            V(accesoArray)
        }
    }
    V(accesoHistorial)
   }
3)
   /*
   3. Un sistema operativo mantiene 5 instancias de un recurso almacenadas en una cola.
    Además, existen P procesos que necesitan usar una instancia del recurso. Para eso, deben
    sacar la instancia de la cola antes de usarla. Una vez usada, la instancia debe ser encolada
    nuevamente para su reúso.
   */
   sem cantCola = 5; sem accesoColacha = 1;
   process accesor[i=0..P-1]{
        P(accesoColacha)
        P(cantCola)
        recurso = cola.pop()
        V(accesoColacha)

        //Usar

        P(accesoColacha)
        cola.push()
        V(accesoColacha)
        V(cantCola)
   }
4)
   /*
   4. Suponga que existe una BD que puede ser accedida por 6 usuarios como máximo al
    mismo tiempo. Además, los usuarios se clasifican como usuarios de prioridad alta y
    usuarios de prioridad baja. Por último, la BD tiene la siguiente restricción:
    • no puede haber más de 4 usuarios con prioridad alta al mismo tiempo usando la BD.
    • no puede haber más de 5 usuarios con prioridad baja al mismo tiempo usando la BD.
    Indique si la solución presentada es la más adecuada. Justifique la respuesta. 
   */
    Var
        total: sem := 6;
        alta: sem := 4;
        baja: sem := 5;
    Process Usuario-Alta [I:1..L]::
    { 
        P (total);
        P (alta);
        //usa la BD
        V(total);
        V(alta);
    }
    Process Usuario-Baja [I:1..K]::
    { 
        P (total);
        P (baja);
        //usa la BD
        V(total);
        V(baja);
    }
    La idea esta bien, sin embargo primero se debe hacer P(alta) y P(baja) para evitar que se bloquee el acceso a la BD globalmente,
    cuando solo se tiene que bloquear por por prio-alta y prio-baja, corte si hay 6 de prio alta no tendrían que ponerse en cola todos para entrar,
    sino solo 4, porque sino los de baja que hay no podrian acceder.
5)
    /*
    5. En una empresa de logística de paquetes existe una sala de contenedores donde se
    preparan las entregas. Cada contenedor puede almacenar un paquete y la sala cuenta con
    capacidad para N contenedores. Resuelva considerando las siguientes situaciones:
    a) La empresa cuenta con 2 empleados: un empleado Preparador que se ocupa de
    preparar los paquetes y dejarlos en los contenedores; un empelado Entregador
    que se ocupa de tomar los paquetes de los contenedores y realizar la entregas.
    Tanto el Preparador como el Entregador trabajan de a un paquete por vez.
    b) Modifique la solución a) para el caso en que haya P empleados Preparadores.
    c) Modifique la solución a) para el caso en que haya E empleados Entregadores.
    d) Modifique la solución a) para el caso en que haya P empleados Preparadores y E
    empleadores Entregadores.
    */
    a)
    typeT buf[n];
    sem vacio = 1; tiene = 0;
    process Preparador[]::{
        Recurso recurso;
        while(true){
            P(vacio)
            buf.push(recurso)
            V(tiene)
        }
    }

    process Entregador[]::{
        Recurso recurso;
        while(true){
            P(tiene)
            buf.pop(recurso)
            V(vacio)
        }
    }
    b)
    typeT buf[n];
    sem vacio = 1; tiene = 0;
    sem semPonedor = 1;
    process Preparador[i=0..P-1]::{
        Recurso recurso;
        while(true){
            P(vacio)
            P(semPonedor)
            buf.push(recurso)
            V(semPonedor)
            V(tiene)
        }
    }

    process Entregador[]::{
        Recurso recurso;
        while(true){
            P(tiene)
            buf.pop(recurso)
            V(vacio)
        }
    }
    c)
    typeT buf[n];
    sem vacio = 1; tiene = 0;
    sem semEntregador = 1;
    process Preparador[]::{
        Recurso recurso;
        while(true){
            P(vacio)
            buf.push(recurso)
            V(tiene)
        }
    }

    process Entregador[i=0..E-1]::{
        Recurso recurso;
        while(true){
            P(tiene)
            P(semEntregador)
            buf.pop(recurso)
            V(semEntregador)
            V(vacio)
        }
    }
    d)
    typeT buf[n];
    sem vacio = 1; tiene = 0;
    sem semPonedor = 1; semEntregador = 1;
    process Preparador[i=0..P-1]::{
        Recurso recurso;
        while(true){
            P(vacio)
            P(semPonedor)
            buf.push(recurso)
            V(semPonedor)
            V(tiene)
        }
    }

    process Entregador[i=0..E-1]::{
        Recurso recurso;
        while(true){
            P(tiene)
            P(semEntregador)
            buf.pop(recurso)
            V(semEntregador)
            V(vacio)
        }
    }
6)
    /*

    */