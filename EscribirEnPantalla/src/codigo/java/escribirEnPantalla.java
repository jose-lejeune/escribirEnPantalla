package codigo.java;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class escribirEnPantalla {
	
	public static String[] PantallaDeTexto;
	public static final String esquina = "+";
	public static final String comando_linux_quien_soy = "whoami";
	public static final String borde_horizontal = "-";
	public static final String etiqueta_usuario = " - Usuario: ";
	public static final String etiqueta_blog = " Nuestros IDEs: C)odeLite  E)clipse  N)etbeans";
	public static final String espacio = " ";
	public static final String borde_vertical = "|";
	public static final int filas_por_defecto = 40;
	public static final int fila_inicio_blog = 6;
	public static final int fila_fin_blog = 38;
	public static final int columnas_por_defecto = 164;
	public static final String cabecera_instituto = "Instituto Alonso de Avellaneda -- Entornos de Desarrollo";
    public static Scanner miScan = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			/*
			 *  Practica de escritura en pantalla
			 *  Paso 1: borramos el contenido de la pantalla
			 */
			limpiarPantalla();
			inicializarPantallaDeTexto(filas_por_defecto,columnas_por_defecto);
			/*
			 * Fila 1: Cabecera  mas nombre de usuario
			 */
			String whoami = leerComando_SO(comando_linux_quien_soy);
			escribirEnLaPantallaDeTexto(1,2,cabecera_instituto + etiqueta_usuario + whoami);
			escribirEnLaPantallaDeTexto(3,8,etiqueta_blog);
			escribirEnLaPantallaDeTexto(4,8,borde_horizontal.repeat(135));
			escribirLaPantallaDeTexto(filas_por_defecto,columnas_por_defecto);		
		} catch (Exception e) {
			System.out.println (e.getMessage());
		}

	}

	private static void inicializarPantallaDeTexto(int filas,int columnas) {
		int UltimaFila = filas - 1;
		PantallaDeTexto = new String[filas];
		for(int i = 0; i < filas ; i++) {
		  if(i==0) { PantallaDeTexto[i] = esquina + borde_horizontal.repeat(columnas-2) + esquina;}
		  else if(i == UltimaFila) {PantallaDeTexto[i] = esquina + borde_horizontal.repeat(columnas-2) + esquina;}
		  else {PantallaDeTexto[i] = borde_vertical + espacio.repeat(columnas-2) + borde_vertical;}
		}
	}
	private static void inicializarBlogPantallaDeTexto(){
		for(int i = fila_inicio_blog; i < fila_fin_blog ; i++) {
			inicializarFilaDeLaPantallaDeTexto(i,columnas_por_defecto);
		}
	}
	private static void inicializarFilaDeLaPantallaDeTexto(int fila,int columnas) {
		PantallaDeTexto[fila] = borde_vertical + espacio.repeat(columnas-2) + borde_vertical;
	}
	private static void escribirLaPantallaDeTexto(int filas, int columnas) {
		limpiarPantalla();
		for(int i = 0; i < filas ; i++) {
			System.out.println (PantallaDeTexto[i]);
		}
		leerOpcionMenu();
	}
	/**
	 *  Metodo para captura una opcion del menu
	 */
	private static void leerOpcionMenu() {
		System.out.println("Seleccione IDE (C,E,N) o pulse X para salir");
        String sIDE = miScan.nextLine();
        switch(sIDE) {
        	case "c":
        	case "C":
        		codelite();
    		break;
        	case "e":
        	case "E":
        		eclipse();
        		break;
        	case "n":
        	case "N":
        		netbeans();
        		break;
        	case "x":
        	case "X":
        		limpiarPantalla();
        		System.exit(0);
        		break;
        }
        escribirLaPantallaDeTexto(filas_por_defecto,columnas_por_defecto);
	}
	private static void escribirEnLaPantallaDeTexto(int fila,int columna, String texto) {
		if(fila < 0) {fila = 0;}
		if(columna < 0) {columna = 0;}
		int hasta =  columna + texto.length();
		if(hasta > columnas_por_defecto -1) {hasta = columnas_por_defecto - 1;}
		if(fila > 0 && fila < columnas_por_defecto) {
			PantallaDeTexto[fila] = borde_vertical + espacio.repeat(columna-1) + texto;
			PantallaDeTexto[fila] = PantallaDeTexto[fila].trim();
			PantallaDeTexto[fila] = PantallaDeTexto[fila] + espacio.repeat(columnas_por_defecto - PantallaDeTexto[fila].length() - 1) + borde_vertical;
		}
	}
	private static void limpiarPantalla() {
		System.out.print("\033[H\033[2J");
        System.out.flush();
	}
	private static String leerComando_SO(String cmd) {
		try {
			if(cmd == null || cmd.equals("")) {return "";}
			Process process = Runtime.getRuntime().exec(cmd);
			InputStream inputstream = process.getInputStream();
			BufferedInputStream in = new BufferedInputStream(inputstream);
			byte[] contents = new byte[1024];
			int bytesRead = 0;
			String strFileContents = ""; 
			while((bytesRead = in.read(contents)) != -1) { 
			    strFileContents += new String(contents, 0, bytesRead);              
			}
			return strFileContents;

		} catch (IOException ioe) {
			System.out.println (ioe);
			return "";
		}
	}
	
	/*
	 *  Entradas del BLOG
	 */
	private static void codelite() {
		inicializarBlogPantallaDeTexto();
		escribirEnLaPantallaDeTexto( 6,8,"CODELITE");
		escribirEnLaPantallaDeTexto( 8,8,"CodeLite es un entorno de desarrollo integrado libre y multiplataforma para el lenguaje de programaci??n C/C++. CodeLite es distribuido");
		escribirEnLaPantallaDeTexto( 9,8,"bajo los t??rminos de la licencia GNU General Public License.");
		escribirEnLaPantallaDeTexto(11,8,"En agosto de 2006, Eran Ifrah, Creador de CodeLite, comenz?? un peque??o proyecto llamado \"CodeLite\". Su idea era crear una librer??a ");
		escribirEnLaPantallaDeTexto(12,8,"de Auto-completado bas??ndose en ctags, SQLite (de ah??, CodeLite) y Yacc un parser que puede ser usado por otros IDEs.");
		escribirEnLaPantallaDeTexto(13,8,"Se desarroll?? LiteEditor, una aplicaci??n para demostrar las funcionalidades de CodeLite. LiteEditor se convirti?? con el tiempo en un ");
		escribirEnLaPantallaDeTexto(14,8,"eficiente entorno para programaci??n y as?? naci?? CodeLite.");
		escribirEnLaPantallaDeTexto(16,8,"CodeLite es una IDE multiplataforma libre y de c??digo abierto para los lenguajes C/C++ que usa wxWidgets para su interfaz gr??fica. ");
		escribirEnLaPantallaDeTexto(17,8,"Para cumplir con el esp??ritu de c??digo abierto de CodeLite, se compila y depura usando solo herramientas libres (MinGW y GDB).");
		escribirEnLaPantallaDeTexto(19,8,"CodeLite ofrece gesti??n de proyectos (espacios de trabajo/proyectos), completaci??n de c??digo, navegaci??n por los ficheros fuente,");
		escribirEnLaPantallaDeTexto(20,8,"resaltado de sintaxis, integraci??n con Subversion, Cscope y UnitTest++, un debugger interactivo montado sobre gdb y un editor de c??digo");
		escribirEnLaPantallaDeTexto(21,8,"potente, basado en Scintilla.....");
	}
	private static void eclipse() {
		inicializarBlogPantallaDeTexto();
		escribirEnLaPantallaDeTexto( 6,8,"ECLIPSE");
		escribirEnLaPantallaDeTexto( 8,8,"Eclipse comenz?? como un proyecto de IBM Canad??. Fue desarrollado por OTI (Object Technology International) como reemplazo de VisualAge");
		escribirEnLaPantallaDeTexto( 9,8,"tambi??n desarrollado por OTI. En noviembre de 2001, se form?? un consorcio para el desarrollo futuro de Eclipse como c??digo abierto."); 
		escribirEnLaPantallaDeTexto(10,8,"En 2003, fue creada la fundaci??n independiente de IBM.");
		escribirEnLaPantallaDeTexto(12,8,"Eclipse incluye las herramientas de desarrollo de Java, ofreciendo un IDE con un compilador de Java interno y un modelo completo de los");
		escribirEnLaPantallaDeTexto(13,8,"archivos fuente de Java. Esto permite t??cnicas avanzadas de refactorizaci??n y an??lisis de c??digo.");
		escribirEnLaPantallaDeTexto(15,8,"Eclipse dispone de un Editor de texto con un analizador sint??ctico. La compilaci??n es en tiempo real. Tiene pruebas unitarias con JUnit,");
		escribirEnLaPantallaDeTexto(16,8,"control de versiones con CVS, integraci??n con Ant, asistentes (wizards) para creaci??n de proyectos, clases, tests, etc...");
	}
	private static void netbeans() {
		inicializarBlogPantallaDeTexto();
		escribirEnLaPantallaDeTexto( 6,8,"NETBEANS");
		escribirEnLaPantallaDeTexto( 8,8,"NetBeans es un entorno de desarrollo integrado libre, hecho principalmente para el lenguaje de programaci??n Java. Existe adem??s un n??mero");
		escribirEnLaPantallaDeTexto( 9,8,"importante de m??dulos para extenderlo. NetBeans IDE es un producto libre y gratuito sin restricciones de uso.");
		escribirEnLaPantallaDeTexto(11,8,"NetBeans es un proyecto de c??digo abierto de gran ??xito con una gran base de usuarios, una comunidad en constante crecimiento.");
		escribirEnLaPantallaDeTexto(12,8,"Sun Microsystems fund?? el proyecto de c??digo abierto NetBeans en junio de 2000 y contin??a siendo el patrocinador principal de los proyectos.");
		escribirEnLaPantallaDeTexto(13,8,"Actualmente Sun Microsystems es administrado por Oracle Corporation.");
		escribirEnLaPantallaDeTexto(15,8,"La plataforma NetBeans permite que las aplicaciones sean desarrolladas a partir de un conjunto de componentes de software llamados m??dulos.");
		escribirEnLaPantallaDeTexto(16,8,"Un m??dulo es un archivo Java que contiene clases de java escritas para interactuar con las API de NetBeans y un archivo especial");
		escribirEnLaPantallaDeTexto(17,8,"que lo identifica como m??dulo. Las aplicaciones construidas a partir de m??dulos pueden ser extendidas agreg??ndole nuevos m??dulos."); 
		escribirEnLaPantallaDeTexto(18,8,"Debido a que los m??dulos pueden ser desarrollados independientemente, las aplicaciones basadas en la plataforma NetBeans pueden ser extendidas");
		escribirEnLaPantallaDeTexto(19,8,"f??cilmente por otros desarrolladores de software.");		
	}
}
