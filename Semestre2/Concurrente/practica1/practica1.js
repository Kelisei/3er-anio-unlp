1) 
    a- V
    b- V
    c- V
2)
    int contador = 0; N = ...; M = ...; 
    process encontrar[i = 0 to M-1]{
      if (array[i] == N){
        <contador++>
      }
    }
3) 
   a) No anda, debería bloquear el acceso al buffer.
   b) Además de bloquear el acceso al buffer debemos bloquear el acceso a la variable.
4)
    int cant = 5; int cola [cant];
    process proceso[i = 0 t0 N-1]{
      <await (cant > 0); int elem=pop(cola); cant--;>
      //Usar elemento
      <push(cola,elem); cant++>
    }
5)
  a)
    int n = ...;
      Process persona[id: 0..n-1]{ <imprimir(documento)> }
  b)
    int n = ...; int colaIDs[n]; bool ocupado = false; int siguiente = -1;
    process persona[id: 0..n-1]{
      <if (siguiente == -1) { siguiente = i} else {push(colaIDs, i)}>
      imprimir(documento)
      <if (empty(colaIDs) { siguiente = -1} else {siguiente=pop(colaIDs)})>
    }

  c) Lo mismo que en el b) pero asumo que la cola agrega ordenado
  d)
    int n = ...; bool termino = false; sig = -1; int colaIDs[n];
    process persona [i = 0 to n-1]{
      <push(colaIDs, i);>
      <await (sig == i)>
      imprimir(documento)
      termino = true;
    }

    process coordinador {
      while (true){
        <await (!empty(colaIDs))>
        <await (termino)>
        termino = false 
        <sig = pop(colaIDs)>
      }
    }
         