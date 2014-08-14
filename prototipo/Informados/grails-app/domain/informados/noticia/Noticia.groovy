package informados.noticia

import informados.usuario.Usuario
import net.sf.javaml.clustering.Clusterer
import net.sf.javaml.clustering.KMeans
import net.sf.javaml.core.Dataset
import net.sf.javaml.tools.data.FileHandler
import ranking.Voto

import informados.noticia.Tema

class Noticia {
	private static final String POSITIVAS_TXT = "positivas.txt"
	private static final String NEGATIVAS_TXT = "negativas.txt"
	String titulo
	String copete
	String resumen
	String contenido
	String RSS
	static belongsTo = [diario:Diario]
	Seccion seccion
	Date fecha = new Date()
	String link
	Integer hash
    Tema tema

    // numero del 1..100 que indica el nivel de positividad de la noticia
    Integer positivismo

	static constraints = {
		contenido blank: false, maxSize:500000
		titulo blank:false
		resumen blank:false, maxSize:500000
		link blank:true, nullable:true
        tema nullable:true
	}

	
	public String quitarStopwords() {
		String sinStopwords
		List<String> stopwords = crearListadoPalabras("stopwords.txt")
		List<String> palabrasNoticia = this.contenido.split(" ")
		for(palabra in palabrasNoticia) {
			if(!stopwords.contains(palabra)) {
				sinStopwords += " " 
				sinStopwords += palabra
			}
		}
		return sinStopwords
	}
	
	public void calcularPositivismo() {
		Integer cantidad_positivas = 0;
		List<String> palabrasPositivas = crearListadoPalabras(POSITIVAS_TXT)
		for(palabra in palabrasPositivas) {
			if(this.resumen.matches(palabra+"*")) {
				++cantidad_positivas
			}
		}
		Integer cantidad_negativas = calcularNegatividad();
		if(cantidad_positivas > 0) {
			print "encontre palabras positivas!"
		}
		if(cantidad_negativas > 0) {
			print "encontre palabras negativas!"
		}
		this.positivismo = (cantidad_positivas - cantidad_negativas)/this.contenido.size() 
	}
	
	private Integer calcularNegatividad() {
		Integer cantidad_negativas = 0;
		List<String> palabrasPositivas = crearListadoPalabras(NEGATIVAS_TXT)
		for(palabra in palabrasPositivas) {
			if(this.resumen.matches(palabra+"*")) {
				++cantidad_negativas
			}
		}
		return cantidad_negativas
	}
	
	private void createDataSet() {
		def noticias = Noticia.list()
		BufferedWriter writer = new BufferedWriter(new FileWriter("noticias.data"));
		for(noticia in noticias) {
			noticia.resumen = noticia.resumen.replace(";", " ")
			noticia.contenido = noticia.contenido.replace(";", " ")
			writer.writeLine(noticia.titulo+";"+noticia.resumen+";"+noticia.seccion+";"+noticia.fecha)			
		}
		writer.flush()
		writer.close()
	}
	
	public void setTemas() {
		createDataSet()
		Dataset data = FileHandler.loadDataset(new File("noticias.data"), ";")
		Clusterer km = new KMeans();
		Dataset[] clusters = km.cluster(data);
		println("clusters: "+clusters)
	}
	
	private List<String> crearListadoPalabras(String filename) {
		def palabras = new ArrayList<String>()
		Scanner scanner = new Scanner(new FileReader(filename));
		while(scanner.hasNextLine()) {
			palabras.add(scanner.nextLine().trim());
		}
		return palabras
	}
	
	public String toString() {
		return titulo + "(positivismo="+positivismo+")"+"["+hash+"]"
	}

	public String votar(Usuario usuario) {
		List votos = Voto.findAllByUsuarioAndNoticia(usuario, this)
		if(votos.empty) {
			Voto voto = new Voto(usuario:usuario, noticia:this)
			voto.save(flush:true)
			if(voto.hasErrors()) {
				return voto.errors
			}
			this.save(flus:true)
			if(this.hasErrors()) {
				return this.errors
			}
		} else {
			return "Hey no puedes volver a votar la misma noticia!"
		}
	}
	
	public Integer getPuntos() {
		return Voto.findAllByNoticia(this).size()
	}
}
