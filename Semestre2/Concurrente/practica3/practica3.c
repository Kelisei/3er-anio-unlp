2)
    /*
    2. Existen N procesos que deben leer información de una base de datos, la cual es administrada
    por un motor que admite una cantidad limitada de consultas simultáneas.
    a) Analice el problema y defina qué procesos, recursos y monitores/sincronizaciones
    serán necesarios/convenientes para resolverlo.
    b) Implemente el acceso a la base por parte de los procesos, sabiendo que el motor de
    base de datos puede atender a lo sumo 5 consultas de lectura simultáneas.
    */

    Monitor Motor {
        cond libre; 
        int cant = 0;

        procedure acceder(){
            while(cant == 5) wait(libre);
            cant++;
        }
        procedure salir(){
            cant--;
            signal(libre)
        }
    }
    process Proceso[i=1..N]{
        Motor.acceder();
        // Hago algo    
        Motor.salir();
    }
3)
    /*
    3. Existen N personas que deben fotocopiar un documento. La fotocopiadora sólo puede ser
    usada por una persona a la vez. Analice el problema y defina qué procesos, recursos y
    monitores serán necesarios/convenientes, además de las posibles sincronizaciones requeridas
    para resolver el problema. Luego, resuelva considerando las siguientes situaciones:
        a) Implemente una solución suponiendo no importa el orden de uso. Existe una función
        Fotocopiar() que simula el uso de la fotocopiadora.
        b) Modifique la solución de (a) para el caso en que se deba respetar el orden de llegada.
        c) Modifique la solución de (b) para el caso en que se deba dar prioridad de acuerdo con la
        edad de cada persona (cuando la fotocopiadora está libre la debe usar la persona de mayor
        edad entre las que estén esperando para usarla).
        d) Modifique la solución de (a) para el caso en que se deba respetar estrictamente el orden
        dado por el identificador del proceso (la persona X no puede usar la fotocopiadora hasta
        que no haya terminado de usarla la persona X-1).
        e) Modifique la solución de (b) para el caso en que además haya un Empleado que le indica
        a cada persona cuando debe usar la fotocopiadora.
        f) Modificar la solución (e) para el caso en que sean 10 fotocopiadoras. El empleado le indica
        a la persona cuál fotocopiadora usar y cuándo hacerlo.
    */
    a)
    Monitor Fotocopiadora{
        int usando = 0; 
        cond libre;

        procedure acceder(){
            while(usando == 1) wait(libre);
            usando++;
        }

        procedure liberar(){
            usando--;
            signal(libre);
        }
    }


    process Persona[id:=1..N]{
        Fotocopiadora.acceder()
        Fotocopiar()
        Fotocopiadora.liberar()
    }

    b)
    Monitor Fotocopiadora{
        cond acceso; 
        bool libre = true;
        int cant = 0;

        procedure acceder(){
            if (libre){
                libre = false;
            } else {
                cant++;
                wait(acceso)
            }
        }

        procedure liberar(){
            if(cant > 0){
                cant--;
                signal(acceso)
            } else {
                libre = true;
            }
        }
    }


    process Persona[id:=1..N]{
        Fotocopiadora.acceder()
        Fotocopiar()
        Fotocopiadora.liberar()
    }

    c)
    Monitor Fotocopiadora{
        cond acceso[N]; 
        int sig = -1;
        cola colaLlegadas;

        procedure acceder(int id, int edad){
            if (sig == -1){
                sig = id;
            } else {
                colaLlegadas.pushOrdenado(id, edad);  //Asumo que pushea y se ordena segun la edad
                wait(acceso[id])
            }
        }

        procedure liberar(){
            if(colaLlegadas.isEmpty()){
                sig = -1;
            } else {
                sig, _ = colaLlegadas.pop();
                signal(acceso[sig]);
            }
        }
    }


    process Persona[id:=1..N]{
        int edad = randInt()
        Fotocopiadora.acceder(id, edad)
        Fotocopiar()
        Fotocopiadora.liberar()
    }

    d)
    Monitor Fotocopiadora{
        int sig = 0; 
        cond libre[N];

        procedure acceder(int id){
            if(id != sig){
                wait(libre[id]);
            }            
        }

        procedure liberar(){
            sig++;
            singal(libre[sig])
        }
    }


    process Persona[id:=1..N]{
        Fotocopiadora.acceder(id)
        Fotocopiar()
        Fotocopiadora.liberar()
    }

    e)
    Monitor Fotocopiadora{
        int cant = 0;
        cond worker, soyAdmin, fin;
        procedure registrarse(){
            cant++;
            signal(worker);
            wait(soyAdmin);
        }

        procedure activar(){
            if cant == 0 {
                wait(worker);
            }
            cant--;
            signal(soyAdmin);
            wait(fin);
        }

        procedure salir(){
            signal(fin);
        }
    }

    process Persona[id:=1..N]{
        Fotocopiadora.registrarse()
        Fotocopiar()
        Fotocopiadora.salir()
    }

    process Trabajador{
        for i:= 1..N 
            Fotocopiadora.activar()
    }
    f)
    Monitor Fotocopiadora{

        cola colaPendientes<int>;
        int cant = 0
        cond trabajador, impresora
        cola impresoras<int> = {1,2,3,4,5,6,7,8,9,10}
        int impresoraRecibida[N]
        cond sigo[N]

        procedure registrarse(out int: impresora, in int:id){
            colaPendientes.push(id)
            signal(trabajador)
            wait(sigo[id])
            impresora = impresoraRecibida[id]
        }

        procedure activar(){
            if(colaPendientes.isEmpty())
                wait(trabajador)
            if(impresoras.isEmpty())
                wait(impresora)
            int id, impresora = colaPendientes.pop(), impresoras.pop()
            impresoraRecibida[id] = impresora
            signal(sigo[id])
        }

        procedure salir(in int: impresora){
            impresoras.push(impresora)
            signal(impresora)
        }
    }

    process Persona[id:=1..N]{
        int impresoraDada
        Fotoicopiadora.registrarse(impresoraDada, id)
        Fotocopiar(impresoraDada)
        Fotocopiadora.salir(impresoraDada)
    }

    process Trabajador{
        for i:= 1..N 
            Fotocopiadora.activar()
    }
