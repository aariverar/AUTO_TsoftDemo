# language: es
  Característica: Comprar online Paralela

    Esquema del escenario:  Carlos quiere comprar en opencart.abstracta.us

      Dado Carlos que quiere comprar en OpenCart "<Caso_Prueba>"
      Y Johnny que quiere comprar en OpenCart "<Caso_Prueba>"
      Cuando Carlos se identifica en la pagina
      Y Johnny se identifica en la pagina
      Y Carlos compra una laptop
      Y Johnny compra una laptop
      Entonces Carlos puede ver la compra exitosa
      Entonces Johnny puede ver la compra exitosa

      Ejemplos:
        | Caso_Prueba |
        | 1           |