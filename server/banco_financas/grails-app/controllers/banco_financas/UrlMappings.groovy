package banco_financas

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        post "/$controller(.$format)?"(action:"index")
        post "/$controller/$id(.$format)?"(action:"marcarPago")
        get "/$controller/$id(.$format)?"(action:"obterLista")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
