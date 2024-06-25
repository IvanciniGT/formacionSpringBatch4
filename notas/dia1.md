
# Kotlin

En otro lenguaje de programación (desarrollado por JetBrains - los que fabrican IntelliJ-) que en el mundo Android ya ha desplazado a JAVA.
En kotlin trabajamos con ficheros.kt -> compilación -> .class -> JVM
(byte code)

En una alternativa al lenguaje JAVA (lenguaje que tiene una cantidad de MIERDAS en su sintaxis GIGANTESCA).

---

# Qué es Spring?

Efectivamente Spring es un framework para el desarrollo de apps... JAVA y no solo JAVA (aunque su origen es JAVA, se ha migrado entero a Kotlin). Aplicaciones. PELOTA.

Puedo montar apps:
- Web (Spring MVC o no)
- Batch (Spring Batch)
- Consola (Spring Shell)

Pero la gracia de Spring es que es un CONTENEDOR DE INVERSION DE CONTROL!

x Un framework de JAVA para apps ~~WEB~~. ??? NOP
x ~~Basado en el~~Soporta patrón MVC (Modelo-Vista-Controlador) si quiero... si no quiero pues no. y no pasa nada!. ??? NOP
x Conjunto de herramientas que nos ayudan a gestionar un proyecto de software desde desarrollo hasta el despliegue. ??? NOOP
Herramientas que nos ayudan a gestionar un proyecto de software desde desarrollo hasta el despliegue: MAVEN

Spring es un framework que tiene más de 200 librerías.

- Tiene un core... que define la base de Spring... el que me ayuda a definir lo que es una aplicación...
    - A configurarla en Spring
    - A solicitar a Spring su ejecución
    - A respetar el ppo de la Inversión de la Dependencia
    - A solicitar inyección de dependencias
- Tiene una librería para el desarrollo de aplicaciones web
- Tiene una librería para el desarrollo de aplicaciones batch
- Tiene una librería para el desarrollo de aplicaciones de consola
- Tiene una libraría para hacer pruebas automáticas
- Tiene una librería para el acceso a BBDD relacionales: JPA
- Tiene una librería para el acceso a BBDD No SQL: Mongo

De cara a montar uin proyecto, tengo que elegir cuales de esas librerías voy a usar... Y eso en si mismo es un problema! Por la cantidad.

Y ahí sale SpringBoot... que nos ayuda a rápidamente configurar un proyecto Spring.
Springboot me ofrece los denominados Starters, que son recopilaciones de librerías de Spring que me permiten montar un proyecto de un tipo concreto (web, batch, consola, etc) en un momento.

Hoy en día, configuramos las apps Spring con Springboot... Antaño me tocada elegir yo una a una las librerías de Spring que iba a usar... y definir ficheros XML para ver que instancias de que librerías iba a usar...
Hoy en día Springboot se encarga de todo eso... y me lo pone muy muy fácil.

# CONTENEDOR DE INVERSION DE CONTROL????

Si tener claro esto... malamente (tra, tra) vamos a ser capaces de usar Spring.
Al contenedor de Inversión de Control le delegamos la responsabilidad de montar nuestra app... de darle coherencia: FLUJO.
Ese flujo lo aporta el framework...

Pero además, Spring nos ayuda mucho con otro tema... Respetar el PPO de la Inversión de la Dependencia. = IMPORTANTISIMO
Los contenedores de Inversión de control (como Spring, .netFramowork, Angular) nos facilitan el uso de patrones de Inyección de Dependencias, para respentar el ppo de la Inversión de la Dependencia, para montar software fácil de mantener y de evolucionar en el futuro.

Y esa es la gracia del FRAMEWORK más aberrante (grande) creado en el mundo JAVA: Spring.

---

## Principios SOLID de desarrollo de software

