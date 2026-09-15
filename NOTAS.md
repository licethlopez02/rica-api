# Notas sobre Lenguaje Ubicuo

## `InvestigadorController`, `InvestigadorRequest` e `InvestigadorResponse`

- No hay campos ni métodos llamados `data`, `info`, `value` o `item` que necesiten un nombre del dominio.
- Los campos tienen nombres concretos y comprensibles para este contexto: `nombreCompleto`, `correoInstitucional` y `grupoInvestigacion`.
- `request` y `response` aparecen como nombres técnicos de DTO y parámetros, no como conceptos del dominio. `listar`, `buscarPorId` y `registrar` son operaciones suficientemente claras por el controlador y el tipo `InvestigadorResponse` que devuelven; no requieren renombrarse para resolver una ambigüedad.

## Términos institucionales

`correoInstitucional` y `grupoInvestigacion` son términos que una persona de la Facultad reconocería directamente. Por eso se conservan: el Lenguaje Ubicuo no exige cambiar nombres que ya expresan correctamente conceptos compartidos por el dominio; también consiste en confirmar y dejar constancia de que esos nombres son adecuados.

## `Publicacion.investigadorCorreo`

`investigadorCorreo` es más preciso que `refId` porque identifica explícitamente que la publicación está asociada al correo institucional del investigador, que es el dato usado para validar y consultar esa relación; `refId` solo indica una referencia genérica y oculta su significado dentro del dominio.

## ADR-001: Límite del agregado `Investigador`

### Estado

Aceptado.

### Contexto

El modelo actual tiene una entidad `Investigador` y otra entidad `Publicacion`. Ambos conceptos forman parte del mismo dominio académico, pero no todos los datos relacionados deben vivir dentro del mismo agregado.

La intuición técnica del proyecto ya apunta a la respuesta: el agregado raíz es `Investigador`, porque es la única clase anotada con `@Entity` dentro del paquete del agregado. Sin embargo, esa decisión no está escrita y conviene dejarla explícita para evitar que alguien más adelante introduzca acoplamientos indebidos.

### Decisión

- La raíz del agregado es `Investigador`.
- El agregado `Investigador` contiene únicamente los datos propios del investigador:
  - `id`
  - `nombreCompleto`
  - `correoInstitucional` (modelado como Value Object)
  - `grupoInvestigacion`
- Las `Publicacion` no forman parte del agregado `Investigador`.
- La relación entre ambos se representa por el correo institucional del investigador, pero la publicación es una entidad independiente, con vida propia y evolución desacoplada.

### ¿Por qué no incluir `List<Publicacion>` dentro de `Investigador`?

Porque rompería el límite del agregado:

- un investigador puede tener muchas publicaciones, y esa colección puede crecer mucho;
- si se guarda dentro del agregado, se dispararía una carga y una transacción desproporcionadas cada vez que se actualiza un investigador;
- dos transacciones concurrentes sobre el mismo investigador podrían pisarse la lista de publicaciones de otra persona;
- las publicaciones dejarían de poder evolucionar, desplegarse o cambiar de forma independiente.

Esto va en línea con la regla de agregados pequeños: un agregado debe ser pequeño, con un único punto de consistencia claro y con datos que cambian frecuentemente y de manera relacionada no mezclados con entidades de vida independiente.

### Consecuencias

- El agregado `Investigador` es estable y pequeño.
- Las publicaciones se gestionan como un concepto independiente, con su propio ciclo de cambios.
- Se reduce el riesgo de transacciones gigantes y de contención innecesaria.
- La evolución del dominio queda más clara: el investigador y sus publicaciones pueden cambiar por caminos distintos sin acoplarse artificialmente.