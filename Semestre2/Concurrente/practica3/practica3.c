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
    Monitor Corralon {
        cola colaLlegadas<int>;
        cond hayLista[N], hayComprobante[N], atendido[N], despertarAlgunTrabajador, fin[N];
        String listas[N], comprobantes[N];

        procedure Comprar(in int id; in String lista; out String comprobante){
            colaLlegadas.push(id);
            signal(despertarAlgunTrabajador);
            wait(atendido[id])
            
            listas[id] = lista;
            signal(hayLista[id]);

            wait(hayComprobante[id]);
            comprobante = comprobantes[id];

            signal(fin[id]);
        }

        procedure Atender(){
            if(colaLlegadas.isEmpty()){
                wait(despertarAlgunTrabajador);
            }

            int idAtendido = colaLlegadas.pop();
            signal(atendido[idAtendido]);
            
            wait(hayLista[idAtendido]);
            String lista = listas[idAtendido];
            comprobantes[idAtendido] = generarComprobante(lista);
            signal(hayComprobante[idAtendido]);
            wait(fin[idAtendido])
        }    
    }
    process Persona[id:=1..N]{
        String lista, comprobante;
        lista = ...;
        Corralon.Comprar(lista, comprobante)
    }

    process Empleado[id:=1..M]{
        for i:=1..N{
            Corralon.Atender()
        }
    }

    c)
    Monitor Corralon {
        cola colaLlegadas<int>;
        cond hayLista[N], hayComprobante[N], atendido[N], despertarAlgunTrabajador, fin[N];
        String listas[N], comprobantes[N];
        int cantAtendidos = 0;
        
        procedure Comprar(in int id; in String lista; out String comprobante){
            colaLlegadas.push(id);
            signal(despertarAlgunTrabajador);
            wait(atendido[id])
            
            listas[id] = lista;
            signal(hayLista[id]);

            wait(hayComprobante[id]);
            comprobante = comprobantes[id];

            signal(fin[id]);
        }

        procedure Atender(out bool tengoQueTerminar){
            // Si la cola esta vacia me siento a esperar q me despierten
            if(colaLlegadas.isEmpty()){
                wait(despertarAlgunTrabajador);
            }
            // Si me despiertan porque terminamos todos los atendidos me salgo a la mierda, 
            // sino hago el proceso normal de atender
            if(cantAtendidos == N){
                tengoQueTerminar = true;
            } else {
                int id = colaLlegadas.pop();
                signal(atendido[id]);
                
                wait(hayLista[id]);
                String lista = listas[id];
                comprobantes[id] = generarComprobante(lista);
                signal(hayComprobante[id]);
                wait(fin[id]);
                // Si al final de proceso de atender me doy cuenta de que no hay mas clientes, le aviso a todos los que estan
                // esperando q se despierten y chequeen la cantidad de restantes
                cantAtendidos++;
                if(cantAtendidos == N){
                    tengoQueTerminar = true;
                    signal_all(despertarAlgunTrabajador)
                }
            }
            
        }    
    }
    process Persona[id:=1..N]{
        String lista, comprobante;
        lista = ...;
        Corralon.Comprar(id, lista, comprobante);
    }

    process Empleado[id:=1..M]{
        bool tengoQueTerminar = false;
        while(!tengoQueTerminar){        
            Corralon.Atender(tengoQueTerminar)
        }
    }
