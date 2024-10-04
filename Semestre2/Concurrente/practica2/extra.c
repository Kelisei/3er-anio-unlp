
sem hayTarea[40] = ([40], 0);  mutexTareasHechas = 1; tareaHecha = 0; huboCorrecion[40] = ([40], 0);
bool ok[40] = ([40], false); 
process alumno[id:1..40]{
    P(hayTarea[id]);
    miTarea = tareas[id];
    //Hacer

    while(!ok[id]){
        P(mutexTareasHechas);
        tareasHechas.push(id, miTarea);
        V(mutexTareasHechas);
        V(tareaHecha)

        P(huboCorrecion[id])
        if(!ok){
            //Hacer
        }
    }
}

process Maestra{
    for i:=1..40{
        tareas[i] = generarTarea();
        V(hayTarea[i]);
    }

    while(true){
        P(tareaHecha);
        P(mutexTareasHechas);
        id, tarea = tareasHechas.pop();
        V(mutexTareasHechas);

        bool tareaOk;
        //Corregir (modifica tareaOk);
        ok[id] = tareaOk;
        V(huboCorrecion[id])
    }
}