package banco_financas

import grails.transaction.Transactional

@Transactional
class TesteService {

    def adicionar(parameters){
        def newNome = Teste.findByNome(parameters.nome)
        if(newNome == null){
            newNome = new Teste(
                    nome: parameters.nome,
                    valor: parameters.valor,
                    data: parameters.data,
                    note: parameters.note,
                    tipo: parameters.tipo,
                    dataSet: parameters.dataSet
            )
        }else{
            return "Já existe"
        }
        if(newNome.note.equals(null)){
            newNome.note = "Sem Anotações"
        }

        newNome.validate()
        if(!newNome.hasErrors()){
            newNome.save(flush: true)
            return "Cadastrado"
        }else{
            return "Não Cadastrado"
        }
    }

    def obter(parameters){
        def usuarios = Teste.findAllByDataSet(parameters.dataSet)
        return usuarios
    }

    def marcarPago(parameters){
        def usuario = Teste.findById(parameters.meuID)
        if(usuario.tipo == "Dívida") {
            usuario.tipo = "Dívida Paga"
        }else if(usuario.tipo == "Dívida Paga"){
            usuario.tipo = "Dívida"
        }

        if(!usuario.hasErrors()){
            usuario.save(flush: true)
            if(usuario.tipo == "Dívida") {
                return "Marcado como NÃO Pago"
            }else if(usuario.tipo == "Dívida Paga"){
                return "Marcado como Pago"
            }
        }else{
            return "Erro ao Pagar"
        }
    }
}
