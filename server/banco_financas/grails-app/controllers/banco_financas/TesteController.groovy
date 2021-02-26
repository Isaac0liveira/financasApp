package banco_financas


import grails.rest.*
import grails.converters.*

class TesteController {
    static responseFormats = ['json', 'xml']
    static allowedMethods = [index: 'POST', obterLista: 'GET', marcarPago: 'POST']
    TesteService testeService

    def index() {
        def model = [:]
        model.put('message', testeService.adicionar(params))
        render model as JSON
    }

    def obterLista() {
        def model = [:]
        model.put('valores', testeService.obter(params))
        render model as JSON
    }

    def marcarPago(){
        def model = [:]
        model.put('message', testeService.marcarPago(params))
        render model as JSON
    }
}