4)  /*
    4. Existen N vehículos que deben pasar por un puente de acuerdo con el orden de llegada.
    Considere que el puente no soporta más de 50000kg y que cada vehículo cuenta con su propio
    peso (ningún vehículo supera el peso soportado por el puente). 
    */
    Monitor Puente {
        bool pasando = false;
        cond pasarAWhileDePeso, chequeoDePeso;
        int pesoTotal = 0;
        procedure Entrar(in int peso){
            //Crucial para mantener el orden de llegada, si hay alguien ya cruzando lo tengo q dormir a todos los siguiente
            // para garantizar que luego hagan el chequeo de peso por orden de llegada
            if(pasando){
                esperando++;
                wait(pasarAWhileDePeso);
            } else {
                pasando = true;
            }
            while (pesoTotal + peso > 50000){
                wait(chequeoDePeso)
            }
            pesoTotal += peso;
            // Si hay alguien esperando le digo que continue para hacer el proceso de chequear si su peso es lo suficientemente
            // Bajo como para pasar
            if (esperando > 0){
                esperando--;
                signal(pasarAWhileDePeso);  //El primero en pasar le dice al sig q haga el chequeo de peso y el sig chequea su peso
                                // y le dice al sig de pasar
            } else {
                pasando = false;
            }
        }

        procedure Salir(in int peso){
            pesoTotal -= peso;
            signal(chequeoDePeso);
        }
    }
    process Camion {
        int peso = randInt();
        Puente.Entrar(peso);
        //Cruzar
        Puente.salir(peso);
    }
