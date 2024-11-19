## Testing E2E y Sonar

Este proyecto forma parte de la asignatura **Ampliación de Ingeniería del Software** del Grado en Ingeniería Informática. Su objetivo es implementar pruebas automáticas de extremo a extremo (E2E) y realizar análisis estático de código con Sonarqube para garantizar la calidad de la aplicación **Nitflex**, que gestiona películas.

### Descripción del Proyecto

La práctica está dividida en dos partes:
- **Parte A: Testing E2E con Selenium**
- **Parte B: Análisis estático de código con Sonarqube**

El objetivo es probar la interfaz web de **Nitflex**, identificar y corregir bugs, y mejorar la calidad del código mediante un análisis detallado.

### Funcionalidades Probadas

#### Parte A: Testing E2E con Selenium
- **Añadir una película:**
  - Verificar que la película se crea correctamente y aparece en la lista principal.
- **Eliminar una película:**
  - Comprobar que aparece el mensaje de borrado y que la película ya no está disponible en la página principal.
- **Identificación y corrección de bugs:**
  - Localizar el fallo reportado por un cliente, crear un test que lo identifique, corregir el código y verificar que el test pase después de la corrección.

#### Parte B: Análisis Estático con Sonarqube
- **Cobertura de pruebas con Jacoco:**
  - Incluir las pruebas unitarias e integración de la práctica anterior para incrementar la cobertura.
- **Clasificación de issues:**
  - Identificar y solucionar bugs, vulnerabilidades y "code smells" reportados por Sonarqube.
  - Marcar como "falsos positivos" aquellos issues que no representen problemas reales.

### Requisitos

- **Independencia de tests:** Cada test debe ser independiente, creando sus propios datos.
- **Modularización:** Separar los tests por su naturaleza (E2E o unitarios/integración).
- **Análisis con Sonarqube:** Revisar y solucionar los issues reportados, mostrando evidencia en un dashboard antes y después de las correcciones.

### Tecnologías Utilizadas

- **Java** para las pruebas y desarrollo.
- **Selenium** para pruebas E2E.
- **Jacoco** para la cobertura de pruebas.
- **Sonarqube** para el análisis estático de código.
- **Maven** como herramienta de construcción y gestión de dependencias.

### Cómo Ejecutarlo

1. **Ejecutar las pruebas E2E:**
   ```bash
   mvn test
   ```
2. **Analizar el código con Sonarqube:**
   - Arranca el servidor de Sonarqube.
   - Ejecuta el análisis desde Maven:
     ```bash
     mvn sonar:sonar
     ```

3. **Ver resultados:**
   - Revisa el dashboard en la interfaz web de Sonarqube para visualizar métricas y coverage.

### Estructura del Proyecto

- `src/main/java`: Código principal de la aplicación.
- `src/test/java`: Código de pruebas, incluyendo pruebas unitarias, de integración y E2E.
- `pom.xml`: Configuración del proyecto Maven.
