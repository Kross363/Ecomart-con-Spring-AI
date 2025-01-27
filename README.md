# Ecomart: Aplicación hecha con Spirng AI

## **Descripción General**

Ecomart es una aplicación backend construida con **Spring Boot** que aprovecha modelos de inteligencia artificial para proporcionar dos funcionalidades principales:

1. **Categorización de Productos:** Clasifica productos según una lista predefinida de categorías.
2. **Generación Dinámica:**
   - Genera listas de productos ecológicos utilizando IA.
   - Genera imágenes personalizadas basadas en descripciones (prompts).

Esta aplicación está diseñada para ser un componente en plataformas de comercio electrónico, herramientas de diseño o aplicaciones sostenibles que promuevan productos ecológicos.

---

## **Requisitos del Sistema**

- **Java:** Versión 17 o superior.
- **Spring Boot:** Versión 3.0.
- **Dependencias adicionales:**
  - Spring AI.
  - JTokkit para conteo de tokens.
  - Biblioteca necesaria para generar imágenes (configurada en `ImageModel`).
- **IDE recomendado:** IntelliJ IDEA o Eclipse.
- **Base de datos:** (No necesaria en esta versión, pero puede integrarse según necesidades futuras).

---

## **Estructura del Proyecto**

El proyecto está organizado de la siguiente manera:

```
com.example.ecomart
├── Controller
│   ├── CategorizadorDeProductosController.java
│   ├── GeneradorDeImagenesController.java
│   └── GeneradorDeProductosController.java
└── Application
    └── EcomartApplication.java
```

### **Controladores Principales**

#### **1. CategorizadorDeProductosController**

Este controlador permite categorizar productos en base a una lista predefinida de categorías:

- **Endpoint:** `GET /categorizador`
- **Parámetro de entrada:** `producto` (nombre del producto a categorizar).
- **Categorías disponibles:**
  - Higiene personal
  - Electrónicos
  - Deportes
  - Otros
- **Ejemplo de Uso:**
  ```http
  GET http://localhost:8080/categorizador?producto=cepillo%20de%20dientes
  ```
  **Respuesta:**
  ```
  Higiene personal
  ```

#### **2. GeneradorDeImagenesController**

Este controlador genera una imagen basada en un texto descriptivo (prompt):

- **Endpoint:** `GET /imagen`
- **Parámetro de entrada:** `prompt` (descripción de la imagen).
- **Opciones preconfiguradas:**
  - Altura: 1024 px.
  - Ancho: 1024 px.
- **Ejemplo de Uso:**
  ```http
  GET http://localhost:8080/imagen?prompt=cepillo%20de%20dientes%20de%20bambú
  ```
  **Respuesta:**
  ```json
  "https://generated-image-url.com/image1234.png"
  ```

#### **3. GeneradorDeProductosController**

Este controlador utiliza inteligencia artificial para generar una lista de productos ecológicos:

- **Endpoint:** `GET /generador`
- **Ejemplo de Uso:**
  ```http
  GET http://localhost:8080/generador
  ```
  **Respuesta esperada:**
  ```
  1. Bolsas reutilizables de tela
  2. Cepillos de dientes de bambú
  3. Botellas de agua de acero inoxidable
  4. Productos de limpieza biodegradables
  5. Pajillas reutilizables de acero inoxidable
  ```

---

## **Configuración Adicional**

### **1. Configuración del Modelo de IA**

El modelo utilizado (como `gpt-4o-mini`) está configurado en el archivo `application.properties`.

**Ejemplo de configuración:**

```properties
spring.ai.model.default=gpt-4o-mini
logging.level.org.springframework.ai.chat.client.advisor=DEBUG
```

### **2. Dependencias del Proyecto**

Asegúrate de incluir las siguientes dependencias en tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>com.knuddels</groupId>
    <artifactId>jtokkit</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## **Posibles Mejoras Futuras**

1. **Personalización de Categorización:**
   - Permitir al usuario definir nuevas categorías o modificar las existentes.
2. **Formato de Respuesta Estructurado:**
   - Devolver respuestas en formato JSON para facilitar la integración con frontends.
3. **Internacionalización:**
   - Soporte para múltiples idiomas en las respuestas de los controladores.
4. **Almacenamiento de Resultados:**
   - Registrar las URLs generadas en una base de datos para su reutilización.
5. **Autenticación y Seguridad:**
   - Implementar autenticación para controlar el acceso a los endpoints.

---