5)  /*
    5. En un corralón de materiales se deben atender a N clientes de acuerdo con el orden de llegada.
    Cuando un cliente es llamado para ser atendido, entrega una lista con los productos que
    comprará, y espera a que alguno de los empleados le entregue el comprobante de la compra
    realizada.
    a) Resuelva considerando que el corralón tiene un único empleado.
    b) Resuelva considerando que el corralón tiene E empleados (E > 1). Los empleados no
    deben terminar su ejecución.
    c) Modifique la solución (b) considerando que los empleados deben terminar su ejecución
    cuando se hayan atendido todos los clientes.
    */
    a)
    Monitor Corralon {
        cond Empleado, atendido, hayLista, hayComprobante;
        int esperando;
        String comprobanteG; String listaG;
        procedure Comprar(in String lista; out String comprobante){
            esperando++;
            signal(Empleado);
            wait(atendido);
            listaG = lista;
            signal(HayLista)
            wait(hayComprobante)
            comprobante = comprobanteG;
            signal(fin);
        }

        procedure Atender(){
            if(esperando = 0){
                wait(Empleado)
            }
            esperando--;
            signal(atendido);
            wait(HayLista)
            comprobanteG = generarComprobante(listaG);
            signal(hayComprobante)
            wait(fin);
        }
    }
    process Persona[id:=1..N]{
        String lista, comprobante;
        lista = ...;
        Corralon.Comprar(lista, comprobante)
    }

    process Empleado{
        for i:=1..N{
            Corralon.Atender()
        }
    }

    b)
    Monitor AtencionAlPublico {
        cola empleadosLibres;
        cond esperaCliente;
        int esperando = 0;
        int cantEmpleadosLibres; // Esto se usa para aplicar el passing the condition
        // Representa los empleados realmente libre(no atendiendo nadie y q no hayan mandado un signal)
        Procedure PedirEmpleado(idEmpleado out int){
            if(cantEmpleadosLibres == 0){
                esperando++;
                wait(esperarCliente);
            } else {
                cantEmpleadosLibres--;
            }
            pop(empleadosLibres, idEmpleado);
        }

        Procedure Proximo(idEmpleado in int){
            push(empleadosLibres, idEmpleado);
            if(esperando >  0){
                esperando--;
                signal(esperaCliente);
            } else {
                cantEmpleadosLibres++;
            }
        }
    }

    Monitor Empleado[id:=1..E] {
        procedure Atencion(lista in String, comprobante out String){
            listaE = lista;
            signal(despertarEmpleado);
            wait(despertarCliente);
            comprobante = comprobanteE;
            signal(despertarEmpleado);
        }

        procedure esperarDatos(lista out String){
            wait(despertarEmpleado);
            lista = listaE;
        }

        procedure enviarResultado(r in String){
            resultados = r;
            signal(despertarCliente);
            wait(despertarEmpleado);
        }
    }
    process Cliente[id:=1..N]{
        int idEmpleado;
        String lista, comprobante;
        AtencionAlPublico.PedirEmpleado(idEmpleado);
        Empleado[idEmpleado].atencion(lista, comprobante);
    }

    process Empleado[id:=1..E]{
        String comprobante;
        while(true){
            AtencionAlPublico.Proximo(id);
            Empleado[id].esperarDatos(datos);
            comprobante = generarComprobante(datos);
            Empleado[id].enviarResultado(comprobante);
        }
    }
    c)
    Monitor AtencionAlPublico {
        cola empleadosLibres;
        cond esperaCliente;
        int esperando = 0;
        int atendidos = 0;
        int cantEmpleadosLibres; // Esto se usa para aplicar el passing the condition
        // Representa los empleados realmente libre(no atendiendo nadie y q no hayan mandado un signal)
        Procedure PedirEmpleado(idEmpleado out int){
            if(cantEmpleadosLibres == 0){
                esperando++;
                wait(esperarCliente);
            } else {
                cantEmpleadosLibres--;
            }
            pop(empleadosLibres, idEmpleado);
        }

        Procedure Proximo(idEmpleado in int; termino out bool){
            if(atendidos != N){
                push(empleadosLibres, idEmpleado);
                if(esperando >  0){
                    esperando--;
                    signal(esperaCliente);
                } else {
                    cantEmpleadosLibres++;
                }
                termino = false;
            } else {
                termino = true;
            }
        }
    }

    Monitor Empleado[id:=1..E] {
        cond despertarCliente, despertarEmpleado;
        String listaE, ComprobanteE;
        bool listo = false;
        procedure Atencion(lista in String, comprobante out String){
            listaE = lista;
            listo = true;
            signal(despertarEmpleado);
            wait(despertarCliente);
            comprobante = comprobanteE;
            signal(despertarEmpleado);
        }

        procedure esperarDatos(lista out String){
            if (! listo){
                wait(despertarEmpleado);
            }
            lista = listaE;
        }

        procedure enviarResultado(res in String){
            ComprobanteE = res;
            signal(despertarCliente);
            wait(despertarEmpleado);
            Contador.AumentarTerminados();
        }
    }

    Monitor Contador{
        int cantTerminados;

        procedure AumentarTerminados(){
            if(cantTerminados < N){
                cantTerminados++;
            }
        }

        procedure TerminaronTodos(terminaron out bool){
            terminaron = cantTerminados == N;
        }
    }
    process Cliente[id:=1..N]{
        int idEmpleado;
        String lista, comprobante;
        AtencionAlPublico.PedirEmpleado(idEmpleado);
        Empleado[idEmpleado].atencion(lista, comprobante);
    }

    process Empleado[id:=1..E]{
        String comprobante;
        terminaron = false;
        while(!terminaron){
            AtencionAlPublico.Proximo(id, terminaron);
            if(!terminaron){
                Empleado[id].esperarDatos(datos);
                comprobante = generarComprobante(datos);
                Empleado[id].enviarResultado(comprobante);
            }
            
        }
    }
