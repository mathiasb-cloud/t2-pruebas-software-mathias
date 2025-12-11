Feature: Practice Form - demoqa.com

  Background:
    Given que abro la página del formulario de práctica

  @happyPath
  Scenario: Escenario 1 - Registro exitoso con campos obligatorios
    When ingreso el nombre "Luis" y el apellido " Lopez"
    And escribo el correo "luis.lopez@example.com"
    And selecciono el género "Male"
    And escribo el número de celular "9234567890"
    And selecciono el hobby "Sports"
    And escribo la dirección actual "Av. Cahuide 123"
    And envío el formulario
    Then debería visualizar el modal de confirmación
    And el título del modal debería ser "Thanks for submitting the form"
    And el modal debería contener el nombre "Luis Lopez"

  Scenario: Escenario 2 - Registro con campos adicionales
    When ingreso el nombre "Juana" y el apellido "Cuba"
    And escribo el correo "juana.cuba@example.com"
    And selecciono el género "Female"
    And escribo el número de celular "9342345678"
    And selecciono el hobby "Reading"
    And escribo la dirección actual "Jr. Los Laureles 496"
    And envío el formulario
    Then debería visualizar el modal de confirmación
    And el título del modal debería ser "Thanks for submitting the form"
    And el modal debería contener el nombre "Juana Cuba"

  Scenario: Escenario 3 - Validación de formulario incompleto
    When ingreso el nombre "" y el apellido ""
    And dejo el correo vacío
    And no selecciono ningún género
    And escribo el número de celular ""
    And no selecciono ningún hobby
    And escribo la dirección actual ""
    And envío el formulario
    Then los campos obligatorios deberían mostrar mensajes de validación
