# Spring

Es un framework para el desarrollo de apps en JAVA.
Es un contenedor de inversión de control (IoC) => patrones de inyección de dependencias (DI) => respetar el principio de inversión de dependencias (DIP).

# Spring batch

Librería de las muchas que tiene Spring para el desarrollo de aplicaciones batch: ETL, procesamiento de datos, etc.

# Spring boot

Es una extensión de Spring que facilita la creación/configuración de aplicaciones Spring. Se encarga de la configuración automática de la aplicación, de la creación de beans, de la configuración de los beans, etc.

---

# Cómo le indicábamos a Spring que debe usar para satisfacer una Inyección de Dependencias (DI)?

En algún momento, mi código solicitará un objeto... y Spring debe saber qué objeto darle.

2 estrategias:
# Anotando nuestra clase, de la que queremos que spring genere instancias, con la anotación @Component

```java
public interface MiInterfaz {
}

@Component
public class MiClase extends MiInterfaz {
}
```

Si alguien pide una instancia de MiInterfaz, Spring le dará una instancia de MiClase.
Por defecto, Spring creará una UNICA instancia de MiClase,y siempre entrega la misma a cualquier clase que solicite un objeto de tipo MiInterfaz (comportamiento Singleton). Eso se puede cambiar con la anotación @Scope.
Hay distintos scopes que podemos usar: Singleton, Prototype, Request, Session, etc. Muy poco habitual.

```java
@Component
@Scope("prototype")
public class MiClase extends MiInterfaz {
}
```

Si puedo usar esa, lo tengo claro. Solo si no puedo hacer lo de arriba:

# Crear una función anotada con @Bean en una clase anotada con @Configuration

```java

public interface MiInterfaz {
}

public class MiClase implements MiInterfaz { // Si esta clase o es mía, no puedo anotarla con @Component
}

@Configuration
public class MiConfiguracion {

    @Bean
    public MiInterfaz miInterfaz() {
        return new MiClase();
    }
}
```

Spring en automático mirará las clases que tengo en mi código (aplicación) anotadas como @Configuration y buscará las funciones anotadas con @Bean. Ejecutará esas funciones... y capturará la salida. Y cuando alguien pida un dato del tipo MiInterfaz, le dará la salida de la función miInterfaz().

Con esas estrategias es cómo le indicamos Spring qué debe devolver / entregar cuando alguien pide una instancia de una interfaz o clase.
---

# Cómo solicitar una dependencia a Spring...

```java
public interface MiInterfaz {
}

@Component
public class MiClase implements MiInterfaz {
}

// OPCION 1 = RUINA !
public class OtraClase {
    @Autowired // Deprecated... ya no se hace. Se basa en reflections... y 1: es lento, 2: no es seguro.
                // Hay muy pocos casos de uso donde la anotación @Autowired vamos a seguir usándola de una forma conveniente y aceptable.
    private MiInterfaz miInterfaz;
} // Eso solo funciona (sigue funcionando aunque ya no lo usemos) SI Y SOLO SI, Spring es quien crea la instancia de OtraClase.

// OPCION 2 = BUENA !
public class OtraClase2 {
    public void unaFuncion(MiInterfaz miInterfaz) { // Si tengo una función que necesita un dato
                                                    // OJO !!!!!!!!!!!!!
                                                    // Y Spring es quién va a llamar a esa función
                                                    // Spring va a inyectar la dependencia en automático.. No hay que decir nada más.
        this.miInterfaz = miInterfaz;
    }
}
// OPCION 3: SUPER GUAY y LA QUE MAS USAMOS !
// Imaginad que tengo una clase que necesita una dependencia... como en el caso de la OtraClase... 
// Pero hemos dicho que lo del Autowired no se hace...
// Alguna alternativa? Si Spring es quién va a crear la instancia de mi Clase, eso significa que Spring es quien va a llamar al 
// Constructor de mi clase (que no es sino una función más de mi clase).
public class OtraClase3 {
    private final MiInterfaz miInterfaz;
    public OtraClase3(MiInterfaz miInterfaz) { // Si spring llama al constructor, le pasa la dependencia en automático.
        this.miInterfaz = miInterfaz;
    }
}
```
Todo esto solo funciona si es Spring quien llama a mis funciones (constructor u otras). Si yo llamo a mis funciones, no hay inyección de dependencias.

# Como consigo eso? Que sea Spring el que llame a mis funciones... o el que cree las instancias de mis clases?

Ya os he contado algunas estrategias:
- Si tengo una clase marcada con @Component o sus subtipos, Spring es quién crea instancias de esa clase en automático.
  Por lo tango... en el constructor de esa clase, puedo pedir dependencias... y Spring las inyectará en automático.