6)
    /*
    6. Existe una comisión de 50 alumnos que deben realizar tareas de a pares, las cuales son
    corregidas por un JTP. Cuando los alumnos llegan, forman una fila. Una vez que están todos
    en fila, el JTP les asigna un número de grupo a cada uno. Para ello, suponga que existe una
    función AsignarNroGrupo() que retorna un número “aleatorio” del 1 al 25. Cuando un alumno 
    ha recibido su número de grupo, comienza a realizar su tarea. Al terminarla, el alumno le avisa
    al JTP y espera por su nota. Cuando los dos alumnos del grupo completaron la tarea, el JTP
    les asigna un puntaje (el primer grupo en terminar tendrá como nota 25, el segundo 24, y así
    sucesivamente hasta el último que tendrá nota 1). Nota: el JTP no guarda el número de grupo
    que le asigna a cada alumno.
    */
    Monitor Tarea {
        int cantEnFila = 0;

        cond tareaFueAsignada[50];
        int tareaAsignada[50];
        
        cola tareaTerminadas<int>;
        cond tareaTerminada;

        int terminaron[25] = ([25], 0);
        int nota = 25;
        int notasFinales[25];
        cond graduados[25];

        process ObtenerTarea(int int id; out int tarea){
            cantEnFila++;
            signal(nuevoEnFila);
            wait(tareaFueAsignada[id]);
            tarea = tareaAsignada[id];
        }


        procedure AsignarTareas(){
            while(cantEnFila != 50){
                wait(nuevoEnFila);
            }

            for i:=1..50 {
                tareaAsignada[i] = AsignarNroGrupo()
                signal(tareaFueAsignada[i])
            }
        }

        procedure RankearNotas(){
            while(nota > 0){
                if(tareaTerminadas.isEmpty()){
                    wait(tareaTerminada);
                }
                int tarea = tareaTerminadas.pop();
                terminaron[tarea]++;
                if(terminaron[tarea] == 2){
                    notasFinales[tarea] = nota;
                    nota--;
                    signal_all(graduado[tarea]);
                }
            }
        }

        procedure EsperarNota(in int tarea; out int notaFinal){
            tareaTerminada.push(tarea);
            signal(tareaTerminada);
            wait(graduado[tarea]);
            notaFinal = notasFinales[tarea];
        }
    }

    process Alumno[id:=1..50]{
        int tarea; int notaFinal;
        Tarea.ObtenerTarea(id, tarea);
        // hacer tarea
        Tarea.EsperarNota(tarea, notaFinal);
    }

    process Profesor{
        Tarea.AsignarTareas();
        Tarea.RankearNotas();
    }
