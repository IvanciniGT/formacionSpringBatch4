
Si quisiera quedarme en un fichro aparte los datos de persoans con DNI invalido o fecha de nacimiento invalida:

Un Step1 que me diga si los datos son buenos o no... y la causa del error, si la hay

STATUS = 0 -> OK
         1 -> DNI invalido  
         2 -> Fecha de nacimiento invalida
class PersonaOutStep1{
    String nombre;
    ....
    int STATUS;
}

Después monto un Step2 que filtre los datos que son buenos a un fichero
Monto un Step 2b que filtre los datos que son malos por DNI a otro fichero
Monto un Step 2c que filtre los datos que son malos por fecha de nacimiento a otro fichero

Y esos 3 steps los lanzo en paralelo despues del step1

FLUJO JOB 1:

STEP1 -> Step2
      -> Step2b
      -> Step2c

if(error=DNI) A UN FICHERO
else if (error=Fecha) A OTRO FICHERO
else A OTRO FICHERO

SpringBatch no va a procesar LA LISTA con un bucle 3 veces... internamente no lo resuelve así... lo que hace es que cada vez que se encuentra un item en la lista, lo pasa por los 3 steps en paralelo y luego sigue con el siguiente item de la lista
En rendimiento es como si meto 3 ifs... 
Solo que cada uno en un archivo independiente. Lo que tengo son pasos especializados en cada escenario

DE NUEVO, componentes DESACOPLADOS.
El dia de mañana si quiero cambiar 1 de los ficheros... todo ese paso... nada mas.
Si quiero añadir otro paso... lo añado sin tocar a nadie...

Mi objetivo no es optimizar 2ns en una operación.
Es hacer un programa FACIL DE MANTENER. DE ESTO VA!

Ni siquiera me preocupa que sean 2ns x 100.000.000 datos = 200ms
Ni que tarde 1 hora más.
Que la tarde.
Lo que quiero es dentro de 10 meses, que el desarollador no tarde 15 horas más cambiando el código
Y dent¡ro de 11 meses que otro desarrollador tarde 10 horas en otro cambio
Y POR ESO USO SPRINGBATCH

Si me preocupan los 2 ns... o la hora de más... Mejor me voy a otra solución.
Si tengo ese volumen de datos: SPARK 
