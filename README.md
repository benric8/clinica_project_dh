# Documentación del Código Java

Este archivo proporciona una explicación detallada del código del proyecto Clínica Odontológica.

## Contenido

1. [Descripción General](#descripción-general)
2. [Modelos](#modelos)
3. [Campos de la Clase](#campos-de-la-clase)
4. [Dependencias](#dependencias)


## Descripción General

Este proyecto representara la funcionalidad de un clinica odontologica.

## Modelos

```java
    @Getter @Setter
    @AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
    public class Paciente {
        Integer id;
        String nombre;
        String apellido;
        String cedula;
        LocalDate fechaIngreso;
        Domicilio domicilio;
    }
```

Las siguientes anotaciones de Lombok se utilizan en la clase `Paciente`:

### `@Getter`

- **Descripción**: Genera automáticamente métodos `getter` para todos los campos de la clase.
- **Efecto**: Proporciona métodos públicos para acceder a los valores de los campos privados.

### `@Setter`

- **Descripción**: Genera automáticamente métodos `setter` para todos los campos de la clase.
- **Efecto**: Proporciona métodos públicos para modificar los valores de los campos privados.

### `@AllArgsConstructor`

- **Descripción**: Genera un constructor con un parámetro para cada campo en la clase.
- **Efecto**: Facilita la creación de objetos `Paciente` con todos los campos inicializados en el momento de la creación del objeto.

### `@FieldDefaults(level = AccessLevel.PRIVATE)`

- **Descripción**: Establece el nivel de acceso predeterminado para todos los campos en la clase como `private`.
- **Efecto**: Todos los campos de la clase serán privados a menos que se indique lo contrario explícitamente.