- En osaciones, Spring va a hacer llamadas a funciones directamente de mi código.
  Os decía ayer, que el flujo de la app lo va a controlar Spring. Y eso significa que Spring va a llamar a ciertas funciones que espera que yo tenga en mi código... y en esas funciones, puedo pedir dependencias... y Spring las inyectará en automático.

---

Realmente hay una cosita más...
Spring no crea instancias de todas las clases que tenga anotadas con @Component o sus subtipos. Ni lee todas las clases que tenga anotadas con @Configuration. Solamente aquellas que yo le indique que debe leer.
Eso se lo podría decir (Y antes lo hacía) mediante una anonotación @ComponentScan, donde daría los paquetes que Spring debe leer.
```java
@ComponenScan("com.empresa.paquete1, com.empresa.paquete2") // en este caso spriong scanea todas las clases de esos paquetes
                                                            // y crea instancias de las que estén anotadas con @Component o sus subtipos.
public class MiClase {
}
```

Realmente hoy en día, con Springboot, no es necesario hacer eso. Springboot ya lo hace por nosotros. Springboot ya escanea todos los paquetes de nuestra aplicación y crea instancias de las clases que estén anotadas con @Component o sus subtipos.
```java
package com.empresa.paquete1;
@SpringBootApplication // Y en automático Springboot escanea el paquetes y sus subpaquetes del paquete donde está esta clase
                        // y crea instancias de las clases que estén anotadas con @Component o sus subtipos.
public class MiClase {
}
```


Ahora... abrimos ECLIPSE o INTELLIJ !!!

En cualquiera: Creamos un proyecto gestionado mediante maven.
ECLIPSE: File -> New -> Maven Project
INTELLIJ: File -> New -> Project

Primero lo hago con IntelliJ
Y después con Eclipse

---

# Qué es Maven?

Maven es mucho más que un gestor de dependencias.
Es una herramienta de automatización de tareas para proyectos de software (principalmente en JAVA... aunque no exclusivamente).
Maven me permite:
- Compilar mi código
- Empaquetar mi código
- Probar mi código
- Enviar mi código a un repositorio
- Enviar mi código a un Sonar
- Generar documentación de mi código
- Ejecutar una app Springboot
- Y ... gestionar dependencias.

## Estructura de un proyecto Maven

proyecto/
|- src/
|   |- main/
|   |   |- java/        Código fuente de mi aplicación
|   |   |- resources/   Archivos que necesita mi aplicación
|   |- test/
|   |   |- java/        Código fuente de mis tests (programa de pruebas)
|   |   |- resources/   Archivos que necesita mi programa de pruebas
|- target/              Carpeta donde se guardan los resultados de las tareas de Maven
|   |- classes/         Código compilado
|   |- test-classes/    Código de los tests compilado
|   |- miApp.jar        Mi aplicación empaquetada (ZIP que contiene la carpeta target/classes/)
|- pom.xml              Configuración de mi proyecto para Maven


# pom.xml

- Coordenadas del proyecto: groupId, artifactId, version
- Metadatos del proyecto: nombre, descripción, url, etc.
- Plugins: configuración de los plugins que vamos a usar
- Dependencias: las dependencias que necesita mi proyecto
- Damos configuración a los plugins que vamos a usar en nuestro proyecto.

Cada tarea que automatizo con Maven, se hace mediante un plugin.
Las tareas las identificamos con Goals. Maven, de serie, trae muchos plugins y muchos goals:

- compile       => Compila el código que tengo en src/main/java y lo deja en target/classes
  Copia los archivos de src/main/resources en target/classes
  ^^
- test-compile  => Compila el código que tengo en src/test/java y lo deja en target/test-classes
  Copia los archivos de src/test/resources en target/test-classes
  ^^
- test          => Ejecuta las pruebas que tenga en target/test-classes
  ^^
- package       => Empaqueta mi aplicación en un JAR o WAR o EAR (mete en un zip la carpeta target/classes)... y lo deja en target/
  ^^
- install       => Copia mi jar en la carpeta .m2/repository.. así puedo usarlo como dependencia en otros proyectos
  Si no voy a usar mi proyecto como dependencia en otros proyectos... para que cojones hago un install??? METER MIERDA !
- clean         => Borra la carpeta target/... como si la borro yo a mano!

## Carpeta .m2

Es una carpeta que maven al instalarlo en mi máquina, crea en mi carpeta de usuario.
c:\Users\miUsuario\.m2
/home/miUsuario/.m2

En esa carpeta en .m2/repository, maven guarda las dependencias que va descargando de internet.

Qué plugins tenemos configurados en nuestro proyecto???? Ahora mismo?

---

