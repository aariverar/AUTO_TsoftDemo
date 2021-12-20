Feature: Web Demo Zairito

  @test1
  Scenario Outline: Diversos escenarios DEMO

      Given cliente quiere registrarse en Zairito "<Caso_Prueba>"
      When  ingresa a mi cuenta y crear cuenta
      Then  llena el formulario con sus "<Caso_Prueba>"

    Examples:
      | Caso_Prueba |
      | 1     |
