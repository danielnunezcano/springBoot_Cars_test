# springBoot_Cars_test

El proyecto se compone de tres servicios REST:

   - El servicio Para buscar precio del coche que se necesita pasar id (int) y fecha (date "yyyy-MM-dd")

    - Ejemplo: coches/search?date=2021-07-23&id=1

  - El servicio Para buscar coche con filtro que se necesita pasar filtro (String)

    - Ejemplo: coches?filter=id eq 2

  - El servicio Para buscar descargar los coches en un xls que no necesita parametros

    - Ejemplo: coches/xls
