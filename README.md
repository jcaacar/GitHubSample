# GitHubSample

Aplicação android que consome a [GitHub Search API](https://docs.github.com/en/rest/search#search-repositories) para exibir a lista de repositórios Kotlin com mais estrelas.


## Architecture

Este projeto foi construído utilizando uma arquitetura **_Clean + MVVM_**.

![image](https://preview.redd.it/56mr0g3l9cm61.png?width=875&format=png&auto=webp&s=d8e951d6a22d20fead0fb1a3ca2c56640804987f)

## Modulos

É um monolitico possuindo somente o modulo `app`, mas os pacotes são isolados para serem criados os modulos futuramente.

### Pacotes

- common - Pacote comum para todos os outros, possui extensions, exceptions, helpers etc.
- data   - Responsável por definir os modelos de dados, possui os Repositórios e os contratos dos DataSources.
- design - Design system do produto, possui os estilos, temas, fontes, componentes etc.
- features - Contém todas features do app, contendo suas Views/ViewModels e UseCases(Opcional).
- remote - Responsável por implementar os DataSources definidos no pacote `data`
- remote.network - Implementa os contratos das APIs remotas, definidas no pacotes das features ex: `remote.repositories.apis`

TODO: DIAGRAMA DOS PACOTES

## Stack

- Kotlin
- Compose
- Paging 3
- Coroutines
- Retrofit
- Hilt

## Débitos Técnicos
- Implementar testes de unidade do `RepositoriesViewModel` e `RepoRepository` (O teste instrumentado está cobrindo eles)
   * O método de extensão _cachedIn_, de alguma forma altera os eventos do flow, não sei bem o que acontece mas não consegui capturar as emissões corretamente.
   * Caso não utilizasse o _cachedIn_, os eventos são emitidos como esperado, mas não consegui extrair os items do `PagingData`.
- Mover o controle de estado da `RepositoryListScreen` para o `RepositoriesViewModel`, o correto seria o ViewModel enviar somente os estados para a view e ela redenrizar o estado, mas não consegui extrair esses dados do `PagingData` na ViewModel.


