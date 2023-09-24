# BE-I---1er-Parcial

Integrantes de este proyecto:
- Rotger, Florencia
- Ocampo, Maximiliano

Justificacion de la implementacion de circuit breaker en el ms de Catalog:

En la clase CatalogService, se implementó un circuit breaker para proteger el servicio de fallas en los microservicios de movie y serie.

El servicio Catalog es el más utilizado del sistema. .
Los microservicios de movie y serie son servicios externos. Por lo tanto, están sujetos a fallas o sobrecargas que pueden afectar el servicio Catalog.
En un escenario hipotético en el que el circuit breaker podría ser útil:
El microservicio de movie experimenta una falla repentina.
El circuit breaker del servicio Catalog se activa y evita que el servicio continúe realizando llamadas al microservicio de movie.
El servicio Catalog continúa funcionando con normalidad, utilizando el método fallback para proporcionar información sobre las películas.
Una vez que el microservicio de películas se recupera de la falla, el circuit breaker del servicio Catalog se desactiva y el servicio vuelve a llamar al microservicio de películas de forma normal.#   F i n a l B E - m i c r o s e r v i c i o s  
 #   F i n a l B E - m i c r o s e r v i c i o s  
 