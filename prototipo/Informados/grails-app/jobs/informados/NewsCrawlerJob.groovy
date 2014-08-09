package informados

import com.sun.syndication.fetcher.*;
import com.sun.syndication.fetcher.impl.*;
import com.sun.syndication.feed.synd.SyndFeed;

import informados.noticia.Noticia;

class NewsCrawlerJob {

    /* feeds de los distintos diarios con sus secciones */
    def feed_source = [clarin: 
                       [politica: "http://www.clarin.com/rss/politica/",
                        deportes: "http://www.clarin.com/rss/deportes/"],
                       
                       lanacion: 
                       [politica: "http://contenidos.lanacion.com.ar/herramientas/rss/categoria_id=30",
                        deportes: "http://contenidos.lanacion.com.ar/herramientas/rss/categoria_id=131"],
                       
                       infobae:
                       [politica: "http://cdn01.am.infobae.com/adjuntos/163/rss/politica.xml",
                        deportes: "http://cdn01.am.infobae.com/adjuntos/163/rss/deportes.xml"]]

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

        for(diario in feed_source) { 

            def nombre_diario = diario.key
            print "procesando diario "+nombre_diario

            for(seccion in diario.value) { 

                def nombre_seccion  = seccion.key
                def url = seccion.value

                readFeed(nombre_diario, nombre_seccion, url)
                }
            }
    }

    /*
     * Lee y parsea el feed a partir de la url, genera la noticia y la
     * gurda en la BD.
     **/
    def readFeed(diario, seccion, url) {
        print "\t leyendo rss de seccion "+seccion+":"+url
        
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
            Noticia noticia = new Noticia(titulo: item.title.text(),
                                          resumen: item.description.text(),
                                          RSS: url,
                                          belongsTo: seccion)

            noticia.save()
        }
    }

}
