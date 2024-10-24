# 1.  
Se requiere modelar un puente de un único sentido que soporta hasta 5 unidades de peso. 
El peso de los vehículos depende del tipo: cada auto pesa 1 unidad, cada camioneta pesa 2 
unidades  y  cada  camión  3  unidades.  Suponga  que  hay  una  cantidad  innumerable  de 
vehículos  (A  autos,  B  camionetas  y  C  camiones).  Analice  el  problema  y  defina  qué  tareas, 
recursos y sincronizaciones serán necesarios/convenientes para resolverlo. 
## Solución usando Rendezvous en ADA

## a. Realice la solución suponiendo que todos los vehículos tienen la misma prioridad. 
```ada
Procedure Puente is
    Task ControlPuente is
        Entry EntrarCaminoneta (Peso: IN Integer);
        Entry EntrarAuto (Peso: IN Integer);
        Entry EntrarCamion (Peso: IN Integer);
        Entry Salir (Peso: IN Integer);
    End ControlPuente;

    Task type Vehiculo (Peso: Integer);
    Autos: array (1..A) of Vehiculo(1);
    Camionetas: array (1..B) of Vehiculo(2);
    Camiones: array (1..C) of Vehiculo(3);

    Task Body Vehiculo is
    Begin
        if Peso = 1 then
            ControlPuente.EntrarAuto(Peso);
        elsif Peso = 2 then
            ControlPuente.EntrarCaminoneta(Peso);
        else
            ControlPuente.EntrarCamion(Peso);
        end if;
        delay 5.0;
        ControlPuente.Salir(Peso);
    End Vehiculo;

    Task Body ControlPuente is
        PesoActual: Integer := 0;
    Begin
        loop
            select
                when PesoActual+1 <= 5 =>
                    accept EntrarAuto (Peso: IN Integer) do
                        PesoActual := PesoActual + Peso;
                    end EntrarAuto;
            or
                when PesoActual+2 <= 5 =>
                    accept EntrarCaminoneta (Peso: IN Integer) do
                        PesoActual := PesoActual + Peso;
                    end EntrarCaminoneta;
            or
                when PesoActual+3 <= 5 =>
                    accept EntrarCamion (Peso: IN Integer) do
                        PesoActual := PesoActual + Peso;
                    end EntrarCamion;
            or
                accept Salir (Peso: IN Integer) do
                    PesoActual := PesoActual - Peso;
                end Salir;
            end select;
        end loop;
    End ControlPuente;

Begin
    null;
End Puente;
```
## b. Modifique la solución para que tengan mayor prioridad los camiones que el resto de los vehículos.
```ada
Procedure Puente is
    Task ControlPuente is
        Entry EntrarCaminoneta (Peso: IN Integer);
        Entry EntrarAuto (Peso: IN Integer);
        Entry EntrarCamion (Peso: IN Integer);
        Entry Salir (Peso: IN Integer);
    End ControlPuente;

    Task type Vehiculo (Peso: Integer);
    Autos: array (1..A) of Vehiculo(1);
    Camionetas: array (1..B) of Vehiculo(2);
    Camiones: array (1..C) of Vehiculo(3);

    Task Body Vehiculo is
        Entre: Boolean := false
    Begin
        if Peso = 1 then
            ControlPuente.EntrarAuto(Peso);
        elsif Peso = 2 & then
            ControlPuente.EntrarCaminoneta(Peso);
        else
            ControlPuente.EntrarCamion(Peso);
        end if;
        delay 5.0;
        ControlPuente.Salir(Peso);
    End Vehiculo;

    Task Body ControlPuente is
        PesoActual: Integer := 0;
    Begin
        loop
            select
                when PesoActual+3 <= 5 =>
                    accept EntrarCamion (Peso: IN Integer) do
                        PesoActual := PesoActual + Peso;
                    end EntrarCamion;
            or
                when PesoActual+2 <= 5 and (EntrarCamion`Count = 0 or  PesoActual > 2)=>
                    accept EntrarCaminoneta (Peso: IN Integer) do
                        PesoActual := PesoActual + Peso;
                    end EntrarCaminoneta;
            or
                when PesoActual+1 <= 5  and (EntrarCamion`Count = 0 or  PesoActual > 2)=>
                    accept EntrarAuto (Peso: IN Integer) do
                        PesoActual := PesoActual + Peso;
                    end EntrarAuto;
            or
                accept Salir (Peso: IN Integer) do
                    PesoActual := PesoActual - Peso;
                end Salir;
            end select;
        end loop;
    End ControlPuente;

Begin
    null;
End Puente;
```

# 2.
Se quiere modelar el funcionamiento de un banco, al cual llegan clientes que deben realizar 
un pago y retirar un comprobante. Existe un único empleado en el banco, el cual atiende de 
acuerdo con el orden de llegada.  

## a) Implemente una solución donde los clientes llegan y se retiran sólo después de haber sido atendidos. 
```ada
Procedure Banco is
    Task AccesoBanco is 
        Entry Pago(Pedido: IN text; Comprobante: out text);
    End AccesoBanco;

    Task type Persona();
    Personas: array (1..N) of Persona();

    Task body Persona is; 
        Comprobante:texto;
    Begin
        select
            Empleado.Pedido("pagar", Comprobante)
        end select
    End Persona;

    Task body AccesoBanco is; Begin
        loop
            accept Pago(IN Pedido; OUT Comprobante) do
                Comprobante = GenerarComprobante(Pedido)
            end Pedido;
        end loop;
    End AccesoBanco;
Begin
    null;
End Banco;
```
## b) Implemente una solución donde los clientes se retiran si esperan más de 10 minutos para realizar el pago.
    ```ada
Procedure Banco is
    Task AccesoBanco is 
        Entry Pago(Pedido: IN text; Comprobante: out text);
    End AccesoBanco;

    Task type Persona();
    Personas: array (1..N) of Persona();

    Task body Persona is; 
        Comprobante:texto;
    Begin
        select
            Empleado.Pedido("pagar", Comprobante)
        or delay 600.0
        end select
    End Persona;

    Task body AccesoBanco is; Begin
        loop
            accept Pago(IN Pedido; OUT Comprobante) do
                Comprobante = GenerarComprobante(Pedido)
            end Pedido;
        end loop;
    End AccesoBanco;
Begin
    null;
End Banco;
```
## c) Implemente una solución donde los clientes se retiran si no son atendidos inmediatamente. 
```ada
Procedure Banco is
    Task AccesoBanco is 
        Entry Pago(Pedido: IN text; Comprobante: out text);
    End AccesoBanco;

    Task type Persona();
    Personas: array (1..N) of Persona();

    Task body Persona is; 
        Comprobante:texto;
    Begin
        select
            Empleado.Pedido("pagar", Comprobante);
        else
        end select;
    End Persona;

    Task body AccesoBanco is; Begin
        loop
            accept Pago(IN Pedido; OUT Comprobante) do
                Comprobante = GenerarComprobante(Pedido);
            end Pedido;
        end loop;
    End AccesoBanco;
Begin
    null;
End Banco;
```
## d)  Implemente  una  solución  donde  los  clientes  esperan  a  lo  sumo  10  minutos  para  ser atendidos. Si pasado ese lapso no fueron atendidos, entonces solicitan atención una vez más y se retiran si no son atendidos inmediatamente. 
```ada
Procedure Banco is
    Task AccesoBanco is 
        Entry Pago(Pedido: IN text; Comprobante: out text);
    End AccesoBanco;

    Task type Persona();
    Personas: array (1..N) of Persona();

    Task body Persona is; 
        Comprobante:texto;
    Begin
        select
            Empleado.Pedido("pagar", Comprobante)
        or
            delay 600.0;
            select
                Empleado.Pedido("pagar", Comprobante) 
            else
            end select;
        end select;
    End Persona;

    Task body AccesoBanco is; Begin
        loop
            accept Pago(IN Pedido; OUT Comprobante) do
                Comprobante = GenerarComprobante(Pedido)
            end Pedido;
        end loop;
    End AccesoBanco;
Begin
    null;
End Banco;
```
# 3.
Se  dispone  de  un  sistema  compuesto  por  1  central  y  2  procesos  periféricos,  que  se 
comunican continuamente. Se requiere modelar su funcionamiento considerando las 
siguientes condiciones: 
- La  central  siempre  comienza  su  ejecución  tomando  una  señal  del  proceso  1;  luego 
toma  aleatoriamente  señales  de  cualquiera  de  los  dos  indefinidamente.  Al  recibir  una 
señal de proceso 2, recibe señales del mismo proceso durante 3 minutos. 
- Los  procesos  periféricos  envían  señales  continuamente  a  la  central.  La  señal  del 
proceso  1  será  considerada  vieja  (se  deshecha)  si  en  2  minutos  no  fue  recibida.  Si  la 
señal del proceso 2 no puede ser recibida inmediatamente, entonces espera 1 minuto y 
vuelve a mandarla (no se deshecha). 
```ada
Procedure Sistema is
    Task Central is
        Entry s1(msj: IN text);
        Entry s2(msj: IN text);
        Entry finTimer();
    End Central;

    Task body Central is
        Boolean seguir;
    Begin
        accept s1();
        loop
            select
                accept s2(msj: IN text) do
                    null;
                end s2;
                Timer.inicioTimer();
                seguir := true;
                while seguir loop
                    select
                        accept finTimer() do
                            seguir := false;
                        end finTimer;
                    or when (finTimer`count = 0) =>
                        accept s2(msj: IN text) do
                            null;
                        end s2;
                    end select;
                end loop;
            or
                accept s1(msj: IN text) do
                    null;
                end s1;
            end select;
        end loop;
    End Central;

    Task Timer is
    Begin
        loop
            accept inicioTimer();
            delay 180.0;
            Central.finTimer();
        end loop;
    End Timer;

    Task Proceso1 is
    Begin
        loop
            select
                Central.s1(generarMensaje());
            or
                delay 120.0;
            end select;
        end loop;
    End Proceso1;

    Task Proceso2 is
        Boolean seguir;
        text msj;
    Begin
        loop
            seguir := true;
            msj := generarMensaje();
            while seguir loop
                select
                    Central.s2(msj);
                    seguir := false;
                else
                    delay 60.0;
                end select;
            end loop;
        end loop;
    End Proceso2;
