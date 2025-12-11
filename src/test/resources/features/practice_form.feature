Feature: Practice Form - demoqa.com

  Background:
    Given que abro la página del formulario de práctica

  @happyPath
  Scenario: Escenario 1 - Registro exitoso con campos obligatorios
    When ingreso el nombre "Juan" y el apellido "Perez"
    And escribo el correo "juan.perez@example.com"
    And selecciono el género "Masculino"
    And escribo el número de celular "9876543210"
    And selecciono el hobby "Sports"
    And escribo la dirección actual "Av. Principal 123"
    And envío el formulario
    Then debería visualizar el modal de confirmación
    And el título del modal debería ser "Gracias por enviar el formulario"
    And el modal debería contener el nombre "Juan Perez"

  Scenario: Escenario 2 - Registro con campos adicionales
    When ingreso el nombre "María" y el apellido "López"
    And escribo el correo "maria.lopez@example.com"
    And selecciono el género "Femenino"
    And escribo el número de celular "9876501234"
    And selecciono el hobby "Reading"
    And escribo la dirección actual "Calle Secundaria 456"
    And envío el formulario
    Then debería visualizar el modal de confirmación
    And el título del modal debería ser "Gracias por enviar el formulario"
    And el modal debería contener el nombre "María López"

  Scenario: Escenario 3 - Validación de formulario incompleto
    When ingreso el nombre "" y el apellido ""
    And dejo el correo vacío
    And no selecciono ningún género
    And escribo el número de celular ""
    And no selecciono ningún hobby
    And escribo la dirección actual ""
    And envío el formulario
    Then los campos obligatorios deberían mostrar mensajes de validación