Cada vez que cambie el archivo pom.xml tengo que recargarlo:
- Eclipse: Boton derecho> maven > update ...
- Intelli: Boton derecho> maven > reload ...

---
Lo primero añadir las dependencias a nuestro proyecto...
Pero teníamos 200 librerías!!!! Que locura !
Cuáles pongo? Estoy perdido

Uso un starter de SpringBoot para Springboot:
Ahora mismo encontraremos 2 versiones que se mantienen en paralelo:
2.X  -  Java 1.8 (Está actualizada... contiene casi todo lo mismo que la 3... algunas cosas menos... pero poco!)
3.X  -  Java 17
---

Y es que SpringBatch, configurado mediante SpringBoot, necesita que le ponga una BBDD para trabajar. De algún lao sacaré los datos...
O a algún lao los enviaré.

Tenéis por ahí una BBDD ? A mano? Para trabajar? O instalamos una? Vamos a montar una...
Vamos a montar una BBDD H2.

H2 es una BBDD que usamos un HUEVO al desarrollar.
Es una BBDD en memoria.
No es una BBDD que se use en producción.
Es una BBDD que se usa para pruebas, para desarrollo, para prototipos, para demos, etc.
Y lo mejor es que Spring tiene integración con H2 de forma nativa.

Me evita en los entornos de desarrollo tener que instalar una BBDD de verdad de la buena.
Y además... para pruebas es cojonuda... ya que puedo tener una BBDD limpia en cada ejecución de mis tests.
No voy dejando un entorno MALEAO!!!

----
Ejecuto... y esta es la salida:

    .   ____          _            __ _ _
    /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
    \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
    '  |____| .__|_| |_|_| |_\__, | / / / /
    =========|_|==============|___/=/_/_/_/
    :: Spring Boot ::               (v2.7.18)

    2024-06-25 16:43:22.281  INFO 66037 --- [           main] com.curso.MiAplicacion                   : Starting MiAplicacion using Java 1.8.0_412 on imac-ivan.local with PID 66037 (/Users/ivan/Desktop/formacionSpringBatch/target/classes started by ivan in /Users/ivan/Desktop/formacionSpringBatch)
    2024-06-25 16:43:22.285  INFO 66037 --- [           main] com.curso.MiAplicacion                   : No active profile set, falling back to 1 default profile: "default"
    2024-06-25 16:43:22.995  INFO 66037 --- [           main] com.curso.MiAplicacion                   : Started MiAplicacion in 1.091 seconds (JVM running for 1.675)

    Process finished with exit code 0

Ha ido bien???? SI... guay...
Spring ya ha ejecutado mi app (Started MiAplicacion)... y me ha dicho que todo ha ido bien (exit code 0 = ESO ES QUE IDO BIEN !!!!)

Le hemos dicho a Spring que nuestra aplicación debe hacer algunas cosas... o que quiero tener algunas cosas.. o algo? NO
Pues me ejecuta una app que no hace nada.

---

Cuando hay problemas con la descarga de las dependencias es porque estais saliendo por un proxy... y no lo habeis configurado en maven.
O porque habeis configurado un proxy en maven y no está saliendo por el proxy

Lo primero... mirar a ver si teneis un proxy configurado en maven:
mvn help:effective-settings
Siguiente:
Mira el archivo:
c:\Users\miUsuario\.m2\settings.xml
Y mira a ver si ahí pone algo de proxy... y el registry del que está descargando las dependencias.
Si te descarga e un nexus, es que tienes configurado el maven para que use un registry de tu empresa!
Puedes comentar esa línea de código: ANTES UN BACKUP DEL ARCHIVO !!!!!

---

Vamos a empezar a Contarle a Spring lo que queremos que TENGA nuestra app...
Y lo haremos con un lenguaje DECLARATIVO... no imperativo.


En el caso que nos atañe (SpringBatch), SpringBatch pone a mi disposición una serie de clases, anotaciones e interfaces que puedo usar para configurar mi app.

Spring Batch se especializa en ejecutar ETLs.

Una ETL en SpringBatch recibe el nombre de `Job`.

Los Jobs de SpringBatch tienen una serie de pasos (`Step`) que se ejecutan en un orden determinado.

Cada paso tiene 3 partes:
- Un lector de datos            `ItemReader`
- Un procesador de datos        `ItemProcessor`
- Un escritor de datos          `ItemWriter`

Si tengo una ETL sencilla, puedo tener un Job, con un solo paso, con un lector, un procesador y un escritor.

Para ETLs más complejas, puedo tener un Job con varios pasos... y cada paso con su lector, procesador y escritor.