Begin
    null;
End Sistema;
```
# 4.
En  una  clínica  existe  un  médico  de  guardia  que  recibe  continuamente  peticiones  de 
atención de las E  enfermeras que trabajan en su piso y de las  P  personas que llegan a la 
clínica ser atendidos.  
Cuando una persona necesita que la atiendan espera a lo sumo 5 minutos a que el médico lo 
haga, si pasado ese tiempo no lo hace, espera 10 minutos y vuelve a requerir la atención del 
médico. Si no es atendida tres veces, se enoja y se retira de la clínica. 
Cuando una enfermera requiere la atención del médico, si este no lo atiende inmediatamente 
le  hace  una  nota  y  se  la  deja  en  el  consultorio  para  que  esta  resuelva  su  pedido  en  el 
momento  que  pueda  (el  pedido  puede  ser  que  el  médico  le  firme  algún  papel).  Cuando  la 
petición  ha  sido  recibida  por  el  médico  o  la  nota  ha  sido  dejada  en  el  escritorio,  continúa 
trabajando y haciendo más peticiones. 
```ada
PROCEDURE clinica is
    TASK medico IS
        ENTRY atencionPaciente();
        ENTRY atencionEnfemero(pedido: IN text);
    END medico;
    TASK BODY medico is
    BEGIN
        LOOP
            SELECT  
                ACCEPT atencionPaciente() DO
                    -- atender paciente
                END atencionPaciente;
            OR WHEN (atencionEnfemero`COUNT = 0) =>
                ACCEPT atencionEnfermero(pedido: IN text) do
                    procesar(pedido);
                END atencionEnfermero;
            ELSE 
                escritorio.buscarTarea(pedido) DO 
                IF deboFirmar(pedido) THEN
                    firmarPedido(pedido);
                END IF;
            END SELECT
        END LOOP;
    END BODY medico;

    TASK escritorio IS
        ENTRY dejarTarea(pedido: IN text);
        ENTRY buscarTarea(pedido: OUT text);
    END escritorio;
    TASK BODY escritorio IS
        cola: colaNotas;
    BEGIN
        LOOP
            SELECT 
                    ACCEPT buscarTarea(pedido: OUT text) do
                        IF size(cola) > 0 THEN
                            pedido = pop(cola);
                        ELSE 
                            pedido = '';
                        END IF;
                    END buscarTarea;
                OR
                    ACCEPT dejarTarea(pedido: IN text) do
                        push(cola, pedido);
                    END dejarTarea;
            END SELECT; 
        END LOOP;
    END BODY escritorio;
    TASK paciente is
        intentos: Integer := 0; 
        termine: Boolean := false;
    BEGIN
        WHILE intentos < 3 AND NOT termine LOOP 
            SELECT 
                medico.atencionPaciente();
                termine := true;
            OR DELAY 300.0;
                intentos := intentos + 1;
                IF intentos < 3 THEN
                    delay 600.0;
                END IF;
            END SELECT;
        END LOOP;
    END paciente;
    TASK enfermera is
        pedido: text;
    BEGIN
        LOOP
            pedido := trabajar();
            SELECT 
                medico.atencionEnfermero(pedido);
            ELSE 
                escritorio.dejarTarea(pedido);
        END LOOP;
    END enfermera;
BEGIN

END clinica;
```
# ?.
En  un  sistema  para  acreditar  carreras  universitarias,  hay  UN  Servidor  que  atiende  pedidos 
de  U  Usuarios  de  a  uno  a  la  vez  y  de  acuerdo  con  el  orden  en  que  se  hacen  los  pedidos. 
Cada  usuario  trabaja  en  el  documento  a  presentar,  y  luego  lo  envía  al  servidor;  espera  la 
respuesta de este que le indica si está todo bien o hay algún error. Mientras haya algún error, 
vuelve a trabajar con el documento y a enviarlo al servidor. Cuando el servidor le responde 
que está todo bien, el usuario se retira. Cuando un usuario envía un pedido espera a lo sumo 
2 minutos a que sea recibido por el servidor, pasado ese tiempo espera un minuto y vuelve a 
intentarlo (usando el mismo documento). 
``` ada
PROCEDURE server IS
    TASK servidor IS
        ENTRY peticionUsuario(documento: IN text; ok: OUT Boolean);
    END servidor;
    TASK BODY servidor IS
    BEGIN
        LOOP
            SELECT 
                ACCEPT peticionUsuario(documento: IN text, ok: OUT Boolean) DO
                    ok = procesarDocumento(documento);
                END peticionUsuario;
            END SELECT;
        END LOOP;
    END servidor;
    TASK TYPE usuario;
    usuarios: array(0..U) of usuario;
    TASK BODY usuario IS
        documento: text; ok:boolean:=false;
    BEGIN
        documento = trabajar();
        WHILE NOT ok LOOP
            SELECT 
                servidor.peticionUsuario(documento, ok);
                IF NOT ok THEN
                    documento = arreglar(documento);
                END IF;
            OR DELAY 120.0
                delay 60.0
            END SELECT;
        END LOOP;
    END BODY usuario;
END server;
```
# 5.  
En una playa hay 5 equipos de 4 personas cada uno (en total son 20 personas donde cada 
una  conoce  previamente  a  que  equipo  pertenece).  Cuando  las  personas  van  llegando 
esperan  con  los  de  su  equipo  hasta  que  el  mismo  esté  completo  (hayan  llegado  los  4 
integrantes), a partir de ese momento el equipo comienza a jugar. El juego consiste en que 
cada integrante del grupo junta 15 monedas de a una en una playa (las monedas pueden ser 
de  1,  2  o  5  pesos)  y  se  suman  los  montos  de  las  60  monedas  conseguidas  en  el  grupo.  Al 
finalizar  cada  persona  debe  conocer  el  grupo  que  más  dinero  junto.  Nota:  maximizar  la 
concurrencia.  Suponga  que  para  simular  la  búsqueda  de  una  moneda  por  parte  de  una 
persona existe una función Moneda() que retorna el valor de la moneda encontrada. 

BARRERA -> LOOP EN PERSONAS DONDE RECOLECTAN (1..15) -> TODOS DEJAN SUS MONEDAS EN EL EQUIPO -> EQUIPOS SE REUNEN EN OTRO Y COMPARAN TAMANIOS
-> PERSONAS LLEGAN Y AGARRAN EL EQUIPO MAS PIOLA
```ada
PROCEDURE playa IS
    TASK playa IS
        ENTRY compararMonedas(monedas, id: IN Integer);
        ENTRY conseguirMaximo(maxima: OUT Integer);
    END playa;
    TASK TYPE equipo IS
        ENTRY llegadaBarrera();
        ENTRY salidaBarrera();
        ENTRY dejarMonedas(monedas: IN Integer);
        ENTRY conseguirID(idE: IN Integer);
    END;
    TASK TYPE persona;

    equipos: array (1..5) of equipo;
    personas: array (1..20) of persona;

    TASK BODY persona IS
        monedas: Integer := 0;
        max: Integer;
        equipo: Integer := conseguirEquipo();
    BEGIN
        equipos[equipo].llegadaBarrera();
        equipos[equipo].salidaBarrera();
        FOR i IN 1..15 LOOP
            monedas := Moneda();
        END LOOP;
        equipos[equipo].dejarMonedas(monedas);
        playa.conseguirMaximo(max);
    END persona;

    TASK BODY equipo IS
        cantidadMonedas: Integer := 0;
        id: Integer;
    BEGIN
        ACCEPT conseguirID(idE: IN Integer) do
            id:=idE;
        END ACCEPT;
        FOR i IN 1..4 LOOP
            ACCEPT llegadaBarrera();
        END LOOP;
        FOR i IN 1..4 LOOP
            ACCEPT salidaBarrera();
        END LOOP;
        FOR i IN 1..4 LOOP
            ACCEPT dejarMonedas(monedas: IN Integer) do
                cantidadMonedas := cantidadMonedas + monedas;
            END dejarMonedas;
        END LOOP;   
        plata.compararMonedas(cantidadMonedas, id);
    END BODY equipo;

    TASK BODY playa IS 
        max, idMax: Integer
    BEGIN
        max:=-1;
        FOR i IN 1..5 LOOP
            ACCEPT compararMonedas(monedas, id: IN Integer) DO
                IF max < monedas THEN
                    max:=monedas;
                    idMax:=id;
                END IF;
            END compararMonedas;        
        END LOOP;
        FOR i IN 1..20 LOOP
            ACCEPT conseguirMaximo(maxima: OUT Integer) DO
                maxima:=max;
            END conseguirMaximo;
        END LOOP;
    END playa;

BEGIN
    FOR i IN 1..4 LOOP
        equipo[i].conseguirID(i);
    END LOOP;
END playa;
```

# 6.  
Se  debe  calcular  el  valor  promedio  de  un  vector  de  1  millón  de  números  enteros  que  se 
encuentra  distribuido  entre  10  procesos  Worker  (es  decir,  cada Worker  tiene  un  vector  de 
100  mil  números).  Para  ello,  existe  un  Coordinador  que  determina  el  momento  en  que  se 
debe  realizar  el  cálculo  de  este  promedio  y  que,  además,  se  queda  con  el  resultado.  Nota: 
maximizar la concurrencia; este cálculo se hace una sola vez.
```ada
PROCEDURE vectorizado IS
    TASK TYPE empleado;
    TASK coordinador IS
        ENTRY dejarSuma(total: IN Float);
    END empleado;

    empleados : array (1..10) of empleado;

    TASK BODY empleado IS
        vector: array (1..100000) of Float;
        total: Integer := 0;
    BEGIN
        FOR i IN 1..100000 LOOP
            total := total + vector[i];
        END LOOP;
        coordinador.dejarSuma(total);
    END BODY empleado;
    
    TASK coordinador IS 
        total: Float :=0;
        resultado: Float :=0;
    BEGIN
        FOR i in 1..10 LOOP
            ACCEPT dejarSuma(suma) DO
                total:= total+suma;
            END dejarSuma;
        END LOOP;
        resultado := total/1000000.0;
    END coordinador;
BEGIN
    null;
END vectorizado;
```
# 7.
Hay un sistema de reconocimiento de huellas dactilares de la policía que tiene 8 Servidores 
para realizar el reconocimiento, cada uno de ellos trabajando con una Base de Datos propia; 
a su vez hay un Especialista que utiliza indefinidamente. El sistema funciona de la siguiente 
manera: el Especialista toma una imagen de una huella (TEST) y se la envía a los servidores 
para que cada uno de ellos le devuelva el código y el valor de similitud de la huella que más 
se  asemeja  a  TEST  en  su  BD;  al  final  del  procesamiento,  el  especialista  debe  conocer  el 
código  de  la  huella  con  mayor  valor  de  similitud  entre  las  devueltas  por  los  8  servidores. 
Cuando  ha  terminado  de  procesar  una  huella  comienza  nuevamente  todo  el  ciclo.  Nota: 
suponga  que  existe  una  función  Buscar(test,  código,  valor)  que  utiliza  cada  Servidor  donde 
recibe  como  parámetro  de  entrada  la  huella  test,  y  devuelve  como  parámetros  de  salida  el 
código  y  el  valor  de  similitud  de  la  huella  más  parecida  a  test  en  la  BD  correspondiente. 
Maximizar la concurrencia y no generar demora innecesaria.
```java
PROCEDURE sistema IS
    

BEGIN

END sistema;
```