7)
    /*
    7. Se debe simular una maratón con C corredores donde en la llegada hay UNA máquina
    expendedoras de agua con capacidad para 20 botellas. Además, existe un repositor encargado
    de reponer las botellas de la máquina. Cuando los C corredores han llegado al inicio comienza
    la carrera. Cuando un corredor termina la carrera se dirigen a la máquina expendedora, espera
    su turno (respetando el orden de llegada), saca una botella y se retira. Si encuentra la máquina
    sin botellas, le avisa al repositor para que cargue nuevamente la máquina con 20 botellas;
    espera a que se haga la recarga; saca una botella y se retira. Nota: mientras se reponen las
    botellas se debe permitir que otros corredores se encolen. 
    */

    Monitor Carrera {
        int esperando;
        cond salir;
        procedure Iniciar(){
            esperando++;
            if esperando == C {
                signal_all(salir);
            } else {
                wait(salir);
            }
        }

        cond despertarRepositor;
        int cantBotellas = 20;

        cola corredoresEsperando<int>;
        int sigCorredor;
        cond siguiente[C];

        procedure Reponer(){
            if(esperandoBotella == 0){
                wait(despertarRepositor)
            }
            cantBotellas = 20;
            signal(hayBotellas);
        }

        procedure TomarBotellita(in int id){
            if(sigCorredor == -1){
                sigCorredor = id;
            } else {
                corredoresEsperando.push(id);
                wait(siguiente[id])
            }

            if(cantBotellas == 0){
                signal(despertarRepositor);
                wait(hayBotellas);
            }

            cantBotellas--;

            if(corredoresEsperando.isEmpty()) {
                sigCorredor == -1;
            } else {
                signal(siguiente[corredoresEsperando.pop()]);
            }
        }
    }

    process Repositor{
        while(true){
            Carrera.Reponer();
        }
    }
    process Corredor[id:=1..C]{
        Carrera.Iniciar();
        //Corre carrera
        Carrera.TomarBotellita();
    }
8)
    /*
    8. En un entrenamiento de fútbol hay 20 jugadores que forman 4 equipos (cada jugador conoce
    el equipo al cual pertenece llamando a la función DarEquipo()). Cuando un equipo está listo
    (han llegado los 5 jugadores que lo componen), debe enfrentarse a otro equipo que también
    esté listo (los dos primeros equipos en juntarse juegan en la cancha 1, y los otros dos equipos
    juegan en la cancha 2). Una vez que el equipo conoce la cancha en la que juega, sus jugadores
    se dirigen a ella. Cuando los 10 jugadores del partido llegaron a la cancha comienza el partido,
    juegan durante 50 minutos, y al terminar todos los jugadores del partido se retiran (no es
    necesario que se esperen para salir).
    */
    /*
        pasos:
        1. Conseguir equipo, se hace desde el proceso.
        2. Deben ir a cancha y reunirse los jugadores de mismo equipo. Ordenarlos por orden de reunión.
        3. Ir cancha y esperar a los otros jugadores (de ambos equipos).
        4. Delay 50.
        5. Se van.
    */
    process Jugador[id:=1..20]{
        int equipo, cancha;
        equipo = DarEquipo(equipo);
        Equipo[equipo].llegar(cancha);
        Cancha[cancha].llegar();
    }

    process Cancha[id:=1..4]{
        Cancha[id].iniciar();
        delay(50);
        Cancha[id].terminar();
    }

    Monitor Equipo[id:=1..4]{
        int jugadoresEsperando = 0, numCancha;
        cond esperarAlResto;

        procedure llegar(cancha out int){
            jugadoresEsperando++;
            if(cant != 5){
                wait(esperarAlResto);
            } else {
                Admin.DarCancha(numCancha);
                signal_all(esperarAlResto);
            }
            cancha = numCancha;
        }
    }

    Monitor AdminCanchas{
        int llegadasDeEquipo = 0;
        procedure DarCancha(cancha out int){
            llegadasDeEquipo++;
            if(llegadasDeEquipo > 2){ 
                cancha = 2;
            } else {
                cancha = 1;
            }
        }
    }

    Monitor Cancha[id:=1..2]{
        int jugadoresEsperando = 0;
        cond esperarAlResto, iniciar;

        procedure llegar(){
            jugadoresEsperando++;
            if(jugadoresEsperando == 10) signal(iniciar);
            wait(esperar)
        }

        procedure iniciar(){
            if(jugadoresEsperando < 10){
                wait(iniciar);
            }
        }

        procedure Terminar(){
            signal_all(esperar);
        }
    }
