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
    sem vacio = n; tiene = 0;
    int libre = 0; ocupado = 0;
    process Preparador[]::{
        Recurso recurso;
        while(true){
            P(vacio)
            buf[libre] = recurso
            V(tiene)
            libre = (libre + 1 ) mod n
        }
    }

    process Entregador[]::{
        Recurso recurso;
        while(true){
            P(tiene)
            recurso = buf[ocupado]
            V(vacio)
            ocupado = (ocupado + 1) mod n
        }
    }
    b)
    typeT buf[n];
    sem vacio = n; tiene = 0;
    sem semPonedor = 1;
    int libre = 0; ocupado = 0;
    process Preparador[i=0..P-1]::{
        Recurso recurso;
        while(true){
            P(vacio)
            P(semPonedor)
            buf[libre] = recurso;
            libre = (libre + 1) mod n
            V(semPonedor)
            V(tiene)
        }
    }

    process Entregador[]::{
        Recurso recurso;
        while(true){
            P(tiene)
            recurso = buf[ocupado]
            ocupado = (ocupado + 1) mod n
            V(vacio)
        }
    }
    c)
    typeT buf[n];
    sem vacio = n; tiene = 0;
    sem semEntregador = 1;
    int libre = 0; ocupado = 0;
    process Preparador[]::{
        Recurso recurso;
        while(true){
            P(vacio)
            buf[libre] = recurso;
            libre = (libre+1) mod n
            V(tiene)
        }
    }

    process Entregador[i=0..E-1]::{
        Recurso recurso;
        while(true){
            P(tiene)
            P(semEntregador)
            recurso = buf[ocupado]
            ocupado = (ocupado+1) mod n
            V(semEntregador)
            V(vacio)
        }
    }
    d)
    typeT buf[n];
    sem vacio = n; tiene = 0;
    sem semPonedor = 1; semEntregador = 1;
    int libre = 0; ocupado = 0;
    process Preparador[i=0..P-1]::{
        Recurso recurso;
        while(true){
            P(vacio)
            P(semPonedor)
            buf[libre] = recurso;
            libre = (libre + 1) mod n
            V(semPonedor)
            V(tiene)
        }
    }

    process Entregador[i=0..E-1]::{
        Recurso recurso;
        while(true){
            P(tiene)
            P(semEntregador)
            recurso = buf[ocupado]
            ocupado = (ocupado+1) mod n
            V(semEntregador)
            V(vacio)
        }
    }
