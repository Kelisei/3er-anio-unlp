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
            delay 360.0;
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
                END ACCEPT;
            OR WHEN (atencionEnfemero`COUNT = 0) =>
                ACCEPT atencionEnfermero(pedido) do
                    procesar(pedido);
                END ACCEPT;
                ELSE 
                    SELECT escritorio.buscarTarea(pedido) DO 
                        IF deboFirmar(pedido) THEN
                            firmarPedido(pedido);
                        END IF;
                    ELSE 
                        null;
                    END SELECT;
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
                WHEN (buscarTarea`COUNT > 0) =>
                    ACCEPT buscarTarea(pedido) do
                        pedido = pop(cola);
                    END ACCEPT;
                OR
                    ACCEPT dejarTarea(pedido) do
                        push(cola, pedido);
                    END ACCEPT;
            END SELECT; 
        END LOOP;
    END BODY escritorio;
    TASK paciente is
        intentos: Integer := 0;
    BEGIN
        WHILE intentos < 3 LOOP 
            SELECT 
                medico.atencionPaciente();
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
# 5.
``` java

```