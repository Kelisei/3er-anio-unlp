# 1.  
Se requiere modelar un puente de un único sentido que soporta hasta 5 unidades de peso. 
El peso de los vehículos depende del tipo: cada auto pesa 1 unidad, cada camioneta pesa 2 
unidades  y  cada  camión  3  unidades.  Suponga  que  hay  una  cantidad  innumerable  de 
vehículos  (A  autos,  B  camionetas  y  C  camiones).  Analice  el  problema  y  defina  qué  tareas, 
recursos y sincronizaciones serán necesarios/convenientes para resolverlo. 
## Solución usando Rendezvous en ADA

## a. Realice la solución suponiendo que todos los vehículos tienen la misma prioridad. 
```java
Procedure Puente is
    Task ControlPuente is
        Entry Entrar (Peso: IN Integer);
        Entry Salir (Peso: IN Integer);
    End ControlPuente;

    Task type Vehiculo (Peso: Integer);
    Autos: array (1..A) of Vehiculo(1);
    Camionetas: array (1..B) of Vehiculo(2);
    Camiones: array (1..C) of Vehiculo(3);

    Task Body Vehiculo is
    Begin
        ControlPuente.Entrar(Peso);
        // Simula el cruce del puente
        delay 5.0;
        ControlPuente.Salir(Peso);
    End Vehiculo;

    Task Body ControlPuente is
        PesoActual: Integer := 0;
    Begin
        loop
            select
                when PesoActual <= 5 =>
                    accept Entrar (Peso: IN Integer) do
                        if PesoActual + Peso <= 5 then
                            PesoActual := PesoActual + Peso;
                        end if;
                    end Entrar;
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