6)
    /*
    6. Existen N personas que deben imprimir un trabajo cada una. Resolver cada ítem usando
    semáforos:
    a) Implemente una solución suponiendo que existe una única impresora compartida por
    todas las personas, y las mismas la deben usar de a una persona a la vez, sin importar
    el orden. Existe una función Imprimir(documento) llamada por la persona que simula el
    uso de la impresora. Sólo se deben usar los procesos que representan a las Personas.
    b) Modifique la solución de (a) para el caso en que se deba respetar el orden de llegada.
    c) Modifique la solución de (a) para el caso en que se deba respetar estrictamente el
    orden dado por el identificador del proceso (la persona X no puede usar la impresora
    hasta que no haya terminado de usarla la persona X-1).
    d) Modifique la solución de (b) para el caso en que además hay un proceso Coordinador
    que le indica a cada persona que es su turno de usar la impresora.
    e) Modificar la solución (d) para el caso en que sean 5 impresoras. El coordinador le
    indica a la persona cuando puede usar una impresora, y cual debe usar. 
    */

    a)
    sem semImpresora = 1;
    process Persona[i= 0..N-1]{
        Documento doc;
        P(semImpresora)
        Imprimir(doc)
        V(semImpresora)
    }
    b)
    sem semCola = 1;
    sem calentaQueSalis[N] = ([N], 0);
    colaLlegadas c;
    int sig = -1;
    process Persona[i= 0..N-1]{
        Documento doc;
        P(semCola)
        if(sig == -1){
            sig=i;
            V(semCola)
        } else {
            c.push(i);
            V(semCola)
            P(calentaQueSalis[i])
        }

        Imprimir(doc)

        P(semCola)
        if(c.isEmpty()){
            sig = -1;
        } else {
            V(calentaQueSalis[c.pop()])
        }
        V(semCola)
    }
    c)
    sem soyAdmin[N] = ([N], 0)
    int sig = 0;

    process Persona[i= 0..N-1]{
        Documento doc;
        if(sig != i){
            P(soyAdmin[i])
        }
        Imprimir(doc)
        if(i < N){
            V(soyAdmin[i+1])
        }
    }

    d)
    sem semCola = 1, cantListos = 0;
    sem calentaQueSalis[N] = ([N], 0);
    colaLlegadas c;

    sem imprimio = 0;
    process Persona[i= 0..N-1]{
        Documento doc;
        P(semCola)
        colaLlegadas.push(i)
        V(semCola)
        V(cantListos)

        P(calentaQueSalis[i])
        Imprimir(doc)
        V(imprimio)
    }

    process Coordinador{
        while (true) {
            P(cantListos)
            P(semCola)
            V(calentaQueSalis[cola.pop()])
            V(semCola)
            P(imprimio)
        }
    }
    e) 
    sem semColaLlegadas = 1;
    sem cantListos = 0; 
    sem cantImpresorasLibres = 5;
    sem calentaQueSalis[N] = ([N],0);
    int impresoraSelec[N];
    cola colaLlegadas;
    sem semImpresoraLibres = 1;
    colaImpresorasLibres = {1,2,3,4,5}
    process Persona[i=0..N-1]{
        //Me agrego por orden de llegada
        P(semColaLlegadas)
        colaLlegadas.push(i)
        V(semColaLlegadas)
        V(cantListos)
        //Espero que el coordinador me diga para salir y obtengo la impresora
        P(calentaQueSalis[i])
        impresora = impresoraSelec[i]
        Imprimir(documento, impresora)
        
        // Persona devuelve la impresora
        P(semImpresoraLibres)
        colaImpresorasLibres.push(impresora)
        V(semImpresoraLibres)
        V(cantImpresorasLibres)
    }

    process Coordinador{
        for i:= 1..N{
            P(cantListos)
            P(semColaLlegadas)
            int sig = colaLlegadas.pop()
            V(semColaLlegadas)
            P(cantImpresorasLibres)
            P(semImpresoraLibres)
            impresoraSelec[sig] = colaImpresorasLibres.pop()
            V(semImpresoraLibres)
            V(calentaQueSalis[sig])
        }
    }
7)
    sem mutexElegidos = 1; int elegidos=0;
    sem barreraAlumnos = 0; 
    sem mutexTareaFinalizadas = 1; cola tareasFinalizadas;
    sem semMaestro = 1;
    sem fuePuntuado[5] = ([5], 0);
    int puntajeTarea[5];
    process alumno[i=0..49]{
        int tarea = elegir();
        P(mutexElegidos);
        elegidos++;
        if(elegidos == 50){
            for j:= 0..50 { V(barreraAlumnos);}
        }
        V(mutexElegidos);
        P(barreraAlumnos);
        //HACER TAREA
        P(mutexTareaFinalizadas);
        tareasFinalizadas.push(tarea);
        V(mutexTareasFinalizadas)
        V(semMaestro);
        V(fuePuntuado[tarea]);
    }

    process maestro{
        finalizados[10] = ([10],0); nota =10;
        for i:= 1..50 { 
            P(semMaestro); 
            P(mutexTareaFinalizadas);
            tarea = tareasFinalizadas.pop();
            V(mutexTareaFinalizadas);
            finalizados[tarea]++;
            if(finalizados[tarea]==5){
                puntajeTarea[tarea]=nota;
                nota--;
                for j := 1..5 {
                    V(fuePuntuado[tarea]);
                }
            }
        }
    }
