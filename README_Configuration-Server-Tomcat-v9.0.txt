- Clonar el proyecto en el espacio de trabajo de Eclipse

- Vaya a "Ayuda" > "Instalar nuevo software", haga clic en "Agregar...", dé un nombre e inserte el enlace https://donwload.eclipse.org/releases/kepler y desplácese hacia abajo hasta "Web, XML, Java EE y OSGi Enterprise Development".

- Seleccione "JST Server Adapter" y "JST Server Adapters Extensions", luego haga clic en Finish

- A continuación, vaya a "Ventana" > "Mostrar vista" > "Otros..." y busque "Servidores". En la ventana que se abre en la parte inferior de la pantalla, haga clic en "Add a new server" (Añadir un nuevo servidor), desplácese por la lista de servidores Apache y seleccione "Tomcat v9.0". La carpeta de instalación se encuentra en la raíz del proyecto Bibliotecapp, es la carpeta "apache-tomcat-9.0.27" y termina la instalación.

- A continuación, vaya a Ayuda > Búsqueda en el mercado "Maven (Java EE) Integration for Eclipse WTP", búsquelo e instálelo.

- Finalmente, vuelva a la vista "Servidores", haga clic con el botón derecho del ratón sobre el servidor Tomcat > "Añadir y quitar..." y añada la aplicación "Aplicación". A continuación, ejecute la aplicación con el servidor.
