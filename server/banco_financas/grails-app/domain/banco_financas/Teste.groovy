package banco_financas

class Teste {
    String tipo
    String nome
    Float valor
    String data
    String dataSet
    String note

    static constraints = {
        nome nullable: true, blank: true
    }
}