9)
    /*
    9. En un examen de la secundaria hay un preceptor y una profesora que deben tomar un examen
    escrito a 45 alumnos. El preceptor se encarga de darle el enunciado del examen a los alumnos
    cundo los 45 han llegado (es el mismo enunciado para todos). La profesora se encarga de ir
    corrigiendo los exámenes de acuerdo con el orden en que los alumnos van entregando. Cada
    alumno al llegar espera a que le den el enunciado, resuelve el examen, y al terminar lo deja
    para que la profesora lo corrija y le envíe la nota. Nota: maximizar la concurrencia; todos los
    procesos deben terminar su ejecución; suponga que la profesora tiene una función
    corregirExamen que recibe un examen y devuelve un entero con la nota. 
    */
    
    /*
        45 alumnos
        Preceptor da enunciado a los 45.
        Profesora corrije segun orden de llegada.
        Alumnos esperan recibir enunciado, resuelven y espera a recibir su nota.
        Todos deben terminar.
    */

    Process Preceptor{
        Enunciado.Dar();
    }

    process Alumno[id:=1..45]{
        text enunciado, resolucion; int nota;
        Enunciado.Recibir(enunciado);
        // Resolver
        Profesora.PedirCorrecion(resolucion, nota);
    }

    process Profesora{
        text res; int nota;
        for i:=1..45{
            Profesora.RecibirResolucion(res);
            //Corregir
            Profesora.DevolverCorrecion(nota)
        }
    }

    Monitor Enunciado {
        text enunciado[45];
        int esperando = 0;
        cond despertarPreceptor;
        procedure Recibir(e out text){
            esperando++;
            if(espeando == 45){
                signal(despertarPreceptor)
            }
            wait(despertarAlumno);
            e = enunciado;
        }

        procedure Dar(){
            wait(despertarPreceptor);
            for i:= 1..45{
                enunciado[i] = nuevoEnunciado();
            }
            signal_all(despertarAlumno);
            
        }
    }

    Monitor Profesora(){
        Cola resoluciones; Cola notas; int esperando = 0;
        procedure RecibirResolucion(res out text){
            if(esperando == 0){
                wait(despetarProfesora);
            }
            res = resoluciones.pop();
        }

        procdure DevolverCorrecion(nota in int){
            notas.push(nota);
            signal(despertarAlumno);
        }

        procedure PedirCorrecion(res in text, nota out int){
            resoluciones.push(res);
            esperando++;
            signal(despetarProfesora);
            wait(despertarAlumno);
            nota = notas.pop();
            esperando--;
        }
    }
10)
    /*
    10.En un parque hay un juego para ser usada por N personas de a una a la vez y de acuerdo al
    orden en que llegan para solicitar su uso. Además, hay un empleado encargado de desinfectar el
    juego durante 10 minutos antes de que una persona lo use. Cada persona al llegar espera hasta
    que el empleado le avisa que puede usar el juego, lo usa por un tiempo y luego lo devuelve.
    Nota: suponga que la persona tiene una función Usar_juego que simula el uso del juego; y el
    empleado una función Desinfectar_Juego que simula su trabajo. Todos los procesos deben
    terminar su ejecución. 
    */
    /*
        1 juego, cola de personas para usarlo (passing the condition)
        1 empleado lo desinfecta antes de cada persona durante 10 minutos.
        Empleado le avisa a quien usar el juego.
    */
    process Persona[id:=1..N]{
        Juego.SolicitarUso();
        Usar_juego();
        Juego.MeFui();
    }

    process Empleado{
        for i:=1..N{
            Juego.EsperarLlegada();
            Desinfectar_Juego();
            Juego.InformarDesinfeccion();
        }
    }

    Monitor Juego{
        cond despertarEmpleado, fin;
        bool usando = false;
        int esperando++;
        procedure SolicitarUso(){
            signal(despertarEmpleado);
            esperando++;
            wait(despertarPersona);
        }

        procedure MeFui(){
            esperando--;
            signal(fin);
        }

        procedure EsperarLlegada(){
            if(esperando == 0){
                wait(despertarEmpleado);
            }
        }

        procedure InformarDesinfeccion(){
            signal(despertarPersona);
            wait(fin)
        }
    }




