BFF Finanzas ABC

Este proyecto implementa BFF para la empresa Finanzas ABC

1.- Estrategias implementadas de BFF
se ha seleccionado BFF por tipo de cliente, debido a la necesidad de entregar datos (payloads) diferentes
* Canal Web: datos completos que incluyen ID y fecha de transaccion
* Canal Movil: Datos mas ligeros y transformados, para ahorrar ancho de banda
* Canal ATM: filtros de seguridad estrictos para operaciones criticas de saldo

2.- Estructura del proyecto
* controller/BffController.java : centraliza los endponts por cada canal.
* service/AccountService.java : contiene la logica del negocio y la transformacion de datos cuando es necesario.
*  dto/ : clases de transferencia de datos, segun las necesidades de cada cliente
* config/SecurityConfig.java : configuracion de seguridad por roles de usuario.

3.- Seguridad y Autorizacion
Se implemento Spring Security con una pilitica de control de acceso basado en  roles
-- Cada canal usa un rol especifico role_web. role_atm, role_mobile
Esto garantiza que un usuario de un rol especifico, no logre acceder a un canal distinto de informacion, lo que cumple el minimo privilegio

4.- Para la prueba de cada endpoint se usa en Postman, la autprizacion  Basic Auth con las credenciales aqui descritas

/ canal /      usuario      /   contraseña   /    rol       /

/ web   /   cliente_web     /   web123       /  ROLE_WEB    /

/ movil /   cliente_movil   /   movil123     /  ROLE_MOVIL  /

/ atm   /   cajero01        /   atm23        /  ROLE_ATM    /

5.- Configuracion de seguridad (semana5)
* protocolo de transporte: se implementa HTTPS en el puerto 8443, utilizando certificado SSL para segurar el cifrado de los datos bancarios
*  autenticacion: se implementa mediante httpBasic
* Autotizacion: control baso en roles, igual que en semana 4

