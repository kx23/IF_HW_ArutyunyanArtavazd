# language: ru

@RickAndMorty
Функционал: Rick and Morty API

  @LastCharacterComparison
  Сценарий: Последний персонаж из последнего эпизода Морти
    Допустим персонаж "Morty Smith" найден в API
    Когда я нахожу последний эпизод персонажа "Morty Smith"
    И я нахожу последнего персонажа этого эпизода
    Тогда детали последнего персонажа сравнены с "Morty Smith"
