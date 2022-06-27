### Ejercicio prácito - Java Spring Boot - 2022-Q2

## Objetivo
Crear una API Rest para administrar los repositorios de los desarrolladores BackEnd agrupados por tribus.

## Modelo
![](./modelo.jpg "Repositories model")

## Base de datos en memoria
Al cargar la aplicación, se inicializará los datos de la base inicial, la cual servirá como insumo para desarrollar el ejercicio. 

---

## Indicaciones Generales

- Crear una rama con el nombre del participante: Ejemplo: Q22022-lincango-baquero-fernando-daniel.
- Subir cambios a sus ramas una vez terminado el tiempo establecido.
    - Al finalizar el tiempo el repositorio no permitirá nuevos cambios.
- Elaborar al menos una prueba de integración.
- Implementar buenas prácticas de programación.
- Prueba de integración: Cargar el contexto de la aplicación.
- Pruebas unitarias: Comportamientos

---
## Ejercicio 1: Administración de repositorios 

<p><strong>Con el objetivo</strong> de poder administrar las organizaciones dentro de mi plataforma<br>
<strong>Como</strong> administrador de la plataforma<br>
<strong>Quiero</strong> tener una API que me permita crear, editar, obtener y eliminar</p>

#### Criterios de aceptación

- Crear organización:

<p><strong>Dado</strong> que envío los datos de una organización<br>
<strong>Cuando</strong> consumo el servicio de creación<br>
<strong>Entonces</strong> se debe crear la organización</p>

- Editar organización
<p><strong>Dado</strong> que modifico los datos de una organización<br>
<strong>Cuando</strong> consumo el servicio de actualización<br>
<strong>Entonces</strong> se debe actualizar la organización</p>

- Obtener organizaciones
<p><strong>Dado</strong> que quiero obtener una lista de organizaciones<br>
<strong>Cuando</strong> consumo servicio para obtener organizaciones<br>
<strong>Entonces</strong> se debe obtener un listado de organizaciones</p>

- Eliminar organizaciones
<p><strong>Dado</strong> que quiero eliminar una organización<br>
<strong>Cuando</strong> consumo servicio para eliminar una organización<br>
<strong>Entonces</strong> se debe obtener un listado de organizaciones</p>

#### Tareas

- Crear un endpoint para crear una organización.
- Crear un endpoint para actualizar una organización.
- Crear un endpoint para obtener una organización por ID.
- Crear un endpoint para eliminar una organización.

#### Expectativas técnicas del ejercicio

- Diseñar una API Rest con las operaciones básicas (CRUD).
- Arquitectura del proyecto.
- Buenas prácticas.
- Mapeado de entidad a un DTO (Data Transfer Object).
- Micro commits. 

---

### Ejercicio 2: Servicio para obtener las métricas de un repositorio

<p><strong>Con</strong> el objetivo de obtener las métricas de un repositorio<br>
<strong>Como</strong> administrador de la plataforma<br>
<strong>Quiero</strong> tener servicio que me permita obtener los repositorios de una tribu</p>

#### Criterios de aceptación

- Criterio 1: Obtener métricas de repositorios por tribu:

<p><strong>Dado</strong> que envío el identificador de una tribu<br>
<strong>Cuando</strong> consumo el servicio para obtener los repositorios<br>
<strong>Entonces</strong> me retornará el detalle de las métricas de los repositorios creados este año <br>
Y que se encuentren habilitados (state: ENABLE) <br>
Y que su cobertura sea superior a 75%</p>

- Criterio 2: Tribu inexistente.

<p><strong>Dado</strong> que envío el identificador de una tribu<br>
<strong>Cuando</strong> consumo el servicio para obtener los repositorios<br>
Y la tribu no existe <br>
<strong>Entonces</strong> me retornará el siguiente error: 'La Tribu no se encuentra registrada'</p>

- Criterio 3: Información de verificación.

<p><strong>Dado</strong> que envío el identificador de una tribu<br>
<strong>Cuando</strong> consumo el servicio para obtener los repositorios<br>
Y obtengo el estado de verificación de los repositorios desde API Externa <br>
<strong>Entonces</strong> me retornará una etiqueta en la respuesta indicando un texto en lenguaje natural del estado de verificación actual de cada repositorio</p>

- Criterio 4: Tribu no tiene repositorios que cumplan con la cobertura.

<p><strong>Dado</strong> que envío el identificador de una tribu<br>
<strong>Cuando</strong> consumo el servicio para obtener los repositorios<br>
Y la tribu no tiene repositorios que cumplan con el 75% de cobertura <br>
<strong>Entonces</strong> me retornará el siguiente error: 'La Tribu no tiene repositorios que cumplan con la cobertura necesaria'</p>

#### Tareas

- Crear consulta para filtrar los repositorios por fecha, estado y porcentaje.
- Consumir API Externa para validar códigos de verificación.
- Mapear estructura de respuesta.
- Pruebas unitarias de cada criterio de aceptación.
- Prueba de integración: Cargar el contexto de Spring.

#### Expectativas técnicas del ejercicio

- Consultas en JPQL o Query Nativos.
- Relaciones entre de entidades.
- Control de excepciones.
- Pruebas unitarias por comportamientos.
- Formato de respuesta requerida.
- Uso correcto de enumerados y constantes.

#### Estructura de respuesta

```
{
	"repositories": [
		{
			"id": "1",
			"name": "cd-common-utils",
			"tribe": "Centro Digital",
			"organization": "Banco Pichincha",
			"coverage": "35%",
			"codeSmells": 0,
			"bugs": 0,
			"vulnerabilities": 0,
			"hotspots": 0,
			"verificationState": "Verificado",
			"state":"Habilitado"
		},
		{
			"id": "2",
			"name": "cd-common-text",
			"tribe": "Centro Digital",
			"organization": "Banco Pichincha",
			"coverage": "75%",
			"codeSmells": 1,
			"bugs": 0,
			"vulnerabilities": 2,
			"hotspots": 0,
			"verificationState": "En espera",
			"state":"Archivado"
		}
	]
}
````

---

## API Externa

### Antecedente
El endpoint de tipo GET retorna un arreglo con los identificadores de los repositorios junto con su código de verificación.


Servicio: [Servicio web obtener repositorios verificados](https://253b6042-ec17-4ee8-8d7d-ea9d62805337.mock.pstmn.io/v1/repositories/verified)

### Código de verificación

<ul>
  <li><strong>604:</strong> Verificado</li>
  <li><strong>605:</strong> En espera</li>
  <li><strong>606:</strong> Aprobado</li> 
</ul>