Más conceptos de SpringBatch:
- JobLauncher: Es el encargado de lanzar un Job.
- JobRepository: Es el encargado de guardar el estado de los Jobs que se ejecutan (ahora yu antes... histórico)... que se persiste en BBDD. De ahí que SpringBatch nos requiera una BBDD.
  SpringBatch va a crear tablas en esa BBDD... con su propio esquema... para guardar el estado de los Jobs que se ejecutan.
- Item: Es un objeto que se lee, se procesa y se escribe. Es el objeto que se va a mover por mi ETL.

Además, a muchos de esos objetos, les pondré configurar Listeners...
- Los listeners me permiten tomar acciones antes, durante y después de la ejecución de un Job, de un Step, de un Item, etc.
  YO NO PONGO EL FLUJO DE LA APP.... ese le pone SpringBatch...

Yo le dire a Spring, que voy a montar una app usando SpringBatch.
Y que quiero un Job.
Ah! y  que quiero que ese job tenga 1 Step.
Ah! y que quiero que ese estep lea los datos de un fichero CSV.
Ah! Y que quiero que ese step deje los datos en una BBDD.
Ah! Y que quiero que esos datos los procese antes de dejarlos en la BBDD.
Ah! Y que quiero que me mande un email cuando empiece el Job.
Ah! Y que quiero que me mande un email cuando termine el Job.

Y como veis SOLO USO LENGUAJE DECLARATIVO... no imperativo.
Ni hablo de flujo... ese lo pone SpringBatch.

Cuando SpringBatch entienda cómo es mi app... que tiene... que quiero!
ÉL junta todas esas piezas, las arranca y va pasando cosas (Items) de un lado a otro (lector, procesador, escritor).
Eso lo hace el propio SpringBatch... yo no tengo que hacer nada. CONTENEDOR DE INVERSIÓN DE CONTROL.

El problema viene cuando no entiendo la filosofía de spring/SpringBatch... e intento buscarle un flujo a mi app en mi código.... y no lo encuentro. NI LO ENCONTRARÉ. porque no está ahí. Está en SpringBatch.

---

# Cómo montamos un proyecto!

Voy a definir una estructura de carpetas para ir dejando los componentes de mi app... componentes DESACOPLADOS.
En la empresa definiremos una estructura.. a la cual me adaptaré.
Yo en curso voy a usar una que a mi me parece guay... pero a alguien le parecerá una mierda. Sobre gustos no hay nada escrito.

proyecto/
src/
main/
java/
paquete/
jobs/
job1/
listeners/
IJob1Listener.java
Job1Listener.java
mappers/
steps/
step1/
listener/
IProcess1Listener.java
Process1Listener.java
reader/
IEscritorStep1.java
EscritorStep1.java
processor/
IProcesadorStep1.java
ProcesadorStep1.java
writer/
IEscritorStep1.java
EscritorStep1.java
IStep1.java
Step1.java
Job1.java
models/
PersonaIn.java
PersonaOut.java
MiAplicacion.java... este no hace na' Solo le dice a SpringBatch que busque lo que hay por aquí!

Mi app tendrá distintos tipos de componentes:
Jobs
Steps
Listeners de Jobs
Listeners de Steps
Readers
Processors
Writers
Pero también tendré Datos: La definición de la estructura de los datos que voy a mover por mi ETL, la definiré en ficheros JAVA (clases) independientes (con estructura de POJOs).

POJO??? Plain Old Java Object... un objeto de JAVA normal y corriente... con sus atributos, sus getters y setters... y poco más (no llevan lógica).

Y voy a definir muchos POJOs... muchas estructuras de datos...
- Una cosa son los datos que LEO        (20 campos)
- Otra cosa son los datos que PROCESO   (10 campos)
- Otra cosa son los datos que ESCRIBO   (5 campos)

Tendré que convertir los datos que leo en los datos que proceso... y los datos que proceso en los datos que escribo.
Eso lo dejaré a otras clases... otros componentes de mi aplicación, cuya una misión (motivo de existencia) es esa conversión:
Esos serán los MAPPERS (los mapeadores).

Vamos a acabar con MUCHOS ARCHIVOS.... MUCHOS COMPONENTES!
Y eso es maravilloso... porque así tendré ficheros muy pequeñitos centrados en una tarea concreta.
Si en algún momento hay que cambiar algo, sabré al fichero que tengo que acudir... y allí habrá poco código que me será fácil de entender y de modificar.... De hecho eso es lo que me dice otro de los principios "S"OLID de desarrollo de software: Principio de responsabilidad única (SRP).

Aún no he escrito ni una COMA de código... SOLO LE DOY ESTRUCTURA !
Y esa estructura es la que garantizará que mi app sea mantenible, escalable, testeable, etc.
ESTA ES LA CLAVE !
El código a la que me descuide lo escribe el GITHUB COPILOT por mi! Si es muy sencillo!