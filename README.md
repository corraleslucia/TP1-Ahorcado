1) DIFERENCIA ENTRE RUNNABLE Y THREAD:
La programaci�n multihilos en Java permite la ejecuci�n de dos o m�s partes de un programa 
para la m�xima utilizaci�n del CPU. Los hilos son procesos ligeros dentro de un proceso.
Se puede crear un hilo de dos formas:

A) extendiendo de la clase Thread:
Se crea una clase que extienda de la clase Thread. Esta clase sobreescribe el m�todo run()
disponible en la clase padre. El hilo comienza su vida dentro de run(). Creamos un objeto
de nuestra clase y llamamos al m�todo start() para comenzar la ejecuci�n de nuestro hilo,
y dicho m�todo invoca al m�todo run().
 
B) implementando la interfaz Runnable:
Creamos una nueva clase que implementa la interfaz Runnable y sobreescribimos el m�todo
run(). Luego instanciamos un objeto del tipo Thread y le pasamos por par�metro una nueva
instacia de nuestra clase que implementa Runnable, para luego llamar al m�todo start() del
thread instanciado.

* Si extendemos de la clase Thread, nuestra clase no puede extender de ninguna otra porque
Java no soporta la herencia m�ltiple. Si implementamos Runnable, nuestra clase todav�a
podr�a extender de otras.

* Podemos lograr la funcionalidad b�sica de un hilo al extender de la clase Thread porque 
esta provee algunos m�todos incorporados como yield(), interrupt(), entre otros, que no 
est�n disponibles si se implementa la interfaz Runnable.

2) CICLO DE VIDA DE UN THREAD:
Durante su ciclo de vida, un hilo pasa por varios estados. Durante cualquier momento dado,
un hilo solo puede estar en uno de estos estados:

* New: cuando el hilo acaba de ser creado y no ha empezado su ejecuci�n todavia(hasta que
se lo comience utilizando el m�todo start()).

* Runnable: cuando un hilo est� ejecutando o est� listo para la ejecuci�n pero est� esperando
una asignaci�n de recurso.

* Blocked:esperando para adquirir la entrada al monitor o re-ingresar a un bloque/m�todo
sincronizado.

* Waiting: esperando a que otro hilo realice una acci�n particular sin l�mite de tiempo.

*Timed Waiting: esperando a que otro hilo realice una acci�n espec�fica con un per�odo de
tiempo espec�fico.

*Terminated: el hilo ha completado su ejecuci�n.

3)M�TODOS[START(), SLEEP(), YIELD(), JOIN()]:

* start(): cuando se llama al m�todo start(), este crea los recursos del sistema necesarios
para que el subproceso se ejecute, y a continuaci�n llama al m�todo run(), pasando de estado
new a runnable.

* sleep(): este m�todo genera que el hilo que se est� actualmente ejecutando se "duerma" por
un n�mero espec�fico de milisegundos, sujeto a la precisi�n y exactitud del temporizador
del sistema. As�, hacemos que un hilo est� en estado dormido por un periodo espec�fico
de tiempo y generamos que el hilo para definitivamente su ejecuci�n por ese lapso de tiempo;
si no hay otros hilos o procesos necesitando ejecutarse, la CPU tendr� tiempo ocioso

* yield(): indica que el hilo no est� haciendo nada particularmente importante y que si otro
hilo o proceso necesita ejecutarse, pueden. Mientras esto noo ocurra, el hilo actual seguir�
ejecutando. Cuando se llama al m�todo yield(), este avisa al organizador de los hilos que est�
listo para pausar su ejecuci�n. El organizador puede obviar este aviso, luego de revisar que
no haya otro hilo con la misma o mayor prioridad. Si lo encuentra, mueve al hilo actual al
estado runnable y le da el procesador a otro hilo; sino, dicho hilo sigue ejecutando.

* join(): este m�todo se utiliza cuando se necesita esperar desde un thread a que otro
finalice. As�, cuando un hilo llama a join(), dicho m�todo no se ejecutar� hasta que el
thread al que lo ha llamado mediante join() finalice.