Son 5 principios, que podemos respetar o no... pero que si respetamos tendremos más probabilidades de montar un software fácil de mantener y de evolucionar. Y AMIG@S esta es la clave.
Cualquier persona puede montar un programa. Eso es fácil... No hay problema hí... Es solo cuestión de horas.
Aún no está?... pues un ratito más... y ya está.

Lo jodido... y lo que diferencia a un Senior de un Junior... es que el Senior es capaz de montar un software que sea fácil de mantener y de evolucionar.

## Principio de la inversión de la dependencia

Un componente de alto nivel de un sistema no debe depender de implementaciones de componentes de más bajo nivel. Ambos deben depender de abstracciones (interfaces).
Y si yo respeto esto, estaré más cerca de conseguir un software fácil de mantener y de evolucionar.

Una forma (de muchas que hay) de asegurarnos que respetamos este principio es a través del uso de un patrón de inyección de dependencias.

## Inyección de dependencias

Patrón de desarrollo de software (Orientación a Objetos) que dice que Nuestras clases no deben crear instancias de los objetos que necesitan...sino que le deben ser suministradas.
---

> quiero montar un programa: No es una ETL. Es un programa por consola que me permite preguntar por una palabra en un idioma.. y me dice si existe y sus significados.

Cuántos repos (proyectos en git independientes) voy a montar? Cuantos archivos pom.xml (proyectos de maven)?
- UNO NI DE COÑA !!!!!! No estoy ya en los 2000... PASO OLIMPICAMENTE DE SISTEMA MONOLITICOS... YA los hemos sufrido años... décadas. NO MAS !
- TRES = GUAY ! No quiero un monolito... Quiero una arquitectura orientada a Componentes DESACOPLADOS!
  Y aquí es donde Spring me ayuda....
  Tened en cuenta que Las arquitecturas, las metodologías, los patrones de diseño, los lenguajes de programación, las herramientas, los frameworks evolucionan conjuntamente en el tiempo para ayudarnos con los problemas que tenemos en ese momento.
  Si intento usar Spring para montar un MONOLITO, lo llevo mal! No está pensado para eso.
  Spring está pensado para montar una arquitectura orientada a componentes desacoplados... usando una metodología de desarrollo de software ágil... y respetando los principios SOLID, realizando pruebas automáticas, etc.

## Frontal: App de consola
A mi app de consola le debería importar una mierda que implementación uso de la API de diccionarios? EN NADA... La que sea... mientras cumpla con el API (contrato)

    ```java
    package com.diccionarios;
    import com.diccionarios.api.SuministradorDeDiccionarios;
    import com.diccionarios.api.Diccionario;
    // import com.diccionarios.ficheros.SuministradorDeDiccionariosEnFicheros; // RUINA! Aquí murió el proyecto!
    // Joder tio... es un puto import... metemos cientos de imports... no será tan malo! HORRIBLE!!!! No es igual a los otros.
    // Los otros son interfaces... y este es una implementación... 
    // Acabo de generar una DEPENDENCIA FUERTE... entre mi app de consola y la implementación de la API de diccionarios.
    // ME acabo de CAGAR en el ppo de la Inversión de la Dependencia.
    public class AppDeConsola {
        public static void main(String[] args) {
            //... ESTO ESTA GUAY... lo que sea que haga!
        }
// Lo único que hemos hecho es postponer el problema... Pasarselo a otro.
// En algún sitio, tendrá que escribirse el código: "new SuministradorDeDiccionariosEnFicheros()"
// Pero ese código dejaremos que lo escriba el Contenedor de Inversión de Control... que es el que va a controlar el flujo de mi app.
// El que va a llamar a esta función... Y en ese momento se dará cuenta que necesito una implementación de SuministradorDeDiccionarios... y la montará él.
// Y la buscará.. A ver qué implementación hay disponible... Y creará una instancia de ella: new SuministradorDeDiccionariosEnFicheros()
// Y la pasará a esta función cuando la llame!
// El contenedor de Inversión de Control me facilita el usar un patrón de Inyección de Dependencias...
// Y en última instancia, el que me ayuda a respertar el ppo de la Inversión de la Dependencia.
// Y llegamos a que el framework MAS GIGANTOMASTEODONICO que existe en JAVA 8 (posiblemente en la programación) se ha creado para
// ayudarnos a respetar el ppo de la Inversión de la Dependencia.
// Fijaros si esto es importante!
public void procesarPeticion(String palabra, String idioma, SuministradorDeDiccionarios suministrador) { // Inyección de dependencias
//SuministradorDeDiccionarios suministrador = new SuministradorDeDiccionariosEnFicheros() ; // GUAY!
if(suministrador.tienesDiccionarioDe(idioma)) {
Diccionario diccionario = suministrador.getDiccionario(idioma).get();
if(diccionario.existe(palabra)) {
System.out.println("La palabra existe");
Optional<List<String>> significados = diccionario.getSignificados(palabra);
if(significados.isPresent()) {
System.out.println("Significados: " + significados.get());
} else {
System.out.println("No hay significados");
}
} else {
System.out.println("La palabra no existe");
}
} else {
System.out.println("No hay diccionario para ese idioma");
}
}
}
```
Este código lleva un truco gordo... ni compila!
Un triste fichero... 4 líneas... Empaquetado en su propio jar-> diccionarios-app.jar