8)
    int llegaron = 0; sem mutexLlegaron = 1;
    int cant[E] = ([E], 0);
    sem barrera = 0;
    sem mutexT = 1;
    int terminaron = 0; sem mutexTerminaron =1;
    sem activarEmpresa = 1;
    sem recibio = 0;
    int ganador;

    process Trabajador[i=0..E-1]{
        P(mutexLlegaron);
        llegaron++;
        if (llegaron == E){
            for j:= 0..E {
                V(barrera);
            }
        }
        V(mutexLlegaron);
        P(barrera);
        P(mutexT);
        while(T > 0){
            T--;
            V(mutexT);
            //laburar
            cant[i]++;
            P(mutexT);
        }
        P(mutexTerminaron);
        terminaron++;
        if(terminaron==E){
            V(activarEmpresa);
            for j:= 0..E{
                V(barrera);
            }
        }
        V(mutexTerminaron);
        P(barrera);
        P(recibio);
        if(ganador == i){
            print("Hola mundo");
        }
    }

    process Empresa {
        P(activarEmpresa);
        ganador = max(cant);
        for i:=0..E{
            V(recibio);
        }
    }
9)
    sem maxMarcos = 30; colaMarcos marcos; sem accesoMarcos = 1;
    sem maxVidrios = 50; colaVidrios vidrios; sem accesoVidrios = 1;
    sem cantMarcos =0; sem cantVidrios = 0;
    sem accesoVentanas = 1; colaVentanas ventanas;
    int ocupadoVidrio = 0; ocupadoMadera = 0; libreVidrio = 0; libreMadera = 0;
    process Carpintero[0..3]{
        while(true){
            //Crear marco
            P(maxMarcos);
            P(accesoMarcos);
            marcos[ocupadoMadera] = marco;
            ocupadoMadera = (ocupadoMadera + 1) mod 30;
            V(cantMarcos);
            P(accesoMarcos);
        }
    }

    process Vidreiero{
        while(true){
            //Crear vidrio
            P(maxVidrios);
            P(accesoVidrios);
            vidrios[ocupadoVidrio] = vidrio;
            ocupadoVidrio = (ocupadoVidrio + 1) mod 50;
            V(cantVidrios);
            P(accesoVidrios);
        }
    }

    process Armador[0..1]{
        while(true){
            P(cantMarcos);
            P(accesoMarcos);
            marco = marcos[libreMadera];
            libreMadera = (libreMadera +1) mod 30;
            V(accesoMarcos);
            P(cantVidrios);
            P(accesoVidrios);
            vidrio = vidrios[libreVidrio];
            libreVidrio = (libreVidrio +1 ) mod 50;
            V(accesoVidrios);
            //Armar ventana
            P(accesoVentanas);
            ventanas.push(ventana);
            V(accesoVentanas);
        }
    }

10) 
    a) 
    sem maxTrigo = 5 sem maxMaiz = 5;
    sem maxTotal = 7;
    cola camiones; sem mutexC = 1;
    sem pasaTrigo[T] = ([T], 0); sem pasaMaiz[M] = ([M],0);
    sem esperando = 0;
    process CamionT[id=0..T-1]{
        P(mutexC);
        camiones.push(id, "trigo");
        V(mutexC);
        V(esperando);
        P(pasaTrigo[id]);
        //Descarga
        V(maxTrigo);
        V(maxTotal);
    }

    process CamionM[id=O..M-1]{
        ... Lo mismo q el otro pero con Maiz
    }

    process Coordinador{
        for i:= 1..M+T{
            P(esperando);
            
            P(mutexC);
            int id, text tipo = camiones.pop();
            V(mutexC);

            if(tipo == "trigo"){
                P(maxTrigo);
                P(maxTotal);
                V(pasaTrigo[id])
            } else if (tipo == "maiz"){
                P(maxMaiz);
                P(maxTotal);
                V(pasaMaiz[id])
            }
        }
    }
    b)
    sem maxTrigo = 5; sem maxMaiz = 5; sem max = 7;
    process CamionT[id=0..T-1]{
        P(maxTrigo);
        P(max);
        //Descargar
        V(maxTrigo);
        V(max);
    }

    process CamionM[id=0..T-1]{
        P(maxMaiz);
        P(max);
        //Descargar
        V(maxMaiz);
        V(max);
    }
