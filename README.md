<h1>Exercicio de Lista de nomes</h1>

<h3>Requisitos:</h3>
<p>The purpose of this exercise is just to evaluate technical skills and coding ability. 
For this test you will need to build a spring boot application from scratch and use only 
spring or JAVA API to resolve it, no use of external libraries.
We need to store in the database a list of names:

var names = ["Jacob","Michael","Matthew","Joshua","Christopher","Nicholas","
Andrew","Joseph","Daniel","Tyler","William","Brandon","Ryan","Jo
hn","Zachary","David","Anthony","James","Justin","Alexander","Jo
nathan"];

We want you to build a rest endpoint that will
1. Insert the list of names in the database
2. List all the names in the database 
3. Search for a name and returns true or false if exists or not
4. Don’t allow duplicated names
5. Create the required unit tests
6. Necessary JavaDoc
We need to have this in a public repository for us to be able to check out the code.
When it’s finish just send an email with the repository URL</p>

<h3>🛠 Tecnologias Utilizadas</h3>
<ul>
    <li>Java 17</li>
    <li>Maven</li>
    <li><strong>Spring Web</strong></li>
    <li><strong>Spring Data JPA</strong></li>
    <li><Strong>H2 Memory database</Strong></li>
</ul>

<h3>Exemplos POST</h3>
<ul>
    <li><strong>/names</strong></li>
    [ { "name": "Jacob" }, { "name": "Michael" }, { "name": "Michael" } ]

<li><strong>/names/list</strong></li>
    "João, Marcos, Antonio, Andrew"
<li><strong>names/new</strong></li>
    {"name": "Jacob"}
</ul>

<h3>Endpoints GET</h3>
<ul>
    <li><strong>/names</strong></li>
    Retorna lista de nomes salvos
    <li><strong>/names/verifiy/{name}</strong></li>
    Verifica se o nome informado existe
</ul>

