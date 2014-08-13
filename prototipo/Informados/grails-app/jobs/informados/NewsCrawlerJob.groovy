package informados

import com.sun.syndication.fetcher.*;
import com.sun.syndication.fetcher.impl.*;
import com.sun.syndication.feed.synd.SyndFeed;

import informados.noticia.Noticia;
import informados.noticia.Seccion;
import informados.noticia.Diario;
import informados.noticia.Tema;

import cue.lang.WordIterator
import cue.lang.stop.StopWords

import java.util.Random  

class NewsCrawlerJob {

    Random rand = new Random()

	/* La tarea se ejecuta cada repeatInterval milisegundos.Se ejecuta repeatCount+1 veces*/
	static triggers = {
		simple name: 'NewsCrawlerTrigger', startDelay: 5000, repeatInterval: 5000, repeatCount: 5
	}

	/*
	 * Levanta los feeds definidos en feed_source, los parsea, crea las Noticias
	 * y las guarda en la BD.
	 */
	def execute(){
		print "NewsCrawler run!"

		def diarios = Diario.findAll();

		if(diarios.empty) {
			print "no se encontraron diarios!"
			return
		}

		for(diario in diarios) {
			print diario.toString()
			for(seccionName in diario.RSSUrls.keySet()) {
				Seccion seccion = Seccion.findByNombre(seccionName)
				readFeed(diario, seccion, diario.RSSUrls[seccionName])
			}
		}

        // proceso las noticias por  secciones
        for(seccion in Seccion.findAll()) { 
            procesarNoticias(Noticia.findAllBySeccion(seccion))   
        }
	}

    def procesarNoticias(noticias) { 

        if (noticias.size()==0) { 
            return 0
        }

        def stopWords = []
        def noticias_relacionadas = []

        for(noticia in noticias) { 
            stopWords.add(sacarStopWordsDeNoticia(noticia))
            noticias_relacionadas.add([noticia])
        }

        // Compara las noticias entre si y elijo aquella que mas se parezca para formar un tema
        for(i in 0..noticias.size()-1) {

            def min_pos = i
            
            for(j in i..noticias.size()-1) {
                def eq = getEquals(stopWords[i], stopWords[j])
                if(eq>0) {
                    min_pos = j
                }
            }

            // agrupo las noticias
            if(i != min_pos) {
                noticias_relacionadas[min_pos].addAll(noticias_relacionadas[i])
                noticias_relacionadas[i] = []
            }
        }

        // armo y guardo los temas con sus noticias relacionadas
        for(lista in noticias_relacionadas){ 
            if(lista.size()>0){ 
                def tema = new Tema()
                tema.save()

                for(noticia in lista) { 
                    noticia.tema = tema
                    noticia.save(flush: true)
                }
            }
        }    
    }

    /*
     * Devuelve un numero que indica el nivel de parecido de dos noticias
     * Por ahora devuelve un 0/1
     */
    def getEquals(stopWords_1, stopWords_2) {
        for(word in stopWords_1){ 
            if(stopWords_2.contains(word))
                return 1
        }
        return 0
    }

    /*
     * Devuelve el nivel de positividad en base al texto de la noticia
     * Por hora esta mockeado y devuelve un numero random.
     **/
    def getPositividad(titulo, descripcion) { 
        return rand.nextInt(100)
    }

 
    def sacarStopWordsDeNoticia(noticia){ 
        def texto = noticia.titulo
        return sacarStopWords(texto)
    }
     
    def sacarStopWords(texto){ 
        def palabras = []

        for (final String word : new WordIterator(texto)) {
            if (!StopWords.Spanish.isStopWord(word)) {
                palabras.add(word)
            }
        }
        return palabras
    }

	/*
	 * Lee y parsea el feed a partir de la url, genera la noticia y la
	 * gurda en la BD.
	 **/
	def readFeed(diario, seccion, url) {
		if(diario == null || seccion == null || url == null) {
			return
		}
		//print "\t leyendo rss de seccion "+seccion.nombre+":"+url

		//print url
		def xmlFeed = null

		try {
			xmlFeed = new XmlParser().parse(url);
		} catch (java.net.ConnectException ex) {
			print "\t error descargando feed "+url
		}

		print "\t total de noticias:"+xmlFeed.channel.item.size() 

		(0..< xmlFeed.channel.item.size()).each {

			def item = xmlFeed.channel.item.get(it);
			//print "Creando noticia "+item.title.text()

			/*
			 * Atributos de los items:
			 *   item.title
			 *   item.link
			 *   item.description
			 *   item.pubDate
			 **/

			/**
			 * Noticia:
			 * 	String titulo
			 String copete
			 String resumen
			 String contenido
			 String RSS
			 Seccion seccion
			 */
			def titulo = item.title.text()

			if( Noticia.findByHash(titulo.hashCode()) == null) {
				
				def resumen = item.description.text()?item.description.text():"la noticia no contiene resumen"

				Noticia noticia = new Noticia(titulo: item.title.text(),
                                              resumen: resumen,
                                              RSS: url,
                                              seccion:seccion,
                                              contenido: resumen,
                                              copete:"copete default" ,
                                              link: item.link.text(),
                                              hash:titulo.hashCode(),
                                              positivismo:getPositividad(titulo,resumen))
                
				noticia.diario=diario;
				noticia.save()
				if(noticia.hasErrors()) {
					println noticia.errors 
				}
			} else {
				//print "\t ya existe noticia con hashCode()="+titulo.hashCode()
			}
		}
	}
}
