package informados

import com.sun.syndication.fetcher.*;
import com.sun.syndication.fetcher.impl.*;
import com.sun.syndication.feed.synd.SyndFeed;

import informados.noticia.Noticia;
import informados.noticia.Seccion;
import informados.noticia.Diario;

class NewsCrawlerJob {

	/* La tarea se ejecuta cada repeatInterval milisegundos.Se ejecuta repeatCount+1 veces*/
	static triggers = {
		simple name: 'NewsCrawlerTrigger', startDelay: 0, repeatInterval: 5000, repeatCount: 5
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

		//print " \t Noticias insertadas:"+Noticia.findAll()
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
				hash:titulo.hashCode())
 
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
