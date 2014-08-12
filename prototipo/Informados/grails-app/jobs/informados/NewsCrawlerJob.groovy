package informados

import com.sun.syndication.fetcher.*;
import com.sun.syndication.fetcher.impl.*;
import com.sun.syndication.feed.synd.SyndFeed;

import informados.noticia.Noticia;
import informados.noticia.Seccion;
import informados.noticia.Diario;

import cue.lang.WordIterator
import cue.lang.stop.StopWords

class NewsCrawlerJob {

    /* Levanta los feeds de los distintos diarios con sus secciones configurados en la base (ver conf/Bootstrap.groovy) */

    /* La tarea se ejecuta cada repeatInterval milisegundos.Se ejecuta repeatCount+1 veces*/
    static triggers = {
        simple name: 'NewsCrawlerTrigger', startDelay: 5000, repeatInterval: 5000, repeatCount: 0
    }

    /*
     * Levanta los feeds definidos en feed_source, los parsea, crea las Noticias
     * y las guarda en la BD.
     */
    def execute(){
        print "NewsCrawler run!"		

		def diarios = Diario.findAll();

		if(diarios== null) {
			print "no se encontraron diarios!"
			return
		}
		for(diario in diarios) {
			for(seccionName in diario.RSSUrls.keySet()) {
				Seccion seccion = Seccion.findByNombre(seccionName)
				readFeed(diario, seccion, diario.RSSUrls[seccionName])
			}
		}

        print " \t Noticias insertadas:"+Noticia.findAll()

        print "Analizando noticias"
        //def noticias = Noticia.findAll()

        //procesarNoticias(noticias)
    }

    def procesarNoticias(noticias) { 

        def stopWords = {}

        for(noticia in noticias) { 
            stopWords.put(noticia.titulo, sacarStopWordsDeNoticia(noticia))
        }

        for(n in stopWords.keySet()) { 
            print n
        }
    }

    def sacarStopWordsDeNoticia(noticia) { 
        sacarStopWords(noticia.titulo)
    }

    def sacarStopWords(texto) { 

        def words = []

        for (final String word : new WordIterator(texto)) {
            if (!StopWords.Spanish.isStopWord(word)) {
                words.add(word)
            }
        }
        words
    }
    /*
     * Lee y parsea el feed a partir de la url, genera la noticia y la
     * gurda en la BD.
     **/
    def readFeed(diario, seccion, url) {
        print "\t leyendo rss de seccion "+seccion.nombre+":"+url
        
		print url
        def xmlFeed = null
        
        try {
            xmlFeed = new XmlParser().parse(url);
        } catch (java.net.ConnectException ex) { 
            print "\t error descargando feed "+url
        }

        print "\t total de noticias:"+xmlFeed.channel.item.size()
    	
        (0..< xmlFeed.channel.item.size()).each {

            def item = xmlFeed.channel.item.get(it);
            print "Creando noticia "+item.title.text()

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

            print "\t intentando inertar noticia con hashCode()="+titulo.hashCode()+":"+Noticia.findByHash(titulo.hashCode())
			if( Noticia.findByHash(titulo.hashCode()) == null) { 

                Noticia noticia = new Noticia(titulo: item.title.text(),
                                              resumen: item.description.text(),
                                              RSS: url,
                                              seccion:seccion,
                                              contenido: item.link.text(),
                                              copete:"hola",
                                              hash: titulo.hashCode())
                noticia.diario=diario;
                noticia.save()
                if(noticia.hasErrors()) {
                    println noticia.errors
                }
                
                print sacarStopWordsDeNoticia(noticia)
            } else { 
                print "\t ya existe noticia con hashCode()="+titulo.hashCode()
            }
        }
    }
    
}
