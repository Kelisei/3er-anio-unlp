# Pasaje de mensajes 
1) En  una  oficina  existen  100  empleados  que  envían  documentos  para  imprimir  en  5  impresoras 
compartidas. Los pedidos de impresión son procesados por orden de llegada y se asignan a la primera 
impresora que se encuentre libre: 
a) Implemente un programa que permita resolver el problema anterior usando PMA. 
```cpp
chan impresorasLibres(int)
chan pedidos(text)
chan peticiones[5](text)
process Empleado [1..100]{
    while(true){
        send pedidos(new pedido());
    }
}
process Coordinador{
    while(true) {
        int idImp; text pedido;
        receive impresorasLibres(idImp)
        receive pedidos(pedido)
        send peticiones[idImp](pedido)
    }
}
process Impresora[id:1..5]{
    while(true){
        text pedido
        send impresorasLibres(id);
        receive peticiones[id](pedido)
        imprimir(pedido)
    }
}
```
b) Resuelva el mismo problema anterior pero ahora usando PMS. 
```cpp
process Empleado [1..100]{
    while(true){
        Coordinador!pedido(new Pedido())
    }
}
process Coordinador{
    int idImp; text pedido;
    do 
        Empleado[*]?pedido(pedido) -> cola.push(pedidos);
        [] !cola.empty(); Impresora[*]?impresoraLibre(id) -> Impresora[id]!peticion(cola.pop())
    od
}
process Impresora[id:1..5]{
    while(true){
        text pedido
        Coordinador!impresoraLibre(id);
        Coordinador?peticion(pedido)
        imprimir(pedido)
    }
}
```
2) Resolver el siguiente problema con PMS. En la estación de trenes hay una terminal de SUBE que 
debe ser usada por P personas de acuerdo con el orden de llegada. Cuando la persona accede a la 
terminal,  la  usa  y  luego  se  retira  para  dejar  al  siguiente.  Nota:  cada  Persona  usa  sólo  una  vez  la 
terminal.  
```cpp
process Persona[id:1..P]{
    Coordinador!pedido(id)
    Coordinador?puedo()
    // Usar
    Coordinador!meFui()
}

process Coordinador{
    cola ids; bool ocupado = false
    do
        Persona[*]?pedido(id) -> 
            if(ocupado){
                ids.push(id)
            } else {
                ocupado = true
                Persona[id]!puedo()
            }
        [] Persona[*]?meFui; -> 
            if(!ids.empty()){
                Persona[ids.pop()]!puedo();
            } else {
                ocupado = false;
            }
    od
}
```
3) Resolver el siguiente problema  con PMA. En  un  negocio de cobros digitales hay P personas que 
deben  pasar  por  la  única  caja  de  cobros  para  realizar  el  pago  de  sus  boletas.  Las  personas  son 
atendidas de acuerdo con el orden de llegada, teniendo prioridad aquellos que deben pagar menos 
de 5 boletas de los que pagan más. Adicionalmente, las personas embarazadas tienen prioridad sobre 
los dos casos anteriores. Las personas entregan sus boletas al cajero y el dinero de pago; el cajero les 
devuelve el vuelto y los recibos de pago. 
```cpp
chan colaCaja[3](int)
chan hayPedido(bool)
chan devolucion[P](int, text)
process Persona[id:1..P]{
    bool prioridad = ...; int cantBoletas = ...
    if (cantBoletas < 5){
        send colaCaja[1](id)
    } else if (prioridad){
        send colaCaja[2](id)
    } else {
        send colaCaja[0](id)
    }
    send hayPedido()
    int vuelto; text recibo
    receive devolucion[id](vuelto, recibo)
}

process Caja{
    while(true){
        receive hayPedido()
        if(!empty(colaCaja[2])){
            receive colaCaja[2](id)
        } else if(!empty(colaCaja[1])){
            receive colaCaja[1](id)
        } else {
            receive colaCaja[0](id)
        }
        vuelto, boleta = procesarPago(id)
        send devolucion[id](vuelto, boleta)
    }
}


```
# ADA 
1) Resolver el siguiente problema. La página web del Banco Central exhibe las diferentes cotizaciones 
del dólar oficial de 20 bancos del país, tanto para la compra como para la venta. Existe una tarea 
programada que se ocupa de actualizar la página en forma periódica y para ello consulta la cotización 
de cada uno de los 20 bancos. Cada banco dispone de una API, cuya única función es procesar las 
solicitudes de aplicaciones externas. La tarea programada consulta de a una API por vez, esperando 
a lo sumo 5 segundos por su respuesta. Si pasado ese tiempo no respondió, entonces se mostrará 
vacía la información de ese banco. 
```ada
PROCEDURE pagina is
    TASK TYPE banco IS
        ENTRY consultarCambio(cambio: OUT Float);
    END banco;

    bancos: array (1..20) of banco;
    TASK BODY actualizador IS
        cambios: array (1..20) of Float;
    BEGIN
        LOOP
            FOR i IN 1..20 LOOP
                SELECT
                    bancos[i].consultarCambio(cambios[i])
                OR DELAY 5.0
                    cambios[i]:=null
            END LOOP;
        END LOOP;
    END BODY actualizador;

    TASK BODY banco IS
        cambio: Float;
    BEGIN
        LOOP
            cambioB := actualizarCambio();
            ACCEPT consultarCambio(cambio OUT Float) DO
                cambio := cambioB;
            END consultarCambio;    
        END LOOP;
    END BODY banco;
BEGIN

END pagina.
```
2) Resolver el siguiente problema. En un negocio de cobros digitales hay P personas que deben pasar 
por la única caja de cobros para realizar el pago de sus boletas. Las personas son atendidas de acuerdo 
con el orden de llegada, teniendo prioridad aquellos que deben pagar menos de 5 boletas de los que 
pagan más. Adicionalmente, las personas ancianas tienen prioridad sobre los dos casos anteriores. 
Las personas entregan sus boletas al cajero y el dinero de pago; el cajero les devuelve el vuelto y los 
recibos de pago.  
```ada
PROCEDURE negocio IS
    TASK caja IS 
        ENTRY colaPersonas(pedido: IN Integer, vuelto: Float, recibo: text);
        ENTRY colaBoletas(pedido: IN Integer, vuelto: Float, recibo: text);
        ENTRY colaPrioridades(pedido: IN Integer, vuelto: Float, recibo: text);
    END caja;
    TASK TYPE persona IS
    END persona;
    personas: array (1..P) of persona;
    TASK BODY caja IS
    BEGIN
        LOOP
            SELECT
                WHEN(colaPrioridades`COUNT =0 AND colaBoletas`Count=0) 
                    ACCEPT colaPersonas(pedido: IN Integer, vuelto: Float, recibo: text) DO
                        vuelto, recibo = pagar(pedido);
                    END colaPersonas;
            OR
                WHEN(colaPrioridades`COUNT =0 ) 
                    ACCEPT colaBoletas(pedido: IN Integer, vuelto: Float, recibo: text) DO
                        vuelto, recibo = pagar(pedido);
                    END colaPersonas;
            OR
                ACCEPT colaPrioridades(pedido: IN Integer, vuelto: Float, recibo: text) DO
                    vuelto, recibo = pagar(pedido);
                END colaPersonas;
        END LOOP;
    END;
    TASK BODY persona IS
        cantBoletas: Integer := ...; anciano:bool:= ...;
        pedido:Integer := ...;
        vuelto: Float, recibo: text;
    BEGIN
        IF (anciano) THEN
            caja.colaPrioridades(pedido, vuelto, recibo);
        ELSE IF cantBoletas < 5 THEN
            caja.colaBoletas(pedido, vuelto, recibo);
        ELSE
            caja.colaPersonas(pedido, vuelto, recibo);
        END IF
    END persona;