## API de comunicación entre ellos
    ```java
    package com.diccionarios;
    public interface Diccionario {
        boolean existe(String palabra);
        Optional<List<String>> getSignificados(String palabra);
        // Qué devuelve está función... alguien lo sabe? Ni de coña... es ambigua!
        // "manzana" en idioma "ES": Lista de significados: ["Fruta del manzano"]
        // "archilococo" en "ES"???? 
        // - Lista vacia    \
        // - null           / NPI... Al código a ver que hace.. o a la docu... En serio??? Año 2024 y así estamos?
        // Optional sirve para romper la ambigüedad... O te devuelvo un optional (una cajita) con una lista dentro)....
        // O te devuelvo un optional vacío... una cajita vacia sin anda dentro. En cualquier caso, siempre te devolveré una cajita.
        // Desde Java 1.8 etá FATAL por convenio que una función devuelva null.
    }
    public interface SuministradorDeDiccionarios {
        boolean tienesDiccionarioDe(String idioma);
        Optional<Diccionario> getDiccionario(String idioma); // si le pido un diccionario del idioma de los elfos del bosque
    }
    ```
    2 tristes ficheros.. 4 líneas... Empaquetados en su propio jar-> diccionarios-api.jar
    Estas interfaces tienen una megacagada! Hay 2 funciones DESASTROSAS AHI! El SONAR me las escupiría a la cara!
    Desde Java 1.8: getSignificados y getDiccionario tendrían una definición NO DESEABLE en ABSOLUTO.
    Son ambiguas... NADIE es capaz de decirme cómo se comportan esas funciones.

## Backend: App/Librería que permite operar con diccionarios.. y preguntarle por palabritas y esas cosas...
Le importa el uso que se va a hacer del él? En nada... él solo debe cumplir con el API... el resto se la trae al peiro!

    ```java
    package com.diccionarios.ficheros;
    import com.diccionarios.Diccionario;
    import com.diccionarios.SuministradorDeDiccionarios;
    public class DiccionarioEnFichero implements Diccionario {
        // Implementación de las funciones de las interfaces
    }
    public class SuministradorDeDiccionariosEnFicheros implements SuministradorDeDiccionarios {
        // Implementación de las funciones de las interfaces
    }
    ```
A un .jar: diccionarios-ficheros.jar
Es mi implementación... quizás mañana monte diccionario-bbdd.jar

    AppCONSOLA > API < ImplementaciónFICHEROS



