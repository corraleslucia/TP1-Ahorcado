1) DIFERENCIA ENTRE RUNNABLE Y THREAD:
La programación multihilos en Java permite la ejecución de dos o más partes de un programa 
para la máxima utilización del CPU. Los hilos son procesos ligeros dentro de un proceso.
Se puede crear un hilo de dos formas:

A. extendiendo de la clase Thread:
Se crea una clase que extienda de la clase Thread. Esta clase sobreescribe el método run()
disponible en la clase padre. El hilo comienza su vida dentro de run(). Creamos un objeto
de nuestra clase y llamamos al método start() para comenzar la ejecución de nuestro hilo,
y dicho método invoca al método run().
 
B. implementando la interfaz Runnable:
Creamos una nueva clase que implementa la interfaz Runnable y sobreescribimos el método
run(). Luego instanciamos un objeto del tipo Thread y le pasamos por parámetro una nueva
instacia de nuestra clase que implementa Runnable, para luego llamar al método start() del
thread instanciado.

* Si extendemos de la clase Thread, nuestra clase no puede extender de ninguna otra porque
Java no soporta la herencia múltiple. Si implementamos Runnable, nuestra clase todavía
podría extender de otras.

* Podemos lograr la funcionalidad básica de un hilo al extender de la clase Thread porque 
esta provee algunos métodos incorporados como yield(), interrupt(), entre otros, que no 
están disponibles si se implementa la interfaz Runnable.

2) CICLO DE VIDA DE UN THREAD:
Durante su ciclo de vida, un hilo pasa por varios estados. Durante cualquier momento dado,
un hilo solo puede estar en uno de estos estados:

* New: cuando el hilo acaba de ser creado y no ha empezado su ejecución todavia(hasta que
se lo comience utilizando el método start()).

* Runnable: cuando un hilo está ejecutando o está listo para la ejecución pero está esperando
una asignación de recurso.

* Blocked:esperando para adquirir la entrada al monitor o re-ingresar a un bloque/método
sincronizado.

* Waiting: esperando a que otro hilo realice una acción particular sin límite de tiempo.

* Timed Waiting: esperando a que otro hilo realice una acción específica con un período de
tiempo específico.

* Terminated: el hilo ha completado su ejecución.

3) MÉTODOS[START(), SLEEP(), YIELD(), JOIN()]:

* start(): cuando se llama al método start(), este crea los recursos del sistema necesarios
para que el subproceso se ejecute, y a continuación llama al método run(), pasando de estado
new a runnable.

* sleep(): este método genera que el hilo que se está actualmente ejecutando se "duerma" por
un número específico de milisegundos, sujeto a la precisión y exactitud del temporizador
del sistema. Así, hacemos que un hilo esté en estado dormido por un periodo específico
de tiempo y generamos que el hilo para definitivamente su ejecución por ese lapso de tiempo;
si no hay otros hilos o procesos necesitando ejecutarse, la CPU tendrá tiempo ocioso

* yield(): indica que el hilo no está haciendo nada particularmente importante y que si otro
hilo o proceso necesita ejecutarse, pueden. Mientras esto noo ocurra, el hilo actual seguirá
ejecutando. Cuando se llama al método yield(), este avisa al organizador de los hilos que está
listo para pausar su ejecución. El organizador puede obviar este aviso, luego de revisar que
no haya otro hilo con la misma o mayor prioridad. Si lo encuentra, mueve al hilo actual al
estado runnable y le da el procesador a otro hilo; sino, dicho hilo sigue ejecutando.

* join(): este método se utiliza cuando se necesita esperar desde un thread a que otro
finalice. Así, cuando un hilo llama a join(), dicho método no se ejecutará hasta que el
thread al que lo ha llamado mediante join() finalice.
