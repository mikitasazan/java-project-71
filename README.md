# Вычислитель отличий (Java)

[![hexlet-check](https://github.com/mikitasazan/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/mikitasazan/java-project-71/actions)
[![Build](https://github.com/mikitasazan/java-project-71/actions/workflows/main.yml/badge.svg)](https://github.com/mikitasazan/java-project-71/actions)

В этом проекте отрабатывается работа с коллекциями и структурами данных. Изучаются способы построения и обхода деревьев. Вы познакомитесь с разными форматами данных (json, yml), научитесь их парсить и формировать. Начнете писать тесты (JUnit) и освоите разработку через них. Познакомитесь с непрерывной интеграцией (CI) и элементами экстремального программирования (XP). Прокачаете ООП мышление.

Учебный проект Хекслета: https://ru.hexlet.io/programs/java
Как это должно работать: https://asciinema.org/a/NFIQgLVMu1ymFsqg4ESeOQeXi

## Стек

- Java 21, Gradle (application plugin)
- picocli — разбор аргументов и опций командной строки
- Jackson (jackson-databind, jackson-dataformat-yaml) — парсинг JSON и YAML
- JUnit 5 — тесты, JaCoCo — покрытие тестами (порог 80%)
- Spotless + google-java-format — стиль кода
- gradle-versions-plugin — проверка обновлений зависимостей
- GitHub Actions — своя сборка (`make build`) плюс проверка Хекслета

## Установка

```bash
git clone https://github.com/mikitasazan/java-project-71.git
cd java-project-71/app
make install
```

## Использование

```bash
cd app
make install
./build/install/app/bin/app -h
./build/install/app/bin/app src/test/resources/fixtures/file1.json src/test/resources/fixtures/file2.json
```

Пример вывода (формат stylish, по умолчанию):

```
{
  - follow: false
    host: hexlet.io
  - proxy: 123.234.53.22
  - timeout: 50
  + timeout: 20
  + verbose: true
}
```

Формат plain (`-f plain`, для чтения человеком, без вложенных значений целиком):

```
Property 'follow' was removed
Property 'proxy' was removed
Property 'timeout' was updated. From 50 to 20
Property 'verbose' was added with value: true
```

---

<details>
<summary>Автоматические тесты Хекслета</summary>

Тесты запускаются на каждый коммит. За запуск отвечает файл `.github/workflows/hexlet-check.yml` — не удаляйте и не переименовывайте ни его, ни репозиторий.

</details>

## О Хекслете

[Хекслет](https://ru.hexlet.io/) — школа программирования: авторские программы обучения с практикой, поддержкой наставников и реальными проектами, которые остаются в резюме. Этот репозиторий — один из таких проектов.