FUERA !
|                       ^
+-----------------------+   Y aquí es donde la he cagado.

        Si el día de mañana cambiase la implementación de ficheros... o la quitase para poner una que trabajase con palabras definidas en una BBDD... tendría que tocar el código del proyecto de la app de consola... y eso es una RUINA!
        En serio? Vaya mantenibilidad !!!


---

Java soporta per se, varios paradigmas de programación (formas distintas de escribir código):
- Lenguaje Imperativo       Cuando doy instrucciones a la computadora que deben procesarse en orden.
  A veces queremos romper ese order: Nos salen las palabras de control de flujo típicas de los lenguajes imperativos: if, else, for, while, switch, etc.  EVIDENTEMENTE JAVA SOPORTA ESTE PARADIGMA.
- Procedural                Cuando el lenguaje me permite definir funciones/procedimientos/métodos/subrutinas...
  E invocarlos posteriormente.
  EVIENDENTEMENTE JAVA SOPORTA ESTE PARADIGMA.
- Funcional                 Se soporta en JAVA desde 1.8.
  Es cuando el lenguaje nos permite que una variable apunte a una función.
  Y posteriormente ejecutar esa función a través de la variable.
  El concepto es simple.
  Las implicaciones enormes... y con ellas mi cabeza vuela (EXPLOTA !!!!!)
  Desde el momento que puedo hacer eso ^^^^ soy capaz de definir funciones que acepten funciones como argumentos.
  O crear funciones que devuelvan funciones como resultado.
  Esto es una locura... pero muy potente. Nos encanta.
  El API de JAVA está migrando hacía programación funcional. Cada vez más y más funciones propias del API de JAVA nos obligan a trabajar con ese paradigma.
  Veremos algún ejemplo en la formación.
- Orientado a Objetos       Cuando el lenguaje me permite definir mis propios tipos de datos ... con sus características y
  comportamientos.
  Características         funciones
  Date    Dia, Mes, Año           .caeEnBisisesto()
  Usuario Nombre, Apellidos       .eresMayorDeEdad()
  EVIDENTEMENTE JAVA SOPORTA ESTE PARADIGMA.

Hay otro paradigma que nos encanta... NOS APASIONA:
- Declarativo

Estamos muy acostumbrados a paradigma imperativo... PERO ES UNA MIERDA QUE TE CAGAS !!!! DA ASCO !!!!
Esto de los paradigmas... que parece sacado de una novela friki... es algo que los humanos también tenemos en nuestro lenguaje natural (ESPAÑOL)

    FELIPE!, pon una silla debajo de la ventana!    IMPERATIVO = RUINA !!!!
                                                    Al usar lenguaje imperativo nos olvidamos de lo que queremos conseguir... centrándonos en lo que debemos hacer (realmente nosotros no... Felipe = computadora)

¿Qué pasa si hay un mueble debajo de la ventana y no entra la silla? Qué dice Felipe? Que es un tio bien mandao...y colaborador... aunque un poquito cortito!
FELIPE, SI (IF) hay algo que no sea una silla debajo de la ventana,
QUITALO (IMPERATIVO)
FELIPE... Si no hay una silla debajo de la ventana,
Felipe, IF no hay sillas,
VETE AL IKEA A POR UNA !!!
FELIPE!, pon una silla debajo de la ventana! -> ERROR ThrowException... 404, Return code 127!!! OSTION QUE TE CAGAS !

    Y entonces empieza la fiesta de los lenguajes imperativos....

Una alternativa a eso, es el lenguaje DECLARATIVO:

    Felipe, Debajo de la ventana ha de haber una silla. (DECLARATIVO)
    No le doy órdenes a Felipe... SOLO LE DIGO LO QUE QUIERO CONSEGUIR.
        DELEGO EN FELIPE LA RESPONSABILIDAD DE CONSEGUIRLO.
        Felipe: es que ya hay una silla... Felipe! A mi que me cuentas!
        Felipe: es que no hay silla... Felipe! A mi que me cuentas! BÚSCATE LA VIDA !

        Haz lo que quieras... que cuando acabes, allí tiene que haber una silla debajo de la ventana.

