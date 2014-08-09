package informados

import com.sun.syndication.fetcher.*;
import com.sun.syndication.fetcher.impl.*;
import com.sun.syndication.feed.synd.SyndFeed;

import informados.noticia.Noticia;
import informados.noticia.Seccion;
import informados.noticia.Diario;

class NewsCrawlerJob {

    /* feeds de los distintos diarios con sus secciones */
    /*def feed_source = [clarin: 
                       [politica: "http://www.clarin.com/rss/politica/",
                        deportes: "http://www.clarin.com/rss/deportes/"],
                       
                       lanacion: 
                       [politica: "http://contenidos.lanacion.com.ar/herramientas/rss/categoria_id=30",
                        deportes: "http://contenidos.lanacion.com.ar/herramientas/rss/categoria_id=131"],
                       
                       infobae:
                       [politica: "http://cdn01.am.infobae.com/adjuntos/163/rss/politica.xml",
                        deportes: "http://cdn01.am.infobae.com/adjuntos/163/rss/deportes.xml"]]*/

    /* La tarea se ejecuta cada repeatInterval milisegundos.Se ejecuta repeatCount+1 veces*/
    static triggers = {
        simple name: 'NewsCrawlerTrigger', startDelay: 0, repeatInterval: 5000, repeatCount: 0
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
    }

    /*
     * Lee y parsea el feed a partir de la url, genera la noticia y la
     * gurda en la BD.
     **/
    def readFeed(diario, seccion, url) {
        print "\t leyendo rss de seccion "+seccion.nombre+":"+url
        
		print url
        def xmlFeed = new XmlParser().parse(url);
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
				
			
            Noticia noticia = new Noticia(titulo: item.title.text(),
                                          resumen: item.description.text(),
                                          RSS: url,
										  seccion:seccion,
										  contenido: item.link.text(),
										  copete:"hola" )
			noticia.diario=diario;
            noticia.save()
			if(noticia.hasErrors()) {
				println noticia.errors
			}
        }
    }

}