11)
    sem accesoCola = 1; colaLlegadas llegadas;
    sem esperando = 0; 
    sem vacunado = ([49], 0);
    Persona[id=0..49]{
        P(accesoCola);
        llegadas.push(id);
        V(accesoCola);
        V(esperando);
        P(vacunado[id]);
    }
    
    Empleado{
        for j:=1..10{
            for i:=1..5 {
                P(esperando);
            }
            for i:= 1..5{
                P(accesoCola);
                int id = llegadas.pop();
                V(accesoCola);
                VacunarPersona(id);
                V(vacunado[id]);
            }
        }
    }
12)
    a)
    sem accesoCola = 1; colaLlegadas llegadas; 
    sem esperando = 0;
    sem termino[149] = ([149],0)
    sem accesoColaEnfermeras[3] = ([3], 1); colaEnfermeras enfermeras[3];
    sem mutexTerminaron = 1; int cantTerminaron = 0;
    sem esperandoEnf[3] = ([3],0)

    int vec[3] = ([3],0); sem mutexVec = 1;
    process Pasajero [id=0..149]{
        //Me agrego a la cola y le aviso a la recepcionista q estoy, luego espero a terminar
        P(accesoCola);
        llegadas.push(id);
        V(accesoCola);
        V(esperando);
        P(termino[id])
    }

    process Recepcionista{
        for i:=1..149{
            //Espero a que haya un pasajero, consigo el puesto y su id
            P(esperando);

            P(mutexVec);
            int idPuesto = min(vec);
            vec[idPuesto]++;
            V(mutexVec);

            P(accesoCola)
            int idPersona = llegadas.pop();
            V(accesoCola)

            //Lo agrego a la cola de su respectiva enfermera y le aviso que tiene uno.
            P(accesoColaEnfermeras[idPuesto])
            enfermeras[idPuesto].push(idPersona)
            V(accesoColaEnfermeras[idPuesto])
            V(esperandoEnf[idPuesto])
        } 
        //Sacamos a las enfermeras si q estan esperando
        for j = 0..2 {
            V(esperandoEnf[j]);
        }
    }

    process Enfermera[id:=0..2]{
        //Espero a que me activen
        P(esperandoEnf[id]);
        P(accesoColaEnfermeras[id]);
        //Si no hay nada en la cola entonces es porque terminaron todos los pasajeros de ser hisopados,
        //Solo habría algo en la cola si se me fue asignado.
        while(!enfermeras[id].isEmpty()){
            int idPasajero = enfermeras[id].pop();
            V(accesoColaEnfermeras[id])
            Hisopar(idPasajero)
            V(termino[idPasajero])

            P(mutexVec);
            vec[id]--;
            V(mutexVec);

            P(esperandoEnf[id]);
            P(accesoColaEnfermeras[id]);
        }
        V(accesoColaEnfermeras[id])
    }
    b)
    process Pasajero[id=0..149]{
        V(mutexVec);
        idEnfemera = min(vec);
        vec[idEnfemera]++;
        P(mutexVec);

        P(mutexEnfemeras[idEnfemera]);
        enfermeras[idEnfemera].push(id);
        V(mutexEnfemeras[idEnfemera]);

        V(esperando[idEnfemera])
        P(termino[id])
    }

    process Enfermera[id:=0..2]{
        while(true){
            P(esperando[id]);

            P(mutexEnfemeras[id])
            int sig = enfermeras[id].pop();
            V(mutexEnfemeras);

            Hisopar(sig);
            
            V(termino[sig])

            P(mutexVec);
            vec[id]--;
            V(mutexVec);
        }
    }