SPRING habla LENGUAJE DECLARATIVO.
Yo le voy a decir a Spring:
- Quiero una aplicación.
- Quiero un proceso ETL.
- Y quiero que los datos los saques de una BBDD
- Ah! Y quiero que los datos los metas en un fichero CSV.
- Ah! Y quiero que valides los dnis de las personas que vienen...
- Ah! Y quiero que si los DNIs están mal... que los quites
- Ah! Y quiero que cuando empieces me mandes un email.
- Ah! Y quiero que cuando acabes me mandes otro email.

Yo voy explicándole a Spring, cómo quiero que MONTE MI APP... Porque quién va a montar mi app... y a ejecutarla es Spring... no yo!
No voy a dar flujo en mi programa. El flujo de mi app le provee el framework (Spring) = CONTENEDOR DE INVERSION DE CONTROL.
Como intente buscar el flujo en mi código VOY JODIDO... y nos volvemos locos cuando empezamos con Spring... porque vamos corriendo a buscar / escribir ese flujo... y no lo encontramos... porque no está en nuestro código... está en el framework.

Mi programa (lo que se ejecuta: función main) va a tener 1 línea de código:
SpringApplication.run(MiApp::class.java, args)

    Spring: Ejecuta mi programa. <<<<  Esta es la única orden que le voy a dar a Spring. Te delego a ti el marrón!
            De ahora en adelante es TU RESPONSABILIDAD que mi app funciones y se ejecute correctamente.

Trabajando con un lenguaje IMPERATIVO... De cara a montar una ETL, escribiría un programa del tipo:
PASO 0: Envía un email: "Comienzo de la ETL"
PASO1: Conecta a la BBDD
PASO2: Lee los datos
PASO3: Para cada persona,         BUCLE = FOR
SUBPASO1: Valida el DNI
SUBPASO2: Si el DNI no es válido, descarta la persona                       IF
SUBPASO3: Si el DNI es válido, escribe la persona en el fichero CSV         ELSE
PASO4: Envía un email: "Fin de la ETL"

Y esto es lo que NO QUEREMOS !

Mejor le pido a Spring que monte él la app..., pero que :
- Quiero un proceso ETL.
- Ah! Y quiero que cuando empieces me mandes un email.
- Ah! Y quiero que valides los dnis de las personas que vienen...
- Ah! Y quiero que si los DNIs están mal... que los quites
- Ah! Y quiero que cuando acabes me mandes otro email.
- Ah! Y quiero que los datos los metas en un fichero CSV.
- Y quiero que los datos los saques de una BBDD
  Sólo le expongo cómo quiero mi app.
  Importaría si le digo lo primero o lo último que "Y quiero que cuando empieces me mandes un email." ??? NO

SPRING DA COHERENCIA A MI APP... IMPLEMENTA EL FLUJO... NO YO.
---

# Spring Batch

Librería de Spring para el procesamiento de datos en lotes (en batch).
Básicamente lo usamos para el desarrollo de ETLs.

ETL: Pequeños scripts de procesamiento de datos que se ejecutan en lotes.
ETL = Extract, Transform, Load

Aunque hay mucha variedad de procesos ETL que podemos montar:
- ETL
- ELT
- ETLT
- TEL
- TELT
  Dependiendo de en qué punto inicie la carga y de donde haga las transformaciones, hablaremos de un tipo de proceso u otro.


---

# Bean en Spring

En Spring está el concepto de Bean... que es un objeto (instancia de una clase) que Spring gestiona por mi.
Yo puedo definir beans de muchas formas.

Cómo le pido yo a Spring que se encargue él de gestionar una instancia de mi código (que él genera las instancias que hagan falta)... y las anote(guarde) para inytectarlas según otras partes de mi programa las soliciten? 2 opciones:

La más sencilla y que usaremos siempre que sea posible es marcando esa clase con la anotación `Component`

    ```java
    @Component // @Service, @Repository, @Controller, @RestController, @Job, @Step, @Tasklet, etc
    public class MiClase {
        // Código de la clase
    }
    ```

Hay más anotaciones disponibles en Spring para definir componentes de una aplicación.
Son subtipos de componentes: Service, Repository, Controller, RestController, Job, Step, Tasklet, etc.
Todos esos son ante todo @Component... pero con una semántica más concreta.
Simplemente le indican a Spring / y al desarrollador el uso previsto que voy a hacer de esa instancia.
SpringBatch, esa librería que vamos a usar tiene sus propios TIPOS DE COMPONENTES... que son subtipos de @Component... y que le indican a Spring que esa instancia se va a usar en un proceso de ETL.



Cuando no podré usar la primera opción?
Cuando la clase de la que quiero que Spring genere una instancia NO SEA UNA CLASE MIA (creada por mi)...
En ese caso, podré poner encima de la definición de la clase... en el fichero de código fuente @Component o variante? NO
En ese caso, tendré que usar la segunda opción.
vvvv

La segunda, y que solo usaremos cuando la primera no sea posible:

    ```java
    @Configuration // Spring solo ejecutará las funciones anotadas con @Bean en clases anotadas con @Configuration
    public class MiConfiguracion {
        @Bean
        public MiClase/*1*/ miClase() { // El nombre del método da igual... 
                                   // yo no voy a llamar a este método en ningún sitio... Le podría llamar FEDERICO
            return new MiClase(); // Devolveré una instancia de lo que haya definido que devuelve mi función: /*1*/
        }
        // Spring llamará a esta función. Y la instancia que se devuelva la guardará. De forma que si 
        // desde este momento alguien pide una instancia de MiClase, Spring le devolverá la que se ha guardado aquí.
    }
    ```

    Por defecto, Spring utiliza esa instancia como si fuera un Singleton... Es algo que puedo cambiar.. y que cada vez que se solicite una inyección... una instancia de esa clase, se genere una nueva... Pero no es lo habitual.

    Me evito implementar el típico patrón singleton:

    ```java
    public class MiClase { // Gracias a Spring pasó a mejor vida este tipo de código
        private static volatile MiClase instancia = null;
        private MiClase() {}
        public static MiClase getInstancia() {
            if(instancia == null) {
                synchronized(MiClase.class) {
                    if(instancia == null) {
                        instancia = new MiClase();
                    }
                }
            }
            return instancia;
        }
    }
    ```

Como le indico a Spring que necesito una instancia de un objeto?
Que quiero que inyecte una dependencia (uno de esos BEANS)

```java
@Component
class MiClase {
    // Código de la clase
}

class MiOtraClase {
    @Autowired // TOTALMENTE DESACONSEJADO HOY EN DIA... NO SE HACE NI DE COÑA !
    private MiClase miClase; // Spring inyectará una instancia de MiClase en esta variable

    public MiOtraClase() { 
    }
}


```

Cómo desde fuera desde una clase se le puede asignar un valor a una variable PRIVADA ?????
Spring debe hacer algo como:
```java
    MiClase miClaseBean = new MiClase();
    MiOtraClase miOtraClase = new MiOtraClase();
    miOtraClase.miClase = miClaseBean; // Esto NO LO PUEDE HACER SPRING NI YO... La variable es privada...
```
Entonces cómo lo hace Spring: Reflections API. Esto permite acceder a los datos que hay en RAM...
Saltándose las restricciones de acceso que impone JAVA. Es una puerta trasera de un calibre pasmoso.
De hecho en JAVA 1.9 se ha restringido el uso de Reflections API... porque se ha visto que se usaba para hacer cosas que no debían hacerse. Y se ha desactivado por defecto en la JVM.