Sistema de Gestión de Pedidos de Farmacia
Descripción del Proyecto
Este es un sistema de gestión de pedidos para una farmacia, desarrollado en Java con una interfaz gráfica de usuario (GUI) utilizando Swing. La aplicación permite a los usuarios crear, visualizar y gestionar pedidos de medicamentos de manera sencilla e intuitiva.
Características Principales

Registro de pedidos de medicamentos
Selección de tipos de medicamentos
Múltiples distribuidores
Gestión de sucursales
Validación de datos de entrada
Visualización de pedidos confirmados

Requisitos del Sistema

Java Development Kit (JDK) 8 o superior
IDE de Java (NetBeans, Eclipse, IntelliJ IDEA)

Estructura del Proyecto
Copypharmacy-order-management/
│
├── src/
│   ├── ui/
│   │   └── FormularioPedido.java
│   ├── model/
│   │   └── Pedido.java
│   └── controller/
│       └── Validador.java
│
└── README.md
Clases Principales
FormularioPedido
Clase principal que gestiona la interfaz de usuario y la lógica de pedidos.
Pedido
Clase de modelo que representa un pedido de medicamento.
Validador
Clase de controlador para validaciones adicionales (no implementada en el código proporcionado).
Funcionalidades

Formulario de Pedido

Ingreso de nombre de medicamento
Selección de tipo de medicamento
Especificación de cantidad
Selección de distribuidor
Selección de sucursal


Gestión de Pedidos

Confirmación de pedidos
Visualización de pedidos confirmados
Opción de procesar pedidos



Instrucciones de Instalación

Clonar el repositorio

bashCopygit clone https://github.com/CRISTIAN7712/farmaciaDesktop.git

Abrir el proyecto en tu IDE de Java
Compilar y ejecutar la clase FormularioPedido

Uso de la Aplicación

Completar el formulario de pedido
Seleccionar todas las opciones requeridas
Hacer clic en "Confirmar" para registrar el pedido
Revisar los pedidos en la pestaña "Pedidos Confirmados"

Validaciones

Nombre del medicamento: Solo caracteres alfanuméricos
Cantidad: Número entero positivo
Distribuidor: Selección obligatoria
Sucursal: Al menos una sucursal debe ser seleccionada

Capturas de Pantalla
Incluir capturas de pantalla de la aplicación (opcional)
Contribuciones
Las contribuciones son bienvenidas. Por favor, sigue estos pasos:

Hacer un fork del proyecto
Crear una rama para tu característica (git checkout -b feature/nueva-caracteristica)
Confirmar tus cambios (git commit -m 'Añadir nueva característica')
Empujar a la rama (git push origin feature/nueva-caracteristica)
Abrir un Pull Request

Nota: Este proyecto es un ejemplo educativo y puede requerir adaptaciones para uso en producción.