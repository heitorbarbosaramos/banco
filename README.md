# Desafio teste - GERENCIAR CONTAS DE CLIENTE

Criar um projeto JAVA(SPRINGBOOT), utilizando o banco de dados relacional que deverá versionado no GitHub.
Projeto bem simples para controlar as receitas e as despesas de vários clientes, a empresa (XPTO)
precisa controlar as receitas e as despesas de diversos clientes (X, Y, Z) que podem ser PJ ou PF.
Além de manter os clientes (Y, X, Z) é preciso fazer o controle da receita da empresa (XPTO)
com relação as movimentações dos clientes (X, Y, Z).
O sistema deve permitir manter (CRUD) os clientes, no cadastro de um novo cliente é preciso ter
uma movimentação inicial, que será o ponto de partida para o controle das despesas e receitas,
além dos dados dos clientes, como:
Nome;
CPF;
CNPJ;
Endereço;
Contas;
Se é Pessoa Física ou Pessoa Jurídica;
Telefone;
e mais...
Observação: Existem alguns dados que não são comuns entre os clientes PJ e PF. Defina a
melhor forma normalizar essa situação.
Um cliente (X) pode ter uma ou várias contas bancárias (C1, C2, C3) cadastras, de onde são
feitas as movimentações, todos os pagamentos e recebimentos são feitos por meio dessas contas.
Os dados são enviados via integração a cada movimentação:
Exemplo:
Instituição Financeira: envia movimentação --->
XPTO: Recebe a movimentação --->
XPTO: identifica o cliente --->
XPTO: efetuar o cadastro da movimentação;
Essa simulação de integração pode ser feita com uma carga de dados no método principal da sua
aplicação;
O cálculo da receita empresa (XPTO) vem da quantidade de operações (entradas e saídas).
Exemplo, a cada período de 30 dias, a partir da data de cadastro o cliente (Y) efetuar:
Até 10 movimentações o cliente irá pagar R$ 1,00 por movimentação;
De 10 a 20 movimentações o cliente irá pagar R$ 0,75 por movimentação;
Acima de 20 movimentações o cliente irá pagar R$ 0,50 por movimentação;
O sistema deve funções para:
CRUD de clientes;
Efetuar a manutenção dos clientes, ficar atento para dados que não podem sofrer alterações para
manter histórico de movimentação;
CRUD de endereços;
Efetuar a manutenção dos endereços dos clientes;
CRUD de contas;
Efetuar a manutenção de contas dos clientes, caso exista alguma movimentação associada, não
permitir alteração, permitir apenas exclusão lógica;

Relatório de saldo do cliente X:
Cliente: X - Cliente desde: DD/MM/YYYY;
Endereço: Rua, número, complemento, bairro, cidade, UF, CEP;
Movimentações de crédito: 00;
Movimentações de débito: 0;
Total de movimentações: 00;
Valor pago pelas movimentações: 00,00
Saldo inicial: 0.000,00
Saldo atual: 00.000,00
Relatório de saldo do cliente X e período:
Período: DD/MM/YYYY a DD/MM/YYYY
Cliente: X - Cliente desde: DD/MM/YYYY;
Endereço: Rua, número, complemento, bairro, cidade, UF, CEP;
Movimentações de crédito: 00;
Movimentações de débito: 0;
Total de movimentações: 00;
Valor pago pelas movimentações: 00,00
Saldo inicial: 0.000,00
Saldo atual: 00.000,00
Relatório de saldo de todos os clientes;
Cliente: X - Cliente desde: DD/MM/YYYY – Saldo em DD/MM/YYYY: 0.000,00
Cliente: Y - Cliente desde: DD/MM/YYYY - Saldo em DD/MM/YYYY: 000,00
Cliente: Z - Cliente desde: DD/MM/YYYY - Saldo em DD/MM/YYYY: 00,00
Relatório de receita da empresa (XPTO) por período:
Período: DD/MM/YYYY a DD/MM/YYYY
123Cliente X - Quantidade de movimentações: 80 - Valor das movimentações: R$ 0.000,00
Cliente Y - Quantidade de movimentações: 120 - Valor das movimentações: R$ 00.000,00
Cliente z - Quantidade de movimentações: 100 - Valor das movimentações: R$ 00.000,00
Total de receitas: R$ 00.000,00
Seja Criativo!

* Use API REST

# ENDPOINTS

Inserindo cliente
/cliente/insert/cliente
methodo POST

modelo: json cliente CPF
{
    "nomeCompleto": "Heitor Fernando Barbosa Ramos",
    "cpfOuCnpj": "975.449.900-44",
    "tipoCliente": "PESSOAFISICA",
    "telefone": "(11) 98946-1545",
    "endereco": {
        "cep": "06725-050",
        "logradouro": "Rua Horácio Batista da Silva",
        "complemento": "casa 4",
        "bairro": "Jardim São Luiz (Caucaia do Alto)",
        "localidade": "Cotia",
        "numero": "295",
        "uf": "SP"
    }
}

modelo: json cliente CNPJ
{
    "nomeCompleto": "Heitor Fernando Barbosa Ramos",
    "cpfOuCnpj": "91.384.807/0001-20",
    "tipoCliente": "PESSOAJURIDICA",
    "telefone": "(11) 98946-1545",
    "endereco": {
        "cep": "06725-050",
        "logradouro": "Rua Horácio Batista da Silva",
        "complemento": "casa 4",
        "bairro": "Jardim São Luiz (Caucaia do Alto)",
        "localidade": "Cotia",
        "numero": "295",
        "uf": "SP"
    }
}


***

criando conta para o cliente
/cliente/createAccount/{idCliente}/{valorAbertura}
methodo POST

***

retornando as contas do cliente

localhost:8080/cliente/numberOfAccounts/{idCliente}
method GET

***

retornando dados do cliente
/cliente/findById/{idCliente}
method GET

***

retorna saldo da conta
/cliente/saldo/{numeroConta}
method GET

***

deposito na conta do cliente
/cliente/deposito/{numeroConta}/{deposito}
method POST

***

saque na conta do cliente
method POST
/cliente/saque/{numeroConta}/{deposito}

***

tranferencia de uma conta para conta do cliente
method POST
/cliente/transferenciaEntrada/{numeroContaOrigem}/{numeroContaDestino}/{valor}

***

transferencia da conta cliente para outra conta
method post
/cliente/transferenciaSaida/{numeroContaOrigem}/{numeroContaDestino}/{valor}

***

extrato conta cliente
method GET
/cliente/extrato/{numeroContaDestino}/{dataInicio}/{dataFim}