BEGIN
    null;
END negocio.
```
3) Resolver el siguiente problema. La oficina central de una empresa de venta de indumentaria debe 
calcular cuántas veces fue vendido cada uno de los artículos de su catálogo. La empresa se compone 
de 100 sucursales y cada una de ellas maneja su propia base de datos de ventas. La oficina central 
cuenta con una herramienta que funciona de la siguiente manera: ante la consulta realizada para un 
artículo determinado, la herramienta envía el identificador del artículo a las sucursales, para que cada 
una  calcule  cuántas  veces  fue  vendido  en  ella.  Al  final  del  procesamiento,  la  herramienta  debe 
conocer cuántas veces fue vendido en total, considerando todas las sucursales. Cuando ha terminado 
de procesar un artículo  comienza con  el siguiente (suponga que la herramienta tiene una función 
generarArtículo()  que  retorna el  siguiente  ID  a  consultar).  Nota:  maximizar  la  concurrencia.  Existe 
una  función  ObtenerVentas(ID)  que  retorna  la  cantidad  de  veces  que  fue  vendido  el  artículo  con 
identificador ID en la base de la sucursal que la llama. 

100 sucursale -> 100 db -> herramienta manda id a sucursal y despues retorna cantventas, herramienta suma

```ada
PROCEDURE oficina IS
    TASK herramienta IS
        ENTRY recibirVentas(ventas: IN Integer);
    END herramienta;
    TASK TYPE db IS
        ENTRY consultarVentas(idArticulo: IN Integer; ventas: OUT Integer);
    END db;
    TASK TYPE sucursal IS
        ENTRY recibirID(id: IN Integer);
        ENTRY recibirArticulo(idArt: In Integer);
    END sucursal;   
    sucursales : array (1..100) of sucursal;
    dbs : array (1..100) of db;
    TASK BODY herramienta IS
        idArt, cantVentas: Integer;
    BEGIN
        LOOP    
            idArt := generarArticulo();
            cantVentas := 0;
            FOR i IN 1..100 LOOP
                sucursales[i].consultarArticulo(idArt);
            END LOOP;
            FOR i IN 1..100 LOOP
                ACCEPT recibirVentas(ventas: IN Integer) DO
                    cantVentas := cantVentas + ventas;
                END recibirVentras;
        END LOOP;
    END BODY herramienta;
    TASK BODY sucursal IS
        idArticulo, cantVentas: Integer;
        id: Integer;
    BEGIN
        ACCEPT recibirID(id);
        LOOP
            ACCEPT consultarArticulo(idArt: IN Integer) DO
                idArticulo := idArt;
            END consultarArticulo;
            dbs[id].consultarVentas(idArticulo, cantVentas);
            herramienta.recibirVentas(cantVentas);
        END LOOP;
    END BODY sucursal;
    TASK BODY db IS
        LOOP
            ACCEPT consultarVentas(idArticulo: IN Integer; ventas: OUT Integer) DO
                ventas := ObtenerVentas(idArticulo);
            END consultarVentas;
        END LOOP
    END db;
BEGIN
    FOR i IN 1..100 LOOP
        sucursales[i].recibirID(i);
    END LOOP;
END oficina;
```
 