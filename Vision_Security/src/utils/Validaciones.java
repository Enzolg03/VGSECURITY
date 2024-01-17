package utils;

public class Validaciones {
	public static final String NOMBRE="[a-zA-ZáéíóúÁÉÍÓÚÑn\\s0-9\\\\.\\\\-]{1,100}";
	public static final String APELLIDO="[a-zA-ZáéíóúÁÉÍÓÚÑn\\s]{1,100}";
	public static final String MARCA="[a-zA-ZáéíóúÁÉÍÓÚÑn\\s]{1,100}";
	public static final String DESCRIPCION="[a-zA-ZáéíóúÁÉÍÓÚÑn\\s]{1,100}";
	public static final String USUARIO="U[0-9]{3}";
	public static final String DNI="[0-9]{8}";
	